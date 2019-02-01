DROP DATABASE IF EXISTS `ps_storage`;
CREATE DATABASE `ps_storage` DEFAULT CHARACTER SET utf8;

USE ps_storage;
DROP TABLE IF EXISTS `t_storage`;
CREATE TABLE `t_storage`(
	`id` bigint auto_increment primary key,
    `product_name` varchar(255) NOT NULL,
    `inventory` int NOT NULL,
    `create_time` timestamp default now(),
    `update_time` timestamp default now()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO t_storage( `product_name`,`inventory`) VALUES ('pseudo product',1000);
