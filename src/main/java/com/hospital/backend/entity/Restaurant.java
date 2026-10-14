package com.hospital.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Restaurant Entity - Represents dining outlets / restaurants.
 * College Project: Food Ordering System
 */
@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Restaurant name is required")
    @Column(nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "cuisine_type", length = 100)
    private String cuisineType;

    @Column(length = 255)
    private String address;

    @Column(length = 20)
    private String phone;

    @Column
    private Double rating = 4.5;

    @Column(name = "delivery_time_minutes")
    private Integer deliveryTimeMinutes = 30;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<MenuItem> menuItems = new ArrayList<>();

    public Restaurant() {}

    public Restaurant(String name, String description, String cuisineType, String address, String phone, Double rating, Integer deliveryTimeMinutes, String imageUrl, Boolean active) {
        this.name = name;
        this.description = description;
        this.cuisineType = cuisineType;
        this.address = address;
        this.phone = phone;
        this.rating = rating != null ? rating : 4.5;
        this.deliveryTimeMinutes = deliveryTimeMinutes != null ? deliveryTimeMinutes : 30;
        this.imageUrl = imageUrl;
        this.active = active != null ? active : true;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getDeliveryTimeMinutes() {
        return deliveryTimeMinutes;
    }

    public void setDeliveryTimeMinutes(Integer deliveryTimeMinutes) {
        this.deliveryTimeMinutes = deliveryTimeMinutes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
