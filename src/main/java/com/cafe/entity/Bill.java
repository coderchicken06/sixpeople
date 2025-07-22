package com.cafe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data                       // Tự sinh getter, setter, toString, equals, hashCode
@NoArgsConstructor          // Sinh constructor không tham số
@AllArgsConstructor         // Sinh constructor đầy đủ tham số
@Builder                   // Sinh builder pattern
public class Bill {

    private Long id;
    private String username;
    private Integer cardId;
    @Builder.Default
    private Date checkin = new Date();
    private Date checkout;
    private int status;

    public enum Status {
        Servicing, Completed, Canceled;
    }
    public static final String DATE_PATTERN = "HH:mm:ss dd-MM-yyyy";
}
