package ch.helvetia.zepasweb.ui.helper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * The default character encoding to set for request / response.
 *
 * @author CH00sca
 */

public class CharacterEncodingFilter implements Filter {
	private String encoding = null;
	private FilterConfig filterConfig;
	private boolean ignore = true;

	public void destroy() {
		encoding = null;
		filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		// conditionally select and set the character encoding to be used
		if ((ignore || (request.getCharacterEncoding() == null)) && (encoding != null)) {
			request.setCharacterEncoding(encoding);
			response.setCharacterEncoding(encoding);
		}
		// pass control on to the next filter
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("ignore");
		this.ignore = ((value == null) || value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes"));
	}
}
