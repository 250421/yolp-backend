package com.revature.yolp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.yolp.models.Restaurant;
import com.revature.yolp.repositories.RestaurantRepository;

import lombok.Data;

@Service
@Data
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
}
