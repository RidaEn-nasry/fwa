


# Cinema Web Application

## Instructions to run the Servlet container

1. install tomcat 7 if not installed
 
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
