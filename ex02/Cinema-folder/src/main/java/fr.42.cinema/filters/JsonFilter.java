
package fr.fortytwo.cinema.filters;

import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import jakarta.servlet.ServletException;
import org.json.JSONObject;

// urlPatterns = { "/users/signIn", "/users/signUp", "/users/signIn/", "/users/signUp" }
@WebFilter(filterName = "JsonFilter")
public class JsonFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = ((HttpServletRequest) request);
        HttpServletResponse resp = ((HttpServletResponse) response);
        String contentType = req.getHeader("Content-Type");
        if (contentType != null && contentType.equals("application/json")) {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                JSONObject jsonRequest = new JSONObject(sb.toString());
                // Make JSON request available as a request attribute
                req.setAttribute("jsonRequest", jsonRequest);
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
        chain.doFilter(req, resp);
    }

}