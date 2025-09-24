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


-- End of script.sql
