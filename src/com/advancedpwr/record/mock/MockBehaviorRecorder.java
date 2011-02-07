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
package com.advancedpwr.record.mock;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.easymock.internal.IProxyFactory;

import com.advancedpwr.record.BeanRecorder;
import com.advancedpwr.record.InstanceTree;
import com.advancedpwr.record.methods.MethodBuilderFactory;

public class MockBehaviorRecorder extends BeanRecorder
{
	protected Set<Class> fieldPreferredInterfaces;
	
	public MockBehaviorRecorder()
	{
		addPreferredInterface( java.sql.Connection.class );
		addPreferredInterface( java.sql.PreparedStatement.class );
		addPreferredInterface( java.sql.Statement.class );
		addPreferredInterface( java.sql.ResultSet.class );
		
	}
	protected Set<Class> getPreferredInterfaces()
	{
		if ( fieldPreferredInterfaces == null )
		{
			fieldPreferredInterfaces = new LinkedHashSet<Class>();
		}
		return fieldPreferredInterfaces;
	}
	
	public void addPreferredInterface( Class inClass )
	{
		if( inClass.isInterface() )
		{
			getPreferredInterfaces().add( inClass );
		}
	}
	
	protected Set<Class> classes()
	{
		Set<Class> sourceClasses = getInstanceTree().getFactory().classes();
		Set<Class> classes = new LinkedHashSet();
		for ( Iterator iterator = sourceClasses.iterator(); iterator.hasNext(); )
		{
			Class sourceClass = (Class) iterator.next();
			if ( hasPreferedInterface( sourceClass ) )
			{
				classes.add( preferredInterface( sourceClass ) );
			}
			else
			{
				classes.add(  sourceClass );
			}
		}
		classes.add( MockFactory.class );
		return classes;
	}

	public <T> T record( T inObject )
	{
		return instrument( inObject );
	}
	
	protected void debug( Object inObject )
	{
		System.out.println( inObject );
	}

	protected <T> T instrument( final T inObject )
	{
		if ( !canInstrument( inObject ) )
		{
			debug( "Not able to instrument object " + inObject );
			return inObject;
		}
		debug( "Instrumenting " + inObject );
		initializeInstanceTree( inObject );
		
		Class objectClass = inObject.getClass();
		if ( objectClass.isArray() )
		{
			return (T)instrumentedArray( (Object[])inObject );
		}
		final BehaviorInstanceTree tree = newInstanceTree( inObject );

		IProxyFactory<T> factory = createProxyFactory( objectClass );
		return factory.createProxy( (Class<T>) objectClass, new MockRecordingInvocationHandler( this, tree ) );

	}
	
	protected <T> IProxyFactory<T> createProxyFactory( Class<T> inClass )
	{
		if ( hasPreferedInterface( inClass ) )
		{
			Class preferedInterace = preferredInterface( inClass );
			return new InterfaceProxyFactory<T>( preferedInterace );
		}
		else
		{
			return new ClassProxyFactory<T>();
		}
	}
	
	protected boolean hasPreferedInterface( Class inClass )
	{
		return preferredInterface( inClass ) != null;
	}

	protected Class preferredInterface( Class inClass )
	{
		Set interfaces = new Interfaces().findInterfaces( inClass );
		for ( Iterator iterator = interfaces.iterator(); iterator.hasNext(); )
		{
			Class currentInterface = (Class) iterator.next();
			if ( getPreferredInterfaces().contains( currentInterface ) )
			{
				return currentInterface;
			}
		}
		return null;
	}
	
	protected boolean isEnhanced( Class objectClass )
	{
		return objectClass.getSimpleName().contains( "CGLIB" );
	}

	protected boolean canInstrument( Object result )
	{
		return canInstrumentClass( result ) || canInstrumentArray( result ) && !isEnhanced( result.getClass() );
	}

	protected boolean canInstrumentClass( Object result )
	{
		return result != null && isPublic( result ) && !isPrimitive( result ) && !isFinal( result ) && !isEnhanced( result.getClass() );
	}

	protected boolean isFinal( Object result )
	{
		return Modifier.isFinal( result.getClass().getModifiers() );
	}

	protected boolean isPrimitive( Object result )
	{
		return result.getClass().isPrimitive();
	}

	protected boolean isPublic( Object result )
	{
		return Modifier.isPublic( result.getClass().getModifiers() );
	}
	
	protected boolean canInstrumentArray( Object result )
	{
		return result != null && result.getClass().isArray();
	}
	
	protected <T> T[] instrumentedArray( T[] inArray )
	{
		if ( inArray == null )
		{
			return (T[]) new Object[0];
		}
		T[] array = Arrays.copyOf( inArray, inArray.length );
		for ( int i = 0; i < inArray.length; i++ )
		{
			if ( canInstrument( inArray[i] ) )
			{
				array[i] = instrument( inArray[i] );
			}
		}
		return array;
	}

	protected BehaviorInstanceTree newInstanceTree( final Object inObject )
	{
		BehaviorInstanceTree tree = (BehaviorInstanceTree)getInstanceTree().getFactory().createInstanceTree( inObject, getInstanceTree() );
		tree.setPreferredInterface( preferredInterface( inObject.getClass() ) );
		return tree;
	}

	protected void initializeInstanceTree( Object inObject )
	{
		if ( fieldInstanceTree == null )
		{
			fieldInstanceTree = createInstanceTree( inObject );
		}
	}

	protected boolean isObjectMethod( Method method )
	{
		return "hashCode".equals( method.getName() ) || "equals".equals( method.getName() ) ;
	}

	public Object endRecording()
	{
		writePackage();
		writeImports();
		writeClassDeclaration();
		openBrace();
		writeObjectBuilderMethod();
		closeBrace();
		closeFile();
		System.out.println( "Finished recording " + getSourceDirectory().getAbsolutePath() + File.separator + getDescriptor().toString() );
		return getObject();
	}

	protected MethodBuilderFactory createMethodBuilderFactory()
	{
		MethodBuilderFactory factory = new MethodBuilderFactory();
		factory.setDefaultFactory( new MockMethodBuilderFactory() );
		return factory;
	}

	protected void writeClassDeclaration()
	{
		write( PUBLIC + CLASS + getDescriptor().getClassName() + " extends MockFactory" );
	}

	protected InstanceTree createInstanceTree( Object object )
	{
		return createBehaviorInstanceTree( object );
	}

	protected BehaviorInstanceTree createBehaviorInstanceTree( Object object )
	{
		BehaviorInstanceTree tree = new BehaviorInstanceTree( object );
		tree.setPreferredInterface( preferredInterface( object.getClass() ) );
		return tree;
	}

}
