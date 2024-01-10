package fr.fortytwo.cinema.servlets;

import java.io.IOException;
import java.util.List;

import org.springframework.context.ApplicationContext;

import fr.fortytwo.cinema.services.UsersService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import fr.fortytwo.cinema.models.AuthLogs;
import fr.fortytwo.cinema.models.FileMapping;
import fr.fortytwo.cinema.models.User;

@WebServlet(name = "ProfileServlet", urlPatterns = { "/profile", "/profile/" })
public class ProfileServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        assert springContext != null;
        usersService = springContext.getBean(UsersService.class);
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Long userId = user.getId();

        List<FileMapping> profilePictures = usersService
                .getProfilePictures(userId);

        List<AuthLogs> authLogs = usersService.getAuthLogs(userId);
        System.out.println("We have " + authLogs.size() + " auth logs");
        // profile pictures have a life cycle of a request, each request = new list (for
        // that user might have added a new picture)
        req.setAttribute("profilePictures", profilePictures);

        req.getRequestDispatcher("/WEB-INF/jsp/Profile.jsp").forward(req, resp);

        // auth logs have a session life cycle,
        req.getSession().setAttribute("authLogs", authLogs);
    }
}