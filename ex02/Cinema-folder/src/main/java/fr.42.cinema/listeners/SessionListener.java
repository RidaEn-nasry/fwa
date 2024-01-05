package fr.fortytwo.cinema.listeners;

import org.springframework.context.ApplicationContext;

import fr.fortytwo.cinema.models.AuthLogs;
import fr.fortytwo.cinema.models.User;
import fr.fortytwo.cinema.services.UsersService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSessionEvent;

@WebListener("sessionListener")
public class SessionListener implements HttpSessionListener {

    private UsersService usersService;

    // set users service si
    private void setUsersService(HttpSessionEvent se) {
        if (usersService == null) {
            ServletContext context = se.getSession().getServletContext();
            ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
            usersService = springContext.getBean(UsersService.class);
            assert usersService != null;
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        setUsersService(se);
        ServletContext context = se.getSession().getServletContext();
        HttpServletRequest request = (HttpServletRequest) context.getAttribute("httpRequest");
        System.out.println("Request is: " + request );
        User user = (User)request.getSession().getAttribute("user");
        System.out.println("User is: " + user);
        // User user = (User) se.getSession().getAttribute("user");
        AuthLogs authLog = new AuthLogs();
        authLog.setUserId(user.getId());
        authLog.setIpAdress(request.getRemoteAddr());
        
        System.out.println("Session created");
        System.out.println("IP: " + request.getRemoteAddr());
        System.out.println("User: " + user.getId());

        usersService.addAuthLog(authLog);

        // set time created attribute for later use in sessionDestroyed
        se.getSession().setAttribute("sessionTimeCreated", System.currentTimeMillis());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Session destroyed");
        setUsersService(se);
        User user = (User) se.getSession().getAttribute("user");
        // timecreated - timenow
        Long timeSpent = System.currentTimeMillis() - (Long) se.getSession().getAttribute("sessionTimeCreated");
        usersService.updateUsersTimeSpent(user.getId(), timeSpent.intValue());
    }
}