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
public interface DrinkController {

    void setBill(Bill bill);

    void open();

    void fillCategories();

    void fillDrinks();

    void addDrinkToBill();
}
