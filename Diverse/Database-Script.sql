DROP TABLE IF EXISTS `cupcake`.`cupcakes`;
DROP TABLE IF EXISTS `cupcake`.`orderLines`;
DROP TABLE IF EXISTS `cupcake`.`invoice`;
DROP TABLE IF EXISTS `cupcake`.`order`;
DROP TABLE IF EXISTS `cupcake`.`cupcakeTops`;
DROP TABLE IF EXISTS `cupcake`.`cupcakeBottoms`;
DROP TABLE IF EXISTS `cupcake`.`users`;


CREATE TABLE `cupcake`.`users` (
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `balance` DOUBLE NOT NULL DEFAULT 0,
  `role` ENUM('ADMIN', 'CUSTOMER') NOT NULL DEFAULT 'CUSTOMER',
  PRIMARY KEY (`username`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE
);

CREATE TABLE `cupcake`.`order` (
  `orderId` INT NOT NULL,
  `invoiceId` INT NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`orderId`, `invoiceId`)
);
    
CREATE TABLE `cupcake`.`invoice` (
  `invoiceId` INT NOT NULL,
  `orderId` INT NOT NULL,
  `price` INT NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`invoiceId`),
  CONSTRAINT `InvoiceToOrderFK`
    FOREIGN KEY (`orderId`)
    REFERENCES `cupcake`.`order` (`orderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION

);

CREATE TABLE `cupcake`.`cupcakeBottoms` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` DOUBLE NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
);

CREATE TABLE `cupcake`.`cupcakeTops` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` DOUBLE NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
);

CREATE TABLE `cupcake`.`orderLines` (
  `orderId` INT NOT NULL,
  `cupcakeTop` INT NOT NULL,
  `cupcakeBottom` INT NOT NULL,
  `qty` INT NOT NULL,
  `price` INT NOT NULL,
  PRIMARY KEY (`orderId`),
  CONSTRAINT `orderLineToOrderFK`
	FOREIGN KEY (`orderId`)
	REFERENCES `cupcake`.`order` (`orderId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `orderLineToCupcakesTopsFK`
	FOREIGN KEY (`cupcakeTop`)
    REFERENCES `cupcake`.`cupcakeTops` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `orderLineToCupcakesBottomsFK`
	FOREIGN KEY (`cupcakeBottom`)
    REFERENCES `cupcake`.`cupcakeBottoms` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE `cupcake`.`cupcakes` (
  `bottomId` INT NOT NULL,
  `topId` INT NOT NULL,
  PRIMARY KEY (`bottomId`, `topId`),
  INDEX `cupcakeTopFK_idx` (`topId` ASC) VISIBLE,
  CONSTRAINT `cupcakeBottomFK`
    FOREIGN KEY (`bottomId`)
    REFERENCES `cupcake`.`cupcakeBottoms` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cupcakeTopFK`
    FOREIGN KEY (`topId`)
    REFERENCES `cupcake`.`cupcakeTops` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

INSERT INTO `cupcake`.`users` VALUES ('Vikke', 'vikkedesign@gmail.com', '81DC9BDB52D04DC20036DBD8313ED055', 100, 'ADMIN');

INSERT INTO `cupcake`.`cupcakeBottoms` (`name`, `price`) VALUES 
('Chocolate', 5.00),
('Vanilla', 5.00),
('Nutmeg', 5.00),
('Pistacio', 6.00),
('Almond', 7.00);

INSERT INTO `cupcake`.`cupcakeTops` (`name`, `price`) VALUES 
('Chocolate', 5.00),
('Blueberry', 5.00),
('Rasberry', 5.00),
('Crispy', 6.00),
('Strawberry', 6.00),
('Rum/Raisin', 7.00),
('Orange', 8.00),
('Lemon', 8.00),
('Blue cheese', 9.00);