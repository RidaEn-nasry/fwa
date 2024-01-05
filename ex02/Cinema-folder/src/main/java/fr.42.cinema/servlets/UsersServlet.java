
package fr.fortytwo.cinema.servlets;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import fr.fortytwo.cinema.services.UsersService;
import fr.fortytwo.cinema.models.User;

// ---------
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UsersServlet", urlPatterns = { "/users/", "/users", "/users/signIn", "/users/signUp",
        "/users/signIn/", "/users/signUp/" })

public class UsersServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        usersService = springContext.getBean(UsersService.class);
        assert usersService != null;
    }

    // handle sign in
    private void signIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject jsonRequest = (JSONObject) req.getAttribute("jsonRequest");
        // getting the body of the request
        String phoneNumber = jsonRequest.getString("email");
        String password = jsonRequest.getString("password");
        if (phoneNumber == null || password == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        try {
            User user = usersService.signIn(phoneNumber, password);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/Cinema/profile");
        } catch (Exception e) {
            System.err.println("Err: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    // handle sign up
    private void signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject jsonRequest = (JSONObject) req.getAttribute("jsonRequest");

        // getting the body of the request
        String first_name = jsonRequest.getString("first_name");
        String last_name = jsonRequest.getString("last_name");
        String user_password = jsonRequest.getString("password");
        String phone_number = jsonRequest.getString("phone_number");
        String email = jsonRequest.getString("email");

        System.out.println("email: " + email);
        if (first_name == null || last_name == null || user_password == null || phone_number == null || email == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        try {
            User user = usersService.signUp(first_name, last_name, user_password, phone_number, email);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/Cinema/profile");
        } catch (Exception e) {
            System.err.println("Err: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        // remove trailing slash for non-root paths
        if (path.length() > 1 && path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        switch (path) {
            case "/users/signIn":
                req.getRequestDispatcher("/WEB-INF/html/Signin.html").forward(req, resp);
                break;
            case "/users/signUp":
                req.getRequestDispatcher("/WEB-INF/html/Signup.html").forward(req, resp);
                break;
            case "/users":
                req.getRequestDispatcher("/WEB-INF/html/Landing.html").forward(req, resp);
                break;
            default:
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/users/signIn":
                signIn(req, res);
                break;
            case "/users/signUp":
                signUp(req, res);
                break;
            default:
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

}