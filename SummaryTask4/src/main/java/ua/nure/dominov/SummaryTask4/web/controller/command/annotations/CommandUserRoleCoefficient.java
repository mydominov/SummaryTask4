package ua.nure.dominov.SummaryTask4.web.controller.command.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author calango
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandUserRoleCoefficient {
	
	/**
	 * @return
	 */
	int roleCoefficient() default 0;
	
}