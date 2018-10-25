CREATE TABLE `bike`(
  `id`             BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `name`           VARCHAR(255)   NULL,
  `model`          VARCHAR(255)   NULL,
  `purchase_price` DECIMAL(19, 2) NULL,
  `serial_number`  VARCHAR(255)   NULL,
  `created_on`     DATETIME     NOT NULL,
  `updated_on`     DATETIME       NULL,
  PRIMARY KEY(`id`)
);