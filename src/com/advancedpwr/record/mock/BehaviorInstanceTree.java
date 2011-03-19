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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.advancedpwr.record.AccessPath;
import com.advancedpwr.record.InstanceTree;
import com.advancedpwr.record.inspect.ArrayInspector;
import com.advancedpwr.record.inspect.BeanInspector;
import com.advancedpwr.record.inspect.CollectionInspector;
import com.advancedpwr.record.inspect.InspectorList;
import com.advancedpwr.record.inspect.MapInspector;

public class BehaviorInstanceTree extends InstanceTree
{
	protected Class fieldPreferredInterface;

	public Class getPreferredInterface()
	{
		return fieldPreferredInterface;
	}

	public void setPreferredInterface( Class preferredInterface )
	{
		fieldPreferredInterface = preferredInterface;
	}

	protected InspectorList initializeInspectorList()
	{
		InspectorList inspectors = new InspectorList();
		inspectors.add( new ArrayInspector() );
		return inspectors;
	}
	
	public BehaviorInstanceTree( Object object )
	{
		super( object );
	}

	public BehaviorInstanceTree( Object object, InstanceTree inTree )
	{
		super( object, inTree );
	}
	
	public InstanceTree createTree( Object result )
	{
		return new BehaviorInstanceTree( result, this );
	}
	
	public void addAccessPaths( Object[] inArguments, Object result )
	{
		MethodCallPath accessor = createMethodCallPath( inArguments, result );
		getAccessPaths().add( accessor );
		getAccessPaths().addAll( accessor.getArguments() );
	}

	protected MethodCallPath createMethodCallPath(  Object[] inArguments, Object result )
	{
		MethodCallPath path = new MethodCallPath();
		path.setMethod( getCurrentMethod() );
		InstanceTree tree = createInstanceTree( result );
		path.setTree( tree );
		List<AccessPath> paths = new ArrayList<AccessPath>();
		for ( int i = 0; i < inArguments.length; i++ )
		{
			Object object = inArguments[ i ];
			AccessPath argPath = new ArgumentPath();
			argPath.setTree( createInstanceTree( object ) );
			paths.add( argPath );
		}
		path.setArguments( paths );
		return path;
	}

	protected Set<Class> fieldDeclaredExceptions;
	
	protected Set<Class> getDeclaredExceptions()
	{
		if ( fieldDeclaredExceptions == null )
		{
			fieldDeclaredExceptions = new LinkedHashSet<Class>();
			
		}

		return fieldDeclaredExceptions;
	}
	
	public void addDeclaredExceptions( Class<?>[] exceptionTypes )
	{
		getDeclaredExceptions().addAll( Arrays.asList( exceptionTypes ) );
	}

	public Set<Class> getExceptions()
	{
		Set<Class> exceptions = super.getExceptions();
		exceptions.addAll( getDeclaredExceptions() );
		return exceptions;
	}

	public Class<? extends Object> objectClass()
	{
		if ( getPreferredInterface() != null )
		{
			return getPreferredInterface();
		}
		return getObject().getClass();
	}
	
	

}
