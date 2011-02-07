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

import java.util.Iterator;

import com.advancedpwr.record.AccessPath;
import com.advancedpwr.record.methods.BaseMethodBuilder;

public class MockMethodBuilder extends BaseMethodBuilder
{

	protected void writeInstance()
	{
		writeIfNotNullReturnInstance();
		writeLine( instanceName() + " = createStrictMock( " + instanceType() + ".class )");
	}
	
	protected void writePopulators()
	{
		for ( AccessPath result : getInstanceTree().getAccessPaths() )
		{
			if ( result instanceof ArgumentPath )
			{
				continue;
			}
			BaseMethodBuilder builder = createMethodBuilder( result );
			String methodCall = instanceName() + "." + result.pathName() + "(" + buildArguments( result ) +")";
			if ( isVoidReturnType( result ) )
			{
				if ( isException( result ) )
				{
					writeLine( methodCall );
					writeLine( "expectLastCall().andThrow( "+ builder.resultBuilder() + " )" );
				}
				else
				{
					writeLine(  methodCall );
				}
			}
			else
			{
				if ( isException( result ) )
				{
					writeLine( "expect( " + methodCall + " ).andThrow( " + builder.resultBuilder() + " )" );
				}
				else
				{
					writeLine( "expect( " + methodCall + " ).andReturn( " + builder.resultBuilder() + " )" );
				}
			}
		}
		
		writeLine( "replay( " + instanceName() + " )");
	}

	protected boolean isException( AccessPath result )
	{
		return Throwable.class.isAssignableFrom( result.getResultClass() );
	}

	protected boolean isVoidReturnType( AccessPath result )
	{
		return result.getReturnType().equals( void.class );	
	}

	protected String buildArguments( AccessPath result )
	{
		StringBuffer sb = new StringBuffer( " " );
		if ( result instanceof MethodCallPath )
		{
			MethodCallPath callPath = (MethodCallPath) result;
			if( callPath.getArguments().size() == 0 )
			{
				return "";
			}
			for ( Iterator iterator = callPath.getArguments().iterator(); iterator.hasNext(); )
			{
				AccessPath path = (AccessPath) iterator.next();
				BaseMethodBuilder builder = createMethodBuilder( path );
				sb.append( builder.resultBuilder() );
				if ( iterator.hasNext() )
				{
					sb.append( "," );
				}
				else
				{
					sb.append( " " );
				}
			}
		}
		return sb.toString();
	}

}
