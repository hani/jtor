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

public class LocaleBuilder extends BuildMethodWriter
{
	
	protected void writeInstance()
	{
		writeIfNotNullReturnInstance();
		String[] parts = result().split( "_" );
		writeLine( instanceName() + " = new " + instanceType() + "( \"" + parts[0] + "\", \"" + parts[1] + "\" )" );
	}

	protected String result()
	{
		return getAccessPath().getResult().toString();
	}
}
