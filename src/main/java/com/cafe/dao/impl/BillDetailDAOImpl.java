/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafe.dao.impl;

import com.cafe.dao.BillDetailDAO;
import com.cafe.entity.BillDetail;
import com.cafe.util.XJdbc;
import com.cafe.util.XQuery;
import java.util.List;

/**
 *
 * @author VAN TRONG
 */
public class BillDetailDAOImpl implements BillDetailDAO {

    String createSql = "INSERT INTO BillDetails (BillId, DrinkId, UnitPrice, Discount, Quantity) VALUES (?, ?, ?, ?, ?)";
    String updateSql = "UPDATE BillDetails SET BillId = ?, DrinkId = ?, UnitPrice = ?, Discount = ?, Quantity = ? WHERE Id = ?";
    String deleteSql = "DELETE FROM BillDetails WHERE Id = ?";
    String findAllSql = "SELECT * FROM BillDetails";
    String findByIdSql = "SELECT * FROM BillDetails WHERE Id = ?";
    String findByBillIdSql = "    SELECT bd.Id, bd.BillId, bd.DrinkId, bd.UnitPrice, bd.Discount, bd.Quantity, d.Name AS DrinkName\n"
            + "    FROM BillDetails bd\n"
            + "    JOIN Drinks d ON bd.DrinkId = d.Id\n"
            + "    WHERE bd.BillId = ?";
    String findByDrinkIdSql = "SELECT * FROM BillDetails WHERE DrinkId = ?";

    @Override
    public BillDetail create(BillDetail entity) {
        XJdbc.executeUpdate(createSql,
                entity.getBillId(),
                entity.getDrinkId(),
                entity.getUnitPrice(),
                entity.getDiscount(),
                entity.getQuantity()
        );
        return entity;
    }

    @Override
    public void update(BillDetail entity) {
        XJdbc.executeUpdate(updateSql,
                entity.getBillId(),
                entity.getDrinkId(),
                entity.getUnitPrice(),
                entity.getDiscount(),
                entity.getQuantity(),
                entity.getId()
        );
    }

    @Override
    public void deleteById(Long id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<BillDetail> findAll() {
        return XQuery.getBeanList(BillDetail.class, findAllSql);
    }

    @Override
    public BillDetail findById(Long id) {
        return XQuery.getSingleBean(BillDetail.class, findByIdSql, id);
    }

    @Override
    public List<BillDetail> findByBillId(Long billId) {
        return XQuery.getBeanList(BillDetail.class, findByBillIdSql, billId);
    }

    @Override
    public List<BillDetail> findByDrinkId(String drinkId) {
        return XQuery.getBeanList(BillDetail.class, findByDrinkIdSql, drinkId);
    }
}
