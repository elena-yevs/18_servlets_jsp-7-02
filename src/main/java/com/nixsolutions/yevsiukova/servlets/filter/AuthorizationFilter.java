package com.nixsolutions.yevsiukova.servlets.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebFilter(filterName = "AuthorizationFilter")
public class AuthorizationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);
    }
}
