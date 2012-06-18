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
package com.advancedpwr.record;

import java.lang.reflect.Method;
import java.util.*;

import com.advancedpwr.record.inspect.*;

public class InstanceTree
{
	protected Object fieldObject;
	protected Method fieldCurrentMethod;
	protected AccessPathList fieldAccessPaths;
	protected int depth;
	protected InstanceTree fieldParent;
	protected InstanceTreeFactory fieldFactory;
	protected InspectorList fieldInspectors;

	protected int fieldIndex;

	protected Set fieldStopClasses;

	public InstanceTree getParent()
	{
		if ( fieldParent == null )
		{
			fieldParent = this;
		}
		return fieldParent;
	}

	public int getDepth()
	{
		return depth;
	}

	public void setDepth( int depth )
	{
		this.depth = depth;
	}

	public boolean isTop()
	{
		return getParent().equals( this );
	}

	public void setParent( InstanceTree parent )
	{
		fieldParent = parent;
	}

	public Method getCurrentMethod()
	{
		return fieldCurrentMethod;
	}

	public void setCurrentMethod( Method currentMethod )
	{
		fieldCurrentMethod = currentMethod;
	}

	public InstanceTree( Set<Class> stopClasses, Object object )
	{
		this( object, null );
		setStopClasses( stopClasses );
		getFactory().getTrees().put( object, this );
		setIndex( getFactory().count++ );
		inspectObject();
	}

	public InstanceTree( Object object )
	{
		this( object, null );
		getFactory().getTrees().put( object, this );
		setIndex( getFactory().count++ );
		inspectObject();
	}

	public InstanceTree( Object object, InstanceTree inTree )
	{
		setParent( inTree );
		if ( inTree != null )
		{
			setFactory( inTree.getFactory() );
			setStopClasses( inTree.getStopClasses() );
		}
		setObject( object );
	}

	protected void inspectObject()
	{
		for ( Inspector inspector : getInspectors() )
		{
			inspector.inspect( this );
		}
	}

	public InspectorList getInspectors()
	{
		if ( fieldInspectors == null )
		{
			fieldInspectors = initializeInspectorList();
		}
		return fieldInspectors;
	}

	protected InspectorList initializeInspectorList()
	{
		InspectorList inspectors = new InspectorList();
		inspectors.add(  new BeanInspector() );
		inspectors.add( new CollectionInspector() );
		inspectors.add( new MapInspector() );
		inspectors.add( new ArrayInspector() );
		return inspectors;
	}

	public void addInspector( Inspector inspector )
	{
		getInspectors().add( inspector );
	}

	public String currentInstanceName()
	{
		if ( getParent().equals( this ) )
		{
			return objectClass().getSimpleName();
		}
		return getParent().getCurrentMethod().getName().replaceFirst( "set", "" ) + "_" + depth;
	}

	public Class<? extends Object> objectClass()
	{
		return getObject().getClass();
	}

	public InstanceTree createInstanceTree( Object result )
	{
		return getFactory().createInstanceTree( result, this );
	}

	public InstanceTree createTree( Object result )
	{
		return new InstanceTree( result, this );
	}

	public AccessPathList getAccessPaths()
	{
		if ( fieldAccessPaths == null )
		{
			fieldAccessPaths = new AccessPathList();
		}
		return fieldAccessPaths;
	}




	public Object getObject()
	{
		return fieldObject;
	}

	public void setObject( Object object )
	{
		if ( object == null )
		{
			object = new Null();
		}
		fieldObject = object;
	}

	protected Set<Class> classes()
	{
		Set<Class> classes = new LinkedHashSet<Class>();
		if ( !objectClass().isArray() )
		{
			addClass( classes, objectClass() );
		}
		for ( AccessPath path : getAccessPaths() )
		{
			addClass( classes, path.getParameterClass() );
			addClass( classes, path.getResultClass() );
			for ( Class aClass : path.getExceptions() )
			{
				addClass( classes, aClass );
			}
		}
		return classes;
	}

	protected void addClass( Set<Class> classes, Class inClass )
	{
		if ( ignoredClass( inClass ) )
		{
			return;
		}
		if ( inClass.isArray() )
		{
			inClass = inClass.getComponentType();
		}
		classes.add( inClass );
	}

	public boolean ignoredClass( Class param )
	{
		return param == null
			|| short.class.isAssignableFrom( param )
		    || int.class.isAssignableFrom( param )
		    || long.class.isAssignableFrom( param )
		    || float.class.isAssignableFrom( param )
		    || double.class.isAssignableFrom( param )
		    || boolean.class.isAssignableFrom( param )
		    || byte.class.isAssignableFrom( param )
		    || char.class.isAssignableFrom( param )
		    || void.class.isAssignableFrom( param )
		    || Null.class.isAssignableFrom( param )
		    || param.getName().startsWith( "java.lang." );

	}

	public int getIndex()
	{
		return fieldIndex;
	}

	public void setIndex( int index )
	{
		fieldIndex = index;
	}

	public InstanceTreeFactory getFactory()
	{
		if ( fieldFactory == null )
		{
			fieldFactory = createFactory();
		}
		return fieldFactory;
	}

	protected InstanceTreeFactory createFactory()
	{
		return new InstanceTreeFactory();
	}

	public void setFactory( InstanceTreeFactory factory )
	{
		fieldFactory = factory;
	}

	public Set<Class> getExceptions()
	{
		Map<InstanceTree, Set<Class>> cache = new HashMap<InstanceTree, Set<Class>>();
		cache.put( this, new LinkedHashSet<Class>() );
		Set<Class> exceptions = getExceptions( cache );
		cache.put( this, exceptions );
		return exceptions;
	}

	protected Set<Class> getExceptions( Map<InstanceTree, Set<Class>> cache )
	{
		Set<Class> exceptions = new LinkedHashSet<Class>();
		for ( AccessPath path : getAccessPaths() )
		{
			Set<Class> cached = cache.get( path.getInstanceTree() );
			if ( cached != null )
			{
				exceptions.addAll( cached );
			}
			else
			{
				cache.put( path.getInstanceTree(), new LinkedHashSet<Class>() );
				exceptions.addAll( path.getExceptions() );
				exceptions.addAll( path.getInstanceTree().getExceptions( cache ) );
				cache.put( path.getInstanceTree(), exceptions );
			}
		}
		return exceptions;
	}

	public Set getStopClasses()
	{
		if ( fieldStopClasses == null )
		{
			fieldStopClasses = new HashSet<Class>();
			}
		return fieldStopClasses;
		}

	public boolean isStop()
	{
		return getStopClasses().contains( objectClass() );
	}

	public void setStopClasses( Set stopClasses )
	{
		fieldStopClasses = stopClasses;
	}

}
