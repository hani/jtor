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
import java.util.Set;

import com.advancedpwr.record.methods.BaseMethodBuilder;
import com.advancedpwr.record.methods.CollectionBuilderFactory;
import com.advancedpwr.record.methods.Factory;
import com.advancedpwr.record.methods.MapBuilderFactory;
import com.advancedpwr.record.methods.MethodBuilderFactory;

public class BeanRecorder extends AbstractRecorder
{
	protected InstanceTree fieldInstanceTree;
	protected MethodBuilderFactory fieldFactoryBuilder;
	
	protected void setObject( Object object )
	{
		if ( object == null )
		{
			throw new RecorderException( "Called setObject with null argument");
		}
		fieldInstanceTree = createInstanceTree( object );
	}

	protected InstanceTree createInstanceTree( Object object )
	{
		return new InstanceTree( object );
	}

	/* (non-Javadoc)
	 * @see com.advancedpwr.record.ObjectRecorder#record(java.lang.Object)
	 */
	public <T> T record( T inObject )
	{
		setObject( inObject );
		writeObject();
		closeFile();
		return inObject;
	}

	protected void closeFile()
	{
		if ( getSourceDirectory() != null )
		{
			close( getJavaFileWriter() );
		}
	}
	

	protected void writeObjectBuilderMethod()
	{
		AccessPath result = new AccessPath();
		result.setTree( getInstanceTree() );
		BaseMethodBuilder builder = createMethodBuilder( result );
		builder.setScopePublic();
		builder.buildMethod();
	}

	protected BaseMethodBuilder createMethodBuilder( AccessPath result )
	{
		BaseMethodBuilder builder = getFactoryBuilder().createMethodBuilder( result );
		builder.setClassWriter( this );
		return builder;
	}
	
	protected Object getObject()
	{
		return getInstanceTree().getObject();
	}

	protected InstanceTree getInstanceTree()
	{
		return fieldInstanceTree;
	}

	protected Set<Class> classes()
	{
		return getInstanceTree().getFactory().classes();
	}
	
	protected MethodBuilderFactory getFactoryBuilder()
	{
		if ( fieldFactoryBuilder == null )
		{
			fieldFactoryBuilder = createMethodBuilderFactory();
			
		}
		return fieldFactoryBuilder;
	}

	protected MethodBuilderFactory createMethodBuilderFactory()
	{
		MethodBuilderFactory factory = new MethodBuilderFactory();
		factory.addBuilderFactory( new CollectionBuilderFactory() );
		factory.addBuilderFactory( new MapBuilderFactory() );
		return factory;
	}
	
	public void addBuilderFactory( Factory inFactory )
	{
		getFactoryBuilder().addBuilderFactory( inFactory );
	}
	
	public PrintWriter getPrintWriter()
	{
		if ( fieldPrintWriter == null && getSourceDirectory() != null )
		{
			setWriter( getJavaFileWriter() );
		}
		return fieldPrintWriter;
	}
}
