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

-- table book: title, author, publish, date, price, amount

-- select
SELECT *
FROM db_javaee.user;

TRUNCATE TABLE db_javaee.user;

SELECT *
FROM mysql.user;

UPDATE mysql.user
SET host = '%'
WHERE host = 'localhost';

FLUSH PRIVILEGES;