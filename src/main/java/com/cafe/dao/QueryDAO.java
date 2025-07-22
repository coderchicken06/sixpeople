/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafe.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.cafe.util.XJdbc;

/**
 *
 * @author VAN TRONG
 */
public interface QueryDAO {

    default <T> List<T> getEntityList(String sql, Object... values) {
        List<T> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, values);
            while (rs.next()) {
                list.add(this.readEntity(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    default <T> T getSingleEntity(String sql, Object... values) {
        List<T> list = this.getEntityList(sql, values);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    <T> T readEntity(ResultSet rs) throws SQLException;
}
