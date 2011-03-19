package com.tutorial.database.generated;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.math.BigDecimal;
import com.advancedpwr.record.mock.MockFactory;

public class SqlOrderSearchConnectionFactory extends MockFactory
{

	protected Connection connection;

	public Connection buildConnection() throws SQLException
	{
		if ( connection != null ) 
		{
			return connection;
		}
		connection = createStrictMock( Connection.class );
		expect( connection.isClosed() ).andReturn( false );
		expect( connection.prepareStatement( "select * from tl_orders where name like ? or id like ? or company like ? or specification like ? or paypalid like ? or ebayid like ? or amazonid like ? or note like ? or Address1 like ? or Address2 like ? or City like ? or StateRegion like ? or Email like ? or ebayusername like ? or miscellaneous like ? order by paymentdate desc" ) ).andReturn( buildPreparedStatement_2_1() );
		expect( connection.isClosed() ).andReturn( false );
		connection.close();
		replay( connection );
		return connection;
	}

	protected PreparedStatement preparedstatement_2_1;

	protected PreparedStatement buildPreparedStatement_2_1() throws SQLException
	{
		if ( preparedstatement_2_1 != null ) 
		{
			return preparedstatement_2_1;
		}
		preparedstatement_2_1 = createStrictMock( PreparedStatement.class );
		preparedstatement_2_1.setString( 1,"%Kirk%" );
		preparedstatement_2_1.setString( 2,"%Kirk%" );
		preparedstatement_2_1.setString( 3,"%Kirk%" );
		preparedstatement_2_1.setString( 4,"%Kirk%" );
		preparedstatement_2_1.setString( 5,"%Kirk%" );
		preparedstatement_2_1.setString( 6,"%Kirk%" );
		preparedstatement_2_1.setString( 7,"%Kirk%" );
		preparedstatement_2_1.setString( 8,"%Kirk%" );
		preparedstatement_2_1.setString( 9,"%Kirk%" );
		preparedstatement_2_1.setString( 10,"%Kirk%" );
		preparedstatement_2_1.setString( 11,"%Kirk%" );
		preparedstatement_2_1.setString( 12,"%Kirk%" );
		preparedstatement_2_1.setString( 13,"%Kirk%" );
		preparedstatement_2_1.setString( 14,"%Kirk%" );
		preparedstatement_2_1.setString( 15,"%Kirk%" );
		expect( preparedstatement_2_1.executeQuery() ).andReturn( buildResultSet_21_2() );
		replay( preparedstatement_2_1 );
		return preparedstatement_2_1;
	}

	protected ResultSet resultset_21_2;

	protected ResultSet buildResultSet_21_2() throws SQLException
	{
		if ( resultset_21_2 != null ) 
		{
			return resultset_21_2;
		}
		resultset_21_2 = createStrictMock( ResultSet.class );
		expect( resultset_21_2.next() ).andReturn( true );
		expect( resultset_21_2.getObject( "Name" ) ).andReturn( "James T Kirk" );
		expect( resultset_21_2.getObject( "LastModified" ) ).andReturn( buildTimestamp_25_3() );
		expect( resultset_21_2.getObject( "Address1" ) ).andReturn( "NCC 1701" );
		expect( resultset_21_2.getObject( "Address2" ) ).andReturn( null );
		expect( resultset_21_2.getObject( "City" ) ).andReturn( "Enterprise" );
		expect( resultset_21_2.getObject( "Company" ) ).andReturn( "UFP" );
		expect( resultset_21_2.getObject( "Email" ) ).andReturn( "james.t.kirk@enterprise.com" );
		expect( resultset_21_2.getObject( "EbayUserName" ) ).andReturn( null );
		expect( resultset_21_2.getObject( "Phone" ) ).andReturn( "5551234444" );
		expect( resultset_21_2.getObject( "Postal" ) ).andReturn( "70007" );
		expect( resultset_21_2.getObject( "StateRegion" ) ).andReturn( "IL" );
		expect( resultset_21_2.getObject( "Amount" ) ).andReturn( buildBigDecimal_43_3() );
		expect( resultset_21_2.getObject( "EBayId" ) ).andReturn( "" );
		expect( resultset_21_2.getObject( "Id" ) ).andReturn( 16400 );
		expect( resultset_21_2.getObject( "Miscellaneous" ) ).andReturn( null );
		expect( resultset_21_2.getObject( "Note" ) ).andReturn( "" );
		expect( resultset_21_2.getObject( "OrderDate" ) ).andReturn( buildTimestamp_25_3() );
		expect( resultset_21_2.getObject( "PaymentDate" ) ).andReturn( buildTimestamp_51_3() );
		expect( resultset_21_2.getObject( "PaymentType" ) ).andReturn( "Amazon" );
		expect( resultset_21_2.getObject( "PayPalId" ) ).andReturn( null );
		expect( resultset_21_2.getObject( "AmazonId" ) ).andReturn( "104-9127098-2444444" );
		expect( resultset_21_2.getObject( "Specification" ) ).andReturn( "D620 1.6GHZ/1GB/60GB/DVD+CDRW amazon" );
		expect( resultset_21_2.getObject( "Status" ) ).andReturn( "SHIPPED" );
		expect( resultset_21_2.getObject( "ShippedDate" ) ).andReturn( buildTimestamp_62_3() );
		expect( resultset_21_2.getObject( "TrackingNumber" ) ).andReturn( "1Z20AR764251799999" );
		expect( resultset_21_2.next() ).andReturn( false );
		replay( resultset_21_2 );
		return resultset_21_2;
	}

	protected Timestamp timestamp_25_3;

	protected Timestamp buildTimestamp_25_3()
	{
		timestamp_25_3 = new Timestamp( 1298006291000l );
		return timestamp_25_3;
	}

	protected BigDecimal bigdecimal_43_3;

	protected BigDecimal buildBigDecimal_43_3()
	{
		if ( bigdecimal_43_3 != null ) 
		{
			return bigdecimal_43_3;
		}
		bigdecimal_43_3 = createStrictMock( BigDecimal.class );
		replay( bigdecimal_43_3 );
		return bigdecimal_43_3;
	}

	protected Timestamp timestamp_51_3;

	protected Timestamp buildTimestamp_51_3()
	{
		timestamp_51_3 = new Timestamp( 1297840793000l );
		return timestamp_51_3;
	}

	protected Timestamp timestamp_62_3;

	protected Timestamp buildTimestamp_62_3()
	{
		timestamp_62_3 = new Timestamp( 1296622800000l );
		return timestamp_62_3;
	}

}
