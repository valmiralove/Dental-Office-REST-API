INSERT INTO dental_office
(dental_office_name, dental_office_address, dental_office_phone)
VALUES('Valmiras Dental Office', '143 Love Heaven, Heaven Place LV 143143', '143-143-1433');



INSERT INTO patient
(dental_office_id, patient_first_name, patient_last_name, patient_phone)
VALUES(1, 'Love', 'Cockatiel', '618-551-6112');

INSERT INTO patient
(dental_office_id, patient_first_name, patient_last_name, patient_phone)
VALUES(1, 'Blue', 'Parrotlet', '519-209-1455');



INSERT INTO staff
(dental_office_id, staff_title, staff_name)
VALUES(1, 'DDS', 'Valmira');

INSERT INTO staff
(dental_office_id, staff_title, staff_name)
VALUES(1, 'LDH', 'Maryann');

INSERT INTO staff
(dental_office_id, staff_title, staff_name)
VALUES(1, 'LDA', 'Bradley');

INSERT INTO staff
(dental_office_id, staff_title, staff_name)
VALUES(1, 'Admin', 'Carol');



INSERT INTO patient_staff
(patient_id, staff_id)
VALUES(1, 1);

INSERT INTO patient_staff
(patient_id, staff_id)
VALUES(1, 2);

INSERT INTO patient_staff
(patient_id, staff_id)
VALUES(2, 1);

INSERT INTO patient_staff
(patient_id, staff_id)
VALUES(2, 2);