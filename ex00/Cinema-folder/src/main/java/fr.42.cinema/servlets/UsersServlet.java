
package fr.fortytwo.cinema.servlets;

// import fr.fortytwo.cinema.services.UsersService;

// // import fr.fortytwo.cinema.services.UsersServiceImpl;

import org.springframework.context.ApplicationContext;

// import javax.servlet.http.HttpServlet;

// import javax.servlet.ServletConfig;
// import javax.servlet.ServletContext;
// import javax.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;

import fr.fortytwo.cinema.services.UsersService;

// ---------

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UsersServlet", urlPatterns = "/users")
public class UsersServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        usersService = springContext.getBean(UsersService.class);
        assert usersService != null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        usersService.signUp("John", "Doe", "password", "1234567890");
        resp.getWriter().println("User signed up");
    }

    @Override
    public void destroy() {
        usersService = null;
    }

}