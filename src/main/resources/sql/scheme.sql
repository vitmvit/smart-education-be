-- Adminer 4.8.1 MySQL 8.0.31 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP DATABASE IF EXISTS `smart_education`;
CREATE DATABASE `smart_education` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `smart_education`;

CREATE TABLE `attachment`
(
    `original_name`  varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
    `generated_path` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
    `generated_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `mime_type`      varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
    PRIMARY KEY (`generated_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;

CREATE TABLE `cabinet`
(
    `id`        bigint                                                       NOT NULL AUTO_INCREMENT,
    `user_type` varchar(31) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `legend`    varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;

CREATE TABLE `link_user_role`
(
    `user_id` bigint NOT NULL,
    `role_id` bigint NOT NULL,
    KEY `fk_role_to_user` (`role_id`),
    KEY `fk_user_to_role` (`user_id`),
    CONSTRAINT `fk_role_to_user` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
    CONSTRAINT `fk_user_to_role` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;

CREATE TABLE `role`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;

CREATE TABLE `user`
(
    `id`                 bigint                                                        NOT NULL AUTO_INCREMENT,
    `cabinet_teacher_id` bigint                                                        DEFAULT NULL,
    `cabinet_student_id` bigint                                                        DEFAULT NULL,
    `avatar_uuid`        varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  DEFAULT NULL,
    `login`              varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `password_hash`      varchar(900) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `name`               varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `last_name`          varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `middle_name`        varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
    `phone`              varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
    `active`             tinyint(1)                                                    DEFAULT NULL,
    `description`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
    `date_creation`      datetime                                                      DEFAULT NULL,
    `last_modified`      datetime                                                      DEFAULT NULL,
    `version`            bigint                                                        DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_user_to_cabinet_teacher` (`cabinet_teacher_id`),
    KEY `fk_user_to_cabinet_student` (`cabinet_student_id`),
    CONSTRAINT `fk_user_to_cabinet_teacher` FOREIGN KEY (`cabinet_teacher_id`) REFERENCES `cabinet` (`id`),
    CONSTRAINT `fk_user_to_cabinet_student` FOREIGN KEY (`cabinet_student_id`) REFERENCES `cabinet` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;

CREATE TABLE `settings`
(
    `k` varchar(255) NOT NULL,
    `v` varchar(255) NOT NULL,
    PRIMARY KEY (`k`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;

-- 2023-04-15 20:54:17