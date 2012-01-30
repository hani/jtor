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

import java.net.URL;

import com.advancedpwr.record.examples.FinalStuff;

public class URLTest extends AbstractMockRecorderTest
{
	public void testURL() throws Exception
	{
		URL url = new URL( "http://localhost" );
		FinalStuff obj = new FinalStuff();
		
		obj = recorder.record( obj );
		obj.setURL( url );
		obj.getURL();
		recorder.endRecording();
		assertResult( "package com.advancedpwr.record.examples.generated;\n" + 
				"\n" + 
				"import com.advancedpwr.record.examples.FinalStuff;\n" + 
				"import java.net.URL;\n" + 
				"import com.advancedpwr.record.mock.MockFactory;\n" + 
				"\n" + 
				"public class FinalStuffFactory extends MockFactory\n" + 
				"{\n" + 
				"\n" + 
				"	protected FinalStuff finalstuff;\n" + 
				"\n" + 
				"	public FinalStuff buildFinalStuff()\n" + 
				"	{\n" + 
				"		if ( finalstuff != null ) \n" + 
				"		{\n" + 
				"			return finalstuff;\n" + 
				"		}\n" + 
				"		finalstuff = createStrictMock( FinalStuff.class );\n" + 
				"		finalstuff.setURL( buildURL_2_1() );\n" + 
				"		expect( finalstuff.getURL() ).andReturn( buildURL_2_1() );\n" + 
				"		replay( finalstuff );\n" + 
				"		return finalstuff;\n" + 
				"	}\n" + 
				"\n" + 
				"	protected URL url_2_1;\n" + 
				"\n" + 
				"	protected URL buildURL_2_1()\n" + 
				"	{\n" + 
				"		if ( url_2_1 != null ) \n" + 
				"		{\n" + 
				"			return url_2_1;\n" + 
				"		}\n" + 
				"		try\n" + 
				"		{\n" + 
				"			url_2_1 = new URL( \"http://localhost\" );\n" + 
				"		}\n" + 
				"		catch( Exception e )\n" + 
				"		{\n" + 
				"			throw new RuntimeException( e );\n" + 
				"		}\n" + 
				"		return url_2_1;\n" + 
				"	}\n" + 
				"}\n" );
	}
}
