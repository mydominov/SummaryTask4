/**
 * 
 */
package ua.nure.dominov.SummaryTask4.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.transform.JDOMSource;

import ua.nure.dominov.SummaryTask4.db.bo.bo.TourBo;
import ua.nure.dominov.SummaryTask4.db.bo.factory.AbstractBoFactory;
import ua.nure.dominov.SummaryTask4.db.model.Tour;
import ua.nure.dominov.SummaryTask4.db.model.User;

/**
 * @author calango
 *
 */
public final class XMLGenerator {
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			XMLGenerator.class.getName());
	
	/**
	 * 
	 */
	private XMLGenerator() {
		super();
	}
	
	/**
	 * @return xml user file
	 */
	public static byte[] generateUserXMLFile() {
		byte[] userXMLFile = null;
		final List<User> list = AbstractBoFactory.getBoFactory(AbstractBoFactory.SIMPLEDEFAULTFAC).
				getUserBo().getAllUsers();
		try {
			final Element root = new Element("users");
			final Document document = new Document(root);
			Element password;
			Element userRole;
			
			Element userElement;
			for (User user : list) {
				userElement = new Element("user");
				userElement.setAttribute(new Attribute(
						"id", String.valueOf(user.getUserId())));
				userElement.setAttribute("isConfirmed", 
						String.valueOf(user.getIsConfirmed()));
				userElement.addContent(new Element("name").
						setText(user.getName()));
				userElement.addContent(new Element("email").
						setText(user.getEmail()));
				password = new Element("password");
				password.setAttribute("salt", user.getSalt());
				password.setText(user.getPassword());
				userElement.addContent(password);
				userRole = new Element("user-role");
				userRole.setAttribute("id", String.valueOf(
						user.getUserRole().getRoleId()));
				userRole.addContent(new Element("role-name").setText(
						user.getUserRole().getRoleName()));
				userRole.addContent(new Element("strength").
						setText(String.valueOf(user.getUserRole().
								getOfficeCoefficient())));
				userElement.addContent(userRole);
				userElement.addContent(new Element("number-of-purchased-tours").
						setText(String.valueOf(user.getPurchasedTours())));
				userElement.addContent(new Element("balance").
						setText(String.valueOf(user.getBalance())));
				document.getRootElement().addContent(userElement); 
			}
			
			try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
				final StreamResult streamResult = new StreamResult(bos);
				final TransformerFactory transformerFactory = TransformerFactory.
						newInstance();
				final Transformer transformer = transformerFactory.newTransformer();
				final JDOMSource source = new JDOMSource(document);
				transformer.transform(source, streamResult);
				userXMLFile = bos.toByteArray();
			}
		} catch (TransformerException | IOException e) {
			LOGGER.error("Issue in generateUserXMLFile(): " + e);
		}
		return userXMLFile;
	}
	
	/**
	 * @return xml tour file
	 */
	public static byte[] generateTourXMLFile() {
		byte[] userXMLFile = null;
		final TourBo tourBo = AbstractBoFactory.getBoFactory(AbstractBoFactory.SIMPLEDEFAULTFAC).
				getTourBo();
		final List<Tour> freeTours = tourBo.getFreeTours();
		try {
			final Element root = new Element("tours");
			final Document document = new Document(root);
			Element typeOfHoliday;
			Element hotelType;
			Element status;
			
			Element tourElement;
			for (Tour tour : freeTours) {
				tourElement = new Element("tour");
				tourElement.setAttribute("id", String.valueOf(tour.getTourId()));
				
				tourElement.addContent(new Element("name").
						setText(tour.getTourName()));
				tourElement.addContent(new Element("price").
						setText(String.valueOf(tour.getPrice())));
				typeOfHoliday = new Element("type-of-holiday");
				typeOfHoliday.setAttribute("id", String.valueOf(
						tour.getTypeOfHoliday().getTypeOfHolidayId()));
				typeOfHoliday.addContent(new Element("holiday-type-name").
						setText(tour.getTypeOfHoliday().getHolidayName()));
				tourElement.addContent(typeOfHoliday);
				tourElement.addContent(new Element("number-of-people").
						setText(String.valueOf(tour.getNumberOfPeople())));
				hotelType = new Element("hotel-type");
				hotelType.setAttribute("id", String.valueOf(tour.
						getHotelType().getHotelTypeId()));
				hotelType.addContent(new Element("hotel-type-name").
						setText(tour.getHotelType().getTypeName()));
				tourElement.addContent(hotelType);
				status = new Element("status");
				status.setAttribute("id", String.valueOf(tour.
						getTourStatus().getStatusId()));
				status.addContent(new Element("status-name").
						setText(tour.getTourStatus().getStatusName()));
				tourElement.addContent(status);
				tourElement.addContent(new Element("isHot").
						setText(String.valueOf(tour.getIsHot())));
				tourElement.addContent(new Element("max-discount").
						setText(String.valueOf(tour.getMaxDiscount())));
				tourElement.addContent(new Element("step-of-discount").
						setText(String.valueOf(tour.getStepDiscount())));
				tourElement.addContent(new Element("increment-of-discount").
						setText(String.valueOf(tour.getIncrementDiscount())));
				document.getRootElement().addContent(tourElement); 
			}
			final List<Tour> bookedTours = tourBo.getBookedTours();
			
			Element user;
			Element password;
			Element userRole;
			for (Tour tour : bookedTours) {
				tourElement = new Element("tour");
				tourElement.setAttribute("id", String.valueOf(tour.getTourId()));
				
				tourElement.addContent(new Element("name").
						setText(tour.getTourName()));
				tourElement.addContent(new Element("price").
						setText(String.valueOf(tour.getPrice())));
				typeOfHoliday = new Element("type-of-holiday");
				typeOfHoliday.setAttribute("id", String.valueOf(tour.
						getTypeOfHoliday().getTypeOfHolidayId()));
				typeOfHoliday.addContent(new Element("holiday-type-name").
						setText(tour.getTypeOfHoliday().getHolidayName()));
				tourElement.addContent(typeOfHoliday);
				tourElement.addContent(new Element("number-of-people").
						setText(String.valueOf(tour.getNumberOfPeople())));
				hotelType = new Element("hotel-type");
				hotelType.setAttribute("id", String.valueOf(tour.
						getHotelType().getHotelTypeId()));
				hotelType.addContent(new Element("hotel-type-name").
						setText(tour.getHotelType().getTypeName()));
				tourElement.addContent(hotelType);
				status = new Element("status");
				status.setAttribute("id", String.valueOf(tour.
						getTourStatus().getStatusId()));
				status.addContent(new Element("status-name").setText(
						tour.getTourStatus().getStatusName()));
				tourElement.addContent(status);
				
				user = new Element("reserved-by");
				user.setAttribute(new Attribute(
						"id", String.valueOf(tour.getReservedBy().getUserId())));
				user.setAttribute("isConfirmed", String.valueOf(tour.
						getReservedBy().getIsConfirmed()));
				user.addContent(new Element("name").
						setText(tour.getReservedBy().getName()));
				user.addContent(new Element("email").
						setText(tour.getReservedBy().getEmail()));
				password = new Element("password");
				password.setAttribute("salt", tour.getReservedBy().getSalt());
				password.setText(tour.getReservedBy().getPassword());
				user.addContent(password);
				userRole = new Element("user-role");
				userRole.setAttribute("id", String.valueOf(
						tour.getReservedBy().getUserRole().getRoleId()));
				userRole.addContent(new Element("role-name").setText(
						tour.getReservedBy().getUserRole().getRoleName()));
				userRole.addContent(new Element("strength").
						setText(String.valueOf(tour.getReservedBy().
								getUserRole().
								getOfficeCoefficient())));
				user.addContent(userRole);
				user.addContent(new Element("number-of-purchased-tours").
						setText(String.valueOf(tour.getReservedBy().
								getPurchasedTours())));
				user.addContent(new Element("balance").
						setText(String.valueOf(tour.getReservedBy().
								getBalance())));
				tourElement.addContent(user);
				
				tourElement.addContent(new Element("isHot").
						setText(String.valueOf(tour.getIsHot())));
				tourElement.addContent(new Element("max-discount").
						setText(String.valueOf(tour.getMaxDiscount())));
				tourElement.addContent(new Element("step-of-discount").
						setText(String.valueOf(tour.getStepDiscount())));
				tourElement.addContent(new Element("increment-of-discount").
						setText(String.valueOf(tour.getIncrementDiscount())));
				document.getRootElement().addContent(tourElement); 
			}
			
			try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
				final StreamResult streamResult = new StreamResult(bos);
				final TransformerFactory transformerFactory = TransformerFactory.
					newInstance();
				final Transformer transformer = transformerFactory.newTransformer();
				final JDOMSource source = new JDOMSource(document);
				transformer.transform(source, streamResult);
				userXMLFile = bos.toByteArray();
			}
		} catch (TransformerException | IOException e) {
			LOGGER.error("Issue in generateUserXMLFile(): " + e);
		}
		return userXMLFile;
	}
	
}
