package com.hospital.backend.service;

import com.hospital.backend.entity.Restaurant;
import com.hospital.backend.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * RestaurantService - Business logic for Restaurants.
 */
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurants(boolean activeOnly) {
        if (activeOnly) {
            return restaurantRepository.findByActiveTrue();
        }
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        if (restaurant.getRating() == null) {
            restaurant.setRating(4.5);
        }
        if (restaurant.getDeliveryTimeMinutes() == null) {
            restaurant.setDeliveryTimeMinutes(30);
        }
        if (restaurant.getActive() == null) {
            restaurant.setActive(true);
        }
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Long id, Restaurant updated) {
        return restaurantRepository.findById(id).map(r -> {
            r.setName(updated.getName());
            r.setDescription(updated.getDescription());
            r.setCuisineType(updated.getCuisineType());
            r.setAddress(updated.getAddress());
            r.setPhone(updated.getPhone());
            if (updated.getRating() != null) r.setRating(updated.getRating());
            if (updated.getDeliveryTimeMinutes() != null) r.setDeliveryTimeMinutes(updated.getDeliveryTimeMinutes());
            if (updated.getImageUrl() != null) r.setImageUrl(updated.getImageUrl());
            if (updated.getActive() != null) r.setActive(updated.getActive());
            return restaurantRepository.save(r);
        }).orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    public List<Restaurant> searchRestaurants(String query) {
        if (query == null || query.isBlank()) {
            return restaurantRepository.findByActiveTrue();
        }
        return restaurantRepository.findByNameContainingIgnoreCaseOrCuisineTypeContainingIgnoreCase(query, query);
    }
}
