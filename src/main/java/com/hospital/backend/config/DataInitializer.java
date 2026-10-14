package com.hospital.backend.config;

import com.hospital.backend.entity.*;
import com.hospital.backend.repository.*;
import com.hospital.backend.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * DataInitializer - Seeds default demo accounts, restaurants, dishes, and orders on application startup.
 * Ensures the application is immediately testable right out of the box.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserService userService;

    public DataInitializer(UserRepository userRepository,
                           RestaurantRepository restaurantRepository,
                           MenuItemRepository menuItemRepository,
                           OrderRepository orderRepository,
                           OrderItemRepository orderItemRepository,
                           UserService userService) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        // 1. Seed Users if not present
        if (userRepository.count() == 0) {
            String adminPasswordHash = userService.hashPassword("admin123");
            User admin = new User(
                    "System Administrator",
                    "admin@foodorder.com",
                    adminPasswordHash,
                    "+91 9999988888",
                    "Admin Office, Tech Park, Bengaluru",
                    "ROLE_ADMIN"
            );
            userRepository.save(admin);

            String customerPasswordHash = userService.hashPassword("customer123");
            User customer = new User(
                    "Rahul Sharma",
                    "customer@foodorder.com",
                    customerPasswordHash,
                    "+91 9876543210",
                    "Flat 402, Green Valley Apartments, MG Road, Bengaluru",
                    "ROLE_CUSTOMER"
            );
            userRepository.save(customer);

            System.out.println(">>> Demo users created: admin@foodorder.com / admin123, customer@foodorder.com / customer123");
        }

        // 2. Seed Restaurants and Menu Items if not present
        if (restaurantRepository.count() == 0) {
            Restaurant r1 = new Restaurant(
                    "Royal Biryani Palace",
                    "Authentic Nizami & Awadhi slow-cooked biryanis, kebabs, and rich gravies cooked with traditional aromatic spices.",
                    "Biryani, North Indian, Mughlai",
                    "12 Brigade Road, Bengaluru",
                    "+91 80 4112 5501",
                    4.8,
                    35,
                    "https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=800&q=80",
                    true
            );
            Restaurant savedR1 = restaurantRepository.save(r1);

            Restaurant r2 = new Restaurant(
                    "Bella Napoli Pizzeria",
                    "Wood-fired artisanal pizzas made with San Marzano tomatoes, fresh buffalo mozzarella, and handmade pastas.",
                    "Italian, Pizza, Pasta",
                    "88 Indiranagar 100ft Road, Bengaluru",
                    "+91 80 4220 9922",
                    4.7,
                    30,
                    "https://images.unsplash.com/photo-1513104890138-7c749659a591?w=800&q=80",
                    true
            );
            Restaurant savedR2 = restaurantRepository.save(r2);

            Restaurant r3 = new Restaurant(
                    "Burger Bistro & Grills",
                    "Juicy gourmet smash burgers, golden crispy seasoned fries, buffalo wings, and thick artisan shakes.",
                    "American, Fast Food, Burgers",
                    "45 Koramangala 5th Block, Bengaluru",
                    "+91 80 4333 1188",
                    4.6,
                    25,
                    "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=800&q=80",
                    true
            );
            Restaurant savedR3 = restaurantRepository.save(r3);

            // Dishes for Royal Biryani Palace
            MenuItem m1 = menuItemRepository.save(new MenuItem(savedR1, "Hyderabadi Chicken Dum Biryani", "Fragrant long-grain basmati rice layered with tender marinated chicken and slow-cooked in dum seal. Served with salan and raita.", 320.0, "Biryani", "https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=800&q=80", false, true));
            MenuItem m2 = menuItemRepository.save(new MenuItem(savedR1, "Mutton Galouti Kebab", "Melt-in-mouth minced mutton patties infused with royal Lucknowi spices. Served with mint chutney.", 380.0, "Starters", "https://images.unsplash.com/photo-1599488615731-7e5c2823ff28?w=800&q=80", false, true));
            MenuItem m3 = menuItemRepository.save(new MenuItem(savedR1, "Paneer Tikka Masala", "Charcoal-grilled cottage cheese cubes simmered in a luscious onion-tomato cream gravy.", 280.0, "Curries", "https://images.unsplash.com/photo-1631452180519-c014fe946bc7?w=800&q=80", true, true));
            MenuItem m4 = menuItemRepository.save(new MenuItem(savedR1, "Butter Garlic Naan", "Clay-oven baked leavened bread brushed generously with garlic butter and fresh cilantro.", 60.0, "Breads", "https://images.unsplash.com/photo-1601050690597-df0568f70950?w=800&q=80", true, true));
            menuItemRepository.save(new MenuItem(savedR1, "Royal Shahi Tukda", "Crispy saffron-soaked bread croutons crowned with thick cardamom rabdi and roasted pistachios.", 150.0, "Dessert", "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=800&q=80", true, true));

            // Dishes for Bella Napoli Pizzeria
            MenuItem m6 = menuItemRepository.save(new MenuItem(savedR2, "Classic Margherita Pizza", "Hand-stretched dough topped with San Marzano sauce, fresh mozzarella di bufala, basil, and extra virgin olive oil.", 399.0, "Pizza", "https://images.unsplash.com/photo-1574071318508-1cdbab80d002?w=800&q=80", true, true));
            menuItemRepository.save(new MenuItem(savedR2, "Pepperoni Feast Pizza", "Crispy pepperoni slices over melted mozzarella and savory herb-infused tomato sauce.", 489.0, "Pizza", "https://images.unsplash.com/photo-1628840042765-356cda07504e?w=800&q=80", false, true));
            menuItemRepository.save(new MenuItem(savedR2, "Creamy Truffle Fettuccine", "Al dente fettuccine pasta in rich parmesan cream sauce with wild button mushrooms and white truffle oil.", 360.0, "Pasta", "https://images.unsplash.com/photo-1645112411341-6c4fd023714a?w=800&q=80", true, true));
            MenuItem m9 = menuItemRepository.save(new MenuItem(savedR2, "Cheesy Garlic Bread", "Warm baguette slices topped with roasted garlic confit, parsley butter, and melted mozzarella.", 180.0, "Starters", "https://images.unsplash.com/photo-1619895092538-128341789043?w=800&q=80", true, true));
            menuItemRepository.save(new MenuItem(savedR2, "Traditional Italian Tiramisu", "Espresso-soaked ladyfingers layered with velvety mascarpone cheese and dusted with Dutch cocoa.", 220.0, "Dessert", "https://images.unsplash.com/photo-1571877227200-a0d98ea607e9?w=800&q=80", true, true));

            // Dishes for Burger Bistro & Grills
            menuItemRepository.save(new MenuItem(savedR3, "Crispy Chicken Deluxe Burger", "Golden spiced fried chicken breast topped with melted cheddar, crisp lettuce, dill pickles, and house secret sauce.", 260.0, "Burgers", "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=800&q=80", false, true));
            menuItemRepository.save(new MenuItem(savedR3, "Double BBQ Smash Burger", "Two smashed beef-style patties, double American cheese, caramelized onions, and smoky hickory barbecue sauce.", 320.0, "Burgers", "https://images.unsplash.com/photo-1586190848861-99aa4a171e90?w=800&q=80", false, true));
            menuItemRepository.save(new MenuItem(savedR3, "Peri-Peri Loaded Cheese Fries", "Crisp crinkle-cut fries tossed in peri-peri dust, smothered in hot cheese sauce and jalapeños.", 160.0, "Sides", "https://images.unsplash.com/photo-1585109649139-366815a0d713?w=800&q=80", true, true));
            menuItemRepository.save(new MenuItem(savedR3, "Oreo Overload Thickshake", "Decadent chilled vanilla shake blended with crunchy Oreo cookies and crowned with whipped cream.", 180.0, "Beverages", "https://images.unsplash.com/photo-1572490122747-3968b75cc699?w=800&q=80", true, true));

            // 3. Seed Sample Orders for customer
            User customer = userRepository.findByEmail("customer@foodorder.com").orElse(null);
            if (customer != null && orderRepository.count() == 0) {
                // Order 1: Delivered
                Order o1 = new Order(
                        "ORD-20261013-1001",
                        customer,
                        savedR1,
                        "DELIVERED",
                        660.0,
                        customer.getAddress(),
                        customer.getPhone(),
                        customer.getFullName(),
                        "UPI",
                        "Please provide extra raita and onion rings."
                );
                Order savedO1 = orderRepository.save(o1);
                orderItemRepository.save(new OrderItem(savedO1, m1, m1.getName(), m1.getPrice(), 1, 320.0));
                orderItemRepository.save(new OrderItem(savedO1, m3, m3.getName(), m3.getPrice(), 1, 280.0));
                orderItemRepository.save(new OrderItem(savedO1, m4, m4.getName(), m4.getPrice(), 1, 60.0));

                // Order 2: Preparing
                Order o2 = new Order(
                        "ORD-20261013-1002",
                        customer,
                        savedR2,
                        "PREPARING",
                        579.0,
                        customer.getAddress(),
                        customer.getPhone(),
                        customer.getFullName(),
                        "CASH_ON_DELIVERY",
                        "Ring the doorbell twice please."
                );
                Order savedO2 = orderRepository.save(o2);
                orderItemRepository.save(new OrderItem(savedO2, m6, m6.getName(), m6.getPrice(), 1, 399.0));
                orderItemRepository.save(new OrderItem(savedO2, m9, m9.getName(), m9.getPrice(), 1, 180.0));

                System.out.println(">>> Seed orders created for customer@foodorder.com");
            }

            System.out.println(">>> Sample restaurants, dishes, and orders initialized successfully!");
        }
    }
}
