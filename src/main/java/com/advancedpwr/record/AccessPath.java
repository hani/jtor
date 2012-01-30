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

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * <code>AccessPath</code> represents the code path leading to the access of an object in the {@link InstanceTree}
 * 
 * Instances of <code>AccessPath</code> are inspected by an {@link ObjectRecorder} at class generation time to determine
 * how to reconstruct the object tree.
 * 
 * @author Matthew Avery, mavery@advancedpwr.com on May 29, 2010
 */
public class AccessPath
{
	protected InstanceTree fieldInstanceTree;

	public Object getResult()
	{
		return getInstanceTree().getObject();
	}
	
	public void setTree( InstanceTree inTree )
	{
		fieldInstanceTree = inTree;
	}

	// Type of the result object, e.g. ArrayList
	public Class getResultClass()
	{
		return getInstanceTree().objectClass();
	}
	
	// Return type of the access path, e.g. List
	public Class getReturnType()
	{
		return getResultClass();
	}
	
	// Parameter type
	public Class getParameterClass()
	{
		return getResultClass();
	}
	
	public InstanceTree getInstanceTree()
	{
		return fieldInstanceTree;
	}
	
	public String nameRoot()
	{
		return getResultClass().getSimpleName() + suffix();
	}
	
	public String suffix()
	{
		int index = getInstanceTree().getIndex();
		if ( index > 0 )
		{
			return  "_" + index;
		}
		return "";
	}

	public String pathName()
	{
		return "";
	}
	
	public String toString()
	{
		return getClass().getSimpleName() + ": " + getInstanceTree().getObject() + " instance " + getInstanceTree();
	}

	public Set<Class> getExceptions()
	{
		return new LinkedHashSet<Class>();
	}
		
}
