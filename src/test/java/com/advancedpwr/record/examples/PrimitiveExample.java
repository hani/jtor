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

public class PrimitiveExample
{
	protected String fieldString;
	protected byte fieldByte;
	protected short fieldShort;
	protected int fieldInt;
	protected long fieldLong;
	protected float fieldFloat;
	protected double fieldDouble;
	protected boolean fieldBoolean;
	protected char fieldCharacter;

	public String getString()
	{
		return fieldString;
	}

	public byte getByte()
	{
		return fieldByte;
	}

	public void setByte( byte b )
	{
		fieldByte = b;
	}

	public short getShort()
	{
		return fieldShort;
	}

	public void setShort( short s )
	{
		fieldShort = s;
	}

	public int getInt()
	{
		return fieldInt;
	}

	public void setInt( int i )
	{
		fieldInt = i;
	}

	public long getLong()
	{
		return fieldLong;
	}

	public void setLong( long l )
	{
		fieldLong = l;
	}

	public float getFloat()
	{
		return fieldFloat;
	}

	public void setFloat( float f )
	{
		fieldFloat = f;
	}

	public double getDouble()
	{
		return fieldDouble;
	}

	public void setDouble( double d )
	{
		fieldDouble = d;
	}

	public boolean isBoolean()
	{
		return fieldBoolean;
	}

	public void setBoolean( boolean b )
	{
		fieldBoolean = b;
	}

	public char getCharacter()
	{
		return fieldCharacter;
	}

	public void setCharacter( char character )
	{
		fieldCharacter = character;
	}

	public void setString( String string )
	{
		fieldString = string;
	}

}
