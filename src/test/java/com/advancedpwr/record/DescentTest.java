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

import com.advancedpwr.record.examples.Person;

public class DescentTest extends AbstractRecorderTest
{

	@Override
	protected void setUp()
	{
		// TODO Auto-generated method stub
		super.setUp();
	}

	public void testRecordDescent()
	{

		recorder.record( Person.createExamplePerson() );
    assertContains("	protected Person person;\n" +
    				"\n" +
    				"	public Person buildPerson()\n" +
    				"	{\n" +
    				"		person = new Person();\n" +
    				"		person.setDad( buildPerson_1_1() );\n" +
    				"		person.setMom( buildPerson_5_1() );\n" +
    				"		person.setName( \"son\" );\n" +
    				"		return person;\n" +
    				"	}\n"
    );
	}

	public void testRecursion()
	{

		Person son = Person.createExamplePerson();
		son.setMom( null );
		// Back to Future situation
		son.getDad().getDad().setDad( son );

		recorder.record( son );
    assertContains("	protected Person person;\n" +
    				"\n" +
    				"	public Person buildPerson()\n" +
    				"	{\n" +
    				"		person = new Person();\n" +
    				"		person.setDad( buildPerson_1_1() );\n" +
    				"		person.setName( \"son\" );\n" +
    				"		return person;\n" +
    				"	}\n" +
    				"\n" +
    				"	protected Person person_1_1;\n" +
    				"\n" +
    				"	protected Person buildPerson_1_1()\n" +
    				"	{\n" +
    				"		person_1_1 = new Person();\n" +
    				"		person_1_1.setDad( buildPerson_2_2() );\n" +
    				"		person_1_1.setName( \"dad\" );\n" +
    				"		return person_1_1;\n" +
    				"	}\n" +
    				"\n" +
    				"	protected Person person_2_2;\n" +
    				"\n" +
    				"	protected Person buildPerson_2_2()\n" +
    				"	{\n" +
    				"		person_2_2 = new Person();\n" +
    				"		person_2_2.setDad( person );\n" +
    				"		person_2_2.setName( \"grandpa\" );\n" +
    				"		return person_2_2;\n" +
    				"	}\n"
    );
	}

	public void testRecursion_2()
	{
		Person son = Person.createExamplePerson();
		son.setMom( null );
		// Back to Future situation
		Person dad = son.getDad();
		Person grandpa = dad.getDad();
		grandpa.setDad( son );

		recorder.record( son );
		assertContains("	protected Person person;\n" +
				"\n" +
				"	public Person buildPerson()\n" +
				"	{\n" +
				"		person = new Person();\n" +
				"		person.setDad( buildPerson_1_1() );\n" +
				"		person.setName( \"son\" );\n" +
				"		return person;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_1_1;\n" +
				"\n" +
				"	protected Person buildPerson_1_1()\n" +
				"	{\n" +
				"		person_1_1 = new Person();\n" +
				"		person_1_1.setDad( buildPerson_2_2() );\n" +
				"		person_1_1.setName( \"dad\" );\n" +
				"		return person_1_1;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_2_2;\n" +
				"\n" +
				"	protected Person buildPerson_2_2()\n" +
				"	{\n" +
				"		person_2_2 = new Person();\n" +
				"		person_2_2.setDad( person );\n" +
				"		person_2_2.setName( \"grandpa\" );\n" +
				"		return person_2_2;\n" +
				"	}\n"
    );
	}
}
