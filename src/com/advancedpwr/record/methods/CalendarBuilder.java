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

import java.util.Calendar;

import com.advancedpwr.record.AccessPath;

public class CalendarBuilder extends BaseMethodBuilder
{


	protected void writePopulators()
	{
		for ( AccessPath result : getInstanceTree().getAccessPaths() )
		{
			if ( "setTimeInMillis".equals( result.pathName() ) )
			{
				BaseMethodBuilder builder = createMethodBuilder( result );
				writeLine( builder.populator( instanceName() ) );
			}
		}
	}
	
	protected void writeBuilderMethods()
	{

	}
	
	protected String instanceType()
	{
		return Calendar.class.getSimpleName();
	}

	protected void writeInstance()
	{
		Calendar calendar = (Calendar)getAccessPath().getResult();
		writeLine( instanceName() + " = " + calendar.getClass().getSimpleName() + ".getInstance()");
	}
}
