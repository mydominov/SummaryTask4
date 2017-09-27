package ua.nure.dominov.SummaryTask4.db.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ConnectionPool create and manage pool of database connections.
 */
public final class ConnectionPool {

	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			ConnectionPool.class.getName());
	
	/**
	 * 
	 */
	public static final int DEFAULT_POOL_SIZE = 10;
	
	/** Single instance. */
	private static volatile ConnectionPool instance;
	
	/** Free connections queue. */
	private final BlockingQueue<Connection> connectionQueue;

	/**
	 * @param path db config file path
	 * @throws SQLException exception
	 */
	public static void init(final String path) throws SQLException {
		LOGGER.info("Initialization of the pool.");
		if (instance == null) {
			final ResourceBundle resourceBundle = ResourceBundle.getBundle(
					path);
			final String driver = resourceBundle.getString("db.driver");
			final String url = resourceBundle.getString("db.url");
			final String user = resourceBundle.getString("db.user");
			final String password = resourceBundle.getString("db.password");
			final String poolSizeStr = resourceBundle.getString("db.poolsize");
			final int poolSize = (poolSizeStr != null) ? Integer
					.parseInt(poolSizeStr) : DEFAULT_POOL_SIZE;
			try {
				LOGGER.info("Trying to create pool of connections...");
				instance = new ConnectionPool(driver, url, user, password,
						poolSize);
				LOGGER.info("Connection pool was succesfully initialized");
			} catch (ClassNotFoundException e) {
				LOGGER.error("Driver " + driver + " not found " + e);
			}
		}
	}

	/**
	 * @throws SQLException ex
	 */
	public static void dispose() throws SQLException {
		LOGGER.info("Disposing connection pool.");
		if (instance != null) {
			instance.clearConnectionQueue();
			instance = null;
			LOGGER.info("Connection pool was succesfully disposed");
		} else {
			LOGGER.warn("Connection pool was already disposed");
		}
	}

	/**
	 * @return instance
	 */
	public static ConnectionPool getInstance() {
		return instance;
	}

	/**
	 * @param driver driver name
	 * @param url URL address
	 * @param user user name
	 * @param password your password in DB
	 * @param poolSize maximum size of this pool
	 * @throws ClassNotFoundException ex
	 * @throws SQLException ex
	 */
	private ConnectionPool(final String driver, final String url, 
			final String user, final String password, final int poolSize)
					throws ClassNotFoundException, SQLException {
		LOGGER.info("Creating connections.");
		Class.forName(driver);
		connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
		for (int i = 0; i < poolSize; i++) {
			final Connection connection = DriverManager.getConnection(url, user,
					password);
			connectionQueue.offer(connection);
		}
	}

	/**
	 * @return connection
	 */
	public Connection takeConnection() {
		LOGGER.info("Taking connection");
		try {
			return connectionQueue.take();
		} catch (InterruptedException e) {
			LOGGER.error("Free connection waiting was interrupted", e);
			return null;
		}
	}

	/**
	 * @param connection pool's connection
	 */
	public void releaseConnection(final Connection connection) {
		LOGGER.info("Releasing connection.");
		try {
			if (!connection.isClosed()) {
				if (!connectionQueue.offer(connection)) {
					LOGGER.warn("Connection not added. Possible `leakage` of connections");
				} else {
					LOGGER.info("Connection was released successfully!");
				}
			} else {
				LOGGER.warn("Trying to release closed connection. Possible `leakage` of connections");
			}
		} catch (SQLException e) {
			LOGGER.error("SQLException at conection isClosed () checking. Connection not added", e);
		}
	}

	/**
	 * @throws SQLException ex
	 */
	private void clearConnectionQueue() throws SQLException {
		LOGGER.info("Clearing connection queue.");
		Connection connection;
		while ((connection = connectionQueue.poll()) != null) {
			/* see java.sql.Connection#close () javadoc */
			if (!connection.getAutoCommit()) {
				connection.commit();
			}
			connection.close();
		}
		LOGGER.info("Connection queue was cleared");
	}
}