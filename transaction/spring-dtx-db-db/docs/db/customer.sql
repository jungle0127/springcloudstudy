DROP DATABASE IF EXISTS `customer`;
CREATE DATABASE `customer` DEFAULT CHARSET utf8;
use `customer`;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`(
    `id` bigint AUTO_INCREMENT PRIMARY KEY,
    `username` varchar(50) NOT NULL,
    `deposit` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into customer(`username`,`deposit`) values('ps',1000);