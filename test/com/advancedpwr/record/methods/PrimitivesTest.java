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
package com.advancedpwr.record.methods;

import java.io.StringWriter;

import junit.framework.TestCase;

import com.advancedpwr.record.AbstractRecorderTest;
import com.advancedpwr.record.BeanRecorder;
import com.advancedpwr.record.examples.LangObjects;
import com.advancedpwr.record.examples.Person;
import com.advancedpwr.record.examples.Primitives;

public class PrimitivesTest extends AbstractRecorderTest
{

	protected void setUp()
	{
		super.setUp();
	}

	public void testPrimitives()
	{
		Primitives primitives = new Primitives();
		primitives.setChar( 'c' );
		primitives.setDouble( 12d );
		primitives.setFloat( 11f );
		primitives.setInt( 5 );
		primitives.setLong( 16l );
		primitives.setShort( (short)3 );
		primitives.setString( "astring" );
		primitives.setBoolean( true );
		primitives.setByte( (byte)123 );
		

		recorder.record( primitives );
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" +
				"import com.advancedpwr.record.examples.Primitives;\n" + 
				"\n" + 
				"public class PrimitivesFactory\n" + 
				"{\n" + 
				"\n" + 
				"	protected Primitives primitives;\n" + 
				"\n" + 
				"	public Primitives buildPrimitives()\n" + 
				"	{\n" + 
				"		primitives = new Primitives();\n" + 
				"		primitives.setBoolean( true );\n" + 
				"		primitives.setByte( (byte)123 );\n" + 
				"		primitives.setChar( 'c' );\n" + 
				"		primitives.setDouble( 12.0d );\n" + 
				"		primitives.setFloat( 11.0f );\n" + 
				"		primitives.setInt( 5 );\n" + 
				"		primitives.setLong( 16l );\n" + 
				"		primitives.setShort( (short)3 );\n" + 
				"		primitives.setString( \"astring\" );\n" + 
				"		return primitives;\n" + 
				"	}\n" + 
				"\n" + 
				"}\n");
	}
	
	public void testLangObjects()
	{
		LangObjects langs = new LangObjects();
		langs.setCharacter( 'c' );
		langs.setDouble( 12d );
		langs.setFloat( 11f );
		langs.setInteger( 5 );
		langs.setLong( 16l );
		langs.setShort( (short)3 );
		langs.setBoolean( true );
		langs.setByte( (byte)123 );
		langs.setClassArgument( Person.class );

		recorder.record( langs );
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" +
				"import com.advancedpwr.record.examples.LangObjects;\n" + 
				"\n" + 
				"public class LangObjectsFactory\n" + 
				"{\n" + 
				"\n" + 
				"	protected LangObjects langobjects;\n" + 
				"\n" + 
				"	public LangObjects buildLangObjects()\n" + 
				"	{\n" + 
				"		langobjects = new LangObjects();\n" + 
				"		langobjects.setBoolean( true );\n" + 
				"		langobjects.setByte( (byte)123 );\n" + 
				"		langobjects.setCharacter( 'c' );\n" + 
				"		langobjects.setClassArgument( com.advancedpwr.record.examples.Person.class );\n" + 
				"		langobjects.setDouble( 12.0d );\n" + 
				"		langobjects.setFloat( 11.0f );\n" + 
				"		langobjects.setInteger( 5 );\n" + 
				"		langobjects.setLong( 16l );\n" + 
				"		langobjects.setShort( (short)3 );\n" + 
				"		return langobjects;\n" + 
				"	}\n" + 
				"\n" + 
				"}\n");
		
	}

}
