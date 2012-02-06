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

import java.io.File;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

import com.advancedpwr.record.examples.Person;
import com.advancedpwr.record.methods.URLBuilderFactory;

public class BeanRecorderTest extends AbstractRecorderTest
{
	protected void setUp()
	{
		super.setUp();
	}

	public void testSetObject()
	{
		try
		{
			recorder.setObject( null );
			fail( "should have thrown an exception" );
		}
		catch ( RecorderException e )
		{
			assertTrue( e.getMessage().contains( "Called setObject with null argument" ) );
		}
	}

	public void testSetGetObject()
	{
		Person person = Person.createExamplePerson();
		recorder.setObject( person );
		assertEquals( person, recorder.getObject() );
	}

	public void testSetDescriptor()
	{
		ClassDescriptor descriptor = new ClassDescriptor()
		{

			public String getPackageName()
			{
				return "foo";
			}

			public String getClassName()
			{
				return "Fighter";
			}
		};
		recorder.setDescriptor( descriptor );
		assertEquals( descriptor, recorder.getDescriptor() );
	}

	public void testPackagePath()
	{
		initDescriptor();
//		recorder.setPackage( "com.advancedpwr.record.examples.generated" );
		assertEquals( "com/advancedpwr/record/examples/generated", recorder.packagePath() );
	}

	private void initDescriptor()
	{
		SimpleClassDescriptor desc = new SimpleClassDescriptor();
		desc.setClassName( "PersonFactory" );
		desc.setPackageName( "com.advancedpwr.record.examples.generated" );
		recorder.setDescriptor( desc );
	}

	public void testGetPrintWriter()
	{
		recorder.fieldPrintWriter = null;
		recorder.setDestination( (File)null );
		assertNull( recorder.getPrintWriter() );

		recorder.setWriter( result );
		assertNotNull( recorder.getPrintWriter() );
	}

	public void testGetPrintWriter_file()
	{
		recorder.fieldPrintWriter = null;
		recorder.setDestination( (File)null );
		assertNull( recorder.getPrintWriter() );

		recorder.setDestination( new File("generated") );
		recorder.setObject( new Person() );
		assertNotNull( recorder.getPrintWriter() );
	}

	public void testCloseFile()
	{
		final Set set = new HashSet();
		recorder = new BeanRecorder()
		{

			protected void close( Writer inWriter )
			{
				set.add(inWriter);
			}

			protected String packagePath()
			{
				return "package.path";
			}

			public String getClassName()
			{
				return "TestFactory";
			}

		};
		recorder.setDestination( "generated" );
		assertEquals( 0, set.size() );
		recorder.closeFile();
		assertEquals( 1, set.size() );
	}

	public void testAddBuilder()
	{
		assertEquals( 22, recorder.getFactoryBuilder().getFactories().size() );
		recorder.addBuilderFactory( new URLBuilderFactory() );
		assertEquals( 23, recorder.getFactoryBuilder().getFactories().size() );
	}

	public void testRecord_Object()
	{
		Object object = new Object();

		recorder.record( object );

		assertResult( "package java.lang.generated;\n" +
				"\n" +
				"\n" +
				"public class ObjectFactory\n" +
				"{\n" +
				"\n" +
				"	protected Object object;\n" +
				"\n" +
				"	public Object buildObject()\n" +
				"	{\n" +
				"		object = new Object();\n" +
				"		return object;\n" +
				"	}\n" +
				"\n" +
				"}\n" );
	}
	public void XtestRecordPersonJavadocExample()
	{
		Person person = new Person();
		person.setName( "Jim" );
		Person dad = new Person();
		dad.setName( "John" );
		person.setDad( dad );

		BeanRecorder recorder = new BeanRecorder();
		recorder.setDestination( "generated" );
		recorder.record( person );
	}

}
