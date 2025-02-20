CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE product (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(15, 2) NOT NULL,
    stock_quantity INT NOT NULL
);

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE orders (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    order_date TIMESTAMP NOT NULL,
    status VARCHAR(255) NOT NULL,
    total_amount DECIMAL(15, 2) NOT NULL,
    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_item (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    quantity INT NOT NULL,
    unit_price DECIMAL(15, 2) NOT NULL,
    subtotal DECIMAL(15, 2) NOT NULL,
    order_id UUID,
    product_id UUID,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE payment (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    payment_date TIMESTAMP NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    status VARCHAR(255) NOT NULL,
    payment_method VARCHAR(255) NOT NULL,
    order_id UUID,
    FOREIGN KEY (order_id) REFERENCES orders(id)
);

CREATE TABLE shipment (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tracking_number VARCHAR(255) NOT NULL,
    carrier VARCHAR(255) NOT NULL,
    estimated_delivery_date TIMESTAMP NOT NULL,
    shipping_status VARCHAR(255) NOT NULL,
    order_id UUID,
    FOREIGN KEY (order_id) REFERENCES orders(id)
);