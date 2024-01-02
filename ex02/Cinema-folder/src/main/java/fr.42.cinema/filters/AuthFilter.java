package fr.fortytwo.cinema.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterConfig;

@WebFilter(urlPatterns = { "/profile", "/profile/" }, filterName = "AuthFilter")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        // if user is already signed in, redirect to profile
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getSession().getAttribute("user") != null) {
            request.getRequestDispatcher("/profile").forward(req, res);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            // return;
            // response.sendRedirect("/Cinema/users/signIn");
        }
    }
}