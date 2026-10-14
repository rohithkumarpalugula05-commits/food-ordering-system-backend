package com.hospital.backend.repository;

import com.hospital.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * OrderRepository - Database operations for Customer Orders.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<Order> findAllByOrderByCreatedAtDesc();
    Optional<Order> findByOrderTrackingNumber(String trackingNumber);

    @Query("SELECT COUNT(o) FROM Order o")
    long countTotalOrders();

    @Query("SELECT COALESCE(SUM(o.totalAmount), 0.0) FROM Order o WHERE o.orderStatus != 'CANCELLED'")
    Double calculateTotalRevenue();
}
