/**
 * 
 */
package ua.nure.dominov.SummaryTask4.customtag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author calango
 *
 */
public class CustomTag extends SimpleTagSupport {
	
	/**
	 * 
	 */
	private String text;
	
	/**
	 * 
	 */
	private String link;
	
	/**
	 * @param text the text to set
	 */
	public final void setText(final String text) {
		this.text = text;
	}

	/**
	 * @param link the link to set
	 */ 
	public final void setLink(final String link) {
		this.link = link;
	}
	
	/**
	 * @return the text
	 */
	public final String getText() {
		return text;
	}

	/**
	 * @return the link
	 */
	public final String getLink() {
		return link;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public final void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		out.println("<form action=\"" + link + "\" method=\"post\">");
		out.println("<a style=\"color:#333;text-decoration: none;\" href=\"javascript:;\" onclick=\"parentNode.submit();\">" + text + "</a>");
		out.println("</form>");
	}
}
