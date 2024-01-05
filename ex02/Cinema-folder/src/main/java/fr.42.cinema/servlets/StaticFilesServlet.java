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
import jakarta.servlet.http.Part;
import jakarta.servlet.GenericServlet;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.context.ApplicationContext;
import fr.fortytwo.cinema.services.UsersService;
import fr.fortytwo.cinema.models.FileMapping;
import fr.fortytwo.cinema.models.User;

@WebServlet(name = "StaticFilesServlet", urlPatterns = { "/images/*" })
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class StaticFilesServlet extends HttpServlet {
    private String StoragePath;
    private UsersService usersService;

    private void validateStoragePath(String storagePath) throws ServletException {
        File file = new File(storagePath);
        if (!file.exists()) {
            throw new ServletException("storage.path does not exist");
        }
        if (!file.isDirectory()) {
            throw new ServletException("storage.path is not a directory");
        }
        if (!file.canWrite()) {
            throw new ServletException("storage.path is not writable");
        }
        // if doesn't end with / , add it
        if (!storagePath.endsWith("/")) {
            storagePath += "/";
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");

        StoragePath = springContext.getEnvironment().getProperty("storage.path");
        validateStoragePath(StoragePath);
        usersService = springContext.getBean(UsersService.class);
        super.init(config);
        assert springContext != null;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getPart("file").getSubmittedFileName();
        if (fileName == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        User user = (User) req.getSession().getAttribute("user");
        System.out.println("user id: " + user.getId());

        usersService.updateProfilePicture(req.getPart("file"), user.getId(), StoragePath);

        resp.setStatus(HttpServletResponse.SC_CREATED);
        // redirect to page which come in the request
        resp.sendRedirect(req.getHeader("referer"));
        // resp.set
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getPathInfo();
        if (fileName == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // if

        // if fileName starts with / , remove it
        if (fileName.startsWith("/")) {
            fileName = fileName.substring(1);
        }

        String filePath = StoragePath + fileName;
        System.out.println("filePath: " + filePath);
        File file = new File(filePath);
        if (file.exists()) {
            resp.setContentType(getServletContext().getMimeType(filePath));
            resp.setContentLength((int) file.length());
            Files.copy(Paths.get(filePath), resp.getOutputStream());
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
