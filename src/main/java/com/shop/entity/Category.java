package com.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                       // Tự sinh getter, setter, toString, equals, hashCode
@NoArgsConstructor          // Sinh constructor không tham số
@AllArgsConstructor         // Sinh constructor đầy đủ tham số
@Builder                   // Sinh builder pattern
public class Category {

    private String id;
    private String name;
}
