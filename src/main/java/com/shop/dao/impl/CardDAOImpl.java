/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shop.dao.impl;

import com.shop.dao.CardDAO;
import com.shop.entity.Card;
import com.shop.util.XJdbc;
import com.shop.util.XQuery;
import java.util.List;

/**
 *
 * @author VAN TRONG
 */
public class CardDAOImpl implements CardDAO {

    String createSql = "INSERT INTO Cards (Id, Status) VALUES (?, ?)";
    String updateSql = "UPDATE Cards SET Status = ? WHERE Id = ?";
    String deleteSql = "DELETE FROM Cards WHERE Id = ?";
    String findAllSql = "SELECT * FROM Cards";
    String findByIdSql = "SELECT * FROM Cards WHERE Id = ?";

    @Override
    public Card create(Card entity) {
        XJdbc.executeUpdate(createSql,
                entity.getId(),
                entity.getStatus()
        );
        return entity;
    }

    @Override
    public void update(Card entity) {
        XJdbc.executeUpdate(updateSql,
                entity.getStatus(),
                entity.getId()
        );
    }

    @Override
    public void deleteById(Integer id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List< Card> findAll() {
        return XQuery.getBeanList(Card.class, findAllSql);
    }

    @Override
    public Card findById(Integer id) {
        return XQuery.getSingleBean(Card.class, findByIdSql, id);
    }
}
