/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafe.dao.impl;

import com.cafe.dao.DrinkDAO;
import com.cafe.entity.Drink;
import com.cafe.util.XJdbc;
import com.cafe.util.XQuery;
import java.util.List;

/**
 *
 * @author VAN TRONG
 */
public class DrinkDAOImpl implements DrinkDAO {

    String createSql = "INSERT INTO Drinks (Id, Name, Image, UnitPrice, Discount, Available, CategoryId) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Drinks SET Name = ?, Image = ?, UnitPrice = ?, Discount = ?, Available = ?, CategoryId = ? WHERE Id = ?";
    String deleteSql = "DELETE FROM Drinks WHERE Id = ?";
    String findAllSql = "SELECT * FROM Drinks";
    String findByIdSql = "SELECT * FROM Drinks WHERE Id = ?";
    String findByCategoryIdSql = "SELECT * FROM Drinks WHERE CategoryId = ?";

    @Override
    public Drink create(Drink entity) {
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
    public void update(Drink entity) {
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
    public List< Drink> findAll() {
        return XQuery.getBeanList(Drink.class, findAllSql);
    }

    @Override
    public Drink findById(String id) {
        return XQuery.getSingleBean(Drink.class, findByIdSql, id);
    }

    @Override
    public List<Drink> findByCategoryId(String categoryId) {
        return XQuery.getBeanList(Drink.class, findByCategoryIdSql, categoryId);
    }
}
