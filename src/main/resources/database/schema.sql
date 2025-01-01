CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Users table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(15),
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Products table
CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL, -- Name of the product
    description TEXT, -- Detailed description of the product
    price DECIMAL(10, 2) NOT NULL, -- Price of the product
    stock INT NOT NULL, -- Quantity available in stock
    category_id INT DEFAULT NULL, -- Category ID (linked to categories table)
    brand VARCHAR(50), -- Brand of the product
    sku VARCHAR(50) UNIQUE, -- Stock Keeping Unit for product identification
    weight DECIMAL(10, 2) DEFAULT NULL, -- Weight of the product (e.g., in kg)
    dimensions VARCHAR(50) DEFAULT NULL, -- Dimensions (e.g., "10x5x2 cm")
    color VARCHAR(30) DEFAULT NULL, -- Product color
    size VARCHAR(30) DEFAULT NULL, -- Product size (e.g., S, M, L, XL)
    material VARCHAR(50) DEFAULT NULL, -- Material used (e.g., cotton, plastic)
    image_url VARCHAR(255) DEFAULT NULL, -- URL for the product image
    is_active BOOLEAN DEFAULT TRUE, -- Whether the product is active and visible
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Timestamp when the product was created
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Last updated timestamp
    FOREIGN KEY (category_id) REFERENCES categories(category_id) -- Linking to categories table
);

-- Orders table
CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('Pending', 'Processing', 'Shipped', 'Delivered', 'Cancelled') DEFAULT 'Pending',
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Order Details table
CREATE TABLE order_details (
    order_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- Categories table (optional, if you want structured product categories)
CREATE TABLE categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT
);

-- Linking Products to Categories (optional)
ALTER TABLE products ADD category_id INT;
ALTER TABLE products ADD FOREIGN KEY (category_id) REFERENCES categories(category_id);

CREATE TABLE discount_codes (
    discount_id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    discount_type ENUM('Percentage', 'Fixed') NOT NULL,
    discount_value DECIMAL(10, 2) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    usage_limit INT DEFAULT NULL, -- NULL means unlimited usage
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Linking Discount Codes to Orders (optional)
ALTER TABLE orders ADD discount_id INT DEFAULT NULL;
ALTER TABLE orders ADD FOREIGN KEY (discount_id) REFERENCES discount_codes(discount_id);

CREATE TABLE payment_methods (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    payment_type ENUM('Credit Card', 'Debit Card', 'PayPal', 'Bank Transfer', 'Cash on Delivery') NOT NULL,
    provider VARCHAR(50), -- e.g., Visa, MasterCard, PayPal
    account_number VARCHAR(50), -- Optional, for non-cash methods
    expiry_date DATE, -- Optional, for cards
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Linking Orders to Payment Methods
ALTER TABLE orders ADD payment_id INT DEFAULT NULL;
ALTER TABLE orders ADD FOREIGN KEY (payment_id) REFERENCES payment_methods(payment_id);

CREATE TABLE reviews (
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    user_id INT NOT NULL,
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5), -- Rating scale: 1 to 5
    review_text TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(product_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
