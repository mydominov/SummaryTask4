/**
 * 
 */
package ua.nure.dominov.SummaryTask4.web.controller.command.commandutils;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;

import ua.nure.dominov.SummaryTask4.web.controller.command.AbstractCommand;
import ua.nure.dominov.SummaryTask4.web.controller.command.CommandEntity;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandUserRoleCoefficient;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandRequestMapping;

/**
 * @author calango
 *
 */
public final class CommandGenerator {
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			CommandGenerator.class.getName());
	
	/**
	 * 
	 */
	private CommandGenerator() { 
		super();
	}
	
	/**
	 * @param packageToScan
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static Map<String, CommandEntity> scan(
			final String packageToScan) throws InstantiationException, 
				IllegalAccessException {
		LOGGER.info("Generating begins");
		final Map<String, CommandEntity> actions = new HashMap<>();

		final Reflections reflections = new Reflections(packageToScan);
		final Set<Class<? extends AbstractCommand>> subTypes = reflections.
				getSubTypesOf(AbstractCommand.class);

		for (Class<? extends AbstractCommand> i : subTypes) {
			CommandRequestMapping key = null;
			CommandEntity commandEntity = new CommandEntity();

			for (Annotation j : i.getAnnotations()) {
				if (j instanceof CommandRequestMapping) {
					key = (CommandRequestMapping) j;
				}

				if (j instanceof CommandUserRoleCoefficient) {
					CommandUserRoleCoefficient act = 
							(CommandUserRoleCoefficient) j;
					commandEntity.setAccessRoleCoefficient(
							act.roleCoefficient());
				}
			}

			commandEntity.setCommandObject(i.newInstance());

			if (!commandEntity.isEmpty()) {
				actions.put(key.url(), commandEntity);
			}
		}
		LOGGER.info("Generating ends");
		/*LOGGER.info("List of commands:");
		for (Map.Entry<String, CommandEntity> entry : actions.entrySet()) {
			
			System.out.println(entry.getKey());
		}*/
		return actions;
	}

}
