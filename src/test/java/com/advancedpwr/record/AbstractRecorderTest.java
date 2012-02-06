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

import java.io.FileWriter;

import junit.framework.TestCase;

public abstract class AbstractRecorderTest extends TestCase
{
	public static final String WRITE_FILES = "write.files";
	protected BeanRecorder recorder;
	protected MultiWriter result;

	protected void setUp()
	{
		setWriteFiles();
		recorder = new BeanRecorder();
		configureRecorder( recorder );
	}

	public void configureRecorder( final AbstractRecorder inRecorder )
	{
		result = new MultiWriter();
		if ( writeFiles() )
		{
			inRecorder.setDestination( "generated" );
			result = new MultiWriter()
			{

				public FileWriter getFileWriter()
				{
					return inRecorder.getJavaFileWriter();
				}

			};
		}

		inRecorder.setWriter( result );

	}

	protected boolean writeFiles()
	{
		return "true".equalsIgnoreCase( System.getProperty( WRITE_FILES ));
	}

	public static void setWriteFiles()
	{
		System.setProperty( WRITE_FILES, "true" );
	}

  public void assertContains(String... values) {
    String res = result.toString();
    for(String value : values) {
      if(!res.contains(value)) {
        assertResult(value);
      }
    }
  }

	public void assertResult( String inString )
	{
		assertEquals("Missing value", inString.replaceAll( "\r\n", "\n" ).trim(), result.toString().replaceAll( "\r\n", "\n" ).trim());
	}
}
