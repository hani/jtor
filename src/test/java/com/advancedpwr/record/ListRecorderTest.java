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

import java.util.Vector;

import com.advancedpwr.record.examples.ListExample;

public class ListRecorderTest extends AbstractRecorderTest
{

	@Override
	protected void setUp()
	{
		// TODO Auto-generated method stub
		super.setUp();
	}

	public void testWriteObject_list()
	{
		Vector vector = new Vector();
		vector.add( "entry 1" );
		vector.add( "entry 2" );

		ListExample listHolder = new ListExample();
		listHolder.setList( vector );

		recorder.record( listHolder );
    assertContains("\tprotected List buildVector_1_1()\n" +
      "\t{\n" +
      "\t\tif ( vector_1_1 != null ) \n" +
      "\t\t{\n" +
      "\t\t\treturn vector_1_1;\n" +
      "\t\t}\n" +
      "\t\tvector_1_1 = new Vector();\n" +
      "\t\tvector_1_1.add( \"entry 1\" );\n" +
      "\t\tvector_1_1.add( \"entry 2\" );\n" +
      "\t\treturn vector_1_1;\n" +
      "\t}\n");
	}
}
