/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shop.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Customers {

    private int customerId;
    private String fullname;
    private Date dateOfBirth;
    private boolean gender;
    private String address;
    private String phoneNumber;
    private String email;

    @Override
    public String toString() {
        return this.fullname;
    }

}
