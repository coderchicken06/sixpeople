/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafe.ui.Manager;

import com.cafe.entity.Bill;

/**
 *
 * @author VAN TRONG
 */
public interface BillController extends CrudController<Bill> {

    void fillBillDetails();

    void selectTimeRange();
}
