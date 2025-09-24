package com.rc008code.hms.repository.custom.impl;

import com.rc008code.hms.entity.Payment;
import com.rc008code.hms.enums.PaymentStatus;
import com.rc008code.hms.enums.PaymentTypes;
import com.rc008code.hms.repository.CrudUtil;
import com.rc008code.hms.repository.custom.PaymentDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {
    @Override
    public boolean create(Payment payment) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO payment (payment_id, patient_id, appointment_id, amount, payment_date, method, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql,
                payment.getPayment_id(),
                payment.getPatient_id(),
                payment.getAppointment_id(),
                payment.getAmount(),
                payment.getPayment_date(),
                payment.getMethod().toString(),
                payment.getStatus().toString()
        );
    }

    @Override
    public Payment find(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM payment WHERE payment_id=?", id);
        if (rs.next()) {
            return mapRow(rs);
        }
        return null;
    }

    @Override
    public boolean update(Payment payment) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE payment SET patient_id=?, appointment_id=?, amount=?, payment_date=?, method=?, status=? WHERE payment_id=?";
        return CrudUtil.execute(sql,
                payment.getPatient_id(),
                payment.getAppointment_id(),
                payment.getAmount(),
                payment.getPayment_date(),
                payment.getMethod().toString(),
                payment.getStatus().toString(),
                payment.getPayment_id()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM payment WHERE payment_id=?", id);
    }

    @Override
    public List<Payment> findAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM payment");
        List<Payment> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapRow(rs));
        }
        return list;
    }

    private Payment mapRow(ResultSet rs) throws SQLException {
        return new Payment(
                rs.getString("payment_id"),
                rs.getString("patient_id"),
                rs.getString("appointment_id"),
                rs.getDouble("amount"),
                rs.getString("payment_date"),
                PaymentTypes.valueOf(rs.getString("method")),
                PaymentStatus.valueOf(rs.getString("status"))
        );
    }
}
