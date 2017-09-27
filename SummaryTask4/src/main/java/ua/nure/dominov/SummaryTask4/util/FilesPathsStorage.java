/**
 * 
 */
package ua.nure.dominov.SummaryTask4.util;

/**
 * @author calango
 *
 */
public final class FilesPathsStorage {
	
	//Pages
	private static final String PAGEPREFIX = "/WEB-INF/pages";
	public static final String PAGE_MAIN_PAGE = "/index.jsp";
	public static final String PAGE_LOGIN = "/login.jsp";
	public static final String PAGE_ERROR_PAGE = PAGEPREFIX + "/error_page.jsp";
	public static final String PAGE_CONTACT_US = "/contacts.jsp";
	public static final String PAGE_SETTINGS = PAGEPREFIX + "/settings.jsp";
	public static final String PAGE_CONFIRM_REDIRECT = PAGEPREFIX + "/redirect.jsp";
	public static final String PAGE_MAIL_CONFIRM_SUCCESS = PAGEPREFIX + "/mail_success.jsp";
	public static final String PAGE_VIEW_REG_STATUS = PAGEPREFIX + "/info.jsp";
	
	public static final String PAGE_ADMIN_MENU = PAGEPREFIX + "/admin/menu.jsp";
	public static final String PAGE_TOUR_MENU = PAGEPREFIX + "/admin/tour-menu.jsp";
	public static final String PAGE_USER_LIST = PAGEPREFIX + "/admin/user_list.jsp";
	
	public static final String PAGE_LIST_ORDERS = PAGEPREFIX + "/manager/list_orders.jsp";
	
	public static final String PAGE_LIST_MENU = PAGEPREFIX + "/client/list_menu.jsp";
	public static final String PAGE_BOOKINGS = PAGEPREFIX + "/client/bookings.jsp";
	
	// Commands
	public static final String COMMAND_MENU = "/pages/admin/commandMenu";
	public static final String COMMAND_TOUR_MENU = "/pages/admin/tour-menu";
	public static final String COMMAND_ORDER_LIST = "pages/manager/order-list";
	public static final String COMMAND_TOUR_LIST = "/pages/client/tour-list";
	
	// Properties
	private static final String DATAPATH = "ua/nure/dominov/SummaryTask4/properties/data";
	private static final String CONFIGPATH = "ua/nure/dominov/SummaryTask4/properties/config";
	public static final String RECAPCHAFILEPATH = CONFIGPATH + "/reCapcha";
	public static final String LANGUAGEFILEPATH = DATAPATH + "/language";
	public static final String EMAILPATH = CONFIGPATH + "/mail";
	
}
