


# Cinema Web Application

## Instructions to setup/run the Servlet container / maven

1. install tomcat 7 if not installed

2. Setup necessary roles:
   2.1. add admin role to manager gui / manager script to allow plugin based deployment
 ```bash
    $ cd {tomcat  dir}/conf
    $ vim tomcat-users.xml
 ```
   2.2. add the following lines to the file
   ```xml
      <user username="admin" password="admin" roles="manager-gui"/>
      <user username="deployer" password="deployer" roles="manager-script"/>
   ```
3. add deployer role maven settings.xml to allow plugin to access credentials for deployement
   ```bash
      $ cd ~/.m2
      $ vim settings.xml
   ```
      3.1. add the following lines to the file
      ```xml
         <server>
         <id>tomcat</id>
         <username>deployer</username>
         <password>deployer</password>
         </server>
      ```
2. start tomcat server
 ```bash
    $ catalina.sh run
 ```

3. open browser and go to http://localhost:8080/


## Instructions to deploy and use the application

1. install, compile and package application into a war archive
 ```bash
    $ mvn clean install compile package
 ```
2. deploy the war archive into a tomcat server
 ```bash
    $ mvn tomcat7:deploy
 ```
3. open browser and go to http://localhost:8080/Cinema/
4. Enjoy!   


<!--  to remove -->

# TODO
[ ] check for 403 in client from authFilter
[ ] add something (possibly filter) to log user authentications 
[ ] add default error pages for common error codes
[ ] figure out bassically the schema.sql file works when you hash passoword in sigup



# RESOURCES

[applets, servlets and JSP.](https://www.utc.fr/~dnace/dokuwiki/_media/fr/servletesjsp_translated.pdf)
