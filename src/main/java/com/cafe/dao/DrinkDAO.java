/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cafe.dao;

import com.cafe.entity.Drink;
import java.util.List;

/**
 *
 * @author VAN TRONG
 */
public interface DrinkDAO extends CrudDAO<Drink, String> {
    List<Drink> findByCategoryId(String categoryId);
}
