-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS STORE;
USE STORE;

-- Create the Buyers table
CREATE TABLE IF NOT EXISTS Buyers (
                                      id INT PRIMARY KEY AUTO_INCREMENT,
                                      name VARCHAR(255) NOT NULL
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Create the Categories table
CREATE TABLE IF NOT EXISTS Categories (
                                          id INT PRIMARY KEY AUTO_INCREMENT,
                                          name VARCHAR(255) NOT NULL
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Create the Products table
CREATE TABLE IF NOT EXISTS Products (
                                        id INT PRIMARY KEY AUTO_INCREMENT,
                                        name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Create the Products_Categories table for the many-to-many relationship
CREATE TABLE IF NOT EXISTS Products_Categories (
                                                   product_id INT,
                                                   category_id INT,
                                                   PRIMARY KEY (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES Products(id),
    FOREIGN KEY (category_id) REFERENCES Categories(id)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Create the Carts table
CREATE TABLE IF NOT EXISTS Carts (
                                     id INT PRIMARY KEY AUTO_INCREMENT,
                                     buyer_id INT,
                                     status VARCHAR(50) NOT NULL,
    FOREIGN KEY (buyer_id) REFERENCES Buyers(id)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Create the Cartitems table
CREATE TABLE IF NOT EXISTS Cartitems (
                                         id INT PRIMARY KEY AUTO_INCREMENT,
                                         cart_id INT,
                                         product_id INT,
                                         quantity INT NOT NULL,
                                         unit_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES Carts(id),
    FOREIGN KEY (product_id) REFERENCES Products(id)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Create the Invoices table
CREATE TABLE IF NOT EXISTS Invoices (
                                        id INT PRIMARY KEY AUTO_INCREMENT,
                                        buyer_id INT,
                                        date DATE NOT NULL,
                                        FOREIGN KEY (buyer_id) REFERENCES Buyers(id)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Create the Invoiceitems table
CREATE TABLE IF NOT EXISTS Invoiceitems (
                                            id INT PRIMARY KEY AUTO_INCREMENT,
                                            invoice_id INT,
                                            product_id INT,
                                            quantity INT NOT NULL,
                                            unit_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (invoice_id) REFERENCES Invoices(id),
    FOREIGN KEY (product_id) REFERENCES Products(id)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
