CREATE DATABASE IF NOT EXISTS autoservice;

ALTER DATABASE autoservice
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON autoservice.* TO 'autoservice'@'%';