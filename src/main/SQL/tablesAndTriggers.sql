/* below syntax is for MYSQL it could changed for other databases like Oracle */

create table PROPERTIES(
ID MEDIUMINT NOT NULL AUTO_INCREMENT,
APPLICATION VARCHAR(50) NOT NULL,
PROFILE VARCHAR(50) NOT NULL,
LABEL VARCHAR(50) NOT NULL,
KY VARCHAR(512) NOT NULL,
VALUE VARCHAR(512) NOT NULL,
MODIFIED_BY VARCHAR(30),
MODIFIED_TIME datetime DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (ID)
) ;

create table PROPERTIES_AUDIT(
ID MEDIUMINT NOT NULL,
APPLICATION VARCHAR(50) NOT NULL,
PROFILE VARCHAR(50) NOT NULL,
LABEL VARCHAR(50) NOT NULL,
KY VARCHAR(512) NOT NULL,
VALUE VARCHAR(512) NOT NULL,
ACTION VARCHAR(50),
MODIFIED_BY VARCHAR(30),
MODIFIED_TIME datetime
) ;

DROP TRIGGER IF EXISTS `config`.`properties_BEFORE_DELETE`;

DELIMITER $$
USE `config`$$
CREATE DEFINER = CURRENT_USER TRIGGER `config`.`properties_BEFORE_DELETE` BEFORE DELETE ON `properties` FOR EACH ROW
BEGIN
insert into `properties_audit`(id, application, profile, label, ky, value,action, modified_by, modified_time)
values(OLD.id, OLD.APPLICATION, OLD.PROFILE, OLD.LABEL, OLD.KY, OLD.VALUE,'DELETE', OLD.MODIFIED_BY, now());
END$$
DELIMITER ;

DROP TRIGGER IF EXISTS `config`.`properties_BEFORE_UPDATE`;

DELIMITER $$
USE `config`$$
CREATE DEFINER = CURRENT_USER TRIGGER `config`.`properties_BEFORE_UPDATE` BEFORE UPDATE ON `properties` FOR EACH ROW
BEGIN
insert into `properties_audit`(id, application, profile, label, ky, value,action, modified_by, modified_time)
values(NEW.id, NEW.APPLICATION, NEW.PROFILE, NEW.LABEL,NEW.KY, NEW.VALUE,'UPDATE', NEW.MODIFIED_BY, now());
SET NEW.modified_time = now();
END$$
DELIMITER ;

DROP TRIGGER IF EXISTS `config`.`properties_AFTER_INSERT`;

DELIMITER $$
USE `config`$$
CREATE DEFINER = CURRENT_USER TRIGGER `config`.`properties_AFTER_INSERT` AFTER INSERT ON `properties` FOR EACH ROW
BEGIN
insert into `properties_audit`(id, application, profile, label, ky, value,action, modified_by, modified_time)
values(NEW.id, NEW.APPLICATION, NEW.PROFILE, NEW.LABEL,NEW.KY, NEW.VALUE,'INSERT', NEW.MODIFIED_BY, NEW.MODIFIED_TIME);
END$$
DELIMITER ;

