<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page import="fr.fortytwo.cinema.models.User" %>
        <%@ page import="fr.fortytwo.cinema.models.FileMapping" %>
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
                </style>

                <body>
                    <!-- HTML -->
                    <!-- HTML -->
                    <!-- getting user -->
                    <% User user=(User) session.getAttribute("user"); %>
                        <% List<FileMapping> fileMappings =user.getFileMapping(); %>
                            <!-- HTML -->
                            <!-- HTML -->

                            <form method="post" action="images" enctype="multipart/form-data">
                                <div class="profile">
                                    <img src="avatar.png" alt="Avatar" />
                                    <p>It's Me</p>
                                    <p>
                                        <%=user.getEmail()%>
                                    </p>
                                </div>
                                <div>
                                </div>

                                <div class="upload">
                                    <label for="file" class="btn">Upload</label>
                                    <input type="file" name="file" id="file" class="file" onchange="this.form.submit()"
                                        accept="image/png, image/jpeg, image/jpg" />
                                    <table>
                                        <thead>
                                            <tr>
                                                <th>Date</th>
                                                <th>Time</th>
                                                <th>IP</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>December 10, 2020</td>
                                                <td>05:00</td>
                                                <td>127.0.1</td>
                                            </tr>
                                            <tr>
                                                <td>December 09, 2020</td>
                                                <td>04:00</td>
                                                <td>127.0.1</td>
                                            </tr>
                                            <tr>
                                                <td>December 08, 2020</td>
                                                <td>03:00</td>
                                                <td>127.0.1</td>
                                            </tr>
                                            <tr>
                                                <td>December 05, 2020</td>
                                                <td>02:00</td>
                                                <td>127.0.1</td>
                                            </tr>
                                        </tbody>
                                    </table>
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
                                                        <td>
                                                            <%=fileMapping.getOriginalFileName()%>
                                                        </td>
                                                        <td>
                                                            <%=fileMapping.getSize()%>
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

                </body>

                </html>



                <!-- <tr>
                                                <td>image.jpg</td>
                                                <td>196KB</td>
                                                <td>image/jpg</td>
                                            </tr>
                                            <tr>
                                                <td>image.png</td>
                                                <td>1MB</td>
                                                <td>image/png</td>
                                            </tr> -->