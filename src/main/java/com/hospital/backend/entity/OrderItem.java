package com.hospital.backend.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * OrderItem Entity - Individual food line-items within an order.
 * College Project: Food Ordering System
 */
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    @Column(name = "item_name", nullable = false, length = 150)
    private String itemName;

    @Column(name = "item_price", nullable = false)
    private Double itemPrice;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double subtotal;

    public OrderItem() {}

    public OrderItem(Order order, MenuItem menuItem, String itemName, Double itemPrice, Integer quantity, Double subtotal) {
        this.order = order;
        this.menuItem = menuItem;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
