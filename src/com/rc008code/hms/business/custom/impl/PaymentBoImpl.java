package com.rc008code.hms.business.custom.impl;

import com.rc008code.hms.business.custom.PaymentBo;
import com.rc008code.hms.dto.PaymentDto;
import com.rc008code.hms.entity.Payment;
import com.rc008code.hms.repository.DaoFactory;
import com.rc008code.hms.repository.custom.PaymentDao;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentBoImpl implements PaymentBo {
    private final PaymentDao paymentDao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.PAYMENT);

    @Override
    public boolean create(PaymentDto entity) throws SQLException, ClassNotFoundException {
        return paymentDao.create(toEntity(entity));
    }

    @Override
    public PaymentDto find(String id) throws SQLException, ClassNotFoundException {
        Payment payment = paymentDao.find(id);
        return payment != null ? toDto(payment) : null;
    }

    @Override
    public boolean update(PaymentDto entity) throws SQLException, ClassNotFoundException {
        return paymentDao.update(toEntity(entity));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return paymentDao.delete(id);
    }

    @Override
    public List<PaymentDto> readAll() throws SQLException, ClassNotFoundException {
        return paymentDao.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    private Payment toEntity(PaymentDto dto) {
        return new Payment(
                dto.getPayment_id(),
                dto.getPatient_id(),
                dto.getAppointment_id(),
                dto.getAmount(),
                dto.getPayment_date(),
                dto.getMethod(),
                dto.getStatus()
        );
    }

    private PaymentDto toDto(Payment entity) {
        return new PaymentDto(
                entity.getPayment_id(),
                entity.getPatient_id(),
                entity.getAppointment_id(),
                entity.getAmount(),
                entity.getPayment_date(),
                entity.getMethod(),
                entity.getStatus()
        );
    }
}
