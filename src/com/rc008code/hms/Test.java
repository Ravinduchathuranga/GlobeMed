package com.rc008code.hms;

import com.rc008code.hms.auth.decorators.service.handlers.UserAdminAuth;
import com.rc008code.hms.auth.decorators.service.handlers.UserDoctorAuth;
import com.rc008code.hms.auth.decorators.service.handlers.UserNurseAuth;
import com.rc008code.hms.auth.decorators.service.handlers.UserPharmacistAuth;
import com.rc008code.hms.business.BoFactory;
import com.rc008code.hms.business.custom.MedicalRecordBo;
import com.rc008code.hms.dto.MedicalRecordDto;
import com.rc008code.hms.enums.Departments;

import java.util.Date;
import java.util.List;


public class Test {
//    private static MedicalRecordBo medicalRecordBo = BoFactory.getInstance().getBo(BoFactory.BoType.MEDICALRECORD);

    public static void main(String[] args) throws Exception {
//        testMedicalRecords();
//        UserAdminAuth userAdminAuth = new UserAdminAuth();
//        UserDoctorAuth userDoctorAuth = new UserDoctorAuth();
//        UserNurseAuth userNurseAuth = new UserNurseAuth();
//        UserPharmacistAuth userPharmacistAuth = new UserPharmacistAuth();
//
//        userAdminAuth.setNextHandler(userDoctorAuth);
//        userDoctorAuth.setNextHandler(userNurseAuth);
//        userNurseAuth.setNextHandler(userPharmacistAuth);
//
//        userAdminAuth.authenticate("pharmacist", "adminpass");
    }
//
//    private static void testMedicalRecords() throws Exception {
//        System.out.println("\n=== Testing Doctor Operations ===");
//
//        // Create Doctor
//        MedicalRecordDto medicalRecordDto = new MedicalRecordDto(
//                "212121",
//                "P554f2",
//                "e4a11581-defe-4ac3-8b26-bb374f665833",
//                "0987654321",
//                "smith@test.com",
//                new Date()
//        );
//
//        boolean created = medicalRecordBo.create(medicalRecordDto);
//        System.out.println("Create Doctor: " + (created ? "SUCCESS" : "FAILED"));
//
//        // Get Doctor
//        MedicalRecordDto retrievedDoctor = medicalRecordBo.read("212121");
//        System.out.println("Get Doctor: " + (retrievedDoctor != null ? "SUCCESS" : "FAILED"));
//
//        // Update Doctor
//        if (retrievedDoctor != null) {
//            retrievedDoctor.setDiagnosis("Dr. Smith Updated");
//            boolean updated = medicalRecordBo.update(retrievedDoctor);
//            System.out.println("Update Doctor: " + (updated ? "SUCCESS" : "FAILED"));
//        }
//
//        // List all Doctors
//        List<MedicalRecordDto> doctors = medicalRecordBo.readAll();
//        System.out.println("List Doctors: Found " + doctors.size() + " doctors");
//
//        // Cleanup
//        boolean deleted = medicalRecordBo.delete("212121");
//        System.out.println("Delete Doctor: " + (deleted ? "SUCCESS" : "FAILED"));


}
