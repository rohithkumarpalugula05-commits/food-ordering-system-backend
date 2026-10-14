package com.hospital.backend.repository;

import com.hospital.backend.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * RestaurantRepository - Database operations for Restaurants.
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByActiveTrue();
    List<Restaurant> findByNameContainingIgnoreCaseOrCuisineTypeContainingIgnoreCase(String name, String cuisine);
}
