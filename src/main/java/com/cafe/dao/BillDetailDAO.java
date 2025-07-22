/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafe.dao;

import com.cafe.entity.BillDetail;
import java.util.List;

/**
 *
 * @author VAN TRONG
 */
public interface BillDetailDAO extends CrudDAO<BillDetail, Long> {
    List<BillDetail> findByBillId(Long billId);
    List<BillDetail> findByDrinkId(String drinkId);
}
