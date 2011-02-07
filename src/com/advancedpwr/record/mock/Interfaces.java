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

import java.util.LinkedHashSet;
import java.util.Set;

public class Interfaces
{
	
	public Set findInterfaces( Class aClass )
	{
		Set allInterfaces = new LinkedHashSet();
		addInterfaces( aClass, allInterfaces );
		return allInterfaces;
	}
	
	protected void addInterfaces( Class aClass, Set allInterfaces )
	{
		if ( aClass == null )
		{
			return;
		}
		if ( aClass.isInterface() )
		{
			allInterfaces.add( aClass );
		}
		
		Class[] interfaces = aClass.getInterfaces();
		for ( int i = 0; i < interfaces.length; i++ )
		{
			allInterfaces.add(  interfaces[i] );
			addInterfaces( interfaces[i], allInterfaces );
		}
		addInterfaces( aClass.getSuperclass(), allInterfaces );
	}
}
