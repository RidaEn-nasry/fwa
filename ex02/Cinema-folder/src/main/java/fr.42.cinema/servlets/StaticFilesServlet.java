package fr.fortytwo.cinema.servlets;

import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.GenericServlet;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.context.ApplicationContext;
import fr.fortytwo.cinema.services.UsersService;
import fr.fortytwo.cinema.models.User;

@WebServlet(name = "StaticFilesServlet", urlPatterns = { "/images/*" })
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class StaticFilesServlet extends HttpServlet {
    private String StoragePath;
    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        assert springContext != null;
        StoragePath = springContext.getEnvironment().getProperty("storage.path");
        usersService = springContext.getBean(UsersService.class);
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getPart("file").getSubmittedFileName();
        if (fileName == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        User user = (User) req.getSession().getAttribute("user");
        usersService.updateProfilePicture(fileName, user.getId(), StoragePath, req);
        resp.setStatus(HttpServletResponse.SC_CREATED);
        // resp.set

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getPathInfo();
        if (fileName == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        fileName = fileName.substring(1);
        System.out.println("fileName: " + fileName);
        // String requestedFileName = fileName;
        User user = (User) req.getSession().getAttribute("user");
        String filePath = StoragePath + fileName;
        System.out.println("filePath: " + filePath);
        File file = new File(filePath);
        if (file.exists()) {
            // resp.sendRedirect(filePath);
            resp.setHeader("Content-Type", getServletContext().getMimeType(fileName));
            resp.setHeader("Content-Length", String.valueOf(file.length()));
            Files.copy(Paths.get(filePath), resp.getOutputStream());
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
