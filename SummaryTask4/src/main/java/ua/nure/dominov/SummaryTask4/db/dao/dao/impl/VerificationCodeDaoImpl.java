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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.dao.dao.VerificationCodeDao;
import ua.nure.dominov.SummaryTask4.db.dao.factory.impl.DaoFactoryImpl;
import ua.nure.dominov.SummaryTask4.db.model.User;
import ua.nure.dominov.SummaryTask4.db.model.UserRole;
import ua.nure.dominov.SummaryTask4.db.model.VerificationCode;

/**
 * @author calango
 *
 */
public class VerificationCodeDaoImpl implements VerificationCodeDao,
	Closeable, AutoCloseable {

	/**
	 * 
	 */
	private final Connection connection;

	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			VerificationCodeDaoImpl.class.getName());
	
	/**
	 * 
	 */
	private static final String INSERTSTATEMENT = "INSERT INTO verification_code (user_id, user_code) VALUES (?, ?)";

	/**
	 * 
	 */
	private static final String FINDBYCODESTATEMENT = "SELECT * FROM verification_code INNER JOIN user on verification_code.user_id=user.user_id INNER JOIN user_role ON user_role.role_id=user.user_role WHERE user_code=?";

	/**
	 * 
	 */
	private static final String GETUSERID = "SELECT user_id FROM verification_code WHERE user_code=?";
	
	/**
	 * 
	 */
	private static final String DELETESTATEMENTBYCODE = "DELETE FROM verification_code WHERE user_code=?";
	
	/**
	 * 
	 */
	private static final String GETSTATUS = "SELECT user_role.role_name FROM user_role INNER JOIN user ON user.user_role=user_role.role_id WHERE user.user_id=?";
	
	/**
	 * 
	 */
	private static final String CHANGEUSERSTATUS = "UPDATE user SET user_role=?,is_confirmed=? WHERE user_id=?";
	
	/**
	 * 
	 */
	private static final String CHANGEUSERBOOL = "UPDATE user SET is_confirmed=? WHERE user_id=?";
	
	/**
	 * 
	 */
	private static final String GETUSERMAILBYID = "SELECT user_email FROM user WHERE user_id=?";
	
	/**
	 * @param connection connection from the factory
	 */
	public VerificationCodeDaoImpl(final Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * @return the connection
	 */
	public final Connection getConnection() {
		return connection;
	}
	
	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.dao.dao.VerificationCodeDao#insert(ua.nure.dominov.SummaryTask4.db.model.VerificationCode)
	 */
	@Override
	public final int insert(final VerificationCode verCode) {
		int result = -1;
		try (PreparedStatement prepStatement = connection.prepareStatement(
				INSERTSTATEMENT, Statement.RETURN_GENERATED_KEYS)) {
			prepStatement.setLong(1, verCode.getUser().getUserId());
			prepStatement.setInt(2, verCode.getVerificationUserCode());
			result = prepStatement.executeUpdate();
			try (ResultSet generatedKeys = prepStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					verCode.setVerifCodeId(generatedKeys.getLong(1));
				}
			}
		} catch (SQLException e) {
			LOGGER.error("Issue happened in insert statement:" + e);
		}
		return result;
		
	}
	
	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.dao.dao.VerificationCodeDao#getVerificationCode(int)
	 */
	@Override
	public final VerificationCode getVerificationCode(final int userCode) {
		final VerificationCode verCode = new VerificationCode();
		final User user = new User();
		final UserRole userRole = new UserRole();
		try (PreparedStatement prepStatement = connection.
				prepareStatement(FINDBYCODESTATEMENT)) {
			prepStatement.setLong(1, userCode);
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				if (resultSet.next()) {
					userRole.setRoleId(resultSet.getLong("role_id"));
					userRole.setRoleName(resultSet.getString("role_name"));
					userRole.setOfficeCoefficient(resultSet.
							getShort("office_coefficient"));
					
					user.setUserId(resultSet.getInt("user_id"));
					user.setName(resultSet.getString("user_name"));
					user.setEmail(resultSet.getString("user_email"));
					user.setPassword(resultSet.getString("user_password"));
					user.setSalt(resultSet.getString("user_salt"));
					user.setUserRole(userRole);
					user.setIsConfirmed(resultSet.getBoolean("is_confirmed"));
					
					verCode.setVerifCodeId(resultSet.getLong("code_id"));
					verCode.setVerificationUserCode(resultSet.getInt("user_code"));
					verCode.setUser(user);
				}
			}
		} catch (SQLException e) {
			LOGGER.error("Issue happened in getVerificationCode statement:" + e);
		}
		return verCode;
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.db.dao.dao.VerificationCodeDao#deleteVerificationCodeAndGetUserMail(int)
	 */
	@Override
	public final String deleteVerificationCodeAndGetUserMail(final int code) {
		String email = null;
		try {
			connection.setAutoCommit(false);
			long userId = -1;
			final UserRole status = new UserRole();
			try (PreparedStatement prepStatement = connection.prepareStatement(
					GETUSERID)) {
				prepStatement.setInt(1, code);
				try (ResultSet resultSet = prepStatement.executeQuery()) {
					if (resultSet.next()) {
						userId = resultSet.getLong(1);
					}
				}
			} 
			if (userId == -1) {
				connection.rollback();
			}
			try (PreparedStatement prepStatement = connection.prepareStatement(
					DELETESTATEMENTBYCODE)) {
				prepStatement.setInt(1, code);
				prepStatement.execute();
			}
			
			try (PreparedStatement prepStatement = connection.prepareStatement(
					GETSTATUS)) {
				prepStatement.setLong(1, userId);
				try (ResultSet resultSet = prepStatement.executeQuery()) {
					if (resultSet.next()) {
						status.setRoleName(resultSet.getString(1));
					}
				}
			}
			if (status.getRoleName().equals("UNCONFIRMED")) {
				try (PreparedStatement prepstatement = connection.
						prepareStatement(CHANGEUSERSTATUS)) {
					prepstatement.setLong(1, 4);
					prepstatement.setBoolean(2, true);
					prepstatement.setLong(3, userId);
					prepstatement.execute();
				}
			} else {
				try (PreparedStatement prepstatement = connection.
						prepareStatement(CHANGEUSERBOOL)) {
					prepstatement.setBoolean(1, true);
					prepstatement.setLong(2, userId);
					prepstatement.execute();
				}
			}
			try (PreparedStatement prepStatement = 
					connection.prepareStatement(GETUSERMAILBYID)) {
				prepStatement.setLong(1, userId);
				try (ResultSet resultSet = prepStatement.executeQuery()) {
					if (resultSet.next()) {
						email = resultSet.getString(1);
					}
				}
			}
			connection.commit();
		} catch (SQLException e) {
			LOGGER.error("Issue happened in deleteVerificationCode statement:" + e);
		}
		return email;
		
	}

	@Override
	public final void close() throws IOException {
		DaoFactoryImpl.getConnectionPool().releaseConnection(connection);
		
	}
	
}
