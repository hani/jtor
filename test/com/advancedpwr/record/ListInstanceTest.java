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

import java.util.ArrayList;
import java.util.List;

import com.advancedpwr.record.examples.Person;

public class ListInstanceTest extends AbstractRecorderTest
{
	@Override
	protected void setUp()
	{
		setWriteFiles();
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
		
		recorder.record( dad );
		
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.Person;\n" + 
				"import java.util.List;\n" + 
				"import java.util.ArrayList;\n" + 
				"\n" + 
				"public class PersonFactory\n" + 
				"{\n" + 
				"\n" + 
				"	protected Person person;\n" + 
				"\n" + 
				"	public Person buildPerson()\n" + 
				"	{\n" + 
				"		person = new Person();\n" + 
				"		person.setMom( buildMom_1_1() );\n" + 
				"		person.setName( \"dad\" );\n" + 
				"		person.setChildren( buildChildren_4_1() );\n" + 
				"		return person;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person mom_1_1;\n" + 
				"\n" + 
				"	protected Person buildMom_1_1()\n" + 
				"	{\n" + 
				"		mom_1_1 = new Person();\n" + 
				"		mom_1_1.setDad( person );\n" + 
				"		mom_1_1.setName( \"Jill\" );\n" + 
				"		return mom_1_1;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected ArrayList children_4_1;\n" + 
				"\n" + 
				"	protected List buildChildren_4_1()\n" + 
				"	{\n" + 
				"		children_4_1 = new ArrayList();\n" + 
				"		children_4_1.add( buildChildren_1_5_2() );\n" + 
				"		children_4_1.add( mom_1_1 );\n" + 
				"		children_4_1.add( buildChildren_1_7_2() );\n" + 
				"		return children_4_1;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person children_1_5_2;\n" + 
				"\n" + 
				"	protected Person buildChildren_1_5_2()\n" + 
				"	{\n" + 
				"		children_1_5_2 = new Person();\n" + 
				"		children_1_5_2.setDad( person );\n" + 
				"		children_1_5_2.setName( \"jack\" );\n" + 
				"		return children_1_5_2;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person children_1_7_2;\n" + 
				"\n" + 
				"	protected Person buildChildren_1_7_2()\n" + 
				"	{\n" + 
				"		children_1_7_2 = new Person();\n" + 
				"		children_1_7_2.setDad( person );\n" + 
				"		children_1_7_2.setMom( mom_1_1 );\n" + 
				"		children_1_7_2.setName( \"joe\" );\n" + 
				"		return children_1_7_2;\n" + 
				"	}\n" + 
				"\n" + 
				"}\n");
	}
}
