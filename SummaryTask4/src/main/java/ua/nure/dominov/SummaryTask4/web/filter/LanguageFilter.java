package ua.nure.dominov.SummaryTask4.web.filter;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.nure.dominov.SummaryTask4.util.FilesPathsStorage;

/**
 * Servlet Filter implementation class LanguageFilter.
 */
@WebFilter("/*")
public class LanguageFilter implements Filter {

	/**
	 * 
	 */
	public static final String DEFAULTLANGUAGE = "en";

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public final void doFilter(final ServletRequest request, 
			final ServletResponse response, final FilterChain chain) 
					throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpSession session = req.getSession(true);
		
		if (session.getAttribute("lang") == null) {
			initializeLanguage(session, DEFAULTLANGUAGE);
		} else {
			if (request.getParameter("language") != null) {
				initializeLanguage(session, req.getParameter("language"));
			}
		}
		chain.doFilter(request, response);
	}
	
	/**
	 * @param session
	 * @param language
	 */
	private void initializeLanguage(final HttpSession session, 
			final String language) {
		final Locale locale = new Locale(language);
		final ResourceBundle resourceBundle = ResourceBundle.getBundle(
        		FilesPathsStorage.LANGUAGEFILEPATH, locale);
		session.setAttribute("lang", resourceBundle);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(final FilterConfig fConfig) throws ServletException { }

}
