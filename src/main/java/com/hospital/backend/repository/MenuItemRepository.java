package com.hospital.backend.repository;

import com.hospital.backend.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * MenuItemRepository - Database operations for Menu Items.
 */
@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByRestaurantId(Long restaurantId);
    List<MenuItem> findByRestaurantIdAndAvailableTrue(Long restaurantId);
    List<MenuItem> findByRestaurantIdAndCategoryIgnoreCase(Long restaurantId, String category);
}
