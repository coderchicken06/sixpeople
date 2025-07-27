/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shop.dao.impl;

import com.shop.entity.SP;
import com.shop.util.XJdbc;
import com.shop.util.XQuery;
import java.util.List;
import com.shop.dao.SPDAO;

/**
 *
 * @author Dung Si Ban Tron
 */
public class SPDAOImpl implements SPDAO {

    String createSql = "INSERT INTO Drinks (Id, Name, Image, UnitPrice, Discount, Available, CategoryId) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Drinks SET Name = ?, Image = ?, UnitPrice = ?, Discount = ?, Available = ?, CategoryId = ? WHERE Id = ?";
    String deleteSql = "DELETE FROM Drinks WHERE Id = ?";
    String findAllSql = "SELECT * FROM Drinks";
    String findByIdSql = "SELECT * FROM Drinks WHERE Id = ?";
    String findByCategoryIdSql = "SELECT * FROM Drinks WHERE CategoryId = ?";

    @Override
    public SP create(SP entity) {
        XJdbc.executeUpdate(createSql,
                entity.getId(),
                entity.getName(),
                entity.getImage(),
                entity.getUnitPrice(),
                entity.getDiscount(),
                entity.isAvailable(),
                entity.getCategoryId()
        );
        return entity;
    }

    @Override
    public void update(SP entity) {
        XJdbc.executeUpdate(updateSql,
                entity.getName(),
                entity.getImage(),
                entity.getUnitPrice(),
                entity.getDiscount(),
                entity.isAvailable(),
                entity.getCategoryId(),
                entity.getId()
        );
    }

    public boolean hasRelatedBillDetails(String drinkId) {
        String checkSql = "SELECT COUNT(*) FROM BillDetails WHERE DrinkId = ?";
        Integer count = XQuery.getSingleBean(Integer.class, checkSql, drinkId);
        return count > 0;
    }

    public void deleteRelatedBillDetails(String drinkId) {
        String deleteBillDetailsSql = "DELETE FROM BillDetails WHERE DrinkId = ?";
        XJdbc.executeUpdate(deleteBillDetailsSql, drinkId);
    }

    @Override
    public void deleteById(String id) {
        deleteRelatedBillDetails(id);
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List< SP> findAll() {
        return XQuery.getBeanList(SP.class, findAllSql);
    }

    @Override
    public SP findById(String id) {
        return XQuery.getSingleBean(SP.class, findByIdSql, id);
    }

    @Override
    public List<SP> findByCategoryId(String categoryId) {
        return XQuery.getBeanList(SP.class, findByCategoryIdSql, categoryId);
    }
}
