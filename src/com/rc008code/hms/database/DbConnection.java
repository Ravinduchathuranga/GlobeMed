package com.rc008code.hms.database;

// Database connection details and methods would go here

import com.rc008code.hms.entity.*;
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
    // create patient
    public boolean createDoctor(Doctor doctor) throws ClassNotFoundException, SQLException {
        // Implementation for creating a patient record in the database
        String sql = "INSERT INTO patient VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1, doctor.getDoctorId());
        preparedStatement.setString(2, doctor.getName());
        preparedStatement.setString(6, doctor.getContact());
        preparedStatement.setString(7, doctor.getEmail());

        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            return true;
        }
        return false;
    }

    // get all patients
    public List<Doctor> getAllDoctors(String searchText) throws ClassNotFoundException, SQLException {

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
    public Doctor getDoctor(String doctorId) throws ClassNotFoundException, SQLException {

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
    public boolean updatePatient(Doctor doctor) throws ClassNotFoundException, SQLException {
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
    public boolean deletePatient(Doctor doctor) throws SQLException, ClassNotFoundException {
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
    // Nurse-related database operations would be implemented in NurseDaoImpl
    /*=========================================================================================================*/

    // create patient
    public boolean createNurse(Patient patient) throws ClassNotFoundException, SQLException {
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
    public List<Nurse> getAllNurses(String searchText) throws ClassNotFoundException, SQLException {

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
    public Nurse getNurse(String NurseId) throws ClassNotFoundException, SQLException {

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
    public boolean updatePatient(Nurse nurse) throws ClassNotFoundException, SQLException {
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
    public boolean deletePatient(Nurse nurse) throws SQLException, ClassNotFoundException {
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
    // Pharmacist-related database operations would be implemented in PharmacistDaoImpl
    /*=========================================================================================================*/


    // create patient
    public boolean createPharmasist(Pharmacist pharmacist) throws ClassNotFoundException, SQLException {
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
    public List<Pharmacist> getAllPharmacists(String searchText) throws ClassNotFoundException, SQLException {

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
    public Pharmacist getPharmacist(String pharmesist) throws ClassNotFoundException, SQLException {

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
    public boolean updatePatient(Pharmacist pharmacist) throws ClassNotFoundException, SQLException {
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
    public boolean deletePatient(Pharmacist pharmacist) throws SQLException, ClassNotFoundException {
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
    // AdministrativeStaff-related database operations would be implemented in AdministrativeStaffDaoImpl
    /*=========================================================================================================*/

    // create patient
    public boolean createAdminStaff(AdminStaff adminStaff) throws ClassNotFoundException, SQLException {
        // Implementation for creating a patient record in the database
        String sql = "INSERT INTO patient VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = MysqlConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(0, adminStaff.getStaff_id()));
        preparedStatement.setString(1, patient.getName());
        preparedStatement.setString(2, patient.getAddress());
        preparedStatement.setInt(3, patient.getAge());
        preparedStatement.setString(4, String.valueOf(patient.getGender()));
        preparedStatement.setString(5, patient.getContact());
        preparedStatement.setString(6, patient.getEmail());

        int i = preparedStatement.executeUpdate();
        if (i > -1) {
            return true;
        }
        return false;
    }

    // get all patients
    public List<AdminStaff> getAllAdminStaff(String searchText) throws ClassNotFoundException, SQLException {

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
    public AdminStaff getAdminMember(String patientId) throws ClassNotFoundException, SQLException {

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
    public boolean updateAdminStaff(AdminStaff adminStaff) throws ClassNotFoundException, SQLException {
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
    public boolean deleteAdminStaff(AdminStaff adminStaff) throws SQLException, ClassNotFoundException {
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
    // Appointment-related database operations would be implemented in AppointmentDaoImpl
    /*=========================================================================================================*/

    /*=========================================================================================================*/
    // Payment-related database operations would be implemented in PaymentDaoImpl
    /*=========================================================================================================*/


}
