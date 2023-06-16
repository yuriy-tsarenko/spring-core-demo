-- Create the customers table
CREATE TABLE customers (
    customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(50),
    contact_name VARCHAR(50),
    country VARCHAR(50)
);

-- Create the suppliers table
CREATE TABLE suppliers (
    supplier_id INT PRIMARY KEY,
    supplier_name VARCHAR(50),
    contact_name VARCHAR(50),
    address VARCHAR(100),
    city VARCHAR(50),
    region VARCHAR(50),
    postal_code VARCHAR(20),
    country VARCHAR(50),
    phone VARCHAR(20),
    email VARCHAR(100)
);

-- Create the categories table
CREATE TABLE categories (
    category_id INT PRIMARY KEY,
    category_name VARCHAR(50),
    description VARCHAR(100)
);

-- Create the products table
CREATE TABLE products (
    product_id INT PRIMARY KEY,
    product_name VARCHAR(50),
    supplier_id INT,
    unit_price DECIMAL(10,2),
    FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)
);

-- Create the orders table
CREATE TABLE orders (
    order_id INT PRIMARY KEY,
    customer_id BIGINT,
    order_date DATE,
    total_amount DECIMAL(10,2),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- Create the employees table
CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    last_name VARCHAR(50),
    first_name VARCHAR(50),
    birth_date DATE,
    hire_date DATE,
    reports_to INT,
    address VARCHAR(100),
    city VARCHAR(50),
    region VARCHAR(50),
    postal_code VARCHAR(20),
    country VARCHAR(50),
    phone VARCHAR(20),
    email VARCHAR(100),
    salary DECIMAL(10,2),
    FOREIGN KEY (reports_to) REFERENCES employees(employee_id)
);

-- Create the shippers table
CREATE TABLE shippers (
    shipper_id INT PRIMARY KEY,
    shipper_name VARCHAR(50),
    phone VARCHAR(20)
);

-- Create the regions table
CREATE TABLE regions (
    region_id INT PRIMARY KEY,
    region_description VARCHAR(50)
);

-- Create the territories table
CREATE TABLE territories (
    territory_id VARCHAR(20) PRIMARY KEY,
    territory_description VARCHAR(50),
    region_id INT,
    FOREIGN KEY (region_id) REFERENCES regions(region_id)
);

ALTER TABLE customers ADD COLUMN customer_last_name VARCHAR(50);

-- Create the customers table
CREATE TABLE user_account (
    id INT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(255)
);

ALTER TABLE user_account
    DROP COLUMN id;

ALTER TABLE customers
    RENAME COLUMN customer_id TO id;

ALTER TABLE user_account
    ADD COLUMN customer_id BIGINT;

ALTER TABLE user_account
    ADD PRIMARY KEY (customer_id);

ALTER TABLE user_account
    ADD FOREIGN KEY(customer_id) REFERENCES customers(id);

ALTER TABLE products
    RENAME COLUMN product_id TO id;

ALTER TABLE products
    ADD COLUMN customer_id BIGINT;

ALTER TABLE products
    ADD FOREIGN KEY(customer_id) REFERENCES customers(id);

ALTER TABLE categories
    RENAME COLUMN category_id TO id;

ALTER TABLE products
  MODIFY COLUMN id BIGINT;

ALTER TABLE categories
  MODIFY COLUMN id BIGINT;

CREATE TABLE product_categories (
    product_id BIGINT,
    category_id BIGINT,
    PRIMARY KEY(product_id, category_id),
    FOREIGN KEY(product_id) REFERENCES products(id),
    FOREIGN KEY(category_id) REFERENCES categories(id)
);