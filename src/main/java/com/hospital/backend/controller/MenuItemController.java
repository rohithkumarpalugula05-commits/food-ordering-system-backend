package com.hospital.backend.controller;

import com.hospital.backend.dto.ApiResponse;
import com.hospital.backend.entity.MenuItem;
import com.hospital.backend.service.MenuItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * MenuItemController - Endpoints for dishes and menu management.
 */
@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MenuItem>>> getAllMenuItems() {
        return ResponseEntity.ok(ApiResponse.ok(menuItemService.getAllMenuItems()));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<ApiResponse<List<MenuItem>>> getItemsByRestaurant(
            @PathVariable Long restaurantId,
            @RequestParam(required = false, defaultValue = "false") boolean all) {
        List<MenuItem> items = menuItemService.getItemsByRestaurant(restaurantId, !all);
        return ResponseEntity.ok(ApiResponse.ok("Menu items retrieved", items));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuItem>> getItemById(@PathVariable Long id) {
        return menuItemService.getItemById(id)
                .map(item -> ResponseEntity.ok(ApiResponse.ok(item)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/restaurant/{restaurantId}")
    public ResponseEntity<ApiResponse<MenuItem>> createMenuItem(
            @PathVariable Long restaurantId,
            @Valid @RequestBody MenuItem menuItem) {
        try {
            MenuItem created = menuItemService.createMenuItem(restaurantId, menuItem);
            return ResponseEntity.ok(ApiResponse.ok("Menu item created successfully", created));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuItem>> updateMenuItem(
            @PathVariable Long id,
            @Valid @RequestBody MenuItem menuItem) {
        try {
            MenuItem updated = menuItemService.updateMenuItem(id, menuItem);
            return ResponseEntity.ok(ApiResponse.ok("Menu item updated successfully", updated));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMenuItem(@PathVariable Long id) {
        try {
            menuItemService.deleteMenuItem(id);
            return ResponseEntity.ok(ApiResponse.ok("Menu item deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}
