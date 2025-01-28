-- Hubs (Kernentitäten)
CREATE TABLE Hub_Customer (
    Customer_ID VARCHAR(50) PRIMARY KEY,
    Load_Date TIMESTAMP NOT NULL,
    Record_Source VARCHAR(50) NOT NULL
);

CREATE TABLE Hub_Product (
    Product_ID VARCHAR(50) PRIMARY KEY,
    Load_Date TIMESTAMP NOT NULL,
    Record_Source VARCHAR(50) NOT NULL
);

CREATE TABLE Hub_Order (
    Order_ID VARCHAR(50) PRIMARY KEY,
    Load_Date TIMESTAMP NOT NULL,
    Record_Source VARCHAR(50) NOT NULL
);

CREATE TABLE Hub_Supplier (
    Supplier_ID VARCHAR(50) PRIMARY KEY,
    Load_Date TIMESTAMP NOT NULL,
    Record_Source VARCHAR(50) NOT NULL
);

CREATE TABLE Hub_Warehouse (
    Warehouse_ID VARCHAR(50) PRIMARY KEY,
    Load_Date TIMESTAMP NOT NULL,
    Record_Source VARCHAR(50) NOT NULL
);

CREATE TABLE Hub_Shipment (
    Shipment_ID VARCHAR(50) PRIMARY KEY,
    Load_Date TIMESTAMP NOT NULL,
    Record_Source VARCHAR(50) NOT NULL
);

-- Links (Beziehungen)
CREATE TABLE Link_Order_Customer (
    Order_ID VARCHAR(50) NOT NULL,
    Customer_ID VARCHAR(50) NOT NULL,
    Load_Date TIMESTAMP NOT NULL,
    Record_Source VARCHAR(50) NOT NULL,
    PRIMARY KEY (Order_ID, Customer_ID),
    FOREIGN KEY (Order_ID) REFERENCES Hub_Order(Order_ID),
    FOREIGN KEY (Customer_ID) REFERENCES Hub_Customer(Customer_ID)
);

CREATE TABLE Link_Order_Product (
    Order_ID VARCHAR(50) NOT NULL,
    Product_ID VARCHAR(50) NOT NULL,
    Load_Date TIMESTAMP NOT NULL,
    Record_Source VARCHAR(50) NOT NULL,
    PRIMARY KEY (Order_ID, Product_ID),
    FOREIGN KEY (Order_ID) REFERENCES Hub_Order(Order_ID),
    FOREIGN KEY (Product_ID) REFERENCES Hub_Product(Product_ID)
);

CREATE TABLE Link_Product_Supplier (
    Product_ID VARCHAR(50) NOT NULL,
    Supplier_ID VARCHAR(50) NOT NULL,
    Load_Date TIMESTAMP NOT NULL,
    Record_Source VARCHAR(50) NOT NULL,
    PRIMARY KEY (Product_ID, Supplier_ID),
    FOREIGN KEY (Product_ID) REFERENCES Hub_Product(Product_ID),
    FOREIGN KEY (Supplier_ID) REFERENCES Hub_Supplier(Supplier_ID)
);

CREATE TABLE Link_Product_Warehouse (
    Product_ID VARCHAR(50) NOT NULL,
    Warehouse_ID VARCHAR(50) NOT NULL,
    Load_Date TIMESTAMP NOT NULL,
    Record_Source VARCHAR(50) NOT NULL,
    PRIMARY KEY (Product_ID, Warehouse_ID),
    FOREIGN KEY (Product_ID) REFERENCES Hub_Product(Product_ID),
    FOREIGN KEY (Warehouse_ID) REFERENCES Hub_Warehouse(Warehouse_ID)
);

CREATE TABLE Link_Order_Shipment (
    Order_ID VARCHAR(50) NOT NULL,
    Shipment_ID VARCHAR(50) NOT NULL,
    Load_Date TIMESTAMP NOT NULL,
    Record_Source VARCHAR(50) NOT NULL,
    PRIMARY KEY (Order_ID, Shipment_ID),
    FOREIGN KEY (Order_ID) REFERENCES Hub_Order(Order_ID),
    FOREIGN KEY (Shipment_ID) REFERENCES Hub_Shipment(Shipment_ID)
);

-- Satellites (Beschreibende Daten)
CREATE TABLE Satellite_Customer (
    Customer_ID VARCHAR(50) NOT NULL,
    Load_Date TIMESTAMP NOT NULL,
    Record_Source VARCHAR(50) NOT NULL,
    Name VARCHAR(100),
    Email VARCHAR(100),
    Address VARCHAR(200),
    Phone VARCHAR(20),
    Valid_From TIMESTAMP NOT NULL,
    Valid_To TIMESTAMP,
    PRIMARY KEY (Customer_ID, Load_Date),
    FOREIGN KEY (Customer_ID) REFERENCES Hub_Customer(Customer_ID)
);

CREATE TABLE Satellite_Product (
    Product_ID VARCHAR(50) NOT NULL,
    Load_Date TIMESTAMP NOT NULL,
    Record_Source VARCHAR(50) NOT NULL,
    Product_Name VARCHAR(100),
    Description TEXT,
    Price DECIMAL(10, 2),
    Category VARCHAR(50),
    Valid_From TIMESTAMP NOT NULL,
    Valid_To TIMESTAMP,
    PRIMARY KEY (Product_ID, Load_Date),
    FOREIGN KEY (Product_ID) REFERENCES Hub_Product(Product_ID)
);

CREATE TABLE Satellite_Order (
    Order_ID VARCHAR(50) NOT NULL,
    Load_Date TIMESTAMP NOT NULL,
    Record_Source VARCHAR(50) NOT NULL,
    Order_Date TIMESTAMP,
    Total_Amount DECIMAL(10, 2),
    Status VARCHAR(50),
    Valid_From TIMESTAMP NOT NULL,
    Valid_To TIMESTAMP,
    PRIMARY KEY (Order_ID, Load_Date),
    FOREIGN KEY (Order_ID) REFERENCES Hub_Order(Order_ID)
);

CREATE TABLE Satellite_Supplier (
    Supplier_ID VARCHAR(50) NOT NULL,
    Load_Date TIMESTAMP NOT NULL,
    Record_Source VARCHAR(50) NOT NULL,
    Supplier_Name VARCHAR(100),
    Contact_Person VARCHAR(100),
    Address VARCHAR(200),
    Phone VARCHAR(20),
    Valid_From TIMESTAMP NOT NULL,
    Valid_To TIMESTAMP,
    PRIMARY KEY (Supplier_ID, Load_Date),
    FOREIGN KEY (Supplier_ID) REFERENCES Hub_Supplier(Supplier_ID)
);

CREATE TABLE Satellite_Warehouse (
    Warehouse_ID VARCHAR(50) NOT NULL,
    Load_Date TIMESTAMP NOT NULL,
    Record_Source VARCHAR(50) NOT NULL,
    Warehouse_Name VARCHAR(100),
    Location VARCHAR(200),
    Capacity INT,
    Valid_From TIMESTAMP NOT NULL,
    Valid_To TIMESTAMP,
    PRIMARY KEY (Warehouse_ID, Load_Date),
    FOREIGN KEY (Warehouse_ID) REFERENCES Hub_Warehouse(Warehouse_ID)
);

CREATE TABLE Satellite_Shipment (
    Shipment_ID VARCHAR(50) NOT NULL,
    Load_Date TIMESTAMP NOT NULL,
    Record_Source VARCHAR(50) NOT NULL,
    Shipment_Date TIMESTAMP,
    Carrier VARCHAR(100),
    Tracking_Number VARCHAR(100),
    Status VARCHAR(50),
    Valid_From TIMESTAMP NOT NULL,
    Valid_To TIMESTAMP,
    PRIMARY KEY (Shipment_ID, Load_Date),
    FOREIGN KEY (Shipment_ID) REFERENCES Hub_Shipment(Shipment_ID)
);