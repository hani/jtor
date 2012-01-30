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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.advancedpwr.record.AccessPath;

public class MethodBuilderFactory
{
	protected Map fieldBuilderCache;

	protected List fieldFactories;

	protected MethodWriterFactory fieldDefaultFactory;
	
	public BuildMethodWriter createMethodBuilder( AccessPath inAccessPath )
	{
		BuildMethodWriter builder = constructRegisteredBuilder( inAccessPath );
		if ( contains( inAccessPath ) )
		{
//			System.out.println( inAccessPath );
			InstanceMethodBuilder instanceBuilder = new InstanceMethodBuilder();
			instanceBuilder.setBuilder( get( inAccessPath ) );
			builder = instanceBuilder;
		}
		builder.setFactory( this );
		builder.setAccessPath( inAccessPath );
		return builder;
	}

	protected BuildMethodWriter get( AccessPath inAccessPath )
	{
		return (BuildMethodWriter) getBuilderCache().get( inAccessPath.getInstanceTree() );
	}

	protected boolean contains( AccessPath result )
	{
		return get( result ) != null;
	}

	public void storeBuilder( BuildMethodWriter inBuilder )
	{
		getBuilderCache().put( inBuilder.getInstanceTree(), inBuilder );
	}

	public Map getBuilderCache()
	{
		if ( fieldBuilderCache == null )
		{
			fieldBuilderCache = new LinkedHashMap();
		}

		return fieldBuilderCache;
	}

	public List<MethodWriterFactory> getFactories()
	{
		if ( fieldFactories == null )
		{
			fieldFactories = createFactories();
		}
		return fieldFactories;
	}

	public void addBuilderFactory( MethodWriterFactory inFactory )
	{
		getFactories().add( inFactory );
	}
	protected List<MethodWriterFactory> createFactories()
	{
		List<MethodWriterFactory> list = new ArrayList<MethodWriterFactory>();
		list.add( new ObjectBuilderFactory()
		{
			public BuildMethodWriter createMethodBuilder( AccessPath inPath )
			{
				return getDefaultFactory().createMethodBuilder( inPath );
			}	
		});
		list.add( new NullBuilderFactory() );
		list.add( new StringBuilderFactory() );
		list.add( new ShortBuilder() );
		list.add( new BooleanBuilder() );
		list.add( new ByteBuilder() );
		list.add( new CharBuilder() );
		list.add( new DoubleBuilder() );
		list.add( new FloatBuilder() );
		list.add( new IntBuilder() );
		list.add( new LongBuilder() );
		list.add( new ArrayBuilderFactory() );
		list.add( new LocaleBuilderFactory() );
		list.add( new URLBuilderFactory() );
		list.add( new DateBuilderFactory() );
		list.add( new CalendarBuilderFactory() );
		list.add( new ClassBuilderFactory() );
		list.add( new BigDecimalBuilder() );
		list.add( new BigIntegerBuilder() );
		return list;
	}
	
	protected BuildMethodWriter constructRegisteredBuilder( AccessPath inAccessPath )
	{
		MethodWriterFactory factory = findFactory( inAccessPath );
		return factory.createMethodBuilder( inAccessPath );
	}

	protected MethodWriterFactory findFactory( AccessPath inAccessPath )
	{
		for ( MethodWriterFactory factory : getFactories() )
		{
			if ( factory.accept( inAccessPath.getParameterClass() ) )
			{
				return factory;
			}
		}
		return getDefaultFactory();
	}

	public MethodWriterFactory getDefaultFactory()
	{
		if ( fieldDefaultFactory == null )
		{
			fieldDefaultFactory = createDefaultFactory();
		}
		return fieldDefaultFactory;
	}

	public void setDefaultFactory( MethodWriterFactory defaultFactory )
	{
		fieldDefaultFactory = defaultFactory;
	}

	protected MethodWriterFactory createDefaultFactory()
	{
		return new BuildMethodWriterFactory();
	}

}
