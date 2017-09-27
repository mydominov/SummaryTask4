package ua.nure.dominov.SummaryTask4.db.dao.dao;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.List;

import ua.nure.dominov.SummaryTask4.db.model.User;
import ua.nure.dominov.SummaryTask4.db.model.UserRole;

/**
 * @author calango
 *
 */
public interface UserDao extends Closeable, AutoCloseable {
	
	/**
	 * @param user
	 */
	int insert(User user) throws SQLException;
	
	/**
	 * @param id
	 * @return user
	 */
	User getUserById(long userId);
	
	/**
	 * @param email
	 * @return user
	 */
	User getUserByEmail(String email);
	
	/**
	 * @param user
	 */
	int updateUser(User user);
	
	/**
	 * @param id
	 * @param name
	 * @param email
	 * @param roleId
	 * @return
	 */
	int updateFromAdmin(final long userId, String name, String email, long roleId,
			long balance);
	
	/**
	 * @param roleName
	 * @return
	 */
	long getRoleIdFromRoleName(String roleName);
	
	/**
	 * @param id user's id
	 */
	int deleteUserById(long userId);
	
	/**
	 * @return
	 */
	List<User> getAllUsers();
	
	/**
	 * @return
	 */
	List<UserRole> getAllUserRoles();
	
	/**
	 * @param id
	 * @param price
	 * @return
	 */
	int payForTheTour(long userId, long price);
	
}