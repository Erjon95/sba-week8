CREATE TABLE `studentcourse` (
	`courseid` INT(11) NOT NULL,
	`studentemail` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`courseid`, `studentemail`),
	INDEX `studentemail` (`studentemail`),
	CONSTRAINT `studentcourse_ibfk_1` FOREIGN KEY (`studentemail`) REFERENCES `student` (`email`) ON DELETE CASCADE,
	CONSTRAINT `studentcourse_ibfk_2` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`) ON DELETE CASCADE
);