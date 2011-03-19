package com.advancedpwr.record.inspect;

import java.util.Collection;
import java.util.Iterator;

import com.advancedpwr.record.InstanceTree;

public class CollectionInspector extends Inspector
{

	public void inspect( InstanceTree inTree )
	{
		setInstanceTree( inTree );
		addCollectionAccessPaths();
	}
	
	protected void addCollectionAccessPaths()
	{
		if ( isCollection() )
		{
			Collection collection = (Collection) getObject();
			for ( Iterator iterator = collection.iterator(); iterator.hasNext(); )
			{
				Object member = iterator.next();
				MultiPath path = new MultiPath();
				path.setTree( createInstanceTree( member ) );
				path.setInstanceName( currentInstanceName() );
				addAccessPath( path );
			}
		}
	}

	protected boolean isCollection()
	{
		return Collection.class.isAssignableFrom( objectClass() );
	}

}
