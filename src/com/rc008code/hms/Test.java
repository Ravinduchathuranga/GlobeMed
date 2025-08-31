package com.rc008code.hms;

import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.*;
import com.rc008code.hms.dto.*;
import com.rc008code.hms.enums.Departments;
import com.rc008code.hms.enums.Gender;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class Test {
    private static DoctorBo doctorBo;
    private static NurseBo nurseBo;
    private static PatientBo patientBo;
    private static PharmacistBo pharmacistBo;
    
    private static String testPatientId;
    private static String testDoctorId;
    private static String testNurseId;
    private static String testPharmacistId;

    public static void main(String[] args) {
        try {
            // Initialize BOs
            doctorBo = BoFactory.getInstance().getBo(BoFactory.BoType.DOCTOR);
            nurseBo = BoFactory.getInstance().getBo(BoFactory.BoType.NURSE);
            patientBo = BoFactory.getInstance().getBo(BoFactory.BoType.PATIENT);
            pharmacistBo = BoFactory.getInstance().getBo(BoFactory.BoType.PHARMACIST);

            // Generate unique test IDs
            testPatientId = "P" + UUID.randomUUID().toString().substring(0, 5);
            testDoctorId = "D" + UUID.randomUUID().toString().substring(0, 5);
            testNurseId = "N" + UUID.randomUUID().toString().substring(0, 5);
            testPharmacistId = "PH" + UUID.randomUUID().toString().substring(0, 4);

            System.out.println("=== Starting Database Operations Test ===\n");

//            // Test Doctor Operations
//            testDoctorOperations();
//
//            // Test Nurse Operations
//            testNurseOperations();

            // Test Patient Operations
            testPatientOperations();

            // Test Pharmacist Operations
//            testPharmacistOperations();

            System.out.println("\n=== All Tests Completed Successfully ===");

        } catch (Exception e) {
            System.err.println("Error during tests: " + e.getMessage());
            e.printStackTrace();
        }
    }

//    private static void testDoctorOperations() throws Exception {
//        System.out.println("\n=== Testing Doctor Operations ===");
//
//        // Create Doctor
//        DoctorDto newDoctor = new DoctorDto(
//                testDoctorId,
//                "Dr. Smith",
//                "Cardiology",
//                "0987654321",
//                Departments.CARDIOLOGY,
//                "smith@test.com",
//                "1212"
//        );
//
//        boolean created = doctorBo.create(newDoctor);
//        System.out.println("Create Doctor: " + (created ? "SUCCESS" : "FAILED"));
//
//        // Get Doctor
//        DoctorDto retrievedDoctor = doctorBo.read(testDoctorId);
//        System.out.println("Get Doctor: " + (retrievedDoctor != null ? "SUCCESS" : "FAILED"));
//
//        // Update Doctor
//        if (retrievedDoctor != null) {
//            retrievedDoctor.setName("Dr. Smith Updated");
//            boolean updated = doctorBo.update(retrievedDoctor);
//            System.out.println("Update Doctor: " + (updated ? "SUCCESS" : "FAILED"));
//        }
//
//        // List all Doctors
//        List<DoctorDto> doctors = doctorBo.readAll();
//        System.out.println("List Doctors: Found " + doctors.size() + " doctors");
//
//        // Cleanup
//        boolean deleted = doctorBo.delete(testDoctorId);
//        System.out.println("Delete Doctor: " + (deleted ? "SUCCESS" : "FAILED"));
//    }
//
//    private static void testNurseOperations() throws Exception {
//        System.out.println("\n=== Testing Nurse Operations ===");
//
//        // Create Nurse
//        NurseDto newNurse = new NurseDto(
//                testNurseId,
//                "Nurse Jane",
//                Departments.CARDIOLOGY,
//                "0987654321",
//                "jane@test.com",
//                "12121"
//        );
//
//        boolean created = nurseBo.create(newNurse);
//        System.out.println("Create Nurse: " + (created ? "SUCCESS" : "FAILED"));
//
//        // Get Nurse
//        NurseDto retrievedNurse = nurseBo.read(testNurseId);
//        System.out.println("Get Nurse: " + (retrievedNurse != null ? "SUCCESS" : "FAILED"));
//
//        // Update Nurse
//        if (retrievedNurse != null) {
//            retrievedNurse.setName("Nurse Jane Updated");
//            boolean updated = nurseBo.update(retrievedNurse);
//            System.out.println("Update Nurse: " + (updated ? "SUCCESS" : "FAILED"));
//        }
//
//        // List all Nurses
//        List<NurseDto> nurses = nurseBo.readAll();
//        System.out.println("List Nurses: Found " + nurses.size() + " nurses");
//
//        // Cleanup
//        boolean deleted = nurseBo.delete(testNurseId);
//        System.out.println("Delete Nurse: " + (deleted ? "SUCCESS" : "FAILED"));
//    }

    private static void testPatientOperations() throws Exception {
        System.out.println("\n=== Testing Patient Operations ===");

        // Create Patient
        PatientDto newPatient = new PatientDto(
                testPatientId,
                "John Doe",
                "123 Test St",
                30,
                Gender.MALE,
                "1234567890",
                "john.doe@test.com"
        );

        boolean created = patientBo.create(newPatient);
        System.out.println("Create Patient: " + (created ? "SUCCESS" : "FAILED"));

        // Get Patient
        PatientDto retrievedPatient = patientBo.read(testPatientId);
        System.out.println("Get Patient: " + (retrievedPatient != null ? "SUCCESS" : "FAILED"));

        // Update Patient
        if (retrievedPatient != null) {
            retrievedPatient.setName("John Updated");
            retrievedPatient.setAge(31);
            boolean updated = patientBo.update(retrievedPatient);
            System.out.println("Update Patient: " + (updated ? "SUCCESS" : "FAILED"));
        }

        // List all Patients
        List<PatientDto> patients = patientBo.readAll();
        System.out.println("List Patients: Found " + patients.size() + " patients");

        // Cleanup
        boolean deleted = patientBo.delete(testPatientId);
        System.out.println("Delete Patient: " + (deleted ? "SUCCESS" : "FAILED"));
    }

//    private static void testPharmacistOperations() throws Exception {
//        System.out.println("\n=== Testing Pharmacist Operations ===");
//
//        // Create Pharmacist
//        PharmacistDto newPharmacist = new PharmacistDto(
//                testPharmacistId,
//                "Pharmacist Bob",
//                "5566778899",
//                "bob@pharmacy.com",
//                "12121"
//        );
//
//        boolean created = pharmacistBo.create(newPharmacist);
//        System.out.println("Create Pharmacist: " + (created ? "SUCCESS" : "FAILED"));
//
//        // Get Pharmacist
//        PharmacistDto retrievedPharmacist = pharmacistBo.read(testPharmacistId);
//        System.out.println("Get Pharmacist: " + (retrievedPharmacist != null ? "SUCCESS" : "FAILED"));
//
//        // Update Pharmacist
//        if (retrievedPharmacist != null) {
//            retrievedPharmacist.setName("Pharmacist Bob Updated");
//            boolean updated = pharmacistBo.update(retrievedPharmacist);
//            System.out.println("Update Pharmacist: " + (updated ? "SUCCESS" : "FAILED"));
//        }
//
//        // List all Pharmacists
//        List<PharmacistDto> pharmacists = pharmacistBo.readAll();
//        System.out.println("List Pharmacists: Found " + pharmacists.size() + " pharmacists");
//
//        // Cleanup
//        boolean deleted = pharmacistBo.delete(testPharmacistId);
//        System.out.println("Delete Pharmacist: " + (deleted ? "SUCCESS" : "FAILED"));
//    }
}
