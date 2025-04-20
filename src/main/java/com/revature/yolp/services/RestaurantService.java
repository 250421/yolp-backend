package com.revature.yolp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.yolp.dtos.requests.NewRestaurantRequest;
import com.revature.yolp.dtos.requests.UpdateRestaurantRequest;
import com.revature.yolp.models.Restaurant;
import com.revature.yolp.repositories.RestaurantRepository;
import com.revature.yolp.utils.custom_exceptions.DuplicateRestaurantNameException;
import com.revature.yolp.utils.custom_exceptions.RestaurantNotFoundException;

import lombok.Data;

@Service
@Data
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));
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

    public Restaurant updateRestaurant(UpdateRestaurantRequest updateRestaurantRequest) {
        Restaurant restaurant = getRestaurantById(updateRestaurantRequest.getId());
        restaurant.setName(updateRestaurantRequest.getName())
                .setDescription(updateRestaurantRequest.getDescription())
                .setImageUrl(updateRestaurantRequest.getImageUrl())
                .setAddress(updateRestaurantRequest.getAddress())
                .setPhone(updateRestaurantRequest.getPhone());
        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new RestaurantNotFoundException("Restaurant not found");
        }
        restaurantRepository.deleteById(id);
    }

    private boolean isUniqueName(String name) {
        return restaurantRepository.existsByName(name);
    }
}
