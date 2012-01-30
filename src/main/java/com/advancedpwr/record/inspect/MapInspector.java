package com.advancedpwr.record.inspect;

import java.util.Iterator;
import java.util.Map;

import com.advancedpwr.record.InstanceTree;

public class MapInspector extends Inspector
{

	public void inspect( InstanceTree inTree )
	{
		setInstanceTree( inTree );
		addMapAccessPaths();
	}

	protected void addMapAccessPaths()
	{
		if ( isMap() )
		{
			Map map = (Map) getObject();
			for ( Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); )
			{
				Map.Entry entry = (Map.Entry) iterator.next();
				
				MapPutPath path = new MapPutPath();
				path.setKeyTree( createInstanceTree( entry.getKey() ) );
				path.setValueTree( createInstanceTree( entry.getValue() ) );
				path.setInstanceName( currentInstanceName() );
				addAccessPath( path );
			}
		}
	}

	protected boolean isMap()
	{
		return Map.class.isAssignableFrom( objectClass() );
	}
}
