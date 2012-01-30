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

import java.util.LinkedHashMap;
import java.util.Map;

import com.advancedpwr.record.examples.MapContainer;
import com.advancedpwr.record.examples.Person;

public class MapTest extends AbstractMockRecorderTest
{
	protected void setUp()
	{
		// TODO Auto-generated method stub
		super.setUp();
	}
	
	public void testMap()
	{
		Person person = Person.createExamplePerson();

		Person cousin = new Person();
		cousin.setName( "cousin" );
		
		Map family = new LinkedHashMap();
		family.put( person.getName(), person );
		family.put( person.getDad().getName(), person.getDad() );
		family.put( person.getMom().getName(), person.getMom() );
		family.put( person, cousin );
		
		MapContainer container = new MapContainer();
		container.setPerson( person );
		container.setMap( family );
		container = recorder.record( container );
		
		person = container.getPerson();
		
		container.getMap().get( person.getDad().getName() );
		container.getMap().get( person.getMom().getName() );
		
		recorder.endRecording();
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.MapContainer;\n" + 
				"import com.advancedpwr.record.examples.Person;\n" + 
				"import java.util.LinkedHashMap;\n" + 
				"import com.advancedpwr.record.mock.MockFactory;\n" + 
				"\n" + 
				"public class MapContainerFactory extends MockFactory\n" + 
				"{\n" + 
				"\n" + 
				"	protected MapContainer mapcontainer;\n" + 
				"\n" + 
				"	public MapContainer buildMapContainer()\n" + 
				"	{\n" + 
				"		if ( mapcontainer != null ) \n" + 
				"		{\n" + 
				"			return mapcontainer;\n" + 
				"		}\n" + 
				"		mapcontainer = createStrictMock( MapContainer.class );\n" + 
				"		expect( mapcontainer.getPerson() ).andReturn( buildPerson_1_1() );\n" + 
				"		expect( mapcontainer.getMap() ).andReturn( buildLinkedHashMap_2_1() );\n" + 
				"		expect( mapcontainer.getMap() ).andReturn( buildLinkedHashMap_2_1() );\n" + 
				"		replay( mapcontainer );\n" + 
				"		return mapcontainer;\n" + 
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
				"		expect( person_1_1.getDad() ).andReturn( buildPerson_3_2() );\n" + 
				"		expect( person_1_1.getMom() ).andReturn( buildPerson_5_2() );\n" + 
				"		replay( person_1_1 );\n" + 
				"		return person_1_1;\n" + 
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
				"		expect( person_3_2.getName() ).andReturn( \"dad\" );\n" + 
				"		replay( person_3_2 );\n" + 
				"		return person_3_2;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person person_5_2;\n" + 
				"\n" + 
				"	protected Person buildPerson_5_2()\n" + 
				"	{\n" + 
				"		if ( person_5_2 != null ) \n" + 
				"		{\n" + 
				"			return person_5_2;\n" + 
				"		}\n" + 
				"		person_5_2 = createStrictMock( Person.class );\n" + 
				"		expect( person_5_2.getName() ).andReturn( \"mom\" );\n" + 
				"		replay( person_5_2 );\n" + 
				"		return person_5_2;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected LinkedHashMap linkedhashmap_2_1;\n" + 
				"\n" + 
				"	protected LinkedHashMap buildLinkedHashMap_2_1()\n" + 
				"	{\n" + 
				"		if ( linkedhashmap_2_1 != null ) \n" + 
				"		{\n" + 
				"			return linkedhashmap_2_1;\n" + 
				"		}\n" + 
				"		linkedhashmap_2_1 = createStrictMock( LinkedHashMap.class );\n" + 
				"		expect( linkedhashmap_2_1.get( \"dad\" ) ).andReturn( person_3_2 );\n" + 
				"		expect( linkedhashmap_2_1.get( \"mom\" ) ).andReturn( person_5_2 );\n" + 
				"		replay( linkedhashmap_2_1 );\n" + 
				"		return linkedhashmap_2_1;\n" + 
				"	}\n" + 
				"}\n" );
	}
}