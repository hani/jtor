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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import junit.framework.TestCase;

public class ClassProxyFactoryTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testCreateProxy()
	{
		ClassProxyFactory factory = new ClassProxyFactory();
		Object object = factory.createProxy( Object.class, new InvocationHandler()
		{
			public Object invoke( Object proxy, Method method, Object[] args ) throws Throwable
			{
				return null;
			}
		});
		
		assertNull( object.toString() );
	}

}
