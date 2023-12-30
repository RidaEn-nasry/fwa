
package fr.fortytwo.cinema.listeners;

// import javax.servlet.annotation.WebListener;
// import org.springframework.context.annotation.Configuration;
import fr.fortytwo.cinema.config.AppConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
// import javax.servlet.annotation.WebListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener("springContextListener")
public class SpringContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext springContext = new AnnotationConfigApplicationContext(AppConfig.class);
        assert springContext != null;
        sce.getServletContext().setAttribute("springContext", springContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        AnnotationConfigApplicationContext springContext = (AnnotationConfigApplicationContext) sce.getServletContext()
                .getAttribute("springContext");
        if (springContext != null)
            springContext.close();
    }
}