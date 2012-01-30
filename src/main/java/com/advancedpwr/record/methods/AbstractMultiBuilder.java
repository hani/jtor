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

import java.util.Iterator;
import java.util.List;

import com.advancedpwr.record.AccessPath;

public abstract class AbstractMultiBuilder extends BuildMethodWriter
{
	protected MethodBuilderFactory fieldCacheFactory;
	
	public MethodBuilderFactory getCacheFactory()
	{
		if ( fieldCacheFactory == null )
		{
			fieldCacheFactory = new MethodBuilderFactory();
			
		}
		fieldCacheFactory.getBuilderCache().putAll( getFactory().getBuilderCache() );
		return fieldCacheFactory;
	}
	
	protected BuildMethodWriter createCachedInstanceMethodBuilder( AccessPath result )
	{
		BuildMethodWriter builder = getFactory().createMethodBuilder( result );
		if ( getCacheFactory().contains( result ) )
		{
			builder = getCacheFactory().createMethodBuilder( result );
		}
		builder.setClassWriter( getClassWriter() );
		cache( builder );
		return builder;
	}

	protected void cache( BuildMethodWriter inBuilder )
	{
		if ( getCacheFactory().contains( inBuilder.getAccessPath() ) )
		{
			return;
		}
		getCacheFactory().storeBuilder( inBuilder );
		List<AccessPath> paths = inBuilder.getInstanceTree().getAccessPaths();
		for ( Iterator iterator = paths.iterator(); iterator.hasNext(); )
		{
			AccessPath accessPath = (AccessPath) iterator.next();
			BuildMethodWriter builder = getCacheFactory().createMethodBuilder( accessPath );
			cache( builder );
		}
	}
}
