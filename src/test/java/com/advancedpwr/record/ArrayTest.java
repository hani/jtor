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

import java.text.SimpleDateFormat;
import java.util.Date;

import com.advancedpwr.record.examples.Family;
import com.advancedpwr.record.examples.Person;
import com.advancedpwr.record.examples.StringArrayHolder;

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
		assertResult( "package com.advancedpwr.record.examples.generated;\n" +
				"\n" +
				"import com.advancedpwr.record.examples.Person;\n" +
				"\n" +
        "@SuppressWarnings(\"all\")\n" +
				"public class PersonArrayFactory\n" +
				"{\n" +
				"\n" +
				"	protected Person[] personArray;\n" +
				"\n" +
				"	public Person[] buildPersonArray()\n" +
				"	{\n" +
				"		if ( personArray != null ) \n" +
				"		{\n" +
				"			return personArray;\n" +
				"		}\n" +
				"		personArray = new Person[3];\n" +
				"		personArray[0] = buildPerson_1_1();\n" +
				"		personArray[1] = buildPerson_5_1();\n" +
				"		personArray[2] = buildPerson_7_1();\n" +
				"		return personArray;\n" +
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
				"		person_2_2.setName( \"grandpa\" );\n" +
				"		return person_2_2;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_5_1;\n" +
				"\n" +
				"	protected Person buildPerson_5_1()\n" +
				"	{\n" +
				"		person_5_1 = new Person();\n" +
				"		person_5_1.setName( \"mom\" );\n" +
				"		return person_5_1;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_7_1;\n" +
				"\n" +
				"	protected Person buildPerson_7_1()\n" +
				"	{\n" +
				"		person_7_1 = new Person();\n" +
				"		person_7_1.setDad( person_1_1 );\n" +
				"		person_7_1.setMom( person_5_1 );\n" +
				"		person_7_1.setName( \"son\" );\n" +
				"		return person_7_1;\n" +
				"	}\n" +
				"\n" +
				"}\n");
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

		assertResult( "package com.advancedpwr.record.examples.generated;\n" +
				"\n" +
				"import com.advancedpwr.record.examples.Person;\n" +
				"\n" +
        "@SuppressWarnings(\"all\")\n" +
				"public class PersonArrayFactory\n" +
				"{\n" +
				"\n" +
				"	protected Person[] personArray;\n" +
				"\n" +
				"	public Person[] buildPersonArray()\n" +
				"	{\n" +
				"		if ( personArray != null ) \n" +
				"		{\n" +
				"			return personArray;\n" +
				"		}\n" +
				"		personArray = new Person[5];\n" +
				"		personArray[0] = buildPerson_1_1();\n" +
				"		personArray[1] = buildPerson_5_1();\n" +
				"		personArray[2] = buildPerson_7_1();\n" +
				"		personArray[3] = buildPerson_9_1();\n" +
				"		personArray[4] = person_10_2;\n" +
				"		return personArray;\n" +
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
				"		person_2_2.setName( \"grandpa\" );\n" +
				"		return person_2_2;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_5_1;\n" +
				"\n" +
				"	protected Person buildPerson_5_1()\n" +
				"	{\n" +
				"		person_5_1 = new Person();\n" +
				"		person_5_1.setName( \"mom\" );\n" +
				"		return person_5_1;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_7_1;\n" +
				"\n" +
				"	protected Person buildPerson_7_1()\n" +
				"	{\n" +
				"		person_7_1 = new Person();\n" +
				"		person_7_1.setDad( person_1_1 );\n" +
				"		person_7_1.setMom( person_5_1 );\n" +
				"		person_7_1.setName( \"son\" );\n" +
				"		return person_7_1;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_9_1;\n" +
				"\n" +
				"	protected Person buildPerson_9_1()\n" +
				"	{\n" +
				"		person_9_1 = new Person();\n" +
				"		person_9_1.setDad( buildPerson_10_2() );\n" +
				"		person_9_1.setMom( buildPerson_13_2() );\n" +
				"		person_9_1.setName( \"son\" );\n" +
				"		return person_9_1;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_10_2;\n" +
				"\n" +
				"	protected Person buildPerson_10_2()\n" +
				"	{\n" +
				"		person_10_2 = new Person();\n" +
				"		person_10_2.setDad( buildPerson_11_3() );\n" +
				"		person_10_2.setName( \"dad2\" );\n" +
				"		return person_10_2;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_11_3;\n" +
				"\n" +
				"	protected Person buildPerson_11_3()\n" +
				"	{\n" +
				"		person_11_3 = new Person();\n" +
				"		person_11_3.setName( \"grandpa\" );\n" +
				"		return person_11_3;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_13_2;\n" +
				"\n" +
				"	protected Person buildPerson_13_2()\n" +
				"	{\n" +
				"		person_13_2 = new Person();\n" +
				"		person_13_2.setName( \"mom2\" );\n" +
				"		return person_13_2;\n" +
				"	}\n" +
				"\n" +
				"}\n" +
				"");
	}

	public void testArray_3()
	{

		Person joe = new Person();
		joe.setName( "Joe" );
		Person frank = new Person();
		frank.setName( "Frank" );

//		person2.setBirthday( GregorianCalendar.getInstance() );
//		person2.setAnniversary( new Date() );
		Person[] array = new Person[]{joe, frank, frank };
		recorder.record( array );
		assertResult( "package com.advancedpwr.record.examples.generated;\n" +
				"\n" +
				"import com.advancedpwr.record.examples.Person;\n" +
				"\n" +
        "@SuppressWarnings(\"all\")\n" +
				"public class PersonArrayFactory\n" +
				"{\n" +
				"\n" +
				"	protected Person[] personArray;\n" +
				"\n" +
				"	public Person[] buildPersonArray()\n" +
				"	{\n" +
				"		if ( personArray != null ) \n" +
				"		{\n" +
				"			return personArray;\n" +
				"		}\n" +
				"		personArray = new Person[3];\n" +
				"		personArray[0] = buildPerson_1_1();\n" +
				"		personArray[1] = buildPerson_3_1();\n" +
				"		personArray[2] = person_3_1;\n" +
				"		return personArray;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_1_1;\n" +
				"\n" +
				"	protected Person buildPerson_1_1()\n" +
				"	{\n" +
				"		person_1_1 = new Person();\n" +
				"		person_1_1.setName( \"Joe\" );\n" +
				"		return person_1_1;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_3_1;\n" +
				"\n" +
				"	protected Person buildPerson_3_1()\n" +
				"	{\n" +
				"		person_3_1 = new Person();\n" +
				"		person_3_1.setName( \"Frank\" );\n" +
				"		return person_3_1;\n" +
				"	}\n" +
				"\n" +
				"}\n");
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
		assertResult( "package com.advancedpwr.record.examples.generated;\n" +
				"\n" +
				"import com.advancedpwr.record.examples.Person;\n" +
				"\n" +
        "@SuppressWarnings(\"all\")\n" +
				"public class PersonArrayFactory\n" +
				"{\n" +
				"\n" +
				"	protected Person[] personArray;\n" +
				"\n" +
				"	public Person[] buildPersonArray()\n" +
				"	{\n" +
				"		if ( personArray != null ) \n" +
				"		{\n" +
				"			return personArray;\n" +
				"		}\n" +
				"		personArray = new Person[2];\n" +
				"		personArray[0] = buildPerson_1_1();\n" +
				"		personArray[1] = person_2_2;\n" +
				"		return personArray;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_1_1;\n" +
				"\n" +
				"	protected Person buildPerson_1_1()\n" +
				"	{\n" +
				"		person_1_1 = new Person();\n" +
				"		person_1_1.setDad( buildPerson_2_2() );\n" +
				"		person_1_1.setName( \"joe\" );\n" +
				"		return person_1_1;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_2_2;\n" +
				"\n" +
				"	protected Person buildPerson_2_2()\n" +
				"	{\n" +
				"		person_2_2 = new Person();\n" +
				"		person_2_2.setName( \"dad\" );\n" +
				"		return person_2_2;\n" +
				"	}\n" +
				"\n" +
				"}\n" +
				"" );
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
		assertResult( "package com.advancedpwr.record.examples.generated;\n" +
				"\n" +
				"import com.advancedpwr.record.examples.Person;\n" +
				"\n" +
        "@SuppressWarnings(\"all\")\n" +
				"public class PersonArrayFactory\n" +
				"{\n" +
				"\n" +
				"	protected Person[] personArray;\n" +
				"\n" +
				"	public Person[] buildPersonArray()\n" +
				"	{\n" +
				"		if ( personArray != null ) \n" +
				"		{\n" +
				"			return personArray;\n" +
				"		}\n" +
				"		personArray = new Person[2];\n" +
				"		personArray[0] = buildPerson_1_1();\n" +
				"		personArray[1] = person_2_2;\n" +
				"		return personArray;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_1_1;\n" +
				"\n" +
				"	protected Person buildPerson_1_1()\n" +
				"	{\n" +
				"		person_1_1 = new Person();\n" +
				"		person_1_1.setDad( buildPerson_2_2() );\n" +
				"		person_1_1.setName( \"joe\" );\n" +
				"		return person_1_1;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_2_2;\n" +
				"\n" +
				"	protected Person buildPerson_2_2()\n" +
				"	{\n" +
				"		person_2_2 = new Person();\n" +
				"		person_2_2.setDad( person_1_1 );\n" +
				"		person_2_2.setName( \"dad\" );\n" +
				"		return person_2_2;\n" +
				"	}\n" +
				"\n" +
				"}\n" +
				"");
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
		assertResult( "package com.advancedpwr.record.examples.generated;\n" +
				"\n" +
				"import com.advancedpwr.record.examples.Family;\n" +
				"import com.advancedpwr.record.examples.Person;\n" +
				"\n" +
        "@SuppressWarnings(\"all\")\n" +
				"public class FamilyArrayFactory\n" +
				"{\n" +
				"\n" +
				"	protected Family[] familyArray;\n" +
				"\n" +
				"	public Family[] buildFamilyArray()\n" +
				"	{\n" +
				"		if ( familyArray != null ) \n" +
				"		{\n" +
				"			return familyArray;\n" +
				"		}\n" +
				"		familyArray = new Family[2];\n" +
				"		familyArray[0] = buildFamily_1_1();\n" +
				"		familyArray[1] = buildFamily_8_1();\n" +
				"		return familyArray;\n" +
				"	}\n" +
				"\n" +
				"	protected Family family_1_1;\n" +
				"\n" +
				"	protected Family buildFamily_1_1()\n" +
				"	{\n" +
				"		family_1_1 = new Family();\n" +
				"		family_1_1.setDad( buildPerson_2_2() );\n" +
				"		family_1_1.setMom( buildPerson_6_2() );\n" +
				"		return family_1_1;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_2_2;\n" +
				"\n" +
				"	protected Person buildPerson_2_2()\n" +
				"	{\n" +
				"		person_2_2 = new Person();\n" +
				"		person_2_2.setDad( buildPerson_3_3() );\n" +
				"		person_2_2.setName( \"dad\" );\n" +
				"		return person_2_2;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_3_3;\n" +
				"\n" +
				"	protected Person buildPerson_3_3()\n" +
				"	{\n" +
				"		person_3_3 = new Person();\n" +
				"		person_3_3.setName( \"grandpa\" );\n" +
				"		return person_3_3;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_6_2;\n" +
				"\n" +
				"	protected Person buildPerson_6_2()\n" +
				"	{\n" +
				"		person_6_2 = new Person();\n" +
				"		person_6_2.setName( \"mom\" );\n" +
				"		return person_6_2;\n" +
				"	}\n" +
				"\n" +
				"	protected Family family_8_1;\n" +
				"\n" +
				"	protected Family buildFamily_8_1()\n" +
				"	{\n" +
				"		family_8_1 = new Family();\n" +
				"		family_8_1.setDad( person_3_3 );\n" +
				"		return family_8_1;\n" +
				"	}\n" +
				"\n" +
				"}\n" +
				"");
	}

	public void testArray_5()
	{
		StringArrayHolder holder = new StringArrayHolder();
		holder.setArray1( new String[]{"Joe", "Frank" } );
		holder.setArray2( new String[]{"Mary", "Jane" } );
		recorder.record( holder );
		assertResult( "package com.advancedpwr.record.examples.generated;\n" +
				"\n" +
				"import com.advancedpwr.record.examples.StringArrayHolder;\n" +
				"import java.lang.String;\n" +
				"\n" +
        "@SuppressWarnings(\"all\")\n" +
				"public class StringArrayHolderFactory\n" +
				"{\n" +
				"\n" +
				"	protected StringArrayHolder stringarrayholder;\n" +
				"\n" +
				"	public StringArrayHolder buildStringArrayHolder()\n" +
				"	{\n" +
				"		stringarrayholder = new StringArrayHolder();\n" +
				"		stringarrayholder.setArray1( buildStringArray_1_1() );\n" +
				"		stringarrayholder.setArray2( buildStringArray_4_1() );\n" +
				"		return stringarrayholder;\n" +
				"	}\n" +
				"\n" +
				"	protected String[] stringArray_1_1;\n" +
				"\n" +
				"	protected String[] buildStringArray_1_1()\n" +
				"	{\n" +
				"		if ( stringArray_1_1 != null ) \n" +
				"		{\n" +
				"			return stringArray_1_1;\n" +
				"		}\n" +
				"		stringArray_1_1 = new String[2];\n" +
				"		stringArray_1_1[0] = \"Joe\";\n" +
				"		stringArray_1_1[1] = \"Frank\";\n" +
				"		return stringArray_1_1;\n" +
				"	}\n" +
				"\n" +
				"	protected String[] stringArray_4_1;\n" +
				"\n" +
				"	protected String[] buildStringArray_4_1()\n" +
				"	{\n" +
				"		if ( stringArray_4_1 != null ) \n" +
				"		{\n" +
				"			return stringArray_4_1;\n" +
				"		}\n" +
				"		stringArray_4_1 = new String[2];\n" +
				"		stringArray_4_1[0] = \"Mary\";\n" +
				"		stringArray_4_1[1] = \"Jane\";\n" +
				"		return stringArray_4_1;\n" +
				"	}\n" +
				"\n" +
				"}\n");
	}

	public void testArray_6() throws Exception
	{
		Person joe = new Person();
		joe.setName( "joe" );

		Person dad = new Person();
		dad.setName( "dad" );
		Date anniversary = new SimpleDateFormat( "MM/dd/yyyy" ).parse( "12/4/2000");
		dad.setAnniversary( anniversary );
		joe.setDad( dad );

		Person[] array = new Person[]{ joe, dad };
		recorder.record( array );
		assertResult( "package com.advancedpwr.record.examples.generated;\n" +
				"\n" +
				"import com.advancedpwr.record.examples.Person;\n" +
				"import java.util.Date;\n" +
				"\n" +
        "@SuppressWarnings(\"all\")\n" +
				"public class PersonArrayFactory\n" +
				"{\n" +
				"\n" +
				"	protected Person[] personArray;\n" +
				"\n" +
				"	public Person[] buildPersonArray() throws Exception\n" +
				"	{\n" +
				"		if ( personArray != null ) \n" +
				"		{\n" +
				"			return personArray;\n" +
				"		}\n" +
				"		personArray = new Person[2];\n" +
				"		personArray[0] = buildPerson_1_1();\n" +
				"		personArray[1] = person_2_2;\n" +
				"		return personArray;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_1_1;\n" +
				"\n" +
				"	protected Person buildPerson_1_1() throws Exception\n" +
				"	{\n" +
				"		person_1_1 = new Person();\n" +
				"		person_1_1.setDad( buildPerson_2_2() );\n" +
				"		person_1_1.setName( \"joe\" );\n" +
				"		return person_1_1;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_2_2;\n" +
				"\n" +
				"	protected Person buildPerson_2_2() throws Exception\n" +
				"	{\n" +
				"		person_2_2 = new Person();\n" +
				"		person_2_2.setAnniversary( buildDate_3_3() );\n" +
				"		person_2_2.setName( \"dad\" );\n" +
				"		return person_2_2;\n" +
				"	}\n" +
				"\n" +
				"	protected Date date_3_3;\n" +
				"\n" +
				"	protected Date buildDate_3_3()\n" +
				"	{\n" +
				"		date_3_3 = new Date( 975906000000l );\n" +
				"		return date_3_3;\n" +
				"	}\n" +
				"\n" +
				"}\n" +
				"" );
	}
}
