package com.hospital.backend.controller;

import com.hospital.backend.dto.ApiResponse;
import com.hospital.backend.entity.Restaurant;
import com.hospital.backend.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * RestaurantController - Endpoints for browsing and managing restaurants.
 */
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Restaurant>>> getAllRestaurants(
            @RequestParam(required = false, defaultValue = "false") boolean all,
            @RequestParam(required = false) String search) {

        List<Restaurant> restaurants;
        if (search != null && !search.isBlank()) {
            restaurants = restaurantService.searchRestaurants(search);
        } else {
            restaurants = restaurantService.getAllRestaurants(!all);
        }
        return ResponseEntity.ok(ApiResponse.ok("Restaurants retrieved", restaurants));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Restaurant>> getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id)
                .map(restaurant -> ResponseEntity.ok(ApiResponse.ok(restaurant)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Restaurant>> createRestaurant(@Valid @RequestBody Restaurant restaurant) {
        Restaurant created = restaurantService.createRestaurant(restaurant);
        return ResponseEntity.ok(ApiResponse.ok("Restaurant created successfully", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Restaurant>> updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody Restaurant restaurant) {
        try {
            Restaurant updated = restaurantService.updateRestaurant(id, restaurant);
            return ResponseEntity.ok(ApiResponse.ok("Restaurant updated successfully", updated));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRestaurant(@PathVariable Long id) {
        try {
            restaurantService.deleteRestaurant(id);
            return ResponseEntity.ok(ApiResponse.ok("Restaurant deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}
