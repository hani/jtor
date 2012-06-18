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

import com.advancedpwr.record.AbstractRecorderTest;
import com.advancedpwr.record.examples.LangObjects;
import com.advancedpwr.record.examples.Person;
import com.advancedpwr.record.examples.Primitives;

public class PrimitivesTest extends AbstractRecorderTest
{

	protected void setUp()
	{
		super.setUp();
	}

  public void testNaN() {
    Primitives primitives = new Primitives();
    primitives.setDouble(Double.NaN);
    recorder.record(primitives);
    assertContains("primitives.setDouble( Double.NaN )");
  }

  public void testNegativeInfinity() {
    Primitives primitives = new Primitives();
    primitives.setDouble(Double.NEGATIVE_INFINITY);
    recorder.record(primitives);
    assertContains("primitives.setDouble( Double.NEGATIVE_INFINITY )");
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
		assertContains("\tprotected Primitives primitives;\n" +
      "\n" +
      "\tpublic Primitives buildPrimitives()\n" +
      "\t{\n" +
      "\t\tif ( primitives != null ) \n" +
      "\t\t{\n" +
      "\t\t\treturn primitives;\n" +
      "\t\t}\n" +
      "\t\tprimitives = new Primitives();\n" +
      "\t\tprimitives.setBoolean( true );\n" +
      "\t\tprimitives.setByte( (byte)123 );\n" +
      "\t\tprimitives.setChar( 'c' );\n" +
      "\t\tprimitives.setDouble( 12.0d );\n" +
      "\t\tprimitives.setFloat( 11.0f );\n" +
      "\t\tprimitives.setInt( 5 );\n" +
      "\t\tprimitives.setLong( 16l );\n" +
      "\t\tprimitives.setShort( (short)3 );\n" +
      "\t\tprimitives.setString( \"astring\" );\n" +
      "\t\treturn primitives;\n" +
      "\t}");
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
		assertContains("\tpublic LangObjects buildLangObjects()\n" +
      "\t{\n" +
      "\t\tif ( langobjects != null ) \n" +
      "\t\t{\n" +
      "\t\t\treturn langobjects;\n" +
      "\t\t}\n" +
      "\t\tlangobjects = new LangObjects();\n" +
      "\t\tlangobjects.setBoolean( true );\n" +
      "\t\tlangobjects.setByte( (byte)123 );\n" +
      "\t\tlangobjects.setCharacter( 'c' );\n" +
      "\t\tlangobjects.setClassArgument( com.advancedpwr.record.examples.Person.class );\n" +
      "\t\tlangobjects.setDouble( 12.0d );\n" +
      "\t\tlangobjects.setFloat( 11.0f );\n" +
      "\t\tlangobjects.setInteger( 5 );\n" +
      "\t\tlangobjects.setLong( 16l );\n" +
      "\t\tlangobjects.setShort( (short)3 );\n" +
      "\t\treturn langobjects;\n" +
      "\t}");

	}

}
