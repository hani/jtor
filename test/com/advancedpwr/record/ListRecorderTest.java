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
		
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.ListExample;\n" + 
				"import java.util.List;\n" + 
				"import java.util.Vector;\n" + 
				"\n" + 
				"public class ListExampleFactory\n" + 
				"{\n" + 
				"\n" + 
				"	protected ListExample listexample;\n" + 
				"\n" + 
				"	public ListExample buildListExample()\n" + 
				"	{\n" + 
				"		listexample = new ListExample();\n" + 
				"		listexample.setList( buildList_1_1() );\n" + 
				"		return listexample;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected Vector list_1_1;\n" + 
				"\n" + 
				"	protected List buildList_1_1()\n" + 
				"	{\n" + 
				"		list_1_1 = new Vector();\n" + 
				"		list_1_1.add( \"entry 1\" );\n" + 
				"		list_1_1.add( \"entry 2\" );\n" + 
				"		return list_1_1;\n" + 
				"	}\n" + 
				"\n" + 
				"}\n");
	}
}
