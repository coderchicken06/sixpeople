/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shop.ui.Manager;

import com.shop.entity.SP;

/**
 *
 * @author Dung Si Ban Tron
 */
public interface SPController extends CrudController<SP>{
    void fillCategories();
    void chooseFile();
}
