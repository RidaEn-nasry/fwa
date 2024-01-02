<!-- a sample jsp file to display user profile -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page import="fr.fortytwo.cinema.models.User" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Profile</title>
        </head>

        <body>
            <% User user=(User) session.getAttribute("user"); String fullName=user.getFirstName() + " " +
                user.getLastName(); %>
                <h1>Profile</h1>
                <p>Full Name: <%= fullName %>
                </p>
        </body>

        </html>
