/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafe.dao;

import java.util.Date;
import java.util.List;
import com.cafe.entity.Revenue;
/**
 *
 * @author VAN TRONG
 */
public interface RevenueDAO {
    List<Revenue.ByCategory> getByCategory(Date begin, Date end);
    List<Revenue.ByUser> getByUser(Date begin, Date end);
}
