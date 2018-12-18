DROP DATABASE IF EXISTS `order`;
CREATE DATABASE `order` DEFAULT CHARACTER SET utf8;
USE `order`;

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`(
    `id` bigint AUTO_INCREMENT PRIMARY KEY,
    `customer_id` bigint NOT NULL,
    `title` varchar(50) NOT NULL,
    `amount` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8