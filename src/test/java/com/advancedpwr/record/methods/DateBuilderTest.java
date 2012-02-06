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
		assertContains("	protected Date date_1_1;\n" +
				"\n" +
				"	protected Date buildDate_1_1()\n" +
				"	{\n" +
				"		date_1_1 = new Date( 1234567689l );\n" +
				"		return date_1_1;\n" +
				"	}\n");

	}
}
