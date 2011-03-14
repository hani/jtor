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

import com.advancedpwr.record.SimpleClassDescriptor;
import com.advancedpwr.record.examples.Being;
import com.advancedpwr.record.examples.Family;
import com.advancedpwr.record.examples.Person;
//import com.advancedpwr.record.examples.generated.PersonFactory;

public class MockBehaviorRecorderTest extends AbstractMockRecorderTest
{
	
	protected void setUp()
	{
		super.setUp();
	}
	
	public void testRecord()
	{
		SimpleClassDescriptor descriptor = new SimpleClassDescriptor();
		descriptor.setClassName( "MockPersonFactory" );
		descriptor.setPackageName( "com.advancedpwr.record.mock.generated" );
		recorder.setDescriptor( descriptor );
		
		Person person = new Person();
		
		person = recorder.record( person );
		
		person.setName( "Joe" );
		person.getName();
		
		person = (Person)recorder.endRecording();
		
		assertResult( "package com.advancedpwr.record.mock.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.Person;\n" + 
				"import com.advancedpwr.record.mock.MockFactory;\n" + 
				"\n" + 
				"public class MockPersonFactory extends MockFactory\n" + 
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
				"		person.setName( \"Joe\" );\n" + 
				"		expect( person.getName() ).andReturn( \"Joe\" );\n" + 
				"		replay( person );\n" + 
				"		return person;\n" + 
				"	}\n" + 
				"}\n");
	}

	
	public void testRecord_2()
	{
		SimpleClassDescriptor descriptor = new SimpleClassDescriptor();
		descriptor.setClassName( "MockPersonFactory" );
		descriptor.setPackageName( "com.advancedpwr.record.mock.generated" );
		recorder.setDescriptor( descriptor );
		
		Person person = new Person();
		
		Person dad = new Person();
		dad.setName( "dad" );
		person.setDad( dad );
		
		person = recorder.record( person );
		
		person.setName( "Joe" );
		assertEquals( "Joe", person.getName() );
		assertEquals( "dad", person.getDad().getName() );
		assertEquals( "dad", person.getDad().getName() );
		
		Person mom = new Person();
		mom.setName( "mom" );
		person.setMom( mom );
		
		assertEquals( "mom", person.getMom().getName() );
		
		person.setMom( mom );
		
		person.setMom( null );
		assertNull( person.getMom() );
		
		person = (Person)recorder.endRecording();
		
		assertResult( "package com.advancedpwr.record.mock.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.Person;\n" + 
				"import com.advancedpwr.record.mock.MockFactory;\n" + 
				"\n" + 
				"public class MockPersonFactory extends MockFactory\n" + 
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
				"		person.setName( \"Joe\" );\n" + 
				"		expect( person.getName() ).andReturn( \"Joe\" );\n" + 
				"		expect( person.getDad() ).andReturn( buildPerson_3_1() );\n" + 
				"		expect( person.getDad() ).andReturn( buildPerson_3_1() );\n" + 
				"		person.setMom( buildPerson_5_1() );\n" + 
				"		expect( person.getMom() ).andReturn( buildPerson_5_1() );\n" + 
				"		person.setMom( buildPerson_5_1() );\n" + 
				"		person.setMom( null );\n" + 
				"		expect( person.getMom() ).andReturn( null );\n" + 
				"		replay( person );\n" + 
				"		return person;\n" + 
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
				"		expect( person_3_1.getName() ).andReturn( \"dad\" );\n" + 
				"		expect( person_3_1.getName() ).andReturn( \"dad\" );\n" + 
				"		replay( person_3_1 );\n" + 
				"		return person_3_1;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person person_5_1;\n" + 
				"\n" + 
				"	protected Person buildPerson_5_1()\n" + 
				"	{\n" + 
				"		if ( person_5_1 != null ) \n" + 
				"		{\n" + 
				"			return person_5_1;\n" + 
				"		}\n" + 
				"		person_5_1 = createStrictMock( Person.class );\n" + 
				"		expect( person_5_1.getName() ).andReturn( \"mom\" );\n" + 
				"		replay( person_5_1 );\n" + 
				"		return person_5_1;\n" + 
				"	}\n" + 
				"}\n" + 
				"" );
	}
	
	public void testRecord_3()
	{
		
		Person person = Person.createExamplePerson();
		
		person = recorder.record( person );
		
		Person gramps = person.getDad().getDad();
		
		assertEquals( "grandpa", gramps.getName() );
		gramps.setName( "Grandpa Joe" );
		assertEquals( "Grandpa Joe", gramps.getName() );
		
		person = (Person)recorder.endRecording();
		
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.Person;\n" + 
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
				"		expect( person.getDad() ).andReturn( buildPerson_1_1() );\n" + 
				"		replay( person );\n" + 
				"		return person;\n" + 
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
				"		expect( person_1_1.getDad() ).andReturn( buildPerson_2_2() );\n" + 
				"		replay( person_1_1 );\n" + 
				"		return person_1_1;\n" + 
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
				"		expect( person_2_2.getName() ).andReturn( \"grandpa\" );\n" + 
				"		person_2_2.setName( \"Grandpa Joe\" );\n" + 
				"		expect( person_2_2.getName() ).andReturn( \"Grandpa Joe\" );\n" + 
				"		replay( person_2_2 );\n" + 
				"		return person_2_2;\n" + 
				"	}\n" + 
				"}\n" );
	}
	
	public void testRecord_4()
	{
		
		Person person = Person.createExamplePerson();
		Family family = new Family(person.getDad(), person.getMom());
		family = recorder.record( family );
		
		family.setParents( person.getDad(), person.getMom() ); 
		
		recorder.endRecording();
		
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.Family;\n" + 
				"import com.advancedpwr.record.examples.Person;\n" + 
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
				"		family.setParents( buildPerson_1_1(),buildPerson_2_1() );\n" + 
				"		replay( family );\n" + 
				"		return family;\n" + 
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
				"		replay( person_2_1 );\n" + 
				"		return person_2_1;\n" + 
				"	}\n" + 
				"}\n");
	}
	
	public void testRecord_5()
	{
		
		Person person = Person.createExamplePerson();
		Family family = new Family(person.getDad(), person.getMom());
		family = recorder.record( family );
		
		try
		{
			family.fued();
		}
		catch ( Exception e )
		{
			
		} 
		
		recorder.endRecording();
		
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.Family;\n" + 
				"import com.advancedpwr.record.mock.MockFactory;\n" + 
				"\n" + 
				"public class FamilyFactory extends MockFactory\n" + 
				"{\n" + 
				"\n" + 
				"	protected Family family;\n" + 
				"\n" + 
				"	public Family buildFamily() throws Exception\n" + 
				"	{\n" + 
				"		if ( family != null ) \n" + 
				"		{\n" + 
				"			return family;\n" + 
				"		}\n" + 
				"		family = createStrictMock( Family.class );\n" + 
				"		family.fued();\n" + 
				"		expectLastCall().andThrow( buildException_1_1() );\n" + 
				"		replay( family );\n" + 
				"		return family;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Exception exception_1_1;\n" + 
				"\n" + 
				"	protected Exception buildException_1_1()\n" + 
				"	{\n" + 
				"		if ( exception_1_1 != null ) \n" + 
				"		{\n" + 
				"			return exception_1_1;\n" + 
				"		}\n" + 
				"		exception_1_1 = createStrictMock( Exception.class );\n" + 
				"		replay( exception_1_1 );\n" + 
				"		return exception_1_1;\n" + 
				"	}\n" + 
				"}\n");
	}
	
	public void testRecord_6()
	{
		
		Person person = Person.createExamplePerson();
		Family family = new Family(person.getDad(), person.getMom());
		family = recorder.record( family );
		
		try
		{
			family.fued( new Person() );
		}
		catch ( Exception e )
		{
			
		} 
		
		recorder.endRecording();
		
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.Family;\n" + 
				"import com.advancedpwr.record.examples.Person;\n" + 
				"import com.advancedpwr.record.mock.MockFactory;\n" + 
				"\n" + 
				"public class FamilyFactory extends MockFactory\n" + 
				"{\n" + 
				"\n" + 
				"	protected Family family;\n" + 
				"\n" + 
				"	public Family buildFamily() throws Exception\n" + 
				"	{\n" + 
				"		if ( family != null ) \n" + 
				"		{\n" + 
				"			return family;\n" + 
				"		}\n" + 
				"		family = createStrictMock( Family.class );\n" + 
				"		expect( family.fued( buildPerson_1_1() ) ).andThrow( buildException_4_1() );\n" + 
				"		replay( family );\n" + 
				"		return family;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Exception exception_4_1;\n" + 
				"\n" + 
				"	protected Exception buildException_4_1()\n" + 
				"	{\n" + 
				"		if ( exception_4_1 != null ) \n" + 
				"		{\n" + 
				"			return exception_4_1;\n" + 
				"		}\n" + 
				"		exception_4_1 = createStrictMock( Exception.class );\n" + 
				"		replay( exception_4_1 );\n" + 
				"		return exception_4_1;\n" + 
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
				"		person_1_1.setName( \"jerk\" );\n" + 
				"		replay( person_1_1 );\n" + 
				"		return person_1_1;\n" + 
				"	}\n" + 
				"}\n");
	}
	// This is a known issue and I'm not sure how to handle it correctly.
	public void XtestRecordRestricted() 
	{
		Person person = Person.createExamplePerson();
		Family family = new Family();
		family.setDad( person );
		family = recorder.record( family );
		
		family.getDad().getRestricted();
		family.getDad().callSynced();
		
		recorder.endRecording();
		
		assertResult(  "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.Family;\n" + 
				"import com.advancedpwr.record.examples.Person;\n" + 
				"import com.advancedpwr.record.examples.Restricted$1;\n" + 
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
				"		expect( family.getDad() ).andReturn( buildPerson_1_1() );\n" + 
				"		expect( family.getDad() ).andReturn( buildPerson_1_1() );\n" + 
				"		replay( family );\n" + 
				"		return family;\n" + 
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
				"		person_1_1.callSynced();\n" + 
				"		replay( person_1_1 );\n" + 
				"		return person_1_1;\n" + 
				"	}\n" + 
				"\n" + 
				"}\n");
	}
	
	public void testEquals()
	{
		Person person = Person.createExamplePerson();	
		Person proxy = recorder.record( person );
		assertTrue( proxy.equals( person ) );
	}
	
	public void testHashCode()
	{
		Person person = Person.createExamplePerson();
		
		Person proxy = recorder.record( person );
		assertEquals( person.hashCode(), proxy.hashCode() );
	}
	
	public void testInstrument()
	{
		Person person = Person.createExamplePerson();
		recorder.instrument( person );
	}
	
	public void testAddPreferredInterface()
	{
		recorder.addPreferredInterface( Being.class );
		Person person = Person.createExamplePerson();
		
		Being being = recorder.record( person );
		
		being.getDad();
		being.getMom();
		
		recorder.endRecording();
		
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.Being;\n" + 
				"import com.advancedpwr.record.mock.MockFactory;\n" + 
				"\n" + 
				"public class PersonFactory extends MockFactory\n" + 
				"{\n" + 
				"\n" + 
				"	protected Being being;\n" + 
				"\n" + 
				"	public Being buildBeing()\n" + 
				"	{\n" + 
				"		if ( being != null ) \n" + 
				"		{\n" + 
				"			return being;\n" + 
				"		}\n" + 
				"		being = createStrictMock( Being.class );\n" + 
				"		expect( being.getDad() ).andReturn( buildBeing_1_1() );\n" + 
				"		expect( being.getMom() ).andReturn( buildBeing_2_1() );\n" + 
				"		replay( being );\n" + 
				"		return being;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Being being_1_1;\n" + 
				"\n" + 
				"	protected Being buildBeing_1_1()\n" + 
				"	{\n" + 
				"		if ( being_1_1 != null ) \n" + 
				"		{\n" + 
				"			return being_1_1;\n" + 
				"		}\n" + 
				"		being_1_1 = createStrictMock( Being.class );\n" + 
				"		replay( being_1_1 );\n" + 
				"		return being_1_1;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Being being_2_1;\n" + 
				"\n" + 
				"	protected Being buildBeing_2_1()\n" + 
				"	{\n" + 
				"		if ( being_2_1 != null ) \n" + 
				"		{\n" + 
				"			return being_2_1;\n" + 
				"		}\n" + 
				"		being_2_1 = createStrictMock( Being.class );\n" + 
				"		replay( being_2_1 );\n" + 
				"		return being_2_1;\n" + 
				"	}\n" + 
				"}\n" + 
				"" );
	}
	
	public void XtestRecordJavadocExample()
	{
		MockBehaviorRecorder recorder = new MockBehaviorRecorder();
		recorder.setDestination( "generated" );
		
		Person person = new Person();
		
		Person dad = new Person();
		dad.setName( "dad" );
		person.setDad( dad );
		
		person = recorder.record( person );
		
		person.setName( "Joe" );
		assertEquals( "Joe", person.getName() );
		assertEquals( "dad", person.getDad().getName() );
		assertEquals( "dad", person.getDad().getName() );
		
		Person mom = new Person();
		mom.setName( "mom" );
		person.setMom( mom );
		
		recorder.endRecording();
		
	}
	
//	public void XtestPlaybackJavadocExample()
//	{
//		Person person = new PersonFactory().buildPerson();
//		
//		person.setName( "Joe" );
//		assertEquals( "Joe", person.getName() );
//		assertEquals( "dad", person.getDad().getName() );
//		assertEquals( "dad", person.getDad().getName() );
//		
//		Person mom = new Person();
//		mom.setName( "mom" );
//		person.setMom( mom );
//	}
}
