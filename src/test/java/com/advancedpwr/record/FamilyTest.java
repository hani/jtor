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

import java.util.Locale;

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
		family.setLocale( Locale.CANADA );
		recorder.record( family );
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.Family;\n" + 
				"import com.advancedpwr.record.examples.Person;\n" + 
				"import java.util.Locale;\n" + 
				"\n" + 
				"public class FamilyFactory\n" + 
				"{\n" + 
				"\n" + 
				"	protected Family family;\n" + 
				"\n" + 
				"	public Family buildFamily()\n" + 
				"	{\n" + 
				"		family = new Family();\n" + 
				"		family.setDad( buildPerson_1_1() );\n" + 
				"		family.setLocale( buildLocale_5_1() );\n" + 
				"		family.setMom( buildPerson_6_1() );\n" + 
				"		return family;\n" + 
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
				"	protected Locale locale_5_1;\n" + 
				"\n" + 
				"	protected Locale buildLocale_5_1()\n" + 
				"	{\n" + 
				"		if ( locale_5_1 != null ) \n" + 
				"		{\n" + 
				"			return locale_5_1;\n" + 
				"		}\n" + 
				"		locale_5_1 = new Locale( \"en\", \"CA\" );\n" + 
				"		return locale_5_1;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person person_6_1;\n" + 
				"\n" + 
				"	protected Person buildPerson_6_1()\n" + 
				"	{\n" + 
				"		person_6_1 = new Person();\n" + 
				"		person_6_1.setName( \"mom\" );\n" + 
				"		return person_6_1;\n" + 
				"	}\n" + 
				"\n" + 
				"}\n"  );
	}
}
