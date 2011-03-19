package com.advancedpwr;

import junit.textui.TestRunner;

public class TestMain
{

	/**
	 * @param args
	 */
	public static void main( String[] args )
	{
		TestRunner runner = new TestRunner();
		runner.run( AllTests.suite() );
	}

}
