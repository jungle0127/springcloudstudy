
DROP DATABASE IF EXISTS `md_ticket`;
CREATE DATABASE `md_ticket` DEFAULT CHARSET=utf8;

use md_ticket;

CREATE TABLE `md_ticket`(
    `id` bigint PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `name` varchar(50) NOT NULL,
    `owner` bigint NOT NULL,
    `lock_user` bigint NOT NULL,
    `ticket_number` bigint NOT NULL
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;