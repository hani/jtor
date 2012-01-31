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
        "import java.util.LinkedHashMap;\n" +
				"import java.util.Map;\n" +
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
				"		mapcontainer.setMap( buildLinkedHashMap_9_1() );\n" +
				"		return mapcontainer;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_1_1;\n" +
				"\n" +
				"	protected Person buildPerson_1_1()\n" +
				"	{\n" +
				"		person_1_1 = new Person();\n" +
				"		person_1_1.setDad( buildPerson_2_2() );\n" +
				"		person_1_1.setMom( buildPerson_6_2() );\n" +
				"		person_1_1.setName( \"son\" );\n" +
				"		return person_1_1;\n" +
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
				"	protected LinkedHashMap linkedhashmap_9_1;\n" +
				"\n" +
				"	protected Map buildLinkedHashMap_9_1()\n" +
				"	{\n" +
				"		linkedhashmap_9_1 = new LinkedHashMap();\n" +
				"		linkedhashmap_9_1.put( \"son\", person_1_1 );\n" +
				"		linkedhashmap_9_1.put( \"dad\", person_2_2 );\n" +
				"		linkedhashmap_9_1.put( \"mom\", person_6_2 );\n" +
				"		linkedhashmap_9_1.put( person_1_1, buildMap_1_10_2() );\n" +
				"		return linkedhashmap_9_1;\n" +
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
      "import com.advancedpwr.record.examples.Person;\n" +
				"import java.util.LinkedHashMap;\n" +
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
				"		linkedhashmap.put( \"dad\", person_3_2 );\n" +
				"		linkedhashmap.put( \"alias\", linkedhashmap_2_1 );\n" +
				"		return linkedhashmap;\n" +
				"	}\n" +
				"\n" +
				"	protected Person linkedhashmap_2_1;\n" +
				"\n" +
				"	protected Person buildLinkedHashMap_2_1()\n" +
				"	{\n" +
				"		linkedhashmap_2_1 = new Person();\n" +
				"		linkedhashmap_2_1.setDad( buildPerson_3_2() );\n" +
				"		linkedhashmap_2_1.setMom( buildPerson_7_2() );\n" +
				"		linkedhashmap_2_1.setName( \"son\" );\n" +
				"		return linkedhashmap_2_1;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_3_2;\n" +
				"\n" +
				"	protected Person buildPerson_3_2()\n" +
				"	{\n" +
				"		person_3_2 = new Person();\n" +
				"		person_3_2.setDad( buildPerson_4_3() );\n" +
				"		person_3_2.setName( \"dad\" );\n" +
				"		return person_3_2;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_4_3;\n" +
				"\n" +
				"	protected Person buildPerson_4_3()\n" +
				"	{\n" +
				"		person_4_3 = new Person();\n" +
				"		person_4_3.setName( \"grandpa\" );\n" +
				"		return person_4_3;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_7_2;\n" +
				"\n" +
				"	protected Person buildPerson_7_2()\n" +
				"	{\n" +
				"		person_7_2 = new Person();\n" +
				"		person_7_2.setName( \"mom\" );\n" +
				"		return person_7_2;\n" +
				"	}\n" +
				"\n" +
				"}\n" );

	}
}