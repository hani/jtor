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

import com.advancedpwr.record.examples.StringExample;

public class StringRecorderTest extends AbstractRecorderTest
{

	@Override
	protected void setUp()
	{
		// TODO Auto-generated method stub
		super.setUp();
	}

	public void testWriteObject_String()
	{
		StringExample stringExample = new StringExample();
		stringExample.setString( "a string" );
		stringExample.setString2( "another string\r\n" +
								  "two lines long" );

		recorder.record( stringExample );
		assertResult( "package com.advancedpwr.record.examples.generated;\n" +
				"\n" +
				"import com.advancedpwr.record.examples.StringExample;\n" +
				"\n" +
        "@SuppressWarnings(\"all\")\n" +
				"public class StringExampleFactory\n" +
				"{\n" +
				"\n" +
				"	protected StringExample stringexample;\n" +
				"\n" +
				"	public StringExample buildStringExample()\n" +
				"	{\n" +
				"		stringexample = new StringExample();\n" +
				"		stringexample.setString( \"a string\" );\n" +
				"		stringexample.setString2( \"another string\\r\\ntwo lines long\" );\n" +
				"		return stringexample;\n" +
				"	}\n" +
				"\n" +
				"}\n");
	}

}
