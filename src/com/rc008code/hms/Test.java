package com.rc008code.hms;

import com.rc008code.hms.dto.PatientDto;
import com.rc008code.hms.enums.Gender;
import com.rc008code.hms.reports.service.EmailReportVisitor;
import com.rc008code.hms.reports.element.PatientReportElement;


public class Test {
//    private static MedicalRecordBo medicalRecordBo = BoFactory.getInstance().getBo(BoFactory.BoType.MEDICALRECORD);

    public static void main(String[] args) throws Exception {

        PatientDto patientDto = new PatientDto(
                "P554f2",
                "John Doe",
                "Kurunegala",
                26,
                Gender.MALE,
                "07122121",
                "ravinducdasanayaka12345@gmail.com"
        );
        EmailReportVisitor emailReportVisitor = new EmailReportVisitor();
        new PatientReportElement(patientDto).accept(emailReportVisitor);

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
