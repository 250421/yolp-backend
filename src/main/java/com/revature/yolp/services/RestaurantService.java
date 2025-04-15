package com.revature.yolp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.yolp.dtos.requests.NewRestaurantRequest;
import com.revature.yolp.models.Restaurant;
import com.revature.yolp.repositories.RestaurantRepository;
import com.revature.yolp.utils.custom_exceptions.DuplicateRestaurantNameException;

import lombok.Data;

@Service
@Data
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant addRestaurant(NewRestaurantRequest newRestaurantRequest) {
        if (isUniqueName(newRestaurantRequest.getName())) {
            throw new DuplicateRestaurantNameException("Restaurant name must be unique");
        }
        Restaurant restaurant = new Restaurant();
        restaurant.setName(newRestaurantRequest.getName())
                .setDescription(newRestaurantRequest.getDescription())
                .setImageUrl(newRestaurantRequest.getImageUrl())
                .setAddress(newRestaurantRequest.getAddress())
                .setPhone(newRestaurantRequest.getPhone());
        return restaurantRepository.save(restaurant);
    }

    private boolean isUniqueName(String name) {
        return restaurantRepository.existsByName(name);
    }
}
