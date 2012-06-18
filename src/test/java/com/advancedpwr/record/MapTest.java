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

import java.util.HashMap;
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
    assertContains("mapcontainer = new MapContainer();", "linkedhashmap_9_1 = new LinkedHashMap();", "linkedhashmap_9_1.put( \"son\", person_1_1 );", ".setName( \"cousin\" );");
	}

  public void testComplexKey() {
    Person person = new Person();
    person.setName("foo");
    Map self = new HashMap();
    self.put(person, new Person());
    recorder.record(self );
    assertContains("protected Person hashmap_3_1;\n" +
      "\n" +
      "\tprotected Person buildHashMap_3_1()\n" +
      "\t{\n" +
      "\t\tif ( hashmap_3_1 != null ) \n" +
      "\t\t{\n" +
      "\t\t\treturn hashmap_3_1;\n" +
      "\t\t}\n" +
      "\t\thashmap_3_1 = new Person();\n" +
      "\t\treturn hashmap_3_1;\n" +
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
      "\t\tperson_1_1.setName( \"foo\" );\n" +
      "\t\treturn person_1_1;\n" +
      "\t}");
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
    assertContains("linkedhashmap.put( \"alias\", linkedhashmap_2_1 );", "linkedhashmap.put( \"son\", buildLinkedHashMap_2_1() );", "person_3_2.setDad( buildPerson_4_3() );");
	}
}