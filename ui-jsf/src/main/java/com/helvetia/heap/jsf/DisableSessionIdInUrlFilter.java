package com.helvetia.heap.jsf;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DisableSessionIdInUrlFilter implements Filter {
    public DisableSessionIdInUrlFilter() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            if (httpRequest.getSession().isNew()) {
                String url = httpRequest.getRequestURL()
                        .append(httpRequest.getQueryString() != null ? "?" + httpRequest.getQueryString() : "")
                        .toString();
                httpResponse.setHeader("Location", url);
                httpResponse.sendError(301);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}
