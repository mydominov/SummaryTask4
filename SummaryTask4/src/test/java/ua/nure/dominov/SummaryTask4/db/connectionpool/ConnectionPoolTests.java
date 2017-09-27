/**
 * 
 */
package ua.nure.dominov.SummaryTask4.db.connectionpool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.MissingResourceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author calango
 *
 */
public class ConnectionPoolTests {
	
	/**
	 * 
	 */
	private static final String DBPROPERTYPATH = "ua/nure/dominov/SummaryTask4/properties/config/H2DbConnectionTests";
	
	/**
	 * 
	 */
	private static final String INVALIDPATH = "INVALIDPATH/PROPERTY";
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ConnectionPool.init(DBPROPERTYPATH);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ConnectionPool.dispose();
	}

	/**
	 * Test method for {@link ua.nure.dominov.SummaryTask4.db.connectionpool.ConnectionPool#getInstance()}.
	 * @throws SQLException 
	 */
	@Test
	public void testConnectionPoolSimpleTest() throws SQLException {
		ConnectionPool.init(DBPROPERTYPATH);
		final ConnectionPool connectionPool = ConnectionPool.getInstance();
		final Connection connection = connectionPool.takeConnection();
		connectionPool.releaseConnection(connection);
		ConnectionPool.dispose();
	}

	/**
	 * Test method for {@link ua.nure.dominov.SummaryTask4.db.connectionpool.ConnectionPool#getInstance()}.
	 * @throws SQLException 
	 */
	@Test
	public void testConnectionPoolDoubleInitialization() throws SQLException {
		ConnectionPool.init(DBPROPERTYPATH);
		ConnectionPool.init(DBPROPERTYPATH);
		ConnectionPool.dispose();
		ConnectionPool.dispose();
	}
	
	/**
	 * Test method for {@link ua.nure.dominov.SummaryTask4.db.connectionpool.ConnectionPool#getInstance()}.
	 * @throws SQLException 
	 */
	@Test(expected=MissingResourceException.class)
	public void testConnectionPoolDangerousTest() throws SQLException {
		ConnectionPool.init(INVALIDPATH);
		final ConnectionPool connectionPool = ConnectionPool.getInstance();
		connectionPool.takeConnection();
	}
	
	/**
	 * Test method for {@link ua.nure.dominov.SummaryTask4.db.connectionpool.ConnectionPool#getInstance()}.
	 * @throws SQLException 
	 */
	public void testConnectionPoolClosedConnection() throws SQLException {
		ConnectionPool.init(DBPROPERTYPATH);
		final ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = connectionPool.takeConnection();
		connection.close();
		connectionPool.releaseConnection(connection);
		ConnectionPool.dispose();
	}
	
}
