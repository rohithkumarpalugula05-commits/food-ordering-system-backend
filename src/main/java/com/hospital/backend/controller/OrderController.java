package com.hospital.backend.controller;

import com.hospital.backend.dto.ApiResponse;
import com.hospital.backend.dto.OrderRequest;
import com.hospital.backend.dto.OrderStatusUpdateRequest;
import com.hospital.backend.entity.Order;
import com.hospital.backend.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * OrderController - Endpoints for placing orders, retrieving user orders, and order tracking.
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Order>> placeOrder(@Valid @RequestBody OrderRequest request) {
        try {
            Order order = orderService.placeOrder(request);
            return ResponseEntity.ok(ApiResponse.ok("Order placed successfully!", order));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Order>>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(ApiResponse.ok("All orders retrieved", orders));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Order>>> getOrdersByUser(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersByUser(userId);
        return ResponseEntity.ok(ApiResponse.ok("Customer orders retrieved", orders));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(order -> ResponseEntity.ok(ApiResponse.ok(order)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/track/{trackingNumber}")
    public ResponseEntity<ApiResponse<Order>> getOrderByTrackingNumber(@PathVariable String trackingNumber) {
        return orderService.getOrderByTrackingNumber(trackingNumber)
                .map(order -> ResponseEntity.ok(ApiResponse.ok(order)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Order>> updateOrderStatus(
            @PathVariable Long id,
            @Valid @RequestBody OrderStatusUpdateRequest request) {
        try {
            Order updated = orderService.updateOrderStatus(id, request.getStatus());
            return ResponseEntity.ok(ApiResponse.ok("Order status updated successfully", updated));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}
