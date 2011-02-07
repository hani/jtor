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
package com.advancedpwr.record.mock;

import java.util.LinkedHashMap;
import java.util.Map;

import com.advancedpwr.record.AccessPath;
import com.advancedpwr.record.methods.AbstractDefaultFactory;
import com.advancedpwr.record.methods.BaseMethodBuilder;

public class MockMethodBuilderFactory extends AbstractDefaultFactory
{
	
	protected Map fieldBuilderCache;

	public BaseMethodBuilder createMethodBuilder( AccessPath inPath )
	{
		if ( contains( inPath ) )
		{
			return get( inPath );
		}
		MockMethodBuilder builder = new MockMethodBuilder();
		builder.setAccessPath( inPath );
		getBuilderCache().put( inPath.getInstanceTree(), builder );
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

	public Map getBuilderCache()
	{
		if ( fieldBuilderCache == null )
		{
			fieldBuilderCache = new LinkedHashMap();
		}

		return fieldBuilderCache;
	}
}
