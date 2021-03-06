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
package com.advancedpwr.record.inspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import com.advancedpwr.record.AccessPath;

/**
 * An {@link AccessPath} representing object access through Java Bean convention setter / getter accessor methods.
 * 
 * @author Matthew Avery, mavery@advancedpwr.com on May 29, 2010
 *
 */
public class AccessorMethodPath extends AccessPath
{

	protected Method fieldSetter;

	protected Method fieldGetter;

	public Method getSetter()
	{
		return fieldSetter;
	}

	public void setSetter( Method setter )
	{
		fieldSetter = setter;
	}

	
	public String pathName()
	{
		return getSetter().getName();
	}
	
	public Class getParameterClass()
	{
		return getSetter().getParameterTypes()[0];
	}

	public Set<Class> getExceptions()
	{
		LinkedHashSet<Class> exceptions = new LinkedHashSet<Class>();
		Class[] types = getSetter().getExceptionTypes();
		exceptions.addAll( Arrays.asList( types ) );
		return exceptions;
	}

	public Method getGetter()
	{
		return fieldGetter;
	}

	public void setGetter( Method getter )
	{
		fieldGetter = getter;
	}

}
