DROP DATABASE IF EXISTS `ps_order`;
CREATE DATABASE `ps-order` DEFAULT CHARACTER SET utf8;

USE ps_order;

DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`(
	`id` bigint auto_increment PRIMARY KEY,
    `customer_id` bigint NOT NULL,
    `storeage_id` bigint NOT NULL,
    `amount` int NOT NULL,
    `order_desc` varchar(255),
    `create_time` timestamp default now()
) ENGINE = InnoDB DEFAULT CHARSET=utf8;