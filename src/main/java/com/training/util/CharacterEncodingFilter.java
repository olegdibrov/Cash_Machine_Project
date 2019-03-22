package com.training.util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * filter for encoding characters
 *
 * @author Oleg Dibrov
 */
@WebFilter(filterName = "CharacterEncodingFilter",
        urlPatterns = "/*",
        initParams = {@WebInitParam(name = "encoding", value = "UTF-8")})
public class CharacterEncodingFilter implements Filter {

    /**
     * encoding field
     */
    private String encoding;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String requestEncoding = request.getCharacterEncoding();
        if (encoding != null && !encoding.equalsIgnoreCase(requestEncoding)) {
            request.setCharacterEncoding(encoding);
        }
        String responseEncoding = response.getCharacterEncoding();
        if (encoding != null && !encoding.equalsIgnoreCase(responseEncoding)) {
            response.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        encoding = null;
    }

}
