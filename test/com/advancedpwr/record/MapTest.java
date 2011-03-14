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

import java.util.LinkedHashMap;
import java.util.Map;

import com.advancedpwr.record.examples.MapContainer;
import com.advancedpwr.record.examples.Person;

public class MapTest extends AbstractRecorderTest
{
	protected void setUp()
	{
		setWriteFiles();
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
		
		recorder.record( container );
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.MapContainer;\n" + 
				"import com.advancedpwr.record.examples.Person;\n" + 
				"import java.util.Map;\n" + 
				"import java.util.LinkedHashMap;\n" + 
				"\n" + 
				"public class MapContainerFactory\n" + 
				"{\n" + 
				"\n" + 
				"	protected MapContainer mapcontainer;\n" + 
				"\n" + 
				"	public MapContainer buildMapContainer()\n" + 
				"	{\n" + 
				"		mapcontainer = new MapContainer();\n" + 
				"		mapcontainer.setPerson( buildPerson_1_1() );\n" + 
				"		mapcontainer.setMap( buildMap_9_1() );\n" + 
				"		return mapcontainer;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person person_1_1;\n" + 
				"\n" + 
				"	protected Person buildPerson_1_1()\n" + 
				"	{\n" + 
				"		person_1_1 = new Person();\n" + 
				"		person_1_1.setDad( buildDad_2_2() );\n" + 
				"		person_1_1.setMom( buildMom_6_2() );\n" + 
				"		person_1_1.setName( \"son\" );\n" + 
				"		return person_1_1;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person dad_2_2;\n" + 
				"\n" + 
				"	protected Person buildDad_2_2()\n" + 
				"	{\n" + 
				"		dad_2_2 = new Person();\n" + 
				"		dad_2_2.setDad( buildDad_3_3() );\n" + 
				"		dad_2_2.setName( \"dad\" );\n" + 
				"		return dad_2_2;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person dad_3_3;\n" + 
				"\n" + 
				"	protected Person buildDad_3_3()\n" + 
				"	{\n" + 
				"		dad_3_3 = new Person();\n" + 
				"		dad_3_3.setName( \"grandpa\" );\n" + 
				"		return dad_3_3;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person mom_6_2;\n" + 
				"\n" + 
				"	protected Person buildMom_6_2()\n" + 
				"	{\n" + 
				"		mom_6_2 = new Person();\n" + 
				"		mom_6_2.setName( \"mom\" );\n" + 
				"		return mom_6_2;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected LinkedHashMap map_9_1;\n" + 
				"\n" + 
				"	protected Map buildMap_9_1()\n" + 
				"	{\n" + 
				"		map_9_1 = new LinkedHashMap();\n" + 
				"		map_9_1.put( \"son\", person_1_1 );\n" + 
				"		map_9_1.put( \"dad\", dad_2_2 );\n" + 
				"		map_9_1.put( \"mom\", mom_6_2 );\n" + 
				"		map_9_1.put( person_1_1, buildMap_1_10_2() );\n" + 
				"		return map_9_1;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person map_1_10_2;\n" + 
				"\n" + 
				"	protected Person buildMap_1_10_2()\n" + 
				"	{\n" + 
				"		map_1_10_2 = new Person();\n" + 
				"		map_1_10_2.setName( \"cousin\" );\n" + 
				"		return map_1_10_2;\n" + 
				"	}\n" + 
				"\n" + 
				"}\n");
	}
	
	public void testMap_2()
	{
		Person person = Person.createExamplePerson();
		
		Map family = new LinkedHashMap();
		family.put( person.getName(), person );
		family.put( person.getDad().getName(), person.getDad() );
		family.put( "alias", person );
		
		// Don't want to try and write a class into the "java.util" package.
		SimpleClassDescriptor desc = new SimpleClassDescriptor();
		desc.setPackageName( "com.advancedpwr.java.util.generated" );
		desc.setClassName( "LinkedHashMapFactory" );
		recorder.setDescriptor( desc );
		recorder.record( family );
		assertResult( "package com.advancedpwr.java.util.generated;\n" + 
				"\n" + 
				"import java.util.LinkedHashMap;\n" + 
				"import com.advancedpwr.record.examples.Person;\n" + 
				"\n" + 
				"public class LinkedHashMapFactory\n" + 
				"{\n" + 
				"\n" + 
				"	protected LinkedHashMap linkedhashmap;\n" + 
				"\n" + 
				"	public LinkedHashMap buildLinkedHashMap()\n" + 
				"	{\n" + 
				"		linkedhashmap = new LinkedHashMap();\n" + 
				"		linkedhashmap.put( \"son\", buildLinkedHashMap_2_1() );\n" + 
				"		linkedhashmap.put( \"dad\", dad_3_2 );\n" + 
				"		linkedhashmap.put( \"alias\", linkedhashmap_2_1 );\n" + 
				"		return linkedhashmap;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person linkedhashmap_2_1;\n" + 
				"\n" + 
				"	protected Person buildLinkedHashMap_2_1()\n" + 
				"	{\n" + 
				"		linkedhashmap_2_1 = new Person();\n" + 
				"		linkedhashmap_2_1.setDad( buildDad_3_2() );\n" + 
				"		linkedhashmap_2_1.setMom( buildMom_7_2() );\n" + 
				"		linkedhashmap_2_1.setName( \"son\" );\n" + 
				"		return linkedhashmap_2_1;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person dad_3_2;\n" + 
				"\n" + 
				"	protected Person buildDad_3_2()\n" + 
				"	{\n" + 
				"		dad_3_2 = new Person();\n" + 
				"		dad_3_2.setDad( buildDad_4_3() );\n" + 
				"		dad_3_2.setName( \"dad\" );\n" + 
				"		return dad_3_2;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person dad_4_3;\n" + 
				"\n" + 
				"	protected Person buildDad_4_3()\n" + 
				"	{\n" + 
				"		dad_4_3 = new Person();\n" + 
				"		dad_4_3.setName( \"grandpa\" );\n" + 
				"		return dad_4_3;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person mom_7_2;\n" + 
				"\n" + 
				"	protected Person buildMom_7_2()\n" + 
				"	{\n" + 
				"		mom_7_2 = new Person();\n" + 
				"		mom_7_2.setName( \"mom\" );\n" + 
				"		return mom_7_2;\n" + 
				"	}\n" + 
				"\n" + 
				"}\n" + 
				"");
		
	}
}