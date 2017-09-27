package ua.nure.dominov.SummaryTask4.db.bo.bo.impl;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.dominov.SummaryTask4.db.bo.bo.UserBo;
import ua.nure.dominov.SummaryTask4.db.bo.factory.AbstractBoFactory;
import ua.nure.dominov.SummaryTask4.db.connectionpool.ConnectionPool;
import ua.nure.dominov.SummaryTask4.db.model.User;
import ua.nure.dominov.SummaryTask4.util.Secure;

public class UserBoImplTests {
	
	/**
	 * 
	 */
	private static UserBo userBo;
	
	/**
	 * 
	 */
	private static final String DBPROPERTYPATH = "ua/nure/dominov/SummaryTask4/properties/config/H2DbConnectionTests";
	
	/**
	 * 
	 */
	private static final String ANOTHERNAME = "Not test";
	
	/**
	 * 
	 */
	private static final long PRICE = 60;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ConnectionPool.init(DBPROPERTYPATH);
		userBo = AbstractBoFactory.getBoFactory(AbstractBoFactory.SIMPLEDEFAULTFAC).getUserBo();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ConnectionPool.dispose();
	}
	
	@Test
	public void testCRUD() throws SQLException {
		final String salt = Secure.generateSalt();
		final User user = new User("Vasya", "vasya.pupkin@proger.you", Secure.siphire("password", salt), salt);
		userBo.insert(user);
		User temp = userBo.getUserById(user.getUserId());
		if (!user.equals(temp)) {
			fail("Fail in insert.");
		}
		user.setName(ANOTHERNAME);
		userBo.updateUser(user);
		temp = userBo.getUserByEmail(user.getEmail());
		if (!user.equals(temp)) {
			fail("Fail in update.");
		}
		userBo.payForTheTour(user.getUserId(), PRICE);
		temp = userBo.getUserById(user.getUserId());
		if (user.getBalance() <= temp.getBalance()) {
			fail("Bad paying system.");
		}
		user.setName("Roman");
		user.setEmail("romg@mail.ua");
		userBo.updateFromAdmin(user.getUserId(), user.getName(), user.getEmail(), 
				user.getUserRole().getRoleId(), user.getBalance());
		temp = userBo.getUserById(user.getUserId());
		if (!user.equals(temp)) {
			fail("Admin update was failed!");
		}
		userBo.deleteUserById(user.getUserId());
	}

	@Test
	public final void testGetRoleIdFromRoleName() {
		assertEquals(1, userBo.getRoleIdFromRoleName("GUEST"));
	}

	@Test
	public final void testGetAllUsers() {
		if (userBo.getAllUsers().size() < 1) {
			fail("testGetAllUsers must be > 0");
		}
	}

	@Test
	public final void testGetAllUserRoles() {
		if (userBo.getAllUserRoles().size() < 1) {
			fail("testGetAllUserRoles must be > 0");
		}
	}

}
