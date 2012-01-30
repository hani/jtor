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
package com.advancedpwr.record.examples;

public class StringExample
{
	protected String fieldString;

	protected String fieldString2;
	
	public String getString2()
	{
		return fieldString2;
	}

	public void setString2( String string2 )
	{
		fieldString2 = string2;
	}

	public String getString()
	{
		return fieldString;
	}

	public void setString( String string )
	{
		fieldString = string;
	}

}
