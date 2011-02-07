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

public class LangObjects
{
	public Short getShort()
	{
		return fieldShort;
	}
	public void setShort( Short s )
	{
		fieldShort = s;
	}
	public Integer getInteger()
	{
		return fieldInteger;
	}
	public void setInteger( Integer integer )
	{
		fieldInteger = integer;
	}
	public Long getLong()
	{
		return fieldLong;
	}
	public void setLong( Long l )
	{
		fieldLong = l;
	}
	public Float getFloat()
	{
		return fieldFloat;
	}
	public void setFloat( Float f )
	{
		fieldFloat = f;
	}
	public Double getDouble()
	{
		return fieldDouble;
	}
	public void setDouble( Double d )
	{
		fieldDouble = d;
	}
	public Character getCharacter()
	{
		return fieldCharacter;
	}
	public void setCharacter( Character character )
	{
		fieldCharacter = character;
	}
	public Boolean getBoolean()
	{
		return fieldBoolean;
	}
	public void setBoolean( Boolean b )
	{
		fieldBoolean = b;
	}
	public Byte getByte()
	{
		return fieldByte;
	}
	public void setByte( Byte b )
	{
		fieldByte = b;
	}
	protected Short fieldShort;
	protected Integer fieldInteger;
	protected Long fieldLong;
	protected Float fieldFloat;
	protected Double fieldDouble;
	protected Character fieldCharacter;
	protected Boolean fieldBoolean;
	protected Byte fieldByte;
	protected Class fieldClassArgument;
	public Class getClassArgument()
	{
		return fieldClassArgument;
	}
	public void setClassArgument( Class classArgument )
	{
		fieldClassArgument = classArgument;
	}
}
