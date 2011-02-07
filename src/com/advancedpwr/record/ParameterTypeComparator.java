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
import java.lang.reflect.Modifier;
import java.util.Comparator;

public abstract class ParameterTypeComparator implements Comparator<Method>
{

	public int compare( Method m1, Method m2 )
	{
		if ( isSetter( m1 ) && isSetter( m2 ) )
		{
			Class c1 = parameterClass( m1 );
			Class c2 = parameterClass( m2 );
			if (isParameterType( c1 ) == isParameterType( c2 ) )
			{
				return 0;
			}
			if( !isParameterType( c1 ) && isParameterType( c2 ) )
			{
				return -1;
			}
			if( isParameterType( c1 ) && !isParameterType( c2 ) )
			{
				return 1;
			}
		}
		return 0;
	}

	protected Class<?> parameterClass( Method m2 )
	{
		return m2.getParameterTypes()[0];
	}

	protected abstract boolean isParameterType( Class c1 );

	protected boolean isSetter( Method method )
	{
		return Modifier.isPublic( method.getModifiers() )  && method.getName().startsWith( "set" ) && method.getParameterTypes().length == 1;
	}
}
