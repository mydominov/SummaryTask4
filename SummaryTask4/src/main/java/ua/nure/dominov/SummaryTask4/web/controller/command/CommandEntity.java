/**
 * 
 */
package ua.nure.dominov.SummaryTask4.web.controller.command;

/**
 * @author calango
 *
 */
public class CommandEntity {
	
	/**
	 * 
	 */
	private int accessRoleCoefficient;
	
	/**
	 * 
	 */
	private AbstractCommand commandObject;
	
	private final int prime = 31;

	/**
	 * @param accessRoleCoefficient
	 * @param commandObject
	 */
	public CommandEntity(final int accessRoleCoefficient, 
			final AbstractCommand commandObject) {
		super();
		this.accessRoleCoefficient = accessRoleCoefficient;
		this.commandObject = commandObject;
	}

	/**
	 * 
	 */
	public CommandEntity() {
		super();
	}

	/**
	 * @return
	 */
	public final int getAccessRoleCoefficient() {
		return accessRoleCoefficient;
	}

	/**
	 * @param accessRoleCoefficient
	 */
	public final void setAccessRoleCoefficient(final int accessRoleCoefficient) {
		this.accessRoleCoefficient = accessRoleCoefficient;
	}

	/**
	 * @return
	 */
	public final AbstractCommand getCommandObject() {
		return commandObject;
	}

	/**
	 * @param commandObject
	 */
	public final void setCommandObject(final AbstractCommand commandObject) {
		this.commandObject = commandObject;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode() {
		int result = 1;
		result = prime * result + accessRoleCoefficient * prime 
				+ commandObject.hashCode();
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		CommandEntity other = (CommandEntity) obj;
		if (commandObject == null) {
			if (other.commandObject != null) {
				return false;
			}
		} else if (!commandObject.equals(other.commandObject)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return "CommandEntity: [accessRole=" + accessRoleCoefficient 
				+ ", commandObject=" + commandObject + "]";
	}

	/**
	 * @return
	 */
	public final boolean isEmpty() {
		return accessRoleCoefficient == 0 || commandObject == null;
	}
	
}
