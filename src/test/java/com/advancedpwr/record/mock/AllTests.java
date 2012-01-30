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
package com.advancedpwr.record.mock;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.advancedpwr.record.AbstractRecorderTest;

public class AllTests
{

	public static Test suite()
	{
//		AbstractRecorderTest.setWriteFiles();
		TestSuite suite = new TestSuite( "Test for com.advancedpwr.record.mock" );
		//$JUnit-BEGIN$
		suite.addTestSuite( ListInstanceTest.class );
		suite.addTestSuite( ClassProxyFactoryTest.class );
		suite.addTestSuite( MockFactoryTest.class );
		suite.addTestSuite( ArrayTest.class );
		suite.addTestSuite( MapTest.class );
		suite.addTestSuite( MockBehaviorRecorderTest.class );
		suite.addTestSuite( URLTest.class );
		//$JUnit-END$
		return suite;
	}

}
