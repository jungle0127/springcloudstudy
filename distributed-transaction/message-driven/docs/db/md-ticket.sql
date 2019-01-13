
DROP DATABASE IF EXISTS `md_ticket`;
CREATE DATABASE `md_ticket` DEFAULT CHARSET=utf8;

use md_ticket;
DROP TABLE IF EXISTS `md_ticket`;
CREATE TABLE `md_ticket`(
    `id` bigint PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `name` varchar(50) NOT NULL,
    `owner` bigint  NULL,
    `lock_user` bigint  NULL,
    `ticket_number` bigint NOT NULL UNIQUE KEY

)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into md_ticket(`name`,`ticket_number`) values ("ps",12345) ;