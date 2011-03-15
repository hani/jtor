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
package com.advancedpwr.record.mock;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.advancedpwr.record.examples.Family;
import com.advancedpwr.record.examples.People;
import com.advancedpwr.record.examples.Person;

public class ArrayTest extends AbstractMockRecorderTest
{
	protected void setUp()
	{
		// TODO Auto-generated method stub
		super.setUp();
	}
	
	public void testArray()
	{
		Person person = Person.createExamplePerson();
		Person[] array = new Person[]{person.getDad(), person.getMom(), person };
		
		array = recorder.record( array );
		assertEquals( "mom", array[1].getName() );
		recorder.endRecording();
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.Person;\n" + 
				"import com.advancedpwr.record.mock.MockFactory;\n" + 
				"\n" + 
				"public class PersonArrayFactory extends MockFactory\n" + 
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
				"		personArray[1] = buildPerson_2_1();\n" + 
				"		personArray[2] = buildPerson_3_1();\n" + 
				"		return personArray;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person person_1_1;\n" + 
				"\n" + 
				"	protected Person buildPerson_1_1()\n" + 
				"	{\n" + 
				"		if ( person_1_1 != null ) \n" + 
				"		{\n" + 
				"			return person_1_1;\n" + 
				"		}\n" + 
				"		person_1_1 = createStrictMock( Person.class );\n" + 
				"		replay( person_1_1 );\n" + 
				"		return person_1_1;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person person_2_1;\n" + 
				"\n" + 
				"	protected Person buildPerson_2_1()\n" + 
				"	{\n" + 
				"		if ( person_2_1 != null ) \n" + 
				"		{\n" + 
				"			return person_2_1;\n" + 
				"		}\n" + 
				"		person_2_1 = createStrictMock( Person.class );\n" + 
				"		expect( person_2_1.getName() ).andReturn( \"mom\" );\n" + 
				"		replay( person_2_1 );\n" + 
				"		return person_2_1;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person person_3_1;\n" + 
				"\n" + 
				"	protected Person buildPerson_3_1()\n" + 
				"	{\n" + 
				"		if ( person_3_1 != null ) \n" + 
				"		{\n" + 
				"			return person_3_1;\n" + 
				"		}\n" + 
				"		person_3_1 = createStrictMock( Person.class );\n" + 
				"		replay( person_3_1 );\n" + 
				"		return person_3_1;\n" + 
				"	}\n" + 
				"}\n");
	}
	
	public void testArray_argument()
	{
		Person person = Person.createExamplePerson();
		Family family = new Family(person.getDad(), person.getMom());
		family = recorder.record( family );
		Person[] children = family.people( new String[]{ "Frank", "Joe" } );
		assertEquals( "Frank", children[0].getName() );
		assertEquals( "Joe", children[1].getName() );
		
		recorder.endRecording();
		
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.Family;\n" + 
				"import com.advancedpwr.record.examples.Person;\n" + 
				"import java.lang.String;\n" + 
				"import com.advancedpwr.record.mock.MockFactory;\n" + 
				"\n" + 
				"public class FamilyFactory extends MockFactory\n" + 
				"{\n" + 
				"\n" + 
				"	protected Family family;\n" + 
				"\n" + 
				"	public Family buildFamily()\n" + 
				"	{\n" + 
				"		if ( family != null ) \n" + 
				"		{\n" + 
				"			return family;\n" + 
				"		}\n" + 
				"		family = createStrictMock( Family.class );\n" + 
				"		expect( family.people( buildStringArray_4_1() ) ).andReturn( buildPersonArray_1_1() );\n" + 
				"		replay( family );\n" + 
				"		return family;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person[] personArray_1_1;\n" + 
				"\n" + 
				"	protected Person[] buildPersonArray_1_1()\n" + 
				"	{\n" + 
				"		if ( personArray_1_1 != null ) \n" + 
				"		{\n" + 
				"			return personArray_1_1;\n" + 
				"		}\n" + 
				"		personArray_1_1 = new Person[2];\n" + 
				"		personArray_1_1[0] = buildPerson_2_2();\n" + 
				"		personArray_1_1[1] = buildPerson_3_2();\n" + 
				"		return personArray_1_1;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person person_2_2;\n" + 
				"\n" + 
				"	protected Person buildPerson_2_2()\n" + 
				"	{\n" + 
				"		if ( person_2_2 != null ) \n" + 
				"		{\n" + 
				"			return person_2_2;\n" + 
				"		}\n" + 
				"		person_2_2 = createStrictMock( Person.class );\n" + 
				"		expect( person_2_2.getName() ).andReturn( \"Frank\" );\n" + 
				"		replay( person_2_2 );\n" + 
				"		return person_2_2;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person person_3_2;\n" + 
				"\n" + 
				"	protected Person buildPerson_3_2()\n" + 
				"	{\n" + 
				"		if ( person_3_2 != null ) \n" + 
				"		{\n" + 
				"			return person_3_2;\n" + 
				"		}\n" + 
				"		person_3_2 = createStrictMock( Person.class );\n" + 
				"		expect( person_3_2.getName() ).andReturn( \"Joe\" );\n" + 
				"		replay( person_3_2 );\n" + 
				"		return person_3_2;\n" + 
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
				"		stringArray_4_1[0] = \"Frank\";\n" + 
				"		stringArray_4_1[1] = \"Joe\";\n" + 
				"		return stringArray_4_1;\n" + 
				"	}\n" + 
				"}\n");
	}
	
	public void testArray_null_element()
	{
		Person person = Person.createExamplePerson();
		Family family = new Family(person.getDad(), person.getMom());
		family = recorder.record( family );
		Person[] children = family.people( new String[]{ "Frank", null } );
		assertEquals( 2, children.length );
		assertEquals( "Frank", children[0].getName() );
	
		recorder.endRecording();
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.Family;\n" + 
				"import com.advancedpwr.record.examples.Person;\n" + 
				"import java.lang.String;\n" + 
				"import com.advancedpwr.record.mock.MockFactory;\n" + 
				"\n" + 
				"public class FamilyFactory extends MockFactory\n" + 
				"{\n" + 
				"\n" + 
				"	protected Family family;\n" + 
				"\n" + 
				"	public Family buildFamily()\n" + 
				"	{\n" + 
				"		if ( family != null ) \n" + 
				"		{\n" + 
				"			return family;\n" + 
				"		}\n" + 
				"		family = createStrictMock( Family.class );\n" + 
				"		expect( family.people( buildStringArray_4_1() ) ).andReturn( buildPersonArray_1_1() );\n" + 
				"		replay( family );\n" + 
				"		return family;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person[] personArray_1_1;\n" + 
				"\n" + 
				"	protected Person[] buildPersonArray_1_1()\n" + 
				"	{\n" + 
				"		if ( personArray_1_1 != null ) \n" + 
				"		{\n" + 
				"			return personArray_1_1;\n" + 
				"		}\n" + 
				"		personArray_1_1 = new Person[2];\n" + 
				"		personArray_1_1[0] = buildPerson_2_2();\n" + 
				"		personArray_1_1[1] = null;\n" + 
				"		return personArray_1_1;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person person_2_2;\n" + 
				"\n" + 
				"	protected Person buildPerson_2_2()\n" + 
				"	{\n" + 
				"		if ( person_2_2 != null ) \n" + 
				"		{\n" + 
				"			return person_2_2;\n" + 
				"		}\n" + 
				"		person_2_2 = createStrictMock( Person.class );\n" + 
				"		expect( person_2_2.getName() ).andReturn( \"Frank\" );\n" + 
				"		replay( person_2_2 );\n" + 
				"		return person_2_2;\n" + 
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
				"		stringArray_4_1[0] = \"Frank\";\n" + 
				"		stringArray_4_1[1] = null;\n" + 
				"		return stringArray_4_1;\n" + 
				"	}\n" + 
				"}\n" + 
				"");
	}
	
	public void testArray_exception()
	{
		Person person = Person.createExamplePerson();
		Family family = new Family(person.getDad(), person.getMom());
		Family family2 = new Family( person.getDad(), new Person() );
		Family[] families = new Family[]{ family, family2 };
		families = recorder.record( families );
		
		try
		{
			families[0].fued();
		}
		catch ( Exception e )
		{
			e.getMessage();
		}
		
		recorder.endRecording();
		
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.Family;\n" + 
				"import com.advancedpwr.record.mock.MockFactory;\n" + 
				"\n" + 
				"public class FamilyArrayFactory extends MockFactory\n" + 
				"{\n" + 
				"\n" + 
				"	protected Family[] familyArray;\n" + 
				"\n" + 
				"	public Family[] buildFamilyArray() throws Exception\n" + 
				"	{\n" + 
				"		if ( familyArray != null ) \n" + 
				"		{\n" + 
				"			return familyArray;\n" + 
				"		}\n" + 
				"		familyArray = new Family[2];\n" + 
				"		familyArray[0] = buildFamily_1_1();\n" + 
				"		familyArray[1] = buildFamily_2_1();\n" + 
				"		return familyArray;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Family family_1_1;\n" + 
				"\n" + 
				"	protected Family buildFamily_1_1() throws Exception\n" + 
				"	{\n" + 
				"		if ( family_1_1 != null ) \n" + 
				"		{\n" + 
				"			return family_1_1;\n" + 
				"		}\n" + 
				"		family_1_1 = createStrictMock( Family.class );\n" + 
				"		family_1_1.fued();\n" + 
				"		expectLastCall().andThrow( buildException_3_2() );\n" + 
				"		replay( family_1_1 );\n" + 
				"		return family_1_1;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Exception exception_3_2;\n" + 
				"\n" + 
				"	protected Exception buildException_3_2()\n" + 
				"	{\n" + 
				"		if ( exception_3_2 != null ) \n" + 
				"		{\n" + 
				"			return exception_3_2;\n" + 
				"		}\n" + 
				"		exception_3_2 = createStrictMock( Exception.class );\n" + 
				"		replay( exception_3_2 );\n" + 
				"		return exception_3_2;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Family family_2_1;\n" + 
				"\n" + 
				"	protected Family buildFamily_2_1()\n" + 
				"	{\n" + 
				"		if ( family_2_1 != null ) \n" + 
				"		{\n" + 
				"			return family_2_1;\n" + 
				"		}\n" + 
				"		family_2_1 = createStrictMock( Family.class );\n" + 
				"		replay( family_2_1 );\n" + 
				"		return family_2_1;\n" + 
				"	}\n" + 
				"}\n" + 
				"");
		
	}
	
	public void testArray_exception_2() throws Exception
	{
		Person person = Person.createExamplePerson();
		Person[] folks = new Person[]{ person.getDad(), person.getMom() };
		People people = new People();
		people.setPersons( folks );
		
		people = recorder.record( people );
		
		Date anniversary = new SimpleDateFormat( "MM/dd/yyyy" ).parse( "12/4/2000");
		people.getPersons()[0].setAnniversary( anniversary );
		
		recorder.endRecording();
		
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.People;\n" + 
				"import com.advancedpwr.record.examples.Person;\n" + 
				"import java.util.Date;\n" + 
				"import com.advancedpwr.record.mock.MockFactory;\n" + 
				"\n" + 
				"public class PeopleFactory extends MockFactory\n" + 
				"{\n" + 
				"\n" + 
				"	protected People people;\n" + 
				"\n" + 
				"	public People buildPeople() throws Exception\n" + 
				"	{\n" + 
				"		if ( people != null ) \n" + 
				"		{\n" + 
				"			return people;\n" + 
				"		}\n" + 
				"		people = createStrictMock( People.class );\n" + 
				"		expect( people.getPersons() ).andReturn( buildPersonArray_1_1() );\n" + 
				"		replay( people );\n" + 
				"		return people;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person[] personArray_1_1;\n" + 
				"\n" + 
				"	protected Person[] buildPersonArray_1_1() throws Exception\n" + 
				"	{\n" + 
				"		if ( personArray_1_1 != null ) \n" + 
				"		{\n" + 
				"			return personArray_1_1;\n" + 
				"		}\n" + 
				"		personArray_1_1 = new Person[2];\n" + 
				"		personArray_1_1[0] = buildPerson_2_2();\n" + 
				"		personArray_1_1[1] = buildPerson_3_2();\n" + 
				"		return personArray_1_1;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person person_2_2;\n" + 
				"\n" + 
				"	protected Person buildPerson_2_2() throws Exception\n" + 
				"	{\n" + 
				"		if ( person_2_2 != null ) \n" + 
				"		{\n" + 
				"			return person_2_2;\n" + 
				"		}\n" + 
				"		person_2_2 = createStrictMock( Person.class );\n" + 
				"		person_2_2.setAnniversary( buildDate_4_1() );\n" + 
				"		replay( person_2_2 );\n" + 
				"		return person_2_2;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Date date_4_1;\n" + 
				"\n" + 
				"	protected Date buildDate_4_1()\n" + 
				"	{\n" + 
				"		date_4_1 = new Date( 975906000000l );\n" + 
				"		return date_4_1;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person person_3_2;\n" + 
				"\n" + 
				"	protected Person buildPerson_3_2()\n" + 
				"	{\n" + 
				"		if ( person_3_2 != null ) \n" + 
				"		{\n" + 
				"			return person_3_2;\n" + 
				"		}\n" + 
				"		person_3_2 = createStrictMock( Person.class );\n" + 
				"		replay( person_3_2 );\n" + 
				"		return person_3_2;\n" + 
				"	}\n" + 
				"}\n");
		
	}
	
	public void testArrayPrimitive()
	{
		int[] array = { 5, 4, 3, 2, 1};
		int[] newArray = recorder.record( array );
		assertEquals( 4, newArray[1]);
	}
	
	public void testCanInstrumentArray()
	{
		int[] array = { 5, 4, 3, 2, 1};
		assertFalse( recorder.canInstrumentArray( null ) );
		assertFalse( recorder.canInstrumentArray( array ) );
		Person person = Person.createExamplePerson();
		Person[] folks = new Person[]{ person.getDad(), person.getMom() };
		assertTrue( recorder.canInstrumentArray( folks ) );
		
	}
}
