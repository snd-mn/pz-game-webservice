SELECT CONCAT('DROP TABLE ', TABLE_NAME, ';')
FROM INFORMATION_SCHEMA.tables
WHERE TABLE_SCHEMA = 'zion';

SET foreign_key_checks = 0;


SET foreign_key_checks = 1;