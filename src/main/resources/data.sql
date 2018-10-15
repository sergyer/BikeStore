INSERT INTO `hibernate_sequence` (next_val)
VALUES (1);

INSERT INTO `bike` (id,model,name,purchase_price,serial_number,created_on)
VALUES ((SELECT next_val FROM hibernate_sequence),'Kawasaki','Ninja',100,'1213399203',NOW());