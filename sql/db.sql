CREATE DATABASE IF NOT EXISTS db_javaee;

-- table user
DROP TABLE IF EXISTS db_javaee.user;
CREATE TABLE db_javaee.user (
  id       INT AUTO_INCREMENT PRIMARY KEY,
  email    VARCHAR(255) NOT NULL
  COMMENT '',
  password VARCHAR(255) NOT NULL
  COMMENT '',
  cities   VARCHAR(255) COMMENT '',
  hobbies  VARCHAR(255) COMMENT ''
);

-- select
SELECT *
FROM db_javaee.user;