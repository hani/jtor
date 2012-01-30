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

public class DefaultClassDescriptor implements ClassDescriptor
{

	public static final String GENERATED = ".generated";

	public static final String FACTORY = "Factory";
	
	protected Class fieldClass;
	
	public DefaultClassDescriptor()
	{
		
	}
	
	public DefaultClassDescriptor( Class inClass )
	{
		setClass( inClass );
	}
	
	public Class subject()
	{
		return fieldClass;
	}

	public void setClass( Class inClass )
	{
		fieldClass = inClass;
	}

	@Override
	public String getClassName()
	{
		return subject().getSimpleName() + FACTORY;
	}

	@Override
	public String getPackageName()
	{
		return subject().getPackage().getName() + GENERATED;
	}
	
	public String toString()
	{
		return getPackageName() + "." + getClassName();
	}

}
