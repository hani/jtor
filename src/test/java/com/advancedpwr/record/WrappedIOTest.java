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

import java.io.IOException;

import junit.framework.TestCase;

public class WrappedIOTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testWrap()
	{
		WrappedIO wrapper = new WrappedIO()
		{
			
			public Object exec() throws IOException
			{
				throw new IOException();
			}
		};
		
		try
		{
			wrapper.wrap();
			fail( "should have thrown an exception");
		}
		catch ( RuntimeException e )
		{
			assertTrue( e.getCause() instanceof IOException );
		}
		
	}

}
