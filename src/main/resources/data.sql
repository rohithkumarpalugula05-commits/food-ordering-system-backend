-- ========================================================
-- Food Ordering System - Sample Seed Data
-- ========================================================

USE food_ordering_db;

-- 1. Insert Default Admin and Customer (passwords are SHA-256 or matched in application)
-- Password for admin@foodorder.com is 'admin123'
-- Password for customer@foodorder.com is 'customer123'
INSERT INTO users (id, full_name, email, password, phone, address, role)
VALUES
(1, 'System Administrator', 'admin@foodorder.com', 'admin123', '+91 9999988888', 'Admin Office, Tech Park, Bengaluru', 'ROLE_ADMIN'),
(2, 'Rahul Sharma', 'customer@foodorder.com', 'customer123', '+91 9876543210', 'Flat 402, Green Valley Apartments, MG Road, Bengaluru', 'ROLE_CUSTOMER')
ON DUPLICATE KEY UPDATE full_name=VALUES(full_name);

-- 2. Insert Sample Restaurants
INSERT INTO restaurants (id, name, description, cuisine_type, address, phone, rating, delivery_time_minutes, image_url, active)
VALUES
(1, 'Royal Biryani Palace', 'Authentic Nizami & Awadhi slow-cooked biryanis, kebabs, and rich gravies cooked with traditional aromatic spices.', 'Biryani, North Indian, Mughlai', '12 Brigade Road, Bengaluru', '+91 80 4112 5501', 4.8, 35, 'https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=800&q=80', TRUE),
(2, 'Bella Napoli Pizzeria', 'Wood-fired artisanal pizzas made with San Marzano tomatoes, fresh buffalo mozzarella, and handmade pastas.', 'Italian, Pizza, Pasta', '88 Indiranagar 100ft Road, Bengaluru', '+91 80 4220 9922', 4.7, 30, 'https://images.unsplash.com/photo-1513104890138-7c749659a591?w=800&q=80', TRUE),
(3, 'Burger Bistro & Grills', 'Juicy gourmet smash burgers, golden crispy seasoned fries, buffalo wings, and thick artisan shakes.', 'American, Fast Food, Burgers', '45 Koramangala 5th Block, Bengaluru', '+91 80 4333 1188', 4.6, 25, 'https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=800&q=80', TRUE)
ON DUPLICATE KEY UPDATE name=VALUES(name);

-- 3. Insert Menu Items
INSERT INTO menu_items (id, restaurant_id, name, description, price, category, image_url, is_vegetarian, available)
VALUES
-- Restaurant 1: Royal Biryani Palace
(1, 1, 'Hyderabadi Chicken Dum Biryani', 'Fragrant long-grain basmati rice layered with tender marinated chicken and slow-cooked in dum seal. Served with mirchi ka salan and raita.', 320.00, 'Biryani', 'https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=800&q=80', FALSE, TRUE),
(2, 1, 'Mutton Galouti Kebab', 'Melt-in-mouth minced mutton patties infused with royal Lucknowi spices. Served with mint chutney.', 380.00, 'Starters', 'https://images.unsplash.com/photo-1599488615731-7e5c2823ff28?w=800&q=80', FALSE, TRUE),
(3, 1, 'Paneer Tikka Masala', 'Charcoal-grilled cottage cheese cubes simmered in a luscious onion-tomato cream gravy.', 280.00, 'Curries', 'https://images.unsplash.com/photo-1631452180519-c014fe946bc7?w=800&q=80', TRUE, TRUE),
(4, 1, 'Butter Garlic Naan', 'Clay-oven baked leavened bread brushed generously with garlic butter and fresh cilantro.', 60.00, 'Breads', 'https://images.unsplash.com/photo-1601050690597-df0568f70950?w=800&q=80', TRUE, TRUE),
(5, 1, 'Royal Shahi Tukda', 'Crispy saffron-soaked bread croutons crowned with thick cardamom rabdi and roasted pistachios.', 150.00, 'Dessert', 'https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=800&q=80', TRUE, TRUE),

-- Restaurant 2: Bella Napoli Pizzeria
(6, 2, 'Classic Margherita Pizza', 'Hand-stretched dough topped with San Marzano sauce, fresh mozzarella di bufala, basil, and extra virgin olive oil.', 399.00, 'Pizza', 'https://images.unsplash.com/photo-1574071318508-1cdbab80d002?w=800&q=80', TRUE, TRUE),
(7, 2, 'Pepperoni Feast Pizza', 'Crispy pepperoni slices over melted mozzarella and savory herb-infused tomato sauce.', 489.00, 'Pizza', 'https://images.unsplash.com/photo-1628840042765-356cda07504e?w=800&q=80', FALSE, TRUE),
(8, 2, 'Creamy Truffle Fettuccine', 'Al dente fettuccine pasta in rich parmesan cream sauce with wild button mushrooms and white truffle oil.', 360.00, 'Pasta', 'https://images.unsplash.com/photo-1645112411341-6c4fd023714a?w=800&q=80', TRUE, TRUE),
(9, 2, 'Cheesy Garlic Bread', 'Warm baguette slices topped with roasted garlic confit, parsley butter, and melted mozzarella.', 180.00, 'Starters', 'https://images.unsplash.com/photo-1619895092538-128341789043?w=800&q=80', TRUE, TRUE),
(10, 2, 'Traditional Italian Tiramisu', 'Espresso-soaked ladyfingers layered with velvety mascarpone cheese and dusted with Dutch cocoa.', 220.00, 'Dessert', 'https://images.unsplash.com/photo-1571877227200-a0d98ea607e9?w=800&q=80', TRUE, TRUE),

-- Restaurant 3: Burger Bistro & Grills
(11, 3, 'Crispy Chicken Deluxe Burger', 'Golden spiced fried chicken breast topped with melted cheddar, crisp lettuce, dill pickles, and house secret sauce.', 260.00, 'Burgers', 'https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=800&q=80', FALSE, TRUE),
(12, 3, 'Double BBQ Smash Burger', 'Two smashed beef-style patties, double American cheese, caramelized onions, and smoky hickory barbecue sauce.', 320.00, 'Burgers', 'https://images.unsplash.com/photo-1586190848861-99aa4a171e90?w=800&q=80', FALSE, TRUE),
(13, 3, 'Peri-Peri Loaded Cheese Fries', 'Crisp crinkle-cut fries tossed in peri-peri dust, smothered in hot cheese sauce and jalapeños.', 160.00, 'Sides', 'https://images.unsplash.com/photo-1585109649139-366815a0d713?w=800&q=80', TRUE, TRUE),
(14, 3, 'Oreo Overload Thickshake', 'Decadent chilled vanilla shake blended with crunchy Oreo cookies and crowned with whipped cream.', 180.00, 'Beverages', 'https://images.unsplash.com/photo-1572490122747-3968b75cc699?w=800&q=80', TRUE, TRUE)
ON DUPLICATE KEY UPDATE name=VALUES(name);

-- 4. Insert Sample Orders
INSERT INTO orders (id, order_tracking_number, user_id, restaurant_id, order_status, total_amount, delivery_address, customer_phone, customer_name, payment_method, special_instructions)
VALUES
(1, 'ORD-20261013-1001', 2, 1, 'DELIVERED', 660.00, 'Flat 402, Green Valley Apartments, MG Road, Bengaluru', '+91 9876543210', 'Rahul Sharma', 'UPI', 'Please provide extra raita and onion rings.'),
(2, 'ORD-20261013-1002', 2, 2, 'PREPARING', 579.00, 'Flat 402, Green Valley Apartments, MG Road, Bengaluru', '+91 9876543210', 'Rahul Sharma', 'CASH_ON_DELIVERY', 'Ring the doorbell twice please.')
ON DUPLICATE KEY UPDATE order_tracking_number=VALUES(order_tracking_number);

-- 5. Insert Sample Order Items
INSERT INTO order_items (id, order_id, menu_item_id, item_name, item_price, quantity, subtotal)
VALUES
(1, 1, 1, 'Hyderabadi Chicken Dum Biryani', 320.00, 1, 320.00),
(2, 1, 3, 'Paneer Tikka Masala', 280.00, 1, 280.00),
(3, 1, 4, 'Butter Garlic Naan', 60.00, 1, 60.00),
(4, 2, 6, 'Classic Margherita Pizza', 399.00, 1, 399.00),
(5, 2, 9, 'Cheesy Garlic Bread', 180.00, 1, 180.00)
ON DUPLICATE KEY UPDATE item_name=VALUES(item_name);
