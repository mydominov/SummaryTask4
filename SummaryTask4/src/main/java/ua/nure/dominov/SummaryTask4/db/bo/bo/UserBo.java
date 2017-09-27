/**
 * The main Business Object interface.
 */
package ua.nure.dominov.SummaryTask4.db.bo.bo;

import java.sql.SQLException;
import java.util.List;

import ua.nure.dominov.SummaryTask4.db.model.User;
import ua.nure.dominov.SummaryTask4.db.model.UserRole;

/**
 * @author calango
 *
 */
public interface UserBo {
	
	/**
	 * @param user new potential client
	 * @throws SQLException ex
	 * @return logical result of this operation (true/false)
	 */
	boolean insert(User user) throws SQLException;
	
	/**
	 * @param id user's id
	 * @return user
	 */
	User getUserById(long userId);
	
	/**
	 * @param email user's email
	 * @return user
	 */
	User getUserByEmail(String email);
	
	/**
	 * @param user edited user
	 * @return logical result of this operation (true/false)
	 */
	boolean updateUser(User user);
	
	/**
	 * @param id user's id
	 * @param name user's new name
	 * @param email user's new email
	 * @param roleId user's new role
	 * @param balance user's increased balance
	 * @return logical result of this operation (true/false)
	 */
	boolean updateFromAdmin(long userId, String name, String email, 
			long roleId, long balance);
	
	/**
	 * @param roleName user role
	 * @return id of the role
	 */
	long getRoleIdFromRoleName(String roleName);
	
	/**
	 * @param id user's id 
	 * @return logical result of this operation (true/false)
	 */
	boolean deleteUserById(long userId);
	
	/**
	 * @return user list
	 */
	List<User> getAllUsers();
	
	/**
	 * @return user role list
	 */
	List<UserRole> getAllUserRoles();
	
	/**
	 * @param id tour id
	 * @param price tour price
	 * @return logical result of this operation (true/false)
	 */
	boolean payForTheTour(long userId, long price);
	
}