/**
 * 
 */
package ua.nure.dominov.SummaryTask4.web.controller.command.admin.report;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.dominov.SummaryTask4.exceptions.ServiceException;
import ua.nure.dominov.SummaryTask4.util.FilesPathsStorage;
import ua.nure.dominov.SummaryTask4.util.XMLFile;
import ua.nure.dominov.SummaryTask4.util.XMLGenerator;
import ua.nure.dominov.SummaryTask4.util.ZIPArchivator;
import ua.nure.dominov.SummaryTask4.web.controller.command.AbstractCommand;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandRequestMapping;
import ua.nure.dominov.SummaryTask4.web.controller.command.annotations.CommandUserRoleCoefficient;

/**
 * @author calango
 *
 */
@CommandUserRoleCoefficient(roleCoefficient = 100)
@CommandRequestMapping(url = "/download-file")
public class ShareReportCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			ShareReportCommand.class.getName());

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executePost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String executePost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ServiceException {
		return "download-file";
	}

	/* (non-Javadoc)
	 * @see ua.nure.dominov.SummaryTask4.web.controller.command.Command#executeGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String executeGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			byte[] archive;
			XMLFile[] files = new XMLFile[2];
			files[0] = new XMLFile("user-list.xml",XMLGenerator.generateUserXMLFile());
			files[1] = new XMLFile("tour-list.xml",XMLGenerator.generateTourXMLFile());
			archive = ZIPArchivator.zipFiles(files);
			final ServletOutputStream sos = response.getOutputStream();
	        response.setContentType("application/zip");
	        response.setHeader("Content-Disposition", "attachment; filename=\"DATA.zip\"");

	        sos.write(archive);
	        sos.flush();
		} catch (IOException e) {
			LOGGER.error("Issue in sharing xml file: " + e);
		}
		return FilesPathsStorage.COMMAND_MENU;
	}

}
