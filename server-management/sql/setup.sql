CREATE DATABASE serverManagementDB;
use serverManagementDB;
CREATE TABLE Server (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(20) NOT NULL DEFAULT '',
  description varchar(200) DEFAULT NULL
);