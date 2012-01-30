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
import java.io.FileWriter;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import com.advancedpwr.record.AbstractRecorder;
import com.advancedpwr.record.ObjectRecorder;
import com.thoughtworks.xstream.XStream;

/**
 * An {@link ObjectRecorder} that records the <i>state</i> of an object tree using the <a href="http://xstream.codehaus.org/">XStream</a> project.
 * The object tree is serialized to XML at recording time and Java factory class is created to rehydrate the object tree at test time.
 * <p>
 * Recording example:
 * </p>
 *<p><blockquote><pre>
	Person person = new Person();
	person.setName( "Jim" );
	Person dad = new Person();
	dad.setName( "John" );
	person.setDad( dad );
	
	XstreamRecorder recorder = new XstreamRecorder();
	recorder.setDestination( "recordings" );
	recorder.record( person );
 * </pre></blockquote><p>
 * 
 * The above example will record the object tree of the "person" instance as a Java class:
 * 
 * <p><blockquote><pre>
	public Person buildPerson()
	{
		XStream xstream = new XStream();
		InputStream in = getClass().getResourceAsStream( "/com/examples/generated/PersonFactory.xml" );
		return (Person)xstream.fromXML( in );
	}
 * </pre></blockquote><p>
 * 
 * To reconstruct the instance of "person" in a unit test:
 * 
 * <p><blockquote><pre>
  	Person person = new PersonFactory().buildPerson();
 * </pre></blockquote><p>
 * 
 * The <code>XstreamRecorder</code> is capable of recording arbitrary object trees and is "instance aware".  This class
 * requires the XStream 1.3.1 jar and the objenesis 1.2 jar.
 *  
 * @author Matthew Avery, mavery@advancedpwr.com on Jun 22, 2010
 *
 */
public class XstreamRecorder extends AbstractRecorder
{
	protected FileWriter fieldXmlFileWriter;
	public <T> T record( T inObject )
	{
		setObject( inObject );
		writeXstream();
		writeFactoryClass();
		return inObject;
	}

	protected void writeXstream()
	{
		XStream xstream = new XStream();
		xstream.toXML( getObject(), getXmlFileWriter() );
		close( getXmlFileWriter() );
	}
	
	protected void writeFactoryClass()
	{
		setWriter( getJavaFileWriter() );
		writeObject();
		close( getJavaFileWriter() );
	}

	public FileWriter getXmlFileWriter()
	{
		if ( fieldXmlFileWriter == null )
		{
			fieldXmlFileWriter = createFileWriter( xmlFile() );
		}

		return fieldXmlFileWriter;
	}
	
	protected File xmlFile()
	{
		return new File( parentDirectory(), getClassName() + ".xml");
	}

	protected Set<Class> classes()
	{
		Set<Class> classes = new HashSet<Class>();
		classes.add( InputStream.class );
		classes.add( XStream.class );
		classes.add( getObject().getClass() );
		return classes;
	}

	protected void writeObjectBuilderMethod()
	{
		newLine();
		write( PUBLIC + returnType() + SPACE + "build" + returnType() + "()" );
		openBrace();
		writeLine( "XStream xstream = new XStream()" );
		writeLine( "InputStream in = getClass().getResourceAsStream( \"" + xmlPath() + "\" )" );
		writeLine( "return (" + returnType() + ")xstream.fromXML( in )" );
		closeBrace();
	}

	protected String xmlPath()
	{
		return "/" + packagePath() + "/" + getClassName() + ".xml";
	}

	protected String returnType()
	{
		return getObject().getClass().getSimpleName();
	}
	
	

}
