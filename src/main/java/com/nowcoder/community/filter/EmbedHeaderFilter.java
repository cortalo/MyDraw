package com.nowcoder.community.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class EmbedHeaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Allow embedding in iframes (required for Notion)
        httpResponse.setHeader("Content-Security-Policy", "frame-ancestors *;");
        httpResponse.setHeader("X-Content-Type-Options", "nosniff");
        // Don't set X-Frame-Options to allow embedding
        filterChain.doFilter(request, response);
    }
}
