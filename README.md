
In this project, I implemented a web application for a cinema booking system using Java Servlet API, The application's backend was developed in Java, using Servlets for handling HTTP requests. The primary goal was to create a functional prototype that included user registration and login processes.

For the user registration feature (/signUp), I designed a form to capture  user info, (name, phone number, and password...) The data submitted through this form was processed by a SignUp servlet. I used the BCrypt algorithm to encrypt passwords before storing them in the database to enhance security.

The SignIn servlet handled the login functionality. It verified user credentials against the database and, upon successful authentication, created an HttpSession object with user data. This session management was needed for maintaining user state across the application.

The application also have a profile page, I implemented a Filter to manage access control, redirecting unauthorized requests and enforcing security constraints. The profile page was built using JSP (Java Server Pages), displaying user information and a log of authentication attempts, including timestamps and IP addresses.

For the avatar image uploads. I set up an endpoint (/images) to process POST requests for image uploads, and implemented file name uniqueness and saved the images to disk. The profile page provided a list of these uploaded images, each linked to open in a new tab.

