/**
 * 
 */
package ua.nure.dominov.SummaryTask4.web.controller.command.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo;
import ua.nure.dominov.SummaryTask4.db.bo.bo.UserBo;
import ua.nure.dominov.SummaryTask4.db.bo.factory.AbstractBoFactory;
import ua.nure.dominov.SummaryTask4.db.model.Tour;
import ua.nure.dominov.SummaryTask4.db.model.User;
import ua.nure.dominov.SummaryTask4.db.model.UserRole;
import ua.nure.dominov.SummaryTask4.exceptions.ServiceException;
import ua.nure.dominov.SummaryTask4.util.FilesPathsStorage;
import ua.nure.dominov.SummaryTask4.web.controller.command.AbstractCommand;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandRequestMapping;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandUserRoleCoefficient;

/**
 * @author calango
 *
 */
@CommandUserRoleCoefficient(roleCoefficient = 100)
@CommandRequestMapping(url = "/admin/user-list")
public class UserList extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executePost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executePost(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException, 
				ServletException, ServiceException {
		final HttpSession session = request.getSession();
		
		final AbstractBoFactory boFactory = AbstractBoFactory.getBoFactory(
				AbstractBoFactory.SIMPLEDEFAULTFAC);
		final UserBo userBo = boFactory.getUserBo();
		final TourBo tourBo = boFactory.getTourBo();
		final List<User> userList = userBo.getAllUsers();
		final List<UserRole> userRoleList = userBo.getAllUserRoles();
		for (User user : userList) {
			Tour tour = tourBo.getTheMostExpensiveUserTour(user.getUserId());
			if (tour != null) {
				user.setTheMostExpensiveName(tour.getTourName());
				user.setTheMostExpensivePrice(tour.getPrice());
			}
		}
		session.setAttribute("userList", userList);
		session.setAttribute("userRoleList", userRoleList);
		return "user-list";
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executeGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public final String executeGet(final HttpServletRequest request, 
			final HttpServletResponse response) {
		return FilesPathsStorage.PAGE_USER_LIST;
	}

}
