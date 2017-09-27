/**
 * 
 */
package ua.nure.dominov.SummaryTask4.db.dao.dao.impl;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.model.User;
import ua.nure.dominov.SummaryTask4.db.model.UserRole;
import ua.nure.dominov.SummaryTask4.db.dao.dao.UserDao;
import ua.nure.dominov.SummaryTask4.db.dao.factory.impl.DaoFactoryImpl;

/**
 * @author calango
 *
 */
public class UserDaoImpl implements UserDao, Closeable, AutoCloseable {

	/**
	 * 
	 */
	private static final String OFFICE_COEFFICIENT = "office_coefficient";

	/**
	 * 
	 */
	private static final String ROLE_NAME = "role_name";

	/**
	 * 
	 */
	private static final String ROLE_ID = "role_id";

	/**
	 * 
	 */
	private final Connection connection;
	
	/**
	 * 
	 */
	private static final String INSERTSTATEMENT = "INSERT INTO user (user_name, user_email, user_password, user_salt, user_role, is_confirmed, purchased_tours, balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	/**
	 * 
	 */
	private static final String FINDBYIDSTATEMENT = "SELECT * FROM user INNER JOIN user_role on user_role.role_id=user.user_role WHERE user.user_id=?";

	/**
	 * 
	 */
	private static final String UPDATEUSERSTATEMENT = "UPDATE user SET user_name=?, user_email=?, user_password=?, user_salt=?, user_role=?, is_confirmed=?, purchased_tours=?, balance=? WHERE user_id=?";
	
	/**
	 * 
	 */
	private static final String UPDATEUSERFROMADMINSTATEMENT = "UPDATE user SET user_name=?, user_email=?, user_role=?, balance=? WHERE user_id=?";

	/**
	 * 
	 */
	private static final String DELETEUSERBYIDSTATEMENT = "DELETE FROM user WHERE user_id=?";

	/**
	 * 
	 */
	private static final String FINDBYEMAILSTATEMENT = "SELECT * FROM user INNER JOIN user_role ON user_role.role_id=user.user_role WHERE user_email=?";
	
	/**
	 * 
	 */
	private static final String ALLUSERSSTATEMENT = "SELECT * FROM user,user_role WHERE user_role.role_id=user.user_role ORDER BY user_role.office_coefficient DESC";
	
	/**
	 * 
	 */
	private static final String ALLUSERROLESSTATEMENT = "SELECT * FROM user_role";
	
	/**
	 * 
	 */
	private static final String GETIDFROMROLENAMESTATEMENT = "SELECT role_id FROM user_role WHERE role_name=?";
	
	/**
	 * 
	 */
	private static final String PAYFORTHETOURSTATEMENT = "UPDATE user SET balance=balance-? WHERE user_id=?";
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			UserDaoImpl.class.getName());

	/**
	 * @param connection connection from the factory
	 */
	public UserDaoImpl(final Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * @return the connection
	 */
	public final Connection getConnection() {
		return connection;
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.dao.dao.UserDao#insert(ua.nure.dominov.SummaryTask4.db.model.User)
	 */
	@Override
	public final int insert(final User user) throws SQLException {
		LOGGER.info("BEGIN INSERT");
		try (PreparedStatement prepStatement = 
				connection.prepareStatement(INSERTSTATEMENT,
						Statement.RETURN_GENERATED_KEYS)) {
			prepStatement.setString(1, user.getName());
			prepStatement.setString(2, user.getEmail());
			prepStatement.setString(3, user.getPassword());
			prepStatement.setString(4, user.getSalt());
			prepStatement.setLong(5, user.getUserRole().getRoleId());
			prepStatement.setBoolean(6, user.getIsConfirmed());
			prepStatement.setLong(7, user.getPurchasedTours());
			prepStatement.setLong(8, user.getBalance());
			final int result = prepStatement.executeUpdate();
			try (ResultSet generatedKeys = prepStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					user.setUserId(generatedKeys.getLong(1));
				}
			}
			return result;
		}
		
	}

	@Override
	public final User getUserById(final long id) {
		final User user = new User();
		final UserRole userRole = new UserRole();
		try (PreparedStatement prepStatement = connection.prepareStatement(
				FINDBYIDSTATEMENT)) {
			prepStatement.setLong(1, id);
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				if (resultSet.next()) {
					userRole.setRoleId(resultSet.getLong(ROLE_ID));
					userRole.setRoleName(resultSet.getString(ROLE_NAME));
					userRole.setOfficeCoefficient(resultSet.getShort(
							OFFICE_COEFFICIENT));
					
					user.setUserId(resultSet.getInt("user_id"));
					user.setName(resultSet.getString("user_name"));
					user.setEmail(resultSet.getString("user_email"));
					user.setPassword(resultSet.getString("user_password"));
					user.setSalt(resultSet.getString("user_salt"));
					user.setUserRole(userRole);
					user.setIsConfirmed(resultSet.getBoolean("is_confirmed"));
					user.setPurchasedTours(resultSet.getLong("purchased_tours"));
					user.setBalance(resultSet.getLong("balance"));
				}
			}
		} catch (SQLException e) {
			LOGGER.error("Issue happened in getUserById statement:" + e);
		}
		return user;
		
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.dao.dao.UserDao#updateUser(ua.nure.dominov.SummaryTask4.db.model.User)
	 */
	@Override
	public final int updateUser(final User user) {
		int result = -1;
		try (PreparedStatement prepStatement = 
				connection.prepareStatement(UPDATEUSERSTATEMENT)) {
			prepStatement.setString(1, user.getName());
			prepStatement.setString(2, user.getEmail());
			prepStatement.setString(3, user.getPassword());
			prepStatement.setString(4, user.getSalt());
			prepStatement.setLong(5, user.getUserRole().getRoleId());
			prepStatement.setBoolean(6, user.getIsConfirmed());
			prepStatement.setLong(7, user.getPurchasedTours());
			prepStatement.setLong(8, user.getBalance());
			prepStatement.setLong(9, user.getUserId());
			
			result = prepStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Issue happened in updateUser statement:" + e);
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.dao.dao.UserDao#updateFromAdmin(
	 * 	long, java.lang.String, java.lang.String, long, long)
	 */
	@Override
	public final int updateFromAdmin(final long id, final String name, 
			final String email, final long roleId, final long balance) {
		int result = -1;
		try (PreparedStatement prepStatement = connection.
				prepareStatement(UPDATEUSERFROMADMINSTATEMENT)) {
			prepStatement.setString(1, name);
			prepStatement.setString(2, email);
			prepStatement.setLong(3, roleId);
			prepStatement.setLong(4, balance);
			prepStatement.setLong(5, id);
			result = prepStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Issue happened in updateUser statement:" + e);
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.dao.dao.
	 * 	UserDao#getRoleIdFromRoleName(java.lang.String)
	 */
	@Override
	public final long getRoleIdFromRoleName(final String roleName) {
		long result = -1;
		try (PreparedStatement prepStatement = connection.
				prepareStatement(GETIDFROMROLENAMESTATEMENT)) {
			prepStatement.setString(1, roleName);
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				if (resultSet.next()) {
					result = resultSet.getLong(1);
				}
			}
		} catch (SQLException e) {
			LOGGER.error("Issue happened in updateUser statement:" + e);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.dao.dao.UserDao#deleteUserById(long)
	 */
	@Override
	public final int deleteUserById(final long id) {
		int result = -1;
		try (PreparedStatement prepStatement = connection.
				prepareStatement(DELETEUSERBYIDSTATEMENT)) {
			prepStatement.setLong(1, id);
			result = prepStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Issue happened in deleteUserById statement:" + e);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.dao.dao.
	 * 	UserDao#getUserByEmail(java.lang.String)
	 */
	@Override
	public final User getUserByEmail(final String email) {
		final User user = new User();
		final UserRole userRole = new UserRole();
		try (PreparedStatement prepStatement = connection.prepareStatement(
				FINDBYEMAILSTATEMENT)) {
			prepStatement.setString(1, email);
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				if (resultSet.next()) {
					userRole.setRoleId(resultSet.getLong(ROLE_ID));
					userRole.setRoleName(resultSet.getString(ROLE_NAME));
					userRole.setOfficeCoefficient(resultSet.
							getShort(OFFICE_COEFFICIENT));
					
					user.setUserId(resultSet.getInt("user_id"));
					user.setName(resultSet.getString("user_name"));
					user.setEmail(resultSet.getString("user_email"));
					user.setPassword(resultSet.getString("user_password"));
					user.setSalt(resultSet.getString("user_salt"));
					user.setUserRole(userRole);
					user.setIsConfirmed(resultSet.getBoolean("is_confirmed"));
					user.setPurchasedTours(resultSet.getLong("purchased_tours"));
					user.setBalance(resultSet.getLong("balance"));
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return user;
	}
	
	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.dao.dao.UserDao#getAllUsers()
	 */
	@Override
	public final List<User> getAllUsers() {
		final List<User> list = new LinkedList<>();
		User user;
		UserRole userRole;
		try (PreparedStatement prepStatement = connection.prepareStatement(
				ALLUSERSSTATEMENT)) {
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				while (resultSet.next()) {
					user = new User();
					userRole = new UserRole();
					
					userRole.setRoleId(resultSet.getLong(ROLE_ID));
					userRole.setRoleName(resultSet.getString(ROLE_NAME));
					userRole.setOfficeCoefficient(resultSet.
							getShort(OFFICE_COEFFICIENT));
					
					user.setUserId(resultSet.getInt("user_id"));
					user.setName(resultSet.getString("user_name"));
					user.setEmail(resultSet.getString("user_email"));
					user.setPassword(resultSet.getString("user_password"));
					user.setSalt(resultSet.getString("user_salt"));
					user.setUserRole(userRole);
					user.setIsConfirmed(resultSet.getBoolean("is_confirmed"));
					user.setPurchasedTours(resultSet.getLong("purchased_tours"));
					user.setBalance(resultSet.getLong("balance"));
					
					list.add(user);
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return list;
	}
	
	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.dao.dao.UserDao#getAllUserRoles()
	 */
	@Override
	public final List<UserRole> getAllUserRoles() {
		final List<UserRole> list = new LinkedList<>();
		UserRole userRole;
		try (PreparedStatement prepStatement = connection.prepareStatement(
				ALLUSERROLESSTATEMENT)) {
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				while (resultSet.next()) {
					userRole = new UserRole();
					
					userRole.setRoleId(resultSet.getLong(ROLE_ID));
					userRole.setRoleName(resultSet.getString(ROLE_NAME));
					userRole.setOfficeCoefficient(resultSet.
							getShort(OFFICE_COEFFICIENT));
					
					list.add(userRole);
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.dao.dao.UserDao#payForTheTour(long, long)
	 */
	@Override
	public final int payForTheTour(final long id, final long price) {
		int result = -1;
		try (PreparedStatement preparedStatement = connection.
				prepareStatement(PAYFORTHETOURSTATEMENT)) {
			preparedStatement.setLong(1, price);
			preparedStatement.setLong(2, id);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Issue happened in 322 line " + e);
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.io.Closeable#close()
	 */
	@Override
	public final void close() throws IOException {
		DaoFactoryImpl.getConnectionPool().releaseConnection(connection);
	}

}