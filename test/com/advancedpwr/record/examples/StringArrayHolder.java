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

public class StringArrayHolder
{
	protected String[] fieldArray1;
	protected String[] fieldArray2;
	public String[] getArray1()
	{
		return fieldArray1;
	}
	public void setArray1( String[] array1 )
	{
		fieldArray1 = array1;
	}
	public String[] getArray2()
	{
		return fieldArray2;
	}
	public void setArray2( String[] array2 )
	{
		fieldArray2 = array2;
	}
}
