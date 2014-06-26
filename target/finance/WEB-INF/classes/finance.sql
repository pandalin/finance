DROP TABLE IF EXISTS `pub_user`;
CREATE TABLE `pub_user` (
	`user_id` VARCHAR(36) NOT NULL,
	`user_code` VARCHAR(30) NOT NULL,
	`user_name` VARCHAR(20) NOT NULL,
	`user_password` VARCHAR(512) NOT NULL,
	`user_status` INT(1) NULL DEFAULT NULL,
	`user_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`user_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `pub_role` (
	`id` VARCHAR(36) NOT NULL,
	`role_code` VARCHAR(36) NOT NULL,
	`role_name` VARCHAR(50) NULL DEFAULT NULL,
	`role_desc` VARCHAR(100) NULL DEFAULT NULL,
	`role_status` INT(1) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `pub_user_role` (
	`id` VARCHAR(36) NOT NULL,
	`user_id` VARCHAR(36) NOT NULL,
	`role_id` VARCHAR(36) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `pub_authorities` (
	`id` VARCHAR(36) NOT NULL,
	`authority_code` VARCHAR(50) NOT NULL,
	`authority_name` VARCHAR(50) NULL DEFAULT NULL,
	`authority_desc` VARCHAR(100) NULL DEFAULT NULL,
	`authority_status` INT(1) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `pub_role_authorities` (
	`id` VARCHAR(36) NOT NULL,
	`role_id` VARCHAR(36) NOT NULL,
	`authority_id` VARCHAR(36) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `pub_resources` (
	`id` VARCHAR(36) NOT NULL,
	`resource_name` VARCHAR(50) NULL DEFAULT NULL,
	`resource_type` VARCHAR(36) NULL DEFAULT NULL,
	`resource_parent` VARCHAR(36) NULL DEFAULT NULL,
	`resource_url` VARCHAR(100) NULL DEFAULT NULL,
	`resource_desc` VARCHAR(100) NULL DEFAULT NULL,
	`resource_status` INT(1) NULL DEFAULT NULL,
	`resource_priority` BIGINT(20) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `pub_authorities_resources` (
	`id` VARCHAR(36) NOT NULL,
	`authority_id` VARCHAR(36) NOT NULL,
	`resource_id` VARCHAR(36) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `pub_consume` (
	`consume_id` VARCHAR(36) NOT NULL,
	`consume_type` VARCHAR(36) ,
	`consume_money` double ,
	`consume_date` datetime ,
	`consume_desc` VARCHAR(200) ,
	`consume_user` VARCHAR(36) ,
	PRIMARY KEY (`consume_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

INSERT INTO `pub_user` (`user_id`,`user_code`, `user_name`, `user_password`, `user_status`) VALUES ('u01', 'admin','admin', '$2a$10$EYGdotnMOEeF2Q3QMf.s9u2Z1qBPfPX9I0a1wDysiuJvQ3qsDt0K.', 1);
INSERT INTO `pub_user` (`user_id`,`user_code`, `user_name`, `user_password`, `user_status`) VALUES ('u02', 'lxm','lxm', '$2a$10$Ejs4PQnNkhkvsUd89xzIFurtJiVfG.7AiQ1IWGmhIQfs9c2InouAG', 1);
INSERT INTO `pub_user` (`user_id`,`user_code`, `user_name`, user_`password`, `user_status`) VALUES ('u03', 'test01','test01', '$2a$10$Ejs4PQnNkhkvsUd89xzIFurtJiVfG.7AiQ1IWGmhIQfs9c2InouAG', 1);

INSERT INTO `pub_role` (`id`, `role_code`, `role_name`, `role_desc`, `role_status`) VALUES ('r01', 'ROLE_ADMIN', 'ADMIN', '', 0);
INSERT INTO `pub_role` (`id`, `role_code`, `role_name`, `role_desc`, `role_status`) VALUES ('r02', 'ROLE_USER', 'USER', '', 0);
INSERT INTO `pub_role` (`id`, `role_code`, `role_name`, `role_desc`, `role_status`) VALUES ('r03', 'ROLE_TEST', 'TEST', '', 0);

INSERT INTO `pub_authorities` (`id`, `authority_code`, `authority_name`, `authority_desc`, `authority_status`) VALUES ('auth01', 'AUTH_ADMIN', 'AUTH_ADMIN', '', 0);
INSERT INTO `pub_authorities` (`id`, `authority_code`, `authority_name`, `authority_desc`, `authority_status`) VALUES ('auth02', 'AUTH_USER', 'AUTH_USER', '', 0);
INSERT INTO `pub_authorities` (`id`, `authority_code`, `authority_name`, `authority_desc`, `authority_status`) VALUES ('auth03', 'AUTH_TEST', 'AUTH_TEST', '', 0);

INSERT INTO `pub_resources` (`id`, `resource_name`, `resource_type`, `resource_url`, `resource_desc`, `resource_status`, `resource_priority`) VALUES ('re01', 'test01', 'url', '/user*', '', 0, 1);
INSERT INTO `pub_resources` (`id`, `resource_name`, `resource_type`, `resource_url`, `resource_desc`, `resource_status`, `resource_priority`) VALUES ('re02', 'test02', 'url', '/admin', '', 0, 2);
INSERT INTO `pub_resources` (`id`, `resource_name`, `resource_type`, `resource_url`, `resource_desc`, `resource_status`, `resource_priority`) VALUES ('re03', 'test03', 'url', '/role*', '', 0, 3);
INSERT INTO `pub_resources` (`id`, `resource_name`, `resource_type`, `resource_url`, `resource_desc`, `resource_status`, `resource_priority`) VALUES ('re04', 'test04', 'method', 'create', '', 0, 4);

INSERT INTO `pub_user_role` (`id`, `user_id`, `role_id`) VALUES ('pur01', 'u01', 'r01');
INSERT INTO `pub_user_role` (`id`, `user_id`, `role_id`) VALUES ('pur02', 'u01', 'r02');
INSERT INTO `pub_user_role` (`id`, `user_id`, `role_id`) VALUES ('pur03', 'u02', 'r02');
INSERT INTO `pub_user_role` (`id`, `user_id`, `role_id`) VALUES ('pur04', 'u03', 'r03');

INSERT INTO `pub_role_authorities` (`id`, `role_id`, `authority_id`) VALUES ('pra01', 'r01', 'auth01');
INSERT INTO `pub_role_authorities` (`id`, `role_id`, `authority_id`) VALUES ('pra02', 'r02', 'auth02');
INSERT INTO `pub_role_authorities` (`id`, `role_id`, `authority_id`) VALUES ('pra03', 'r03', 'auth03');

INSERT INTO `pub_authorities_resources` (`id`, `authority_id`, `resource_id`) VALUES ('par01', 'auth01', 're01');
INSERT INTO `pub_authorities_resources` (`id`, `authority_id`, `resource_id`) VALUES ('par02', 'auth01', 're02');
INSERT INTO `pub_authorities_resources` (`id`, `authority_id`, `resource_id`) VALUES ('par03', 'auth01', 're03');
INSERT INTO `pub_authorities_resources` (`id`, `authority_id`, `resource_id`) VALUES ('par04', 'auth03', 're04');
INSERT INTO `pub_authorities_resources` (`id`, `authority_id`, `resource_id`) VALUES ('par05', 'auth02', 're01');
INSERT INTO `pub_authorities_resources` (`id`, `authority_id`, `resource_id`) VALUES ('par06', 'auth02', 're04');

