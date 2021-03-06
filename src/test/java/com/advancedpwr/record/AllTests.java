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

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests
{

	public static Test suite()
	{
		//		AbstractRecorderTest.setWriteFiles();
		TestSuite suite = new TestSuite( "Test for com.advancedpwr.record" );
		//$JUnit-BEGIN$
		suite.addTestSuite( FamilyTest.class );
		suite.addTestSuite( StringRecorderTest.class );
		suite.addTestSuite( BeanRecorderTest.class );
		suite.addTestSuite( MapTest.class );
		suite.addTestSuite( ArrayTest.class );
		suite.addTestSuite( DefaultClassDescriptorTest.class );
		suite.addTestSuite( ClassWriterTest.class );
		suite.addTestSuite( ListInstanceTest.class );
		suite.addTestSuite( InstanceTreeTest.class );
		suite.addTestSuite( AccessPathTest.class );
		suite.addTestSuite( ListRecorderTest.class );
		suite.addTestSuite( DateCalendarTest.class );
		suite.addTestSuite( WrappedIOTest.class );
		suite.addTestSuite( DescentTest.class );
		suite.addTestSuite( InnerClassTest.class );
		//$JUnit-END$
		return suite;
	}

}
