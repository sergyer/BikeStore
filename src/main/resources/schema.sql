CREATE TABLE `bike`(
  `id`             BIGINT(20)   NOT NULL,
  `name`           VARCHAR(255)   NULL,
  `model`          VARCHAR(255)   NULL,
  `purchase_price` DECIMAL(19, 2) NULL,
  `serial_number`  VARCHAR(255)   NULL,
  `created_on`     DATETIME     NOT NULL,
  `updated_on`     DATETIME       NULL,
  PRIMARY KEY(`id`)
);

CREATE TABLE hibernate_sequence
(
  `next_val` BIGINT(20) NULL
);
