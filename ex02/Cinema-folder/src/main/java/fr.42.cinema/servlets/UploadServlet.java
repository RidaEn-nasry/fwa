package fr.fortytwo.cinema.servlets;

import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UploadSerlvet", urlPatterns = { "/profile/upload", "/profile/upload/" })
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class UploadServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        // to do
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("We've been called there might be a file------------------------");
        // System.out.println(req.getPart("file"));
        // System.out.println(req.getPart("file").getSubmittedFileName());
        // System.out.println(req.getPart("file").getContentType());
        // System.out.println(req.getPart("file").getSize());
        // System.out.println(req.getPart("file").getInputStream());
        // System.out.println(req.getPart("file").getName());

    }
}