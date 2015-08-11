Install tools:
java se 8;
apatch maven 2.5
mysql 5.5

Set systems variables java_home, maven_home!

In database create user with name "root" and password "".

Restore database from dump file using command:
mysql --u [username] --password=[password] [database name] < [dump file]

Enter in project directory and enter command:
mvn jetty:run

Put in browser url:
http://localhost:8080/com/



