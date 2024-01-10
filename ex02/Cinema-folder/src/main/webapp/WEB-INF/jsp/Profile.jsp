<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page import="fr.fortytwo.cinema.models.User" %>
        <%@ page import="fr.fortytwo.cinema.models.FileMapping" %>
            <%@ page import="fr.fortytwo.cinema.models.AuthLogs" %>
                <%@ page import="java.text.SimpleDateFormat" %>
                    <%@ page import="java.util.List" %>
                        <!DOCTYPE html>
                        <html lang="en">

                        <head>
                            <meta charset="UTF-8">
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <title>Profile page</title>
                        </head>

                        <style>
                            /* CSS */
                            /* CSS */
                            body {
                                background-color: #262A2B;
                            }

                            * {
                                box-sizing: border-box;
                                margin: 0;
                                padding: 0;

                            }

                            form {
                                display: flex;
                                flex-direction: column;
                                align-items: center;
                                background-color: #333;
                                color: white;
                                font-family: Arial, sans-serif;
                                height: 100vh;
                                width: 100vw;
                            }

                            .profile {
                                display: flex;
                                flex-direction: column;
                                align-items: center;
                                margin-top: 20px;
                            }

                            .profile img {
                                height: 100px;
                                width: 100px;
                                border-radius: 50%;
                                border: 2px solid #ccc;

                            }

                            .profile p {
                                margin-top: 10px;
                            }

                            .upload {
                                display: flex;
                                flex-direction: column;
                                align-items: center;
                                margin-top: 20px;
                            }

                            .upload button {
                                background-color: #262A2B;
                                border: none;
                                border-radius: 5px;
                                cursor: pointer;
                                padding: 10px 20px;
                            }

                            .upload table {
                                border-collapse: collapse;
                                margin-top: 20px;
                            }

                            .upload table th,
                            .upload table td {
                                border: 1px solid #ccc;
                                padding: 10px;
                            }

                            .files {
                                margin-top: 20px;
                            }

                            .files table {
                                border-collapse: collapse;
                            }

                            .files table th,
                            .files table td {
                                border: 1px solid #ccc;
                                padding: 10px;
                            }

                            table th {
                                background-color: #555;
                                color: #ccc;
                            }


                            .file {
                                display: none;
                            }

                            .btn {
                                background-color: #555;
                                border: none;
                                color: white;
                                border-radius: 5px;
                                cursor: pointer;
                                padding: 10px 20px;
                            }

                            .btn:hover {
                                background-color: #333;
                            }

                            /* if file-name is too long cut it  */
                            .file-name {
                                max-width: 200px;
                                overflow: hidden;
                                text-overflow: ellipsis;
                                white-space: nowrap;
                            }

                            /* logout button to the top right of the page  ---*/
                            form button {
                                position: absolute;
                                top: 10px;
                                right: 10px;
                                background-color: #555;
                                border: none;
                                color: white;
                                border-radius: 5px;
                                cursor: pointer;
                                padding: 10px 20px;
                            }

                            form button:hover {
                                background-color: #333;
                            }
                        </style>

                        <body>
                            <!-- getting user / fileMappings from session / request -->
                            <% User user=(User) session.getAttribute("user"); %>
                                <% List < FileMapping> fileMappings = (List < FileMapping> )
                                        request.getAttribute("profilePictures"); %>
                                        <% List<AuthLogs> authLogs = (List<AuthLogs>) session.getAttribute("authLogs");
                                                %>
                                                <form method="post" action="images" enctype="multipart/form-data">
                                                    <div class="profile">
                                                        <% if(fileMappings!=null && !fileMappings.isEmpty()){ %>
                                                            <!-- last elem is last added -->
                                                            <img src="http://localhost:8080/Cinema/images/<%=fileMappings.get(fileMappings.size()-1).getGeneratedFileName()%>"
                                                                alt="Avatar" />
                                                            <% }else{ %>


                                                                <img src="<%=request.getContextPath()%>/assets/default.jpeg"
                                                                    alt="Avatar" />

                                                                <% } %>
                                                                    <p>It's Me</p>
                                                                    <p>
                                                                        <%=user.getEmail()%>
                                                                    </p>
                                                    </div>
                                                    </div>
                                                    <div>
                                                    </div>
                                                    <!-- getting authLogs -->
                                                    <div class="upload">
                                                        <label for="file" class="btn">Upload</label>
                                                        <input type="file" name="file" id="file" class="file"
                                                            onchange="this.form.submit()"
                                                            accept="image/png, image/jpeg, image/jpg" />
                                                        <% if(authLogs!=null && !authLogs.isEmpty()){ %>
                                                            <table>
                                                                <thead>
                                                                    <tr>
                                                                        <th>Date</th>
                                                                        <th>Time</th>
                                                                        <th>IP</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <!-- listing all  authLogs from List<AuthLogs>, only if authLogs is not empty -->

                                                                    <% for(AuthLogs authLog:authLogs){ %>
                                                                        <tr>
                                                                            <td>
                                                                                <%=new SimpleDateFormat("MMMMM dd, yyyy").format(authLog.getAttemptedAt())%>
                                                                            </td>
                                                                            <td>
                                                                                <!-- time format: MM:SS -->
                                                                                <% int minutes = authLog.getTimeSpent() / 60; %>
                                                                                    <% int seconds = authLog.getTimeSpent() % 60; %>
                                                                                        <%=String.format("%02d:%02d", minutes, seconds)%>
                                                                            </td>
                                                                            <td>
                                                                                <%=authLog.getIpAddress()%>
                                                                            </td>
                                                                        </tr>
                                                                        <% } %>
                                                                </tbody>
                                                            </table>
                                                            <% } %>
                                                    </div>
                                                    <div class="files">
                                                        <% if(fileMappings!=null && !fileMappings.isEmpty()){ %>
                                                            <table>
                                                                <thead>
                                                                    <tr>
                                                                        <th>File name</th>
                                                                        <th>Size</th>
                                                                        <th>MIME</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <!-- listing all  files mappping from List<FileMapping>, only if fileMappings is not empty -->

                                                                    <% for(FileMapping fileMapping:fileMappings){ %>
                                                                        <tr>
                                                                            <td class="file-name">
                                                                                <!-- a link  -->
                                                                                <!-- a new tab -->
                                                                                <a href="images/<%=fileMapping.getGeneratedFileName()%>"
                                                                                    target="_blank">
                                                                                    <%=fileMapping.getOriginalFileName()%>
                                                                                </a>
                                                                            </td>
                                                                            <!-- normalizing the size, showing it in either B, KB, or MB -->
                                                                            <td>
                                                                                <% long size=fileMapping.getSize(); %>
                                                                                    <% if(size<1024){ %>
                                                                                        <%=size%> B
                                                                                            <% }else if(size<1024*1024){
                                                                                                %>
                                                                                                <%=size /1024%> KB
                                                                                                    <% }else{ %>
                                                                                                        <%=size
                                                                                                            /(1024*1024)%>
                                                                                                            MB
                                                                                                            <% } %>

                                                                            </td>
                                                                            <td>
                                                                                <%=fileMapping.getMimeType()%>
                                                                            </td>
                                                                        </tr>
                                                                        <% } %>
                                                                </tbody>
                                                            </table>
                                                            <% } %>
                                                    </div>
                                                </form>
                                                <!-- logout button to the top right of the page  --->
                                                <form method="post" action="users/logOut">
                                                    <button type="submit">Logout</button>
                                                </form>



                        </body>

                        </html>