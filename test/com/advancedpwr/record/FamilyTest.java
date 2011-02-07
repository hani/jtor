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

import com.advancedpwr.record.examples.Family;
import com.advancedpwr.record.examples.Person;

public class FamilyTest extends AbstractRecorderTest
{
	protected void setUp()
	{
		setWriteFiles();
		super.setUp();
	}
	
	public void testRecordFamily()
	{
		Person person = Person.createExamplePerson();
		Family family = new Family(person.getDad(), person.getMom());
		recorder.record( family );
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.Family;\n" + 
				"import com.advancedpwr.record.examples.Person;\n" + 
				"\n" + 
				"public class FamilyFactory\n" + 
				"{\n" + 
				"\n" + 
				"	protected Family family;\n" + 
				"\n" + 
				"	public Family buildFamily()\n" + 
				"	{\n" + 
				"		family = new Family();\n" + 
				"		family.setDad( buildDad_1_1() );\n" + 
				"		family.setMom( buildMom_5_1() );\n" + 
				"		return family;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person dad_1_1;\n" + 
				"\n" + 
				"	protected Person buildDad_1_1()\n" + 
				"	{\n" + 
				"		dad_1_1 = new Person();\n" + 
				"		dad_1_1.setDad( buildDad_2_2() );\n" + 
				"		dad_1_1.setName( \"dad\" );\n" + 
				"		return dad_1_1;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person dad_2_2;\n" + 
				"\n" + 
				"	protected Person buildDad_2_2()\n" + 
				"	{\n" + 
				"		dad_2_2 = new Person();\n" + 
				"		dad_2_2.setName( \"grandpa\" );\n" + 
				"		return dad_2_2;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person mom_5_1;\n" + 
				"\n" + 
				"	protected Person buildMom_5_1()\n" + 
				"	{\n" + 
				"		mom_5_1 = new Person();\n" + 
				"		mom_5_1.setName( \"mom\" );\n" + 
				"		return mom_5_1;\n" + 
				"	}\n" + 
				"\n" + 
				"}\n" );
	}
}
