package com.hospital.backend.controller;

import com.hospital.backend.dto.ApiResponse;
import com.hospital.backend.dto.DashboardStatsResponse;
import com.hospital.backend.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AdminController - Provides overall metrics and summary data for the Admin panel.
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final OrderService orderService;

    public AdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<DashboardStatsResponse>> getStats() {
        DashboardStatsResponse stats = orderService.getDashboardStats();
        return ResponseEntity.ok(ApiResponse.ok("Dashboard statistics retrieved", stats));
    }
}
