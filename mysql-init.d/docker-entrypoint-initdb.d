CREATE USER 'admin'@'localhost' IDENTIFIED BY 'lge123!';
CREATE USER 'admin'@'%' IDENTIFIED BY 'lge123!';

GRANT ALL PRIVILEGES ON *.* TO 'admin'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'%';

CREATE DATABASE utility DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

create table chart_info (
        id bigint not null auto_increment,
        full_file_path varchar(255),
        memo varchar(255),
        publisher varchar(255),
        web_path varchar(255),
        chart BLOB,
        primary key (id)
    ) engine=InnoDB;

create index i_publisher
       on chart_info (publisher);