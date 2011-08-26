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

import com.advancedpwr.record.AccessPath;

public class ArrayBuilder extends AbstractMultiBuilder
{
	private static final String ARRAY = "Array";
	
	protected void writePopulators()
	{
		int index = 0;
		for ( AccessPath result : getInstanceTree().getAccessPaths() )
		{
			BuildMethodWriter builder = createCachedInstanceMethodBuilder( result );
			writeLine( instanceName() + "[" + index++ + "] = " + builder.resultBuilder() );
		}
	}
	
	protected String instanceName()
	{
		return nameRoot().toLowerCase() + ARRAY + getAccessPath().suffix() + depth();
	}
	
	public String resultBuilder()
	{
		return "build" + nameRoot() + ARRAY + getAccessPath().suffix() + depth() + "()";
	}
	
	protected String nameRoot()
	{
		return getAccessPath().getResultClass().getComponentType().getSimpleName();
	}
	
	protected void writeInstance()
	{
		writeIfNotNullReturnInstance();
		writeLine( instanceName() + " = new " + nameRoot() + "[" + getInstanceTree().getAccessPaths().size() + "]");
	}	

}
