package com.advancedpwr.record.inspect;

import com.advancedpwr.record.InstanceTree;

public class ArrayInspector extends Inspector
{

	public void inspect( InstanceTree inTree )
	{
		setInstanceTree( inTree );
		addArrayAccessPaths();
	}

	protected void addArrayAccessPaths()
	{
		if ( isArray() )
		{
			Object[] array = (Object[]) getObject();
		
			for ( int i = 0; i < array.length; i++ )
			{
				Object member = array[i];
				MultiPath path = new MultiPath();
				path.setTree( createInstanceTree( member ) );
				path.setInstanceName( objectClass().getComponentType().getSimpleName() );
				addAccessPath( path );
			}
		}
	}

	protected boolean isArray()
	{
		return objectClass().isArray() && !objectClass().getComponentType().isPrimitive();
	}
}
