package com.tutorial.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import junit.framework.TestCase;

import com.advancedpwr.record.SimpleClassDescriptor;
import com.advancedpwr.record.mock.MockBehaviorRecorder;
import com.tampalaptops.data.Order;
import com.tutorial.database.generated.SqlOrderSearchConnectionFactory;

public class SqlOrderSearchTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testRecordOrderSearch()
	{
		MockBehaviorRecorder recorder = new MockBehaviorRecorder();
		recorder.setDestination( "recordings" );
		SimpleClassDescriptor descriptor = new SimpleClassDescriptor();
		descriptor.setPackageName( "com.tampalaptops.search.generated" );
		descriptor.setClassName( "SqlOrderSearchConnectionFactory" );
		recorder.setDescriptor( descriptor );
		
		SqlOrderSearch search = new SqlOrderSearch();
		Connection connection = search.createConnection();
		Connection instrumentedConnection = recorder.record( connection );
		search.setConnection( instrumentedConnection );
		
		search.setSearchText( "Kirk" );
		List orderDetails = search.searchDetails();
		recorder.endRecording();
		
		assertEquals( 5, orderDetails.size() );
		
	}
	
	public void testSearch() throws Exception
	{
		SqlOrderSearch search = new SqlOrderSearch();
		search.setConnection( new SqlOrderSearchConnectionFactory().buildConnection() );
		search.setSearchText( "Kirk" );
		List orderDetails = search.searchDetails();
		assertEquals( 1, orderDetails.size() );
	}
}
