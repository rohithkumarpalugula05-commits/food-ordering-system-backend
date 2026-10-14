-- ========================================================
-- Food Ordering System - Database Schema (MySQL)
-- College Full-Stack Java Project
-- ========================================================

CREATE DATABASE IF NOT EXISTS food_ordering_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE food_ordering_db;

-- 1. Users table (Customers and Admins)
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    address TEXT,
    role VARCHAR(20) NOT NULL DEFAULT 'ROLE_CUSTOMER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Restaurants table
CREATE TABLE IF NOT EXISTS restaurants (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    cuisine_type VARCHAR(100),
    address VARCHAR(255),
    phone VARCHAR(20),
    rating DECIMAL(2,1) DEFAULT 4.5,
    delivery_time_minutes INT DEFAULT 30,
    image_url VARCHAR(500),
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Menu Items table
CREATE TABLE IF NOT EXISTS menu_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    restaurant_id BIGINT NOT NULL,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    category VARCHAR(50) NOT NULL,
    image_url VARCHAR(500),
    is_vegetarian BOOLEAN DEFAULT TRUE,
    available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);

-- 4. Orders table
CREATE TABLE IF NOT EXISTS orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_tracking_number VARCHAR(64) NOT NULL UNIQUE,
    user_id BIGINT NOT NULL,
    restaurant_id BIGINT NOT NULL,
    order_status VARCHAR(30) NOT NULL DEFAULT 'PLACED',
    total_amount DECIMAL(10,2) NOT NULL,
    delivery_address TEXT NOT NULL,
    customer_phone VARCHAR(20) NOT NULL,
    customer_name VARCHAR(100) NOT NULL,
    payment_method VARCHAR(30) DEFAULT 'CASH_ON_DELIVERY',
    special_instructions TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE RESTRICT,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE RESTRICT
);

-- 5. Order Items table
CREATE TABLE IF NOT EXISTS order_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    menu_item_id BIGINT,
    item_name VARCHAR(150) NOT NULL,
    item_price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (menu_item_id) REFERENCES menu_items(id) ON DELETE SET NULL
);
