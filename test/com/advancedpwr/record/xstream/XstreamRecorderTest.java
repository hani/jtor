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
package com.advancedpwr.record.xstream;

import java.io.File;

import com.advancedpwr.record.AbstractRecorderTest;
import com.advancedpwr.record.SimpleClassDescriptor;
import com.advancedpwr.record.examples.Person;

public class XstreamRecorderTest extends AbstractRecorderTest
{
	XstreamRecorder recorder;

	protected void setUp()
	{
		setWriteFiles();
		recorder = new XstreamRecorder();
		configureRecorder( recorder );
		initDescriptor();
	}

	private void initDescriptor()
	{
		SimpleClassDescriptor desc = new SimpleClassDescriptor();
		desc.setClassName( "XstreamPersonFactory" );
		desc.setPackageName( "com.advancedpwr.record.examples.xstream.generated" );
		recorder.setDescriptor( desc );
	}
	
	public void testRecordPerson()
	{
		Person person = new Person();
		person.setName( "Jim" );
		Person dad = new Person();
		dad.setName( "John" );
		person.setDad( dad );
		recorder.record( person );
	}

	
	public void testXmlFile()
	{
		initDescriptor();
		File xmlFile = recorder.xmlFile();
		System.out.println( xmlFile.getAbsolutePath() );
	}
}
