
package fr.fortytwo.cinema.servlets;

// import fr.fortytwo.cinema.services.UsersService;
// // import fr.fortytwo.cinema.services.UsersServiceImpl;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;

import fr.fortytwo.cinema.services.UsersService;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        usersService = springContext.getBean(UsersService.class);
    }

    

}