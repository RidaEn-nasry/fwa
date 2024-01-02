package fr.fortytwo.cinema.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;;
import jakarta.servlet.http.HttpServletResponse;
// urlPatterns = { "/users/", "/users", "/users/signIn", "/users/signUp", "/users/signIn/", "/users/signUp/"},

@WebFilter(filterName = "GuestFilter", urlPatterns = { "/users/", "/user" })
public class GuestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        // if user is already signed in, redirect to profile
        if (httpReq.getSession().getAttribute("user") != null) {
            httpResp.sendRedirect("/Cinema/profile");
            return;
        }
        chain.doFilter(req, resp);
    }
}