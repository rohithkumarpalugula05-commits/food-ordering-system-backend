package com.hospital.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderItemRequest {

    private Long menuItemId;

    @NotNull(message = "Item name is required")
    private String itemName;

    @NotNull(message = "Item price is required")
    @Positive(message = "Item price must be positive")
    private Double itemPrice;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be at least 1")
    private Integer quantity;

    public OrderItemRequest() {}

    public OrderItemRequest(Long menuItemId, String itemName, Double itemPrice, Integer quantity) {
        this.menuItemId = menuItemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
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
}
