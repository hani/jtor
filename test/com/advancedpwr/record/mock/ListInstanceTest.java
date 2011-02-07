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

import java.util.ArrayList;
import java.util.List;

import com.advancedpwr.record.examples.Person;

public class ListInstanceTest extends AbstractMockRecorderTest
{
	@Override
	protected void setUp()
	{
		// TODO Auto-generated method stub
		super.setUp();
	}

	public void testListInstance()
	{
		Person dad = new Person();
		dad.setName( "dad" );
		
		Person jack = new Person();
		jack.setName( "jack" );
		jack.setDad( dad );
		
		Person jill = new Person();
		jill.setName( "Jill" );
		jill.setDad( dad );
		
		dad.setMom( jill );
		
		Person joe = new Person();
		joe.setName( "joe" );
		joe.setDad( dad );
		joe.setMom( jill );
		
		List list = new ArrayList();
		list.add( jack );
		list.add( jill );
		list.add( joe );
		dad.setChildren( list );
		
		dad = recorder.record( dad );
		
		List children = dad.getChildren();
		
		Person child1 = (Person)children.get( 0 );
		assertEquals( "jack", child1.getName() );
		Person dadTest = child1.getDad();
//		assertEquals( dad, child1.getDad() );
		
		recorder.endRecording();
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.Person;\n" + 
				"import java.util.ArrayList;\n" + 
				"import com.advancedpwr.record.mock.MockFactory;\n" + 
				"\n" + 
				"public class PersonFactory extends MockFactory\n" + 
				"{\n" + 
				"\n" + 
				"	protected Person person;\n" + 
				"\n" + 
				"	public Person buildPerson()\n" + 
				"	{\n" + 
				"		if ( person != null ) \n" + 
				"		{\n" + 
				"			return person;\n" + 
				"		}\n" + 
				"		person = createStrictMock( Person.class );\n" + 
				"		expect( person.getChildren() ).andReturn( buildArrayList_1_1() );\n" + 
				"		replay( person );\n" + 
				"		return person;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected ArrayList arraylist_1_1;\n" + 
				"\n" + 
				"	protected ArrayList buildArrayList_1_1()\n" + 
				"	{\n" + 
				"		if ( arraylist_1_1 != null ) \n" + 
				"		{\n" + 
				"			return arraylist_1_1;\n" + 
				"		}\n" + 
				"		arraylist_1_1 = createStrictMock( ArrayList.class );\n" + 
				"		expect( arraylist_1_1.get( 0 ) ).andReturn( buildPerson_2_2() );\n" + 
				"		replay( arraylist_1_1 );\n" + 
				"		return arraylist_1_1;\n" + 
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
				"		expect( person_2_2.getName() ).andReturn( \"jack\" );\n" + 
				"		expect( person_2_2.getDad() ).andReturn( person );\n" + 
				"		replay( person_2_2 );\n" + 
				"		return person_2_2;\n" + 
				"	}\n" + 
				"}\n" );
	}
}
