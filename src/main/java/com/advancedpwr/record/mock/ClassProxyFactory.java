/*
 * Copyright 2011 Matthew Avery, mavery@advancedpwr.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * Copyright 2003-2009 OFFIS, Henri Tremblay
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Matthew Avery, mavery@advancedpwr.com on Oct 5, 2010
 * Removed the ObjectMethodsFilter hack so that this will work with a generic InvocationHandler
 */
package com.advancedpwr.record.mock;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Set;

import net.sf.cglib.core.CodeGenerationException;
import net.sf.cglib.core.CollectionUtils;
import net.sf.cglib.core.DefaultNamingPolicy;
import net.sf.cglib.core.NamingPolicy;
import net.sf.cglib.core.Predicate;
import net.sf.cglib.core.VisibilityPredicate;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.Factory;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.easymock.ConstructorArgs;
import org.easymock.internal.BridgeMethodResolver;
import org.easymock.internal.ClassExtensionHelper;
import org.easymock.internal.ClassInstantiatorFactory;
import org.easymock.internal.IProxyFactory;

public class ClassProxyFactory<T> implements IProxyFactory<T>
{

	public static class MockMethodInterceptor implements MethodInterceptor, Serializable
	{

		private static final long serialVersionUID = -9054190871232972342L;

		private final InvocationHandler handler;


		public MockMethodInterceptor( final InvocationHandler handler )
		{
			this.handler = handler;
		}

		public Object intercept( final Object obj, final Method method, final Object[] args,
				final MethodProxy proxy ) throws Throwable
		{

			// Bridges should be called so they can forward to the real
			// method
			if ( method.isBridge() )
			{
				final Method m = BridgeMethodResolver.findBridgedMethod( method );
				return handler.invoke( obj, m, args );
			}

			// We conveniently mock abstract methods be default
			if ( Modifier.isAbstract( method.getModifiers() ) )
			{
				return handler.invoke( obj, method, args );
			}

			return handler.invoke( obj, method, args );
		}

	}

	// ///CLOVER:OFF (I don't know how to test it automatically yet)
	private static final NamingPolicy ALLOWS_MOCKING_CLASSES_IN_SIGNED_PACKAGES = new DefaultNamingPolicy()
	{
		@Override
		public String getClassName( final String prefix, final String source, final Object key,
				final Predicate names )
		{
			return "codegen." + super.getClassName( prefix, source, key, names );
		}
	};

	// ///CLOVER:ON

	@SuppressWarnings( "unchecked" )
	public T createProxy( final Class<T> toMock, final InvocationHandler handler )
	{

		final Enhancer enhancer = createEnhancer( toMock );

		final MethodInterceptor interceptor = new MockMethodInterceptor( handler );
		enhancer.setCallbackType( interceptor.getClass() );

		Class mockClass;
		try
		{
			mockClass = enhancer.createClass();
		}
		catch ( final CodeGenerationException e )
		{
			// ///CLOVER:OFF (don't know how to test it automatically)
			// Probably caused by a NoClassDefFoundError, let's try EasyMock class loader
			// instead of the default one (which is the class to mock one
			// This is required by Eclipse Plug-ins, the mock class loader doesn't see
			// cglib most of the time. Using EasyMock class loader solves this
			// See issue ID: 2994002
			enhancer.setClassLoader( getClass().getClassLoader() );
			mockClass = enhancer.createClass();
			// ///CLOVER:ON
		}

		Enhancer.registerCallbacks( mockClass, new Callback[] { interceptor } );

		if ( ClassExtensionHelper.getCurrentConstructorArgs() != null )
		{
			// Really instantiate the class
			final ConstructorArgs args = ClassExtensionHelper.getCurrentConstructorArgs();
			Constructor cstr;
			try
			{
				// Get the constructor with the same params
				cstr = mockClass.getDeclaredConstructor( args.getConstructor().getParameterTypes() );
			}
			catch ( final NoSuchMethodException e )
			{
				// Shouldn't happen, constructor is checked when ConstructorArgs is instantiated
				// ///CLOVER:OFF
				throw new RuntimeException( "Fail to find constructor for param types", e );
				// ///CLOVER:ON
			}
			T mock;
			try
			{
				cstr.setAccessible( true ); // So we can call a protected
				// constructor
				mock = (T) cstr.newInstance( args.getInitArgs() );
			}
			catch ( final InstantiationException e )
			{
				// ///CLOVER:OFF
				throw new RuntimeException( "Failed to instantiate mock calling constructor", e );
				// ///CLOVER:ON
			}
			catch ( final IllegalAccessException e )
			{
				// ///CLOVER:OFF
				throw new RuntimeException( "Failed to instantiate mock calling constructor", e );
				// ///CLOVER:ON
			}
			catch ( final InvocationTargetException e )
			{
				throw new RuntimeException(
						"Failed to instantiate mock calling constructor: Exception in constructor",
						e.getTargetException() );
			}
			return mock;
		}
		else
		{
			// Do not call any constructor

			Factory mock;
			try
			{
				mock = (Factory) ClassInstantiatorFactory.getInstantiator().newInstance( mockClass );
			}
			catch ( final InstantiationException e )
			{
				// ///CLOVER:OFF
				throw new RuntimeException( "Fail to instantiate mock for " + toMock + " on "
						+ ClassInstantiatorFactory.getJVM() + " JVM" );
				// ///CLOVER:ON
			}

			// This call is required. CGlib has some "magic code" making sure a
			// callback is used by only one instance of a given class. So only
			// the
			// instance created right after registering the callback will get
			// it.
			// However, this is done in the constructor which I'm bypassing to
			// allow class instantiation without calling a constructor.
			// Fortunately, the "magic code" is also called in getCallback which
			// is
			// why I'm calling it here mock.getCallback(0);
			mock.getCallback( 0 );

			return (T) mock;
		}
	}

	private Enhancer createEnhancer( final Class<T> toMock )
	{
		// Create the mock
		final Enhancer enhancer = new Enhancer()
		{
			/**
			 * Filter all private constructors but do not check that there are
			 * some left
			 */
			@SuppressWarnings( "unchecked" )
			@Override
			protected void filterConstructors( final Class sc, final List constructors )
			{
				CollectionUtils.filter( constructors, new VisibilityPredicate( sc, true ) );
			}
		};
		enhancer.setSuperclass( toMock );

		// ///CLOVER:OFF (I don't know how to test it automatically yet)
		// See issue ID: 2994002
		if ( toMock.getSigners() != null )
		{
			enhancer.setNamingPolicy( ALLOWS_MOCKING_CLASSES_IN_SIGNED_PACKAGES );
		}
		// ///CLOVER:ON

		return enhancer;
	}
}
