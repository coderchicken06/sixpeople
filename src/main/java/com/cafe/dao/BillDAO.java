/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafe.dao;

import com.cafe.entity.Bill;
import java.util.Date;
import java.util.List;

/**
 *
 * @author VAN TRONG
 */
public interface BillDAO extends CrudDAO<Bill, Long> {
    List<Bill> findByUsername(String username);
    List<Bill> findByCardId(Integer cardId);
    public Bill findServicingByCardId(Integer cardId);
    public List<Bill> findByTimeRange(Date from, Date to);
    public List<Bill> findByUserAndTimeRange(String username, Date from, Date to);
}
