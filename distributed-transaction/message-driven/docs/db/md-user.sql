DROP DATABASE IF EXISTS `md_user`;
CREATE DATABASE `md_user` DEFAULT CHARSET=utf8;

use md_user;

CREATE  TABLE `md_customer`(
`id` bigint AUTO_INCREMENT PRIMARY KEY,
    `username` varchar(50) NOT NULL,
    `password` varchar(50) NOT NULL,
    `role` varchar(50) NOT NULL,
    `deposit` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into md_customer(`username`,`password`,`role`,`deposit`) values('ps','lotus','admin',1000);