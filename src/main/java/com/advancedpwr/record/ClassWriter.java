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

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Set;

/**
 * <code>ClassWriter</code> wraps a {@link PrintWriter} with convenience methods for creating Java source code output.
 *
 *  @author Matthew Avery, mavery@advancedpwr.com on Mar 26, 2010
 */
public abstract class ClassWriter
{
	public static final String SPACE = " ";
	public static final String PACKAGE = "package ";
	public static final String IMPORT = "import ";
	public static final String CLASS = "class ";
	public static final String PUBLIC = "public ";
	public static final String PROTECTED = "protected ";
	public static final String PRIVATE = "private ";

	protected PrintWriter fieldPrintWriter;
	protected int tabDepth;
	protected ClassDescriptor fieldDescriptor;
	protected Object fieldObject;

	protected Object getObject()
	{
		return fieldObject;
	}

	protected void setObject( Object object )
	{
		fieldObject = object;
	}

	public PrintWriter getPrintWriter()
	{
		return fieldPrintWriter;
	}

	public void setWriter( Writer writer )
	{
		fieldPrintWriter = new PrintWriter( writer );
	}

	public void writeLine( String inString )
	{
		tabs();
		getPrintWriter().println( inString + ";" );
	}

	public void write( String inString )
	{
		tabs();
		getPrintWriter().println( inString );
	}

	protected ClassWriter tab()
	{
		getPrintWriter().print( '\t' );
		return this;
	}

	protected ClassWriter tabs()
	{
		for ( int i = 0; i < tabDepth; i++ )
		{
			tab();
		}
		return this;
	}

	public ClassWriter openBrace()
	{
		write( "{" );
		tabDepth++;
		return this;
	}

	public ClassWriter closeBrace()
	{
		tabDepth--;
		if ( tabDepth < 0 )
		{
			throw new ClassWriterException( "Possible unmatched opening brace.");
		}
		write( "}" );
		return this;
	}

	public ClassWriter newLine()
	{
		getPrintWriter().println();
		return this;
	}

	public ClassDescriptor getDescriptor()
	{
		if ( fieldDescriptor == null )
		{
			fieldDescriptor = createDefaultDescriptor();
		}
		return fieldDescriptor;
	}

	protected abstract ClassDescriptor createDefaultDescriptor();

	public void setDescriptor( ClassDescriptor descriptor )
	{
		fieldDescriptor = descriptor;
	}
	/**
	 * This method is a "short cut" to easily set the ClassDescriptor
	 * @param The fully qualified class name
	 */
	public void setClassName( String inName )
	{
		int lastDotIndex = inName.lastIndexOf( '.' );
		String packageName = inName.substring( 0, lastDotIndex );
		String className = inName.substring( lastDotIndex + 1 );
		SimpleClassDescriptor descriptor = new SimpleClassDescriptor();
		descriptor.setClassName( className );
		descriptor.setPackageName( packageName );
		setDescriptor( descriptor );
	}

	protected void writeClassDeclaration()
	{
    write("@SuppressWarnings(\"all\")");
		write( PUBLIC + CLASS + getDescriptor().getClassName() );
	}

	protected void writePackage()
	{
		writeLine( PACKAGE + packageName() );
		newLine();
	}

	protected void writeImports()
	{
		for ( Class aClass : classes() )
		{
			writeLine( IMPORT + aClass.getName() );
		}
		newLine();
	}

	protected abstract Set<Class> classes();

	protected String packageName()
	{
		return getDescriptor().getPackageName();
	}

	public String getClassName()
	{
		return getDescriptor().getClassName();
	}

	public String getPackageName()
	{
		return getDescriptor().getPackageName();
	}

	protected void writeObject()
	{
		writePackage();
		writeImports();
		writeClassDeclaration();
		openBrace();
		writeObjectBuilderMethod();
		newLine();
		closeBrace();
	}

	protected abstract void writeObjectBuilderMethod();
}
