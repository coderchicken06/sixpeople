/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shop.ui.Manager;

import com.shop.entity.Drink;

/**
 *
 * @author VAN TRONG
 */
public interface DrinkController extends CrudController<Drink>{
    void fillCategories();
    void chooseFile();
}
