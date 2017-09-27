package ua.nure.dominov.SummaryTask4.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet Filter implementation class EncodingFilter.
 */
@WebFilter("/")
public class EncodingFilter implements Filter {
	
	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger(
			EncodingFilter.class.getName());

	/**
	 * 
	 */
	private String encoding;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() { }

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public final void doFilter(final ServletRequest request, 
			final ServletResponse response, final FilterChain chain) 
					throws IOException, ServletException {
		LOGGER.info("Filter starts");
		
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		LOGGER.trace("Request uri --> " + httpRequest.getRequestURI());
		
		final String requestEncoding = request.getCharacterEncoding();
		if (requestEncoding == null) {
			LOGGER.trace("Request encoding = null, set encoding --> "
					+ encoding);
			request.setCharacterEncoding(encoding);
		}
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		LOGGER.info("Filter finished");		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public final void init(final FilterConfig config) 
			throws ServletException {
		LOGGER.info("Filter initialization starts");
		encoding = config.getInitParameter("requestEncoding");
		if (encoding == null) {
			encoding = "UTF-8";
		}
		LOGGER.trace("Encoding from web.xml --> " + encoding);
		LOGGER.info("Filter initialization finished");
	}

}
