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

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Person implements Being
{
	protected Person fieldDad;
	protected Person fieldMom;
	protected List fieldChildren;
	protected Map fieldRelatives;
	protected Calendar fieldBirthday;
	protected Date fieldAnniversary;
	
	public Date getAnniversary()
	{
		return fieldAnniversary;
	}

	public void setAnniversary( Date anniversary ) throws Exception
	{
		fieldAnniversary = anniversary;
	}

	public Calendar getBirthday()
	{
		return fieldBirthday;
	}

	public void setBirthday( Calendar birthday ) throws BirthdayException
	{
		fieldBirthday = birthday;
	}

	public Map getRelatives()
	{
		return fieldRelatives;
	}

	public void setRelatives( Map relatives )
	{
		fieldRelatives = relatives;
	}

	public List getChildren()
	{
		return fieldChildren;
	}

	public void setChildren( List children )
	{
		fieldChildren = children;
	}

	public Person getMom()
	{
		return fieldMom;
	}

	public void setMom( Person mom )
	{
		fieldMom = mom;
	}

	protected String fieldName;

	public Person getDad()
	{
		return fieldDad;
	}

	public void setDad( Person parent )
	{
		fieldDad = parent;
	}

	public String getName()
	{
		return fieldName;
	}

	public void setName( String name )
	{
		fieldName = name;
	}
	
	public static Person createExamplePerson()
	{
		Person grandpa = new Person();
		grandpa.setName( "grandpa" );
		
		Person dad = new Person();
		dad.setName( "dad" );
		dad.setDad( grandpa );
		
		Person mom = new Person();
		mom.setName( "mom" );
		
		Person son = new Person();
		son.setName( "son" );
		son.setDad( dad );
		son.setMom( mom );
		
		return son;
	}
	
	public String toString()
	{
		return super.toString() + " : " + getName();
	}
	
	public Restricted getRestricted()
	{
		return Restricted.createRestricted();
	}

	public synchronized void  callSynced()
	{
		getRestricted().synced();
	}
}
