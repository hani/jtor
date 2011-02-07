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
import java.io.IOException;
import java.io.StringWriter;

public class MultiWriter extends StringWriter
{
	protected FileWriter fieldFileWriter;
	
	public void write( String str, int off, int len )
	{
		super.write( str, off, len );
		if ( getFileWriter() != null )
		{
			try
			{
				getFileWriter().write( str, off, len );
			}
			catch ( IOException e )
			{
				throw new RuntimeException( e );
			}
		}
	}

	public FileWriter getFileWriter()
	{
		return fieldFileWriter;
	}

	public void setFileWriter( FileWriter fileWriter )
	{
		fieldFileWriter = fileWriter;
	}

	public void close() throws IOException
	{
		super.close();
		if ( getFileWriter() != null )
		{
			try
			{
				getFileWriter().close();
			}
			catch ( IOException e )
			{
				throw new RuntimeException( e );
			}
		}
	}

	public void flush()
	{
		super.flush();
		if ( getFileWriter() != null )
		{
			try
			{
				getFileWriter().flush();
			}
			catch ( IOException e )
			{
				throw new RuntimeException( e );
			}
		}
	}

	public void write( char[] cbuf, int off, int len )
	{
		super.write( cbuf, off, len );
		if ( getFileWriter() != null )
		{
			try
			{
				getFileWriter().write( cbuf, off, len );
			}
			catch ( IOException e )
			{
				throw new RuntimeException( e );
			}
		}
	}

	public void write( int c )
	{
		super.write( c );
		if ( getFileWriter() != null )
		{
			try
			{
				getFileWriter().write( c );
			}
			catch ( IOException e )
			{
				throw new RuntimeException( e );
			}
		}
	}

	public void write( String str )
	{
		super.write( str );
		if ( getFileWriter() != null )
		{
			try
			{
				getFileWriter().write( str );
			}
			catch ( IOException e )
			{
				throw new RuntimeException( e );
			}
		}
	}


}
