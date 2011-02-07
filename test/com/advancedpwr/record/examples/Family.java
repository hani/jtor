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

public class Family implements Being
{
	public Family()
	{
		
	}
	
	public Family( Person inDad, Person inMom )
	{
		setParents( inDad, inMom );
	}
	public Person getDad()
	{
		return fieldDad;
	}

	public void setDad( Person dad )
	{
		fieldDad = dad;
	}

	public Person getMom()
	{
		return fieldMom;
	}

	public void setMom( Person mom )
	{
		fieldMom = mom;
	}

	protected Person fieldDad;
	protected Person fieldMom;
	
	public void setParents( Person inDad, Person inMom )
	{
		fieldDad = inDad;
		fieldMom = inMom;
	}
	
	public Person[] people( String[] inNames )
	{
		Person[] people = new Person[ inNames.length ];
		for ( int i = 0; i < inNames.length; i++ )
		{
			Person person = new Person();
			person.setName( inNames[i] );
			if ( inNames[i] != null )
			{
				people[i] = person;
			}
		}
		return people;
	}
	
	public void fued() throws Exception
	{
		throw new Exception("Bad stuff");
	}
	
	public Object fued( Person inPerson ) throws Exception
	{
		inPerson.setName( "jerk" );
		throw new Exception("Bad stuff");
	}
}
