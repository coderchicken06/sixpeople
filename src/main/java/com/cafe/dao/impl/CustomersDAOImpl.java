package com.cafe.dao.impl;

import java.util.List;
import com.cafe.entity.Customers;
import com.cafe.util.XJdbc;
import com.cafe.util.XQuery;
import com.cafe.dao.CustomersDAO;

public class CustomersDAOImpl implements CustomersDAO {

    private final String createSql = "INSERT INTO KhachHang(fullname, dateofbirth, gender, address, phonenumber, email) VALUES(?, ?, ?, ?, ?, ?)";
    private final String updateSql = "UPDATE KhachHang SET fullname = ?, dateofbirth = ?, gender = ?, address = ?, phonenumber = ?, email = ? WHERE customerId = ?";
    private final String deleteByIdSql = "DELETE FROM KhachHang WHERE customerId = ?";

    private final String findAllSql = "SELECT * FROM KhachHang";
    private final String findByIdSql = findAllSql + " WHERE customerId = ?";

    @Override
    public Customers create(Customers entity) {
        Object[] values = {
            entity.getFullname(),
            entity.getDateOfBirth(),
            entity.isGender(),
            entity.getAddress(),
            entity.getPhoneNumber(),
            entity.getEmail()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(Customers entity) {
        Object[] values = {
            entity.getFullname(),
            entity.getDateOfBirth(),
            entity.isGender(),
            entity.getAddress(),
            entity.getPhoneNumber(),
            entity.getEmail(),
            entity.getCustomerId()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteByIdSql, Integer.parseInt(id));
    }

    @Override
    public List<Customers> findAll() {
        return XQuery.getBeanList(Customers.class, findAllSql);
    }

    @Override
    public Customers findById(String id) {
        return XQuery.getSingleBean(Customers.class, findByIdSql, Integer.parseInt(id));
    }
}
