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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.advancedpwr.record.examples.Person;

public class DateCalendarTest extends AbstractRecorderTest
{

	
	protected void setUp()
	{
		setWriteFiles();
		super.setUp();
	}

	public void testRecordPerson() throws Exception
	{
		Person person = Person.createExamplePerson();
		Person dad = person.getDad();
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTimeInMillis( 123456789 );
		dad.setBirthday( calendar );
		dad.setAnniversary( new Date( 987654321 ) );
		
		recorder.record( person );
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.Person;\n" + 
				"import java.util.Date;\n" + 
				"import java.util.Calendar;\n" + 
				"import java.util.GregorianCalendar;\n" + 
				"import com.advancedpwr.record.examples.BirthdayException;\n" + 
				"import java.util.TimeZone;\n" + 
				"import sun.util.calendar.ZoneInfo;\n" + 
				"\n" + 
				"public class PersonFactory\n" + 
				"{\n" + 
				"\n" + 
				"	protected Person person;\n" + 
				"\n" + 
				"	public Person buildPerson() throws Exception, BirthdayException\n" + 
				"	{\n" + 
				"		person = new Person();\n" + 
				"		person.setDad( buildDad_1_1() );\n" + 
				"		person.setMom( buildMom_30_1() );\n" + 
				"		person.setName( \"son\" );\n" + 
				"		return person;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person dad_1_1;\n" + 
				"\n" + 
				"	protected Person buildDad_1_1() throws Exception, BirthdayException\n" + 
				"	{\n" + 
				"		dad_1_1 = new Person();\n" + 
				"		dad_1_1.setAnniversary( buildAnniversary_2_2() );\n" + 
				"		dad_1_1.setBirthday( buildBirthday_10_2() );\n" + 
				"		dad_1_1.setDad( buildDad_27_2() );\n" + 
				"		dad_1_1.setName( \"dad\" );\n" + 
				"		return dad_1_1;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Date anniversary_2_2;\n" + 
				"\n" + 
				"	protected Date buildAnniversary_2_2()\n" + 
				"	{\n" + 
				"		anniversary_2_2 = new Date( 987654321l );\n" + 
				"		return anniversary_2_2;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Calendar birthday_10_2;\n" + 
				"\n" + 
				"	protected Calendar buildBirthday_10_2()\n" + 
				"	{\n" + 
				"		birthday_10_2 = GregorianCalendar.getInstance();\n" + 
				"		birthday_10_2.setTimeInMillis( new Long( 123456789 ) );\n" + 
				"		return birthday_10_2;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person dad_27_2;\n" + 
				"\n" + 
				"	protected Person buildDad_27_2()\n" + 
				"	{\n" + 
				"		dad_27_2 = new Person();\n" + 
				"		dad_27_2.setName( \"grandpa\" );\n" + 
				"		return dad_27_2;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Person mom_30_1;\n" + 
				"\n" + 
				"	protected Person buildMom_30_1()\n" + 
				"	{\n" + 
				"		mom_30_1 = new Person();\n" + 
				"		mom_30_1.setName( \"mom\" );\n" + 
				"		return mom_30_1;\n" + 
				"	}\n" + 
				"\n" + 
				"}\n");
	}
}
