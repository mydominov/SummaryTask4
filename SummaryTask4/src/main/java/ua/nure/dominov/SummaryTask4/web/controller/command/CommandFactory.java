package ua.nure.dominov.SummaryTask4.web.controller.command;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.db.model.User;
import ua.nure.dominov.SummaryTask4.db.model.UserRole;
import ua.nure.dominov.SummaryTask4.exceptions.CommandException;
import ua.nure.dominov.SummaryTask4.web.controller.command.commandutils.
	CommandGenerator;


/**
 * @author calango
 *
 */
public final class CommandFactory {
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			CommandFactory.class.getName());
	
	/**
	 * 
	 */
	private static Map<String, CommandEntity> map;
	
	/**
	 * 
	 */
	private CommandFactory() {
		super();
	}

	/**
	 * @param pack package where to search
	 */
	public static void init(final String pack) {
		try {
			map = CommandGenerator.scan(pack);
		} catch (InstantiationException | IllegalAccessException e) {
			LOGGER.error("Issue in init:" + e);
		}
	}

	/**
	 * @param session http session
	 * @param comEntity command entity
	 * @return is command allowed
	 */
	public static boolean isCommandAllowed(final HttpSession session, 
			final CommandEntity comEntity) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			user = new User();
			final UserRole userRole = new UserRole("GUEST", 1);
			user.setUserRole(userRole);
		}
		LOGGER.info("User role => " + user.getUserRole().getRoleName());
		return comEntity.getAccessRoleCoefficient() <= user.getUserRole().
				getOfficeCoefficient();
	}

	/**
	 * @param session http session
	 * @param path user path request
	 * @return command class
	 */
	public static AbstractCommand getCommand(final HttpSession session, 
			final String path) {
		try {
			LOGGER.info(path);
			
			final CommandEntity appropriateCommandEntity = map.get(path);
			if (appropriateCommandEntity == null) {
				throw new CommandException("No Command");
			} else {
				if (isCommandAllowed(session, appropriateCommandEntity)) {
					return appropriateCommandEntity.getCommandObject();
				}
			}
		} catch (CommandException e) {
			LOGGER.error("Issue in getCommand:" + e);
		}
		return new NoCommand();
	}
	
}
