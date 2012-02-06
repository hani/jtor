package com.advancedpwr.record.methods;

import java.util.Date;

import com.advancedpwr.record.BeanRecorderTest;

public class DateBuilderTest extends BeanRecorderTest
{
	protected void setUp()
	{
		setWriteFiles();
		super.setUp();
	}

	public void testSameDateInstance()
	{
		DateHolder dateHolder = new DateHolder();
		Date date = new Date( 1234567689 );

		dateHolder.setDate( date );
		dateHolder.setOtherDate( date );

		recorder.record( dateHolder );
		assertResult( "package com.advancedpwr.record.methods.generated;\n" +
				"\n" +
				"import com.advancedpwr.record.methods.DateHolder;\n" +
				"import java.util.Date;\n" +
				"\n" +
        "@SuppressWarnings(\"all\")\n" +
				"public class DateHolderFactory\n" +
				"{\n" +
				"\n" +
				"	protected DateHolder dateholder;\n" +
				"\n" +
				"	public DateHolder buildDateHolder()\n" +
				"	{\n" +
				"		dateholder = new DateHolder();\n" +
				"		dateholder.setDate( buildDate_1_1() );\n" +
				"		dateholder.setOtherDate( buildDate_1_1() );\n" +
				"		return dateholder;\n" +
				"	}\n" +
				"\n" +
				"	protected Date date_1_1;\n" +
				"\n" +
				"	protected Date buildDate_1_1()\n" +
				"	{\n" +
				"		date_1_1 = new Date( 1234567689l );\n" +
				"		return date_1_1;\n" +
				"	}\n" +
				"\n" +
				"}\n" );

	}
}
