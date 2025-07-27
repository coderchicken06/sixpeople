/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shop.dao;

import com.shop.entity.SP;
import java.util.List;

/**
 *
 * @author Dung Si Ban Tron
 */
public interface SPDAO extends CrudDAO<SP, String> {
    List<SP> findByCategoryId(String categoryId);
}
