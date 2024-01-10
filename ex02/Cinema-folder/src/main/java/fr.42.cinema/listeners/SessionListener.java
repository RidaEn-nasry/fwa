package fr.fortytwo.cinema.listeners;

import java.sql.Timestamp;

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
        // not really needed
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        // The attributes are still accessible because the session is invalidated only
        // after the current request/response life cycle is completed.

        setUsersService(se);
        User user = (User) se.getSession().getAttribute("user");
        AuthLogs authLog = (AuthLogs) se.getSession().getAttribute("authLog");
        Long userId = user.getId();
        Timestamp attemptedAt = authLog.getAttemptedAt();
        String ipAddress = authLog.getIpAddress();
        Integer timeSpent = (int) (System.currentTimeMillis() - attemptedAt.getTime()) / 1000;
        usersService.updateUsersTimeSpent(user.getId(), attemptedAt, ipAddress, timeSpent);
    }
}