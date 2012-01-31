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
        "import com.advancedpwr.record.examples.BirthdayException;\n" +
				"import com.advancedpwr.record.examples.Person;\n" +
        "import java.util.Calendar;\n" +
				"import java.util.Date;\n" +
				"import java.util.GregorianCalendar;\n" +
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
				"		person.setDad( buildPerson_1_1() );\n" +
				"		person.setMom( buildPerson_30_1() );\n" +
				"		person.setName( \"son\" );\n" +
				"		return person;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_1_1;\n" +
				"\n" +
				"	protected Person buildPerson_1_1() throws Exception, BirthdayException\n" +
				"	{\n" +
				"		person_1_1 = new Person();\n" +
				"		person_1_1.setAnniversary( buildDate_2_2() );\n" +
				"		person_1_1.setBirthday( buildGregorianCalendar_10_2() );\n" +
				"		person_1_1.setDad( buildPerson_27_2() );\n" +
				"		person_1_1.setName( \"dad\" );\n" +
				"		return person_1_1;\n" +
				"	}\n" +
				"\n" +
				"	protected Date date_2_2;\n" +
				"\n" +
				"	protected Date buildDate_2_2()\n" +
				"	{\n" +
				"		date_2_2 = new Date( 987654321l );\n" +
				"		return date_2_2;\n" +
				"	}\n" +
				"\n" +
				"	protected Calendar gregoriancalendar_10_2;\n" +
				"\n" +
				"	protected Calendar buildGregorianCalendar_10_2()\n" +
				"	{\n" +
				"		gregoriancalendar_10_2 = GregorianCalendar.getInstance();\n" +
				"		gregoriancalendar_10_2.setTimeInMillis( new Long( 123456789 ) );\n" +
				"		return gregoriancalendar_10_2;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_27_2;\n" +
				"\n" +
				"	protected Person buildPerson_27_2()\n" +
				"	{\n" +
				"		person_27_2 = new Person();\n" +
				"		person_27_2.setName( \"grandpa\" );\n" +
				"		return person_27_2;\n" +
				"	}\n" +
				"\n" +
				"	protected Person person_30_1;\n" +
				"\n" +
				"	protected Person buildPerson_30_1()\n" +
				"	{\n" +
				"		person_30_1 = new Person();\n" +
				"		person_30_1.setName( \"mom\" );\n" +
				"		return person_30_1;\n" +
				"	}\n" +
				"\n" +
				"}\n" +
				"");
	}
}
