package com.advancedpwr.record.inspect;

import com.advancedpwr.record.AccessPath;
import com.advancedpwr.record.InstanceTree;


public abstract class Inspector
{

	protected InstanceTree fieldInstanceTree;

	public abstract void inspect( InstanceTree inTree );
	
	protected Class objectClass()
	{
		return getInstanceTree().objectClass();
	}

	protected Object getObject()
	{
		return getInstanceTree().getObject();
	}

	protected InstanceTree getInstanceTree()
	{
		return fieldInstanceTree;
	}

	protected void setInstanceTree( InstanceTree instanceTree )
	{
		fieldInstanceTree = instanceTree;
	}

	protected void debug( String inString )
	{
	//		System.out.println( inString );
	}
	
	protected void addAccessPath( AccessPath inPath )
	{
		getInstanceTree().getAccessPaths().add( inPath );
	}

	protected InstanceTree createInstanceTree( Object result )
	{
		return getInstanceTree().createInstanceTree( result );
	}

	protected String currentInstanceName()
	{
		return getInstanceTree().currentInstanceName();
	}

}
