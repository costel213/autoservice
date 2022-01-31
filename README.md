# autoservice

This is a Spring Boot application built with Maven. You can build a jar file and run it from the command line.

In its default configuration, Autoservice uses an in-memory database (H2) which gets populated at startup with data. The h2 console is automatically exposed at http://localhost:8080/h2-console and it is possible to inspect the content of the database using the jdbc:h2:mem:autoservice url.

A similar setup is provided for MySql or Mariadb in case a persistent database configuration is needed. Note that whenever the database type is changed, you have to change some settings in application.properties file.

This application also has a map based implementation.

Change profle to "map" in application.properties file in order to use the map based implementation or change profile to "springdatajpa" in order to use database implementatin (either h2database, mysql or mariadb).

