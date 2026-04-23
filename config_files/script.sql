## Tables for Hospital Management System
## Tables: patient, doctor, pharmacist, AdminStaff, appointment, medical_record

-- Start of script.sql

CREATE DATABASE IF NOT EXISTS global_med;
USE global_med;

## patient table
CREATE TABLE IF NOT EXISTS patient
(
    patient_id   VARCHAR(100),
    patient_name VARCHAR(50),
    address      TEXT,
    age          INT,
    gender       ENUM ("MALE","FEMALE"),
    contact_no   VARCHAR(10),
    email        VARCHAR(100) UNIQUE NOT NULL,
    CONSTRAINT PRIMARY KEY (patient_id)
);
DESC patient;

## doctor table
CREATE TABLE IF NOT EXISTS doctor
(
    doctor_id   VARCHAR(100),
    doctor_name VARCHAR(50),
    specialty   VARCHAR(50),
    contact_no  VARCHAR(10),
    department  ENUM ("CARDIOLOGY", "NEUROLOGY", "PEDIATRICS", "ONCOLOGY", "GENERAL"),
    email       VARCHAR(100) UNIQUE NOT NULL,
    password    VARCHAR(100)        NOT NULL,
    CONSTRAINT PRIMARY KEY (doctor_id)
);
DESC doctor;

## nurse table
CREATE TABLE IF NOT EXISTS nurse
(
    nurse_id   VARCHAR(100),
    nurse_name VARCHAR(50),
    contact_no VARCHAR(10),
    department ENUM ("CARDIOLOGY", "NEUROLOGY", "PEDIATRICS", "ONCOLOGY", "GENERAL"),
    email      VARCHAR(100) UNIQUE NOT NULL,
    password   VARCHAR(100)        NOT NULL,
    CONSTRAINT PRIMARY KEY (nurse_id)
);
DESC nurse;

## pharmacist table
CREATE TABLE IF NOT EXISTS pharmacist
(
    pharmacist_id   VARCHAR(100),
    pharmacist_name VARCHAR(50),
    contact_no      VARCHAR(10),
    email           VARCHAR(100) UNIQUE NOT NULL,
    password        VARCHAR(100)        NOT NULL,
    CONSTRAINT PRIMARY KEY (pharmacist_id)
);
DESC pharmacist;

## AdminStaff table
CREATE TABLE IF NOT EXISTS adminStaff
(
    staff_id   VARCHAR(100),
    staff_name VARCHAR(50),
    contact_no VARCHAR(10),
    email      VARCHAR(100) UNIQUE NOT NULL,
    password   VARCHAR(100)        NOT NULL,
    CONSTRAINT PRIMARY KEY (staff_id)
);
DESC adminStaff;

#########################################################################################################################

## appointment table
CREATE TABLE IF NOT EXISTS appointment
(
    appointment_id   VARCHAR(100),
    patient_id       VARCHAR(100),
    doctor_id        VARCHAR(100),
    admin_staff_id   VARCHAR(100),
    appointment_date DATETIME,
    description      TEXT,
    status           ENUM ("PENDING", "COMPLETED"),
    CONSTRAINT PRIMARY KEY (appointment_id),
    CONSTRAINT fk_patient1 FOREIGN KEY (patient_id) REFERENCES patient (patient_id),
    CONSTRAINT fk_admin_staff FOREIGN KEY (admin_staff_id) REFERENCES adminStaff (staff_id),
    CONSTRAINT fk_doctor1 FOREIGN KEY (doctor_id) REFERENCES doctor (doctor_id)
);
DESC appointment;

CREATE TABLE IF NOT EXISTS medical_record
(
    record_id   VARCHAR(100),
    patient_id  VARCHAR(100),
    doctor_id   VARCHAR(100),
    diagnosis   TEXT,
    treatment   TEXT,
    record_date DATE,
    CONSTRAINT PRIMARY KEY (record_id),
    CONSTRAINT fk_patient2 FOREIGN KEY (patient_id) REFERENCES patient (patient_id),
    CONSTRAINT fk_doctor2 FOREIGN KEY (doctor_id) REFERENCES doctor (doctor_id)
);
DESC medical_record;

CREATE TABLE IF NOT EXISTS prescription
(
    prescription_id VARCHAR(100),
    record_id       VARCHAR(100),
    pharmacist_id   VARCHAR(100),
    patient_id      VARCHAR(100),
    medication      TEXT,
    dosage          TEXT,
    issue_date      DATE,
    CONSTRAINT PRIMARY KEY (prescription_id),
    CONSTRAINT fk_patient_record FOREIGN KEY (patient_id) REFERENCES patient (patient_id),
    CONSTRAINT fk_medical_record FOREIGN KEY (record_id) REFERENCES medical_record (record_id),
    CONSTRAINT fk_pharmacist FOREIGN KEY (pharmacist_id) REFERENCES pharmacist (pharmacist_id)
);

DESC prescription;

CREATE TABLE IF NOT EXISTS payment
(
    payment_id     VARCHAR(100),
    patient_id     VARCHAR(100),
    appointment_id VARCHAR(100),
    amount         DECIMAL(10, 2),
    payment_date   DATE,
    method         ENUM ("cash", "credit_card", "insurance"),
    status         ENUM ("pending", "completed", "failed"),
    CONSTRAINT PRIMARY KEY (payment_id),
    CONSTRAINT fk_patient3 FOREIGN KEY (patient_id) REFERENCES patient (patient_id),
    CONSTRAINT fk_appointment FOREIGN KEY (appointment_id) REFERENCES appointment (appointment_id)
);

CREATE TABLE IF NOT EXISTS nurse_assignment
(
    assignment_id VARCHAR(100),
    nurse_id      VARCHAR(100),
    patient_id    VARCHAR(100),
    assigned_date DATE,
    CONSTRAINT PRIMARY KEY (assignment_id),
    CONSTRAINT fk_nurse FOREIGN KEY (nurse_id) REFERENCES nurse (nurse_id),
    CONSTRAINT fk_patient4 FOREIGN KEY (patient_id) REFERENCES patient (patient_id)
);
DESC nurse_assignment;


-- Sample Data Inserts

-- Patients
INSERT INTO patient (patient_id, patient_name, address, age, gender, contact_no, email) VALUES
 ('P001','Alice Johnson','123 Main St, Cityville',30,'FEMALE','0712345678','alice@example.com'),
 ('P002','Bob Smith','456 Elm St, Townsburg',45,'MALE','0723456789','bob@example.com'),
 ('P003','Carol Perez','789 Oak Ave, Villageton',28,'FEMALE','0734567890','carol@example.com');

-- Doctors
INSERT INTO doctor (doctor_id, doctor_name, specialty, contact_no, department, email, password) VALUES
 ('D001','Gregory House','Diagnostics','0771234567','GENERAL','house@example.com','password1'),
 ('D002','Meredith Grey','Surgery','0779876543','ONCOLOGY','grey@example.com','password2');

-- Nurses
INSERT INTO nurse (nurse_id, nurse_name, contact_no, department, email, password) VALUES
 ('N001','Nina Patel','0751112233','PEDIATRICS','nina.patel@example.com','nursepass1');

-- Pharmacists
INSERT INTO pharmacist (pharmacist_id, pharmacist_name, contact_no, email, password) VALUES
 ('PH001','Phil Baker','0762223344','phil.baker@example.com','pharmapass1');

-- Admin Staff
INSERT INTO adminStaff (staff_id, staff_name, contact_no, email, password) VALUES
 ('AS001','Anna Scott','0743334455','anna.scott@example.com','adminpass1');

-- Appointments (ensure referenced patient, doctor, admin exist)
INSERT INTO appointment (appointment_id, patient_id, doctor_id, admin_staff_id, appointment_date, description, status) VALUES
 ('A1001','P001','D001','AS001','2025-09-24 10:30:00','General check-up','PENDING'),
 ('A1002','P002','D002','AS001','2025-09-25 14:00:00','Follow-up consultation','PENDING');

-- Medical Records
INSERT INTO medical_record (record_id, patient_id, doctor_id, diagnosis, treatment, record_date) VALUES
 ('MR001','P001','D001','Hypertension','Lifestyle changes; medication','2025-09-20'),
 ('MR002','P002','D002','Post-surgery recovery','Physical therapy; pain management','2025-09-21');

-- Prescriptions
INSERT INTO prescription (prescription_id, record_id, pharmacist_id, patient_id, medication, dosage, issue_date) VALUES
 ('PR001','MR001','PH001','P001','Lisinopril 10mg','1 tablet daily','2025-09-21'),
 ('PR002','MR002','PH001','P002','Ibuprofen 200mg','2 tablets as needed','2025-09-22');

-- Payments
INSERT INTO payment (payment_id, patient_id, appointment_id, amount, payment_date, method, status) VALUES
 ('PAY001','P001','A1001',250.00,'2025-09-24','cash','completed'),
 ('PAY002','P002','A1002',150.00,'2025-09-25','credit_card','pending');

-- Nurse Assignments
INSERT INTO nurse_assignment (assignment_id, nurse_id, patient_id, assigned_date) VALUES
 ('NA001','N001','P003','2025-09-23');

-- End of script.sql
