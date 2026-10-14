package com.hospital.backend.service;

import com.hospital.backend.dto.OrderItemRequest;
import com.hospital.backend.dto.OrderRequest;
import com.hospital.backend.dto.DashboardStatsResponse;
import com.hospital.backend.entity.*;
import com.hospital.backend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * OrderService - Handles order placement, item line calculations, and status tracking.
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        UserRepository userRepository,
                        RestaurantRepository restaurantRepository,
                        MenuItemRepository menuItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    /**
     * Generate unique tracking code: e.g. ORD-20261013-8472
     */
    private String generateTrackingNumber() {
        String datePrefix = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int randomCode = 1000 + new Random().nextInt(9000);
        return "ORD-" + datePrefix + "-" + randomCode;
    }

    @Transactional
    public Order placeOrder(OrderRequest req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + req.getUserId()));

        Restaurant restaurant = restaurantRepository.findById(req.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + req.getRestaurantId()));

        String trackingNumber = generateTrackingNumber();

        // Calculate total amount
        double total = 0.0;
        for (OrderItemRequest itemReq : req.getItems()) {
            total += (itemReq.getItemPrice() * itemReq.getQuantity());
        }

        Order order = new Order(
                trackingNumber,
                user,
                restaurant,
                "PLACED",
                Math.round(total * 100.0) / 100.0,
                req.getDeliveryAddress(),
                req.getCustomerPhone(),
                req.getCustomerName(),
                req.getPaymentMethod(),
                req.getSpecialInstructions()
        );

        Order savedOrder = orderRepository.save(order);

        for (OrderItemRequest itemReq : req.getItems()) {
            MenuItem menuItem = null;
            if (itemReq.getMenuItemId() != null) {
                menuItem = menuItemRepository.findById(itemReq.getMenuItemId()).orElse(null);
            }
            double subtotal = Math.round((itemReq.getItemPrice() * itemReq.getQuantity()) * 100.0) / 100.0;
            OrderItem orderItem = new OrderItem(
                    savedOrder,
                    menuItem,
                    itemReq.getItemName(),
                    itemReq.getItemPrice(),
                    itemReq.getQuantity(),
                    subtotal
            );
            orderItemRepository.save(orderItem);
            savedOrder.getItems().add(orderItem);
        }

        return savedOrder;
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Optional<Order> getOrderByTrackingNumber(String trackingNumber) {
        return orderRepository.findByOrderTrackingNumber(trackingNumber);
    }

    public Order updateOrderStatus(Long orderId, String newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        order.setOrderStatus(newStatus.toUpperCase());
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public DashboardStatsResponse getDashboardStats() {
        long totalOrders = orderRepository.countTotalOrders();
        Double totalRevenue = orderRepository.calculateTotalRevenue();
        long totalRestaurants = restaurantRepository.count();
        long totalMenuItems = menuItemRepository.count();
        long totalUsers = userRepository.count();

        return new DashboardStatsResponse(
                totalOrders,
                totalRevenue != null ? Math.round(totalRevenue * 100.0) / 100.0 : 0.0,
                totalRestaurants,
                totalMenuItems,
                totalUsers
        );
    }
}
