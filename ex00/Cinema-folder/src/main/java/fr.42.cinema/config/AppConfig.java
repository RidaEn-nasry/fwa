package fr.fortytwo.cinema.config;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

// using jakrarta insteead
// import jakarta.sql.DataSource;
// import jakarta.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan("fr.fortytwo.cinema")
@PropertySource("file:${webapp.root}/WEB-INF/application.properties")
public class AppConfig {

    @Value("db.url")
    private String dbUrl;

    @Value("db.user")
    private String dbUser;

    @Value("db.password")
    private String dbPassword;

    @Value("db.driver.name")
    private String dbDriverName;

    @Bean
    @Scope("singleton")
    public DataSource getHikariDataSource() {
        final HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(dbUrl);
        ds.setUsername(dbUser);
        ds.setPassword(dbPassword);
        ds.setDriverClassName(dbDriverName);
        return ds;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getHikariDataSource());
    }

    @Bean
    public DataSourceInitializer DataSourceInitializer() {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(getHikariDataSource());
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    @Bean
    public ResourceDatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("schema.sql"));
        populator.addScript(new ClassPathResource("data.sql"));
        return populator;
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
