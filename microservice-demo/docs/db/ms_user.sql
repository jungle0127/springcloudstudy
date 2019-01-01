DROP database IF exists `ms_user`;
CREATE DATABASE `ms_user` DEFAULT CHARSET utf8;
use `ms_user`;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`(
    `id` bigint AUTO_INCREMENT PRIMARY KEY,
    `username` varchar(50) NOT NULL,
    `password` varchar(50) NOT NULL,
    `deposit` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into customer(`username`,`deposit`) values('ps',1000);