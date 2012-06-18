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
    assertContains("\tprotected Person person;\n" +
      "\n" +
      "\tpublic Person buildPerson()\n" +
      "\t{\n" +
      "\t\tif ( person != null ) \n" +
      "\t\t{\n" +
      "\t\t\treturn person;\n" +
      "\t\t}\n" +
      "\t\tperson = new Person();\n" +
      "\t\tperson.setDad( buildPerson_1_1() );\n" +
      "\t\tperson.setMom( buildPerson_5_1() );\n" +
      "\t\tperson.setName( \"son\" );\n" +
      "\t\treturn person;\n" +
      "\t}");
	}

	public void testRecursion()
	{

		Person son = Person.createExamplePerson();
		son.setMom( null );
		// Back to Future situation
		son.getDad().getDad().setDad( son );

		recorder.record( son );
    assertContains("\tprotected Person person;\n" +
      "\n" +
      "\tpublic Person buildPerson()\n" +
      "\t{\n" +
      "\t\tif ( person != null ) \n" +
      "\t\t{\n" +
      "\t\t\treturn person;\n" +
      "\t\t}\n" +
      "\t\tperson = new Person();\n" +
      "\t\tperson.setDad( buildPerson_1_1() );\n" +
      "\t\tperson.setName( \"son\" );\n" +
      "\t\treturn person;\n" +
      "\t}\n" +
      "\n" +
      "\tprotected Person person_1_1;\n" +
      "\n" +
      "\tprotected Person buildPerson_1_1()\n" +
      "\t{\n" +
      "\t\tif ( person_1_1 != null ) \n" +
      "\t\t{\n" +
      "\t\t\treturn person_1_1;\n" +
      "\t\t}\n" +
      "\t\tperson_1_1 = new Person();\n" +
      "\t\tperson_1_1.setDad( buildPerson_2_2() );\n" +
      "\t\tperson_1_1.setName( \"dad\" );\n" +
      "\t\treturn person_1_1;\n" +
      "\t}\n" +
      "\n" +
      "\tprotected Person person_2_2;\n" +
      "\n" +
      "\tprotected Person buildPerson_2_2()\n" +
      "\t{\n" +
      "\t\tif ( person_2_2 != null ) \n" +
      "\t\t{\n" +
      "\t\t\treturn person_2_2;\n" +
      "\t\t}\n" +
      "\t\tperson_2_2 = new Person();\n" +
      "\t\tperson_2_2.setDad( person );\n" +
      "\t\tperson_2_2.setName( \"grandpa\" );\n" +
      "\t\treturn person_2_2;\n" +
      "\t}"
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
		assertContains("\tprotected Person person;\n" +
      "\n" +
      "\tpublic Person buildPerson()\n" +
      "\t{\n" +
      "\t\tif ( person != null ) \n" +
      "\t\t{\n" +
      "\t\t\treturn person;\n" +
      "\t\t}\n" +
      "\t\tperson = new Person();\n" +
      "\t\tperson.setDad( buildPerson_1_1() );\n" +
      "\t\tperson.setName( \"son\" );\n" +
      "\t\treturn person;\n" +
      "\t}\n" +
      "\n" +
      "\tprotected Person person_1_1;\n" +
      "\n" +
      "\tprotected Person buildPerson_1_1()\n" +
      "\t{\n" +
      "\t\tif ( person_1_1 != null ) \n" +
      "\t\t{\n" +
      "\t\t\treturn person_1_1;\n" +
      "\t\t}\n" +
      "\t\tperson_1_1 = new Person();\n" +
      "\t\tperson_1_1.setDad( buildPerson_2_2() );\n" +
      "\t\tperson_1_1.setName( \"dad\" );\n" +
      "\t\treturn person_1_1;\n" +
      "\t}\n" +
      "\n" +
      "\tprotected Person person_2_2;\n" +
      "\n" +
      "\tprotected Person buildPerson_2_2()\n" +
      "\t{\n" +
      "\t\tif ( person_2_2 != null ) \n" +
      "\t\t{\n" +
      "\t\t\treturn person_2_2;\n" +
      "\t\t}\n" +
      "\t\tperson_2_2 = new Person();\n" +
      "\t\tperson_2_2.setDad( person );\n" +
      "\t\tperson_2_2.setName( \"grandpa\" );\n" +
      "\t\treturn person_2_2;\n" +
      "\t}"
    );
	}
}
