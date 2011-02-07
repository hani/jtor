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

	protected Factory fieldDefaultFactory;
	
	public BaseMethodBuilder createMethodBuilder( AccessPath inAccessPath )
	{
		BaseMethodBuilder builder = constructRegisteredBuilder( inAccessPath );
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

	protected BaseMethodBuilder get( AccessPath inAccessPath )
	{
		return (BaseMethodBuilder) getBuilderCache().get( inAccessPath.getInstanceTree() );
	}

	protected boolean contains( AccessPath result )
	{
		return get( result ) != null;
	}

	public void storeBuilder( BaseMethodBuilder inBuilder )
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

	public List<Factory> getFactories()
	{
		if ( fieldFactories == null )
		{
			fieldFactories = createFactories();
		}
		return fieldFactories;
	}

	public void addBuilderFactory( Factory inFactory )
	{
		getFactories().add( inFactory );
	}
	protected List<Factory> createFactories()
	{
		List<Factory> list = new ArrayList<Factory>();
		list.add( new ObjectBuilderFactory()
		{
			public BaseMethodBuilder createMethodBuilder( AccessPath inPath )
			{
				return getDefaultFactory().createMethodBuilder( inPath );
			}	
		});
		list.add( new NullBuilderFactory() );
		list.add( new StringBuilderFactory() );
		list.add( new ShortBuilderFactory() );
		list.add( new BooleanBuilderFactory() );
		list.add( new ByteBuilderFactory() );
		list.add( new CharBuilderFactory() );
		list.add( new DoubleBuilderFactory() );
		list.add( new FloatBuilderFactory() );
		list.add( new IntBuilderFactory() );
		list.add( new LongBuilderFactory() );
		list.add( new ArrayBuilderFactory() );
		list.add( new URLBuilderFactory() );
		list.add( new DateBuilderFactory() );
		list.add( new CalendarBuilderFactory() );
		list.add( new ClassBuilderFactory() );
		return list;
	}
	
	protected BaseMethodBuilder constructRegisteredBuilder( AccessPath inAccessPath )
	{
		Factory factory = findFactory( inAccessPath );
		return factory.createMethodBuilder( inAccessPath );
	}

	protected Factory findFactory( AccessPath inAccessPath )
	{
		for ( Factory factory : getFactories() )
		{
			if ( factory.accept( inAccessPath.getParameterClass() ) )
			{
				return factory;
			}
		}
		return getDefaultFactory();
	}

	public Factory getDefaultFactory()
	{
		if ( fieldDefaultFactory == null )
		{
			fieldDefaultFactory = createDefaultFactory();
		}
		return fieldDefaultFactory;
	}

	public void setDefaultFactory( Factory defaultFactory )
	{
		fieldDefaultFactory = defaultFactory;
	}

	protected Factory createDefaultFactory()
	{
		return new BaseMethodBuilderFactory();
	}

}
