CREATE USER 'admin'@'localhost' IDENTIFIED BY 'lge123!';
CREATE USER 'admin'@'%' IDENTIFIED BY 'lge123!';

GRANT ALL PRIVILEGES ON *.* TO 'admin'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'%';

CREATE DATABASE utility DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;