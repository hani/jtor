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
package com.advancedpwr.record.methods;

import java.io.StringWriter;

import junit.framework.TestCase;

import com.advancedpwr.record.AccessPath;
import com.advancedpwr.record.BeanRecorder;
import com.advancedpwr.record.ClassWriter;
import com.advancedpwr.record.InstanceTree;
import com.advancedpwr.record.examples.Person;

public class MethodBuilderTest extends TestCase
{
	BuildMethodWriter builder;
	StringWriter out;
	
	protected void setUp() throws Exception
	{
		super.setUp();
		
		builder = new BuildMethodWriter();
		ClassWriter classWriter = new BeanRecorder();
		out = new StringWriter();
		classWriter.setWriter( out );
		builder.setClassWriter( classWriter );
		builder.setFactory( new MethodBuilderFactory() );
	}
	
	public void testBuild()
	{
		AccessPath result = new AccessPath();
		assertEquals( "", result.pathName() );
		Person person = new Person();
		person.setName( "Joe" );
		result.setTree( new InstanceTree( person ) );
		builder.setAccessPath( result );
		builder.buildMethod();
		assertEquals( "\n" + 
				"protected Person person;\n" + 
				"\n" + 
				"protected Person buildPerson()\n" + 
				"{\n" + 
				"	person = new Person();\n" + 
				"	person.setName( \"Joe\" );\n" + 
				"	return person;\n" + 
				"}\n", out.toString().replaceAll( "\r\n", "\n" ) );
	}
	
	public void testSetScope()
	{
		builder.setScopePublic();
		assertEquals( "public ", builder.scope() );
		builder.setScopeProtected();
		assertEquals( "protected ", builder.scope() );
	}

}
