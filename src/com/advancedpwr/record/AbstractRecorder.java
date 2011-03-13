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
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * <code>AbstractRecorder</code> is the common base class for recorders.  This class provides
 * some convenience methods for specifying the output directory for recorded classes.  It also
 * provides the default {@link ClassDescriptor} for the superclass {@link ClassWriter}.
 * 
 * It is not necessary for future class recorders to extend AbstractRecorder.  This abstract class
 * exists for convenience only.
 * 
 * @author Matthew Avery, mavery@advancedpwr.com on Sep 30, 2010
 */
public abstract class AbstractRecorder extends ClassWriter implements ObjectRecorder
{

	protected File fieldDestinationDirectory;
	protected FileWriter fieldJavaFileWriter;

	protected FileWriter createFileWriter( final File inFile )
	{
		WrappedIO wrapper = new WrappedIO()
		{
			
			public Object exec() throws IOException
			{
				return new FileWriter( inFile );
			}
		};
		return (FileWriter) wrapper.wrap();
	}

	protected FileWriter getJavaFileWriter()
	{
		if ( fieldJavaFileWriter == null )
		{
			fieldJavaFileWriter = createFileWriter( javaFile() );
		}
	
		return fieldJavaFileWriter;
	}

	protected File parentDirectory()
	{
		File parent = new File( getDestination(), packagePath() );
		parent.mkdirs();
		return parent;
	}

	protected File javaFile()
	{
		return new File( parentDirectory(), getClassName() + ".java");
	}

	protected String packagePath()
	{
		return getPackageName().replaceAll( "[.]", "/" );
	}

	public File getDestination()
	{
		return fieldDestinationDirectory;
	}

	/**
	 * Set the generated output directory as a {@link File}
	 * 
	 * @param directory
	 */
	public void setDestination( File testDirectory )
	{
		fieldDestinationDirectory = testDirectory;
	}
	
	/**
	 * Set the generated output directory as the name of a {@link File}.
	 * 
	 * @param directory
	 */
	public void setDestination( String inFileName )
	{
		setDestination( new File( inFileName ) );
	}

	protected ClassDescriptor createDefaultDescriptor()
	{
		if ( objectClass().isArray() )
		{
			return new ArrayClassDescriptor( objectClass() );
		}
		return new DefaultClassDescriptor( objectClass() );
	}

	protected Class objectClass()
	{
		return getObject().getClass();
	}

	protected void close( final Writer inWriter )
	{
		new WrappedIO()
		{
			public Object exec() throws IOException
			{
				inWriter.flush();
				inWriter.close();
				return null;
			}
		}.wrap();
	}

}
