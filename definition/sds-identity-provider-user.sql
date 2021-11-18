CREATE TABLE `sds-identity-provider`.`user` (
   `user_id` VARCHAR(150) NOT NULL,
   `username` VARCHAR(45) NOT NULL,
   `name` VARCHAR(150) NOT NULL,
   `last_name` VARCHAR(150) NOT NULL,
   `password` VARCHAR(150) NOT NULL,
   `email` VARCHAR(150) NOT NULL,
   `status` VARCHAR(45) NOT NULL,
   `rol` VARCHAR(45) NOT NULL,
   PRIMARY KEY (`user_id`),
   UNIQUE (username));
