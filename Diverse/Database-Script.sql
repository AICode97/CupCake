DROP TABLE IF EXISTS `cupcake`.`users`;

CREATE TABLE `cupcake`.`users` (
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `balance` DOUBLE NOt NULL DEFAULT 0,
  PRIMARY KEY (`username`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE
);