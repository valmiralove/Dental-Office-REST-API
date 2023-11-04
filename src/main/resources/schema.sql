DROP TABLE IF EXISTS patient_staff;
DROP TABLE IF EXISTS staff;
DROP TABLE IF EXISTS patient;
DROP TABLE IF EXISTS dental_office;

CREATE TABLE dental_office (
	dental_office_id int NOT NULL AUTO_INCREMENT,
	dental_office_name varchar(256) NOT NULL,
	dental_office_address varchar(128),
	dental_office_phone varchar(30),
	PRIMARY KEY (dental_office_id)
);

CREATE TABLE patient (
	patient_id int NOT NULL AUTO_INCREMENT,
	dental_office_id int NOT NULL,
	patient_first_name varchar(60) NOT NULL,
	patient_last_name varchar(60) NOT NULL,
	patient_phone varchar(30),
	PRIMARY KEY (patient_id),
	FOREIGN KEY (dental_office_id) REFERENCES dental_office (dental_office_id) ON DELETE CASCADE
);

CREATE TABLE staff (
	staff_id int NOT NULL AUTO_INCREMENT,
	dental_office_id int NOT NULL,
	staff_title varchar(60) NOT NULL,
	staff_name varchar(60) NOT NULL,
	PRIMARY KEY (staff_id),
	FOREIGN KEY (dental_office_id) REFERENCES dental_office (dental_office_id) ON DELETE CASCADE
);

CREATE TABLE patient_staff (
	patient_id int NOT NULL,
	staff_id int NOT NULL,
	FOREIGN KEY (patient_id) REFERENCES patient (patient_id) ON DELETE CASCADE,
	FOREIGN KEY (staff_id) REFERENCES staff (staff_id) ON DELETE CASCADE
);