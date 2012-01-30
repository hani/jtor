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

import java.util.List;
import java.util.Map;

public abstract class InstrumentedPerson extends Person
{

	protected abstract Person wrapped();
	
	protected abstract void writeMockLine( String inString );
	
	public List getChildren()
	{
		return wrapped().getChildren();
	}

	public Person getDad()
	{
		return wrapped().getDad();
	}

	public Person getMom()
	{
		return wrapped().getMom();
	}

	public String getName()
	{
		String string = wrapped().getName();
		writeMockLine( "expect( " + instanceName() + ".getName() ).andReturn( \"" + string + "\" )" );
		return string;
	}

	public Map getRelatives()
	{
		return wrapped().getRelatives();
	}

	public void setChildren( List children )
	{
		wrapped().setChildren( children );
	}

	public void setDad( Person parent )
	{
		wrapped().setDad( parent );
	}

	public void setMom( Person mom )
	{
		wrapped().setMom( mom );
	}

	public void setName( String name )
	{
		writeMockLine( instanceName() + ".setName( \"" + name + "\" )");
		wrapped().setName( name );
	}

	protected String instanceName()
	{
		return "person";
	}

	public void setRelatives( Map relatives )
	{
		wrapped().setRelatives( relatives );
	}

	public String toString()
	{
		return wrapped().toString();
	}

	public int hashCode()
	{
		return wrapped().hashCode();
	}

}
