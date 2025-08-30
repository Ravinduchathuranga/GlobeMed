package com.rc008code.hms.database;

// Database connection details and methods would go here

import com.rc008code.hms.entity.*;
import com.rc008code.hms.enums.Departments;
import com.rc008code.hms.enums.Gender;

import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbConnection {

    /*=========================================================================================================*/
    // Patient-related database operations would be implemented in PatientDaoImpl
    /*=========================================================================================================*/

    // create patient
    public boolean createPatient(Patient patient) throws ClassNotFoundException, SQLException {
        // Implementation for creating a patient record in the database
        String sql = "INSERT INTO patient VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, patient.getPatientId());
        preparedStatement.setString(2, patient.getName());
        preparedStatement.setString(3, patient.getAddress());
        preparedStatement.setInt(4, patient.getAge());
        preparedStatement.setString(5, String.valueOf(patient.getGender()));
        preparedStatement.setString(6, patient.getContact());
        preparedStatement.setString(7, patient.getEmail());

        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            return true;
        }
        return false;
    }

    // get all patients
    public List<Patient> getAllPatients(String searchText) throws ClassNotFoundException, SQLException {

        searchText = "%" + searchText + "%";

        String query = "SELECT * FROM patient WHERE patient_name LIKE ? OR email LIKE ?";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(query);
        preparedStatement.setString(1, searchText);
        preparedStatement.setString(2, searchText);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Patient> patientsArrayList = new ArrayList<>();
        while (resultSet.next()) {
            patientsArrayList.add(
                    new Patient(
                            resultSet.getString("patient_id"),
                            resultSet.getString("patient_name"),
                            resultSet.getString("address"),
                            resultSet.getInt("age"),
                            Gender.valueOf(resultSet.getString("gender")),
                            resultSet.getString("contact_no"),
                            resultSet.getString("email")
                    )
            );
        }
        return patientsArrayList;
    }

    // get patient details
    public Patient getPatient(String patientId) throws ClassNotFoundException, SQLException {

        String query = "SELECT * FROM patient WHERE patient_id='" + patientId + "'";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(query);
        preparedStatement.setString(1, patientId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new Patient(
                    resultSet.getString("patient_id"),
                    resultSet.getString("patient_name"),
                    resultSet.getString("address"),
                    resultSet.getInt("age"),
                    Gender.valueOf(resultSet.getString("gender")),
                    resultSet.getString("contact_no"),
                    resultSet.getString("email")
            );
        } else {
            return null;
        }
    }

    // update patient 
    public boolean updatePatient(Patient patient) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE patient SET patient_name=?,address=?,age=?,gender=?,contact_no=?,email=? WHERE patient_id=?";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, patient.getName());
        preparedStatement.setString(2, patient.getAddress());
        preparedStatement.setInt(3, patient.getAge());
        preparedStatement.setString(4, patient.getGender().toString());
        preparedStatement.setString(5, patient.getContact());
        preparedStatement.setString(6, patient.getEmail());
        preparedStatement.setString(7, patient.getPatientId());

        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            return true;
        }
        return false;
    }

    // delete patient 
    public boolean deletePatient(Patient patient) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM patient WHERE patient_Id=?";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, patient.getPatientId());

        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            return true;
        }
        return false;
    }


    /*=========================================================================================================*/
    // Doctor-related database operations would be implemented in DoctorDaoImpl
    /*=========================================================================================================*/

    // create doctor
    public boolean createDoctor(Doctor doctor) throws ClassNotFoundException, SQLException {
        // Implementation for creating a doctor record in the database
        String sql = "INSERT INTO doctor VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, doctor.getDoctorId());
        preparedStatement.setString(2, doctor.getName());
        preparedStatement.setString(3, doctor.getSpecialty());
        preparedStatement.setString(4, doctor.getContact());
        preparedStatement.setString(5, doctor.getDepartment().toString());
        preparedStatement.setString(6, doctor.getEmail());

        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            return true;
        }
        return false;
    }

    // get all doctors
    public List<Doctor> getAllDoctors(String searchText) throws ClassNotFoundException, SQLException {

        searchText = "%" + searchText + "%";

        String query = "SELECT * FROM doctor WHERE doctor_name LIKE ? OR email LIKE ?";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(query);
        preparedStatement.setString(1, searchText);
        preparedStatement.setString(2, searchText);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Doctor> doctorArrayList = new ArrayList<>();
        while (resultSet.next()) {
            doctorArrayList.add(
                    new Doctor(
                            resultSet.getString("doctor_id"),
                            resultSet.getString("doctor_name"),
                            resultSet.getString("specialty"),
                            resultSet.getString("contact_no"),
                            Departments.valueOf(resultSet.getString("department")),
                            resultSet.getString("email")
                    )
            );
        }
        return doctorArrayList;
    }

    // get doctor details
    public Doctor getDoctor(String doctorId) throws ClassNotFoundException, SQLException {

        String query = "SELECT * FROM doctor WHERE doctor_id='" + doctorId + "'";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(query);
        preparedStatement.setString(1, doctorId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new Doctor(
                    resultSet.getString("doctor_id"),
                    resultSet.getString("doctor_name"),
                    resultSet.getString("specialty"),
                    resultSet.getString("contact_no"),
                    Departments.valueOf(resultSet.getString("department")),
                    resultSet.getString("email")
            );
        } else {
            return null;
        }
    }

    // update doctor
    public boolean updateDoctor(Doctor doctor) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE doctor SET doctor_name=?,specialty=?,contact_no=?,department=?,email=? WHERE doctor_id=?";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, doctor.getDoctorId());
        preparedStatement.setString(2, doctor.getName());
        preparedStatement.setString(3, doctor.getSpecialty());
        preparedStatement.setString(4, doctor.getContact());
        preparedStatement.setString(5, doctor.getDepartment().toString());
        preparedStatement.setString(6, doctor.getEmail());


        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            return true;
        }
        return false;
    }

    // delete doctor
    public boolean deleteDoctor(Doctor doctor) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM doctor WHERE doctor_id=?";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, doctor.getDoctorId());

        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            return true;
        }
        return false;
    }



    /*=========================================================================================================*/
    // Nurse-related database operations would be implemented in NurseDaoImpl
    /*=========================================================================================================*/

    // create nurse
    public boolean createNurse(Nurse nurse) throws ClassNotFoundException, SQLException {
        // Implementation for creating a nurse record in the database
        String sql = "INSERT INTO nurse VALUES (?, ?, ?, ?,?)";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, nurse.getNurseId());
        preparedStatement.setString(2, nurse.getName());
        preparedStatement.setString(3, nurse.getContact());
        preparedStatement.setString(4, nurse.getDepartment().toString());
        preparedStatement.setString(5, nurse.getEmail());

        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            return true;
        }
        return false;
    }

    // get all nurse
    public List<Nurse> getAllNurses(String searchText) throws ClassNotFoundException, SQLException {

        searchText = "%" + searchText + "%";

        String query = "SELECT * FROM nurse WHERE nurse_id LIKE ? OR email LIKE ?";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(query);
        preparedStatement.setString(1, searchText);
        preparedStatement.setString(2, searchText);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Nurse> nurseArrayList = new ArrayList<>();
        while (resultSet.next()) {
            nurseArrayList.add(
                    new Nurse(
                            resultSet.getString("nurse_id"),
                            resultSet.getString("nurse_name"),
                            resultSet.getString("contact_no"),
                            Departments.valueOf(resultSet.getString("department")),
                            resultSet.getString("email")
                    )
            );
        }
        return nurseArrayList;
    }

    // get nurse details
    public Nurse getNurse(String nurseId) throws ClassNotFoundException, SQLException {

        String query = "SELECT * FROM nurse WHERE nurse_id='" + nurseId + "'";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(query);
        preparedStatement.setString(1, nurseId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new Nurse(
                    resultSet.getString("nurse_id"),
                    resultSet.getString("nurse_name"),
                    resultSet.getString("contact_no"),
                    Departments.valueOf(resultSet.getString("department")),
                    resultSet.getString("email")
            );
        } else {
            return null;
        }
    }

    // update nurse
    public boolean updateNurse(Nurse nurse) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE nurse SET nurse_name=?,contact_no=?,department=?,email=? WHERE nurse_id=?";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, nurse.getName());
        preparedStatement.setString(2, nurse.getContact());
        preparedStatement.setString(3, nurse.getDepartment().toString());
        preparedStatement.setString(4, nurse.getEmail());
        preparedStatement.setString(5, nurse.getNurseId());

        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            return true;
        }
        return false;
    }

    // delete nurse
    public boolean deleteNurse(Nurse nurse) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM nurse WHERE nurse_id=?";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, nurse.getNurseId());

        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            return true;
        }
        return false;
    }



    /*=========================================================================================================*/
    // Pharmacist-related database operations would be implemented in PharmacistDaoImpl
    /*=========================================================================================================*/

    // create pharmacist
    public boolean createPharmacist(Pharmacist pharmacist) throws ClassNotFoundException, SQLException {
        // Implementation for creating a pharmacist record in the database
        String sql = "INSERT INTO pharmacist VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, pharmacist.getPharmacistId());
        preparedStatement.setString(2, pharmacist.getName());
        preparedStatement.setString(3, pharmacist.getContactNumber());
        preparedStatement.setString(4, pharmacist.getEmail());

        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            return true;
        }
        return false;
    }

    // get all pharmacists
    public List<Pharmacist> getAllPharmacists(String searchText) throws ClassNotFoundException, SQLException {

        searchText = "%" + searchText + "%";

        String query = "SELECT * FROM pharmacist WHERE pharmacist_name LIKE ? OR email LIKE ?";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(query);
        preparedStatement.setString(1, searchText);
        preparedStatement.setString(2, searchText);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Pharmacist> pharmacistArrayList= new ArrayList<>();
        while (resultSet.next()) {
            pharmacistArrayList.add(
                    new Pharmacist(
                            resultSet.getString("pharmacist_id"),
                            resultSet.getString("pharmacist_name"),
                            resultSet.getString("contact_no"),
                            resultSet.getString("email")
                    )
            );
        }
        return pharmacistArrayList;
    }

    // get pharmacist details
    public Pharmacist getPharmacist(String pharmacist) throws ClassNotFoundException, SQLException {

        String query = "SELECT * FROM pharmacist WHERE pharmacist_id='" + pharmacist + "'";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(query);
        preparedStatement.setString(1, pharmacist);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new Pharmacist(
                    resultSet.getString("pharmacist_id"),
                    resultSet.getString("pharmacist_name"),
                    resultSet.getString("contact_no"),
                    resultSet.getString("email")
            );
        } else {
            return null;
        }
    }

    // update pharmacist
    public boolean updatePharmacist(Pharmacist pharmacist) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE pharmacist SET pharmacist_name=?,contact_no=?,email=? WHERE pharmacist_id=?";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, pharmacist.getPharmacistId());
        preparedStatement.setString(2, pharmacist.getName());
        preparedStatement.setString(3, pharmacist.getContactNumber());
        preparedStatement.setString(4, pharmacist.getEmail());

        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            return true;
        }
        return false;
    }

    // delete pharmacist
    public boolean deletePharmacist(Pharmacist pharmacist) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM pharmacist WHERE pharmacist_id=?";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, pharmacist.getPharmacistId());

        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            return true;
        }
        return false;
    }



    /*=========================================================================================================*/
    // AdministrativeStaff-related database operations would be implemented in AdministrativeStaffDaoImpl
    /*=========================================================================================================*/

    // create administrativeStaff
    public boolean createAdminStaff(AdminStaff adminStaff) throws ClassNotFoundException, SQLException {
        // Implementation for creating a administrativeStaff record in the database
        String sql = "INSERT INTO adminStaff VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, adminStaff.getStaff_id());
        preparedStatement.setString(2, adminStaff.getName());
        preparedStatement.setString(3, adminStaff.getContact_number());
        preparedStatement.setString(4, adminStaff.getEmail());

        int i = preparedStatement.executeUpdate();
        if (i > -1) {
            return true;
        }
        return false;
    }

    // get all administrativeStaff
    public List<AdminStaff> getAllAdminStaff(String searchText) throws ClassNotFoundException, SQLException {

        searchText = "%" + searchText + "%";

        String query = "SELECT * FROM adminStaff WHERE staff_name LIKE ? OR email LIKE ?";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(query);
        preparedStatement.setString(1, searchText);
        preparedStatement.setString(2, searchText);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<AdminStaff> adminStaffArrayList = new ArrayList<>();
        while (resultSet.next()) {
            adminStaffArrayList.add(
                    new AdminStaff(
                            resultSet.getString("staff_id"),
                            resultSet.getString("staff_name"),
                            resultSet.getString("contact_no"),
                            resultSet.getString("email")
                    )
            );
        }
        return adminStaffArrayList;
    }

    // get administrativeStaff details
    public AdminStaff getAdminMember(String entity) throws ClassNotFoundException, SQLException {

        String query = "SELECT * FROM adminStaff WHERE staff_id='" + entity + "'";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(query);
        preparedStatement.setString(1, entity);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new AdminStaff(
                    resultSet.getString("staff_id"),
                    resultSet.getString("staff_name"),
                    resultSet.getString("contact_no"),
                    resultSet.getString("email")
            );
        } else {
            return null;
        }
    }

    // update AdminStaff
    public boolean updateAdminStaff(AdminStaff adminStaff) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE adminStaff SET staff_name=?,contact_no=?,email=? WHERE staff_id=?";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, adminStaff.getName());
        preparedStatement.setString(2, adminStaff.getContact_number());
        preparedStatement.setString(3, adminStaff.getEmail());

        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            return true;
        }
        return false;
    }

    // delete AdminStaff
    public boolean deleteAdminStaff(AdminStaff adminStaff) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM adminStaff WHERE staff_id=?";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, adminStaff.getStaff_id());

        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            return true;
        }
        return false;
    }






    /*=========================================================================================================*/
    // Appointment-related database operations would be implemented in AppointmentDaoImpl
    /*=========================================================================================================*/

    /*=========================================================================================================*/
    // Payment-related database operations would be implemented in PaymentDaoImpl
    /*=========================================================================================================*/


}
