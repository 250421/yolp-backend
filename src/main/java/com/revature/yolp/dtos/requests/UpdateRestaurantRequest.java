package com.revature.yolp.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRestaurantRequest {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String address;
    private String phone;
}
