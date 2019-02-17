DROP DATABASE IF EXISTS `ps_account`;
CREATE DATABASE `ps_account` DEFAULT CHARACTER SET utf8;

use ps_account;

DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account`(
	`id` bigint auto_increment primary key,
    `customer_id` bigint NOT NULL,
    `deposit` int NOT NULL DEFAULT 0,
    `create_time` timestamp NOT NULL DEFAULT now(),
    `update_time` timestamp NOT NULL DEFAULT now()
) ENGINE = InnoDB DEFAULT CHARSET= utf8;