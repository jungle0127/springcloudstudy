DROP database IF EXISTS `ms_order`;
CREATE DATABASE `ms_order` DEFAULT charset = utf8;
use ms_order;
CREATE TABLE `order` (
    `id` bigint AUTO_INCREMENT PRIMARY KEY,
    `title` varchar(50) NOT NULL,
    `detail` varchar(100) NOT NULL,
    `amount` int NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `order` (title,detail,amount) values('title','detail',100);