
DROP DATABASE IF EXISTS `md_order`;
CREATE DATABASE `md_order` DEFAULT CHARSET=utf8;

use md_order;

CREATE TABLE `md_order`(
    `id` bigint PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `uuid` char(36) NOT NULL,
    `customer_id` bigint NOT NULL,
    `title` varchar(50) NOT NULL,
    `ticket_number` bigint NOT NULL,
    `amount` int NOT NULL,
    `status` enum('void','booked') default 'void',
    `reason` varchar(50)

)  ENGINE=InnoDB DEFAULT CHARSET=utf8;