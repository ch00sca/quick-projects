package ch.helvetia.zepasweb.ui.helper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Header filter
 * Add expires header and enable cache control for static resources like graphics, java scripts, css <br>
 * For extensions look at web-local.xml - section filter, init-param.
 * 
 * @author CH00sca
 */

public class HeaderFilter implements Filter {

	private static final String PARAM_EXTENSIONS = "extensions";

	private static final String PARAM_EXPIRES = "expires";

	private Set<String> extensions;

	private Long expires;

	public void init(FilterConfig filterConfig) throws ServletException {
		String extensionsParam = filterConfig.getInitParameter(PARAM_EXTENSIONS);
		if (extensionsParam != null) {
			Set<String> newExtensions = new HashSet<String>();
			for (String extension : extensionsParam.split(",")) {
				newExtensions.add(extension.trim());
			}
			this.extensions = newExtensions;
		}

		String expiresParam = filterConfig.getInitParameter(PARAM_EXPIRES);
		if (expiresParam != null) {
			this.expires = Long.valueOf(expiresParam);
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		if (extensions != null && expires != null) {
			this.addExpiresHeaderAndEnableCache((HttpServletRequest) request, (HttpServletResponse) response);
		}
		chain.doFilter(request, response);
	}

	private void addExpiresHeaderAndEnableCache(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		int slashIndex = uri.lastIndexOf('/');
		int paramIndex = uri.indexOf("xhtml");
		if (slashIndex >= 0 && slashIndex + 1 < uri.length() && paramIndex >= 0) {
			String resourceName = uri.substring(slashIndex + 1, paramIndex).toLowerCase();
			StringTokenizer tokenizer = new StringTokenizer(resourceName, ".");
			while (tokenizer.hasMoreTokens()) {
				String extension = tokenizer.nextToken();
				this.modifyHeader(response, extension);
			}
		} else {
			int dotIndex = uri.lastIndexOf('.');
			if (dotIndex >= 0 && dotIndex + 1 < uri.length()) {
				String extension = uri.substring(dotIndex + 1).toLowerCase();
				if (extension.contains("/")) {
					extension = extension.substring(0,extension.indexOf("/"));
				}
				this.modifyHeader(response, extension);
			}
		}
	}

	private void modifyHeader(HttpServletResponse response, String extension) {
		if (this.extensions.contains(extension)) {
			response.setDateHeader("Expires", System.currentTimeMillis() + this.expires);
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=" + this.expires/1000 );
		}
	}

	public void destroy() {
		this.extensions = null;
		this.expires = null;
	}

}
