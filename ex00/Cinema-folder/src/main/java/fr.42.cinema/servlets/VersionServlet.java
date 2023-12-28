package fr.fortytwo.cinema.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.IOException;

@WebServlet("/version")
public class VersionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        int majorVersion = context.getMajorVersion();
        int minorVersion = context.getMinorVersion();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Servlet API Version: " + majorVersion + "." + minorVersion + "</h1>");
    }
}
