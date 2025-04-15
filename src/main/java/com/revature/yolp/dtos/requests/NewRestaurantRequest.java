package com.revature.yolp.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewRestaurantRequest {
    private String name;
    private String description;
    private String imageUrl;
    private String address;
    private String phone;
}
