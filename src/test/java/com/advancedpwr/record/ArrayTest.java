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

import com.advancedpwr.record.examples.Family;
import com.advancedpwr.record.examples.Person;

public class ArrayTest extends AbstractRecorderTest
{
	protected void setUp()
	{
		setWriteFiles();
		super.setUp();

	}

	public void testArray()
	{
		Person person = Person.createExamplePerson();
		Person[] array = new Person[]{person.getDad(), person.getMom(), person };

		recorder.record( array );
    assertContains("personArray = new Person[3]", "personArray[0] = buildPerson_1_1()");
	}

	public void testArray_2()
	{

		Person person = Person.createExamplePerson();
		Person person2 = Person.createExamplePerson();
		person2.getDad().setName( "dad2" );
		person2.getMom().setName( "mom2" );
//		person2.setBirthday( GregorianCalendar.getInstance() );
//		person2.setAnniversary( new Date() );
		Person[] array = new Person[]{person.getDad(), person.getMom(), person, person2, person2.getDad() };
		recorder.record( array );
    assertContains("personArray[4] = person_10_2");
	}

	public void testArray_4()
	{
		Person joe = new Person();
		joe.setName( "joe" );

		Person dad = new Person();
		dad.setName( "dad" );

		joe.setDad( dad );

		Person[] array = new Person[]{ joe, dad };
		recorder.record( array );
    assertContains("person_1_1.setDad( buildPerson_2_2() );");
	}

	public void testArray_recursion()
	{
		Person joe = new Person();
		joe.setName( "joe" );

		Person dad = new Person();
		dad.setName( "dad" );

		joe.setDad( dad );
		dad.setDad( joe );
		Person[] array = new Person[]{ joe, dad };
		recorder.record( array );
    assertContains("person_2_2.setDad( person_1_1 );");
	}

	public void testArray_imports()
	{
		Person person = Person.createExamplePerson();
		Family family = new Family();
		family.setDad( person.getDad() );
		family.setMom( person.getMom() );

		Family oldFamily = new Family();
		oldFamily.setDad( person.getDad().getDad() );
		Family[] fams = new Family[]{ family, oldFamily };
		recorder.record( fams );
    assertContains("import com.advancedpwr.record.examples.Family;", "import com.advancedpwr.record.examples.Person;");
	}
}
