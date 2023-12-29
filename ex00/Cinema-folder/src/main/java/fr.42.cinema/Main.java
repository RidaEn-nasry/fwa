
package fr.fortytwo.cinema;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import fr.fortytwo.cinema.config.AppConfig;

public class Main {
    public static void main(String[] args) {
        ApplicationContext springContext = new AnnotationConfigApplicationContext(AppConfig.class);
        assert springContext != null;
        System.out.println("Hello World!");
        
    }
}