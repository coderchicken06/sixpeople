/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shop.uii;
import com.shop.entity.Bill;

/**
 *
 * @author VAN TRONG
 */
public interface BillController {

    void setBill(Bill bill);

    void open();

    void close();

    void showDrinkJDialog();

    void removeDrinks();

    void updateQuantity();

    void checkout();

    void cancel();
}
