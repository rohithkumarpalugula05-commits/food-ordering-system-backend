package com.hospital.backend.dto;

public class DashboardStatsResponse {
    private long totalOrders;
    private double totalRevenue;
    private long totalRestaurants;
    private long totalMenuItems;
    private long totalUsers;

    public DashboardStatsResponse() {}

    public DashboardStatsResponse(long totalOrders, double totalRevenue, long totalRestaurants, long totalMenuItems, long totalUsers) {
        this.totalOrders = totalOrders;
        this.totalRevenue = totalRevenue;
        this.totalRestaurants = totalRestaurants;
        this.totalMenuItems = totalMenuItems;
        this.totalUsers = totalUsers;
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public long getTotalRestaurants() {
        return totalRestaurants;
    }

    public void setTotalRestaurants(long totalRestaurants) {
        this.totalRestaurants = totalRestaurants;
    }

    public long getTotalMenuItems() {
        return totalMenuItems;
    }

    public void setTotalMenuItems(long totalMenuItems) {
        this.totalMenuItems = totalMenuItems;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }
}
