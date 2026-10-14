package com.hospital.backend.service;

import com.hospital.backend.entity.MenuItem;
import com.hospital.backend.entity.Restaurant;
import com.hospital.backend.repository.MenuItemRepository;
import com.hospital.backend.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * MenuItemService - Business logic for Restaurant Menu Items.
 */
@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuItemService(MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<MenuItem> getItemsByRestaurant(Long restaurantId, boolean availableOnly) {
        if (availableOnly) {
            return menuItemRepository.findByRestaurantIdAndAvailableTrue(restaurantId);
        }
        return menuItemRepository.findByRestaurantId(restaurantId);
    }

    public Optional<MenuItem> getItemById(Long id) {
        return menuItemRepository.findById(id);
    }

    public MenuItem createMenuItem(Long restaurantId, MenuItem item) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + restaurantId));
        item.setRestaurant(restaurant);
        if (item.getIsVegetarian() == null) item.setIsVegetarian(true);
        if (item.getAvailable() == null) item.setAvailable(true);
        return menuItemRepository.save(item);
    }

    public MenuItem updateMenuItem(Long id, MenuItem updated) {
        return menuItemRepository.findById(id).map(m -> {
            m.setName(updated.getName());
            m.setDescription(updated.getDescription());
            m.setPrice(updated.getPrice());
            m.setCategory(updated.getCategory());
            if (updated.getImageUrl() != null) m.setImageUrl(updated.getImageUrl());
            if (updated.getIsVegetarian() != null) m.setIsVegetarian(updated.getIsVegetarian());
            if (updated.getAvailable() != null) m.setAvailable(updated.getAvailable());
            return menuItemRepository.save(m);
        }).orElseThrow(() -> new RuntimeException("MenuItem not found with id: " + id));
    }

    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }
}
