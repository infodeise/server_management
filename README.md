## Building and Running Server Management System
In order to implement this solution was used:
- Java 1.8
- Maven 3.3.9
- Spring framework
- Hibernate

### Create Database and Table
Go to the server-management\sql folder and execute the setup.sql on MySql client.

### Configuring spring.xml
On server-management\src\main\resources\spring.xml file change the followings properties:

	<property name="url" value="jdbc:mysql://<database_hostname>:<database_port>/serverManagementDB" />
	<property name="username" value="<value>" />
	<property name="password" value="<value>" />
 

### Building the solution
Go to the server-management folder where is located the pom.xml and execute the following command:

<code>mvn package</code>

###Running the solution
Go to the target folder that was generated on previus step and execute the following command:

<code>java -jar server-management-0.0.1-SNAPSHOT.jar</code>

