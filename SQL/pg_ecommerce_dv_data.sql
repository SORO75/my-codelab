-- Funktion zur Generierung von zufälligen Strings
CREATE OR REPLACE FUNCTION random_string(length INT) RETURNS TEXT AS $$
DECLARE
    chars TEXT := 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    result TEXT := '';
BEGIN
    FOR i IN 1..length LOOP
        result := result || substr(chars, floor(random() * length(chars)) + 1, 1);
    END LOOP;
    RETURN result;
END;
$$ LANGUAGE plpgsql;

-- Funktion zur Generierung von zufälligen Datumsangaben
CREATE OR REPLACE FUNCTION random_date() RETURNS TIMESTAMP AS $$
BEGIN
    RETURN NOW() - (random() * INTERVAL '365 days');
END;
$$ LANGUAGE plpgsql;

-- Funktion zur Generierung von zufälligen Preisen
CREATE OR REPLACE FUNCTION random_price() RETURNS DECIMAL(10, 2) AS $$
BEGIN
    RETURN (random() * 1000)::DECIMAL(10, 2);
END;
$$ LANGUAGE plpgsql;

-- Testdaten für Hub_Customer
DO $$
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO Hub_Customer (Customer_ID, Load_Date, Record_Source)
        VALUES ('CUST' || LPAD(i::TEXT, 4, '0'), NOW(), 'ERP System');
    END LOOP;
END $$;

-- Testdaten für Satellite_Customer
DO $$
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO Satellite_Customer (Customer_ID, Load_Date, Record_Source, Name, Email, Address, Phone, Valid_From, Valid_To)
        VALUES (
            'CUST' || LPAD(i::TEXT, 4, '0'),
            NOW(),
            'ERP System',
            'Customer ' || i,
            'customer' || i || '@example.com',
            'Address ' || i,
            '123-456-' || LPAD(i::TEXT, 4, '0'),
            NOW(),
            NULL
        );
    END LOOP;
END $$;

-- Testdaten für Hub_Product
DO $$
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO Hub_Product (Product_ID, Load_Date, Record_Source)
        VALUES ('PROD' || LPAD(i::TEXT, 4, '0'), NOW(), 'ERP System');
    END LOOP;
END $$;

-- Testdaten für Satellite_Product
DO $$
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO Satellite_Product (Product_ID, Load_Date, Record_Source, Product_Name, Description, Price, Category, Valid_From, Valid_To)
        VALUES (
            'PROD' || LPAD(i::TEXT, 4, '0'),
            NOW(),
            'ERP System',
            'Product ' || i,
            'Description for Product ' || i,
            random_price(),
            CASE WHEN i % 3 = 0 THEN 'Electronics' WHEN i % 3 = 1 THEN 'Clothing' ELSE 'Home' END,
            NOW(),
            NULL
        );
    END LOOP;
END $$;

-- Testdaten für Hub_Order
DO $$
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO Hub_Order (Order_ID, Load_Date, Record_Source)
        VALUES ('ORDER' || LPAD(i::TEXT, 4, '0'), NOW(), 'ERP System');
    END LOOP;
END $$;

-- Testdaten für Satellite_Order
DO $$
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO Satellite_Order (Order_ID, Load_Date, Record_Source, Order_Date, Total_Amount, Status, Valid_From, Valid_To)
        VALUES (
            'ORDER' || LPAD(i::TEXT, 4, '0'),
            NOW(),
            'ERP System',
            random_date(),
            random_price(),
            CASE WHEN i % 3 = 0 THEN 'Shipped' WHEN i % 3 = 1 THEN 'Pending' ELSE 'Delivered' END,
            NOW(),
            NULL
        );
    END LOOP;
END $$;

-- Testdaten für Hub_Supplier
DO $$
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO Hub_Supplier (Supplier_ID, Load_Date, Record_Source)
        VALUES ('SUPP' || LPAD(i::TEXT, 4, '0'), NOW(), 'ERP System');
    END LOOP;
END $$;

-- Testdaten für Satellite_Supplier
DO $$
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO Satellite_Supplier (Supplier_ID, Load_Date, Record_Source, Supplier_Name, Contact_Person, Address, Phone, Valid_From, Valid_To)
        VALUES (
            'SUPP' || LPAD(i::TEXT, 4, '0'),
            NOW(),
            'ERP System',
            'Supplier ' || i,
            'Contact Person ' || i,
            'Address ' || i,
            '987-654-' || LPAD(i::TEXT, 4, '0'),
            NOW(),
            NULL
        );
    END LOOP;
END $$;

-- Testdaten für Hub_Warehouse
DO $$
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO Hub_Warehouse (Warehouse_ID, Load_Date, Record_Source)
        VALUES ('WH' || LPAD(i::TEXT, 4, '0'), NOW(), 'ERP System');
    END LOOP;
END $$;

-- Testdaten für Satellite_Warehouse
DO $$
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO Satellite_Warehouse (Warehouse_ID, Load_Date, Record_Source, Warehouse_Name, Location, Capacity, Valid_From, Valid_To)
        VALUES (
            'WH' || LPAD(i::TEXT, 4, '0'),
            NOW(),
            'ERP System',
            'Warehouse ' || i,
            'Location ' || i,
            (random() * 10000)::INT,
            NOW(),
            NULL
        );
    END LOOP;
END $$;

-- Testdaten für Hub_Shipment
DO $$
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO Hub_Shipment (Shipment_ID, Load_Date, Record_Source)
        VALUES ('SHIP' || LPAD(i::TEXT, 4, '0'), NOW(), 'Logistiksystem');
    END LOOP;
END $$;

-- Testdaten für Satellite_Shipment
DO $$
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO Satellite_Shipment (Shipment_ID, Load_Date, Record_Source, Shipment_Date, Carrier, Tracking_Number, Status, Valid_From, Valid_To)
        VALUES (
            'SHIP' || LPAD(i::TEXT, 4, '0'),
            NOW(),
            'Logistiksystem',
            random_date(),
            CASE WHEN i % 3 = 0 THEN 'DHL' WHEN i % 3 = 1 THEN 'UPS' ELSE 'FedEx' END,
            'TRACK' || LPAD(i::TEXT, 4, '0'),
            CASE WHEN i % 3 = 0 THEN 'In Transit' WHEN i % 3 = 1 THEN 'Delivered' ELSE 'Pending' END,
            NOW(),
            NULL
        );
    END LOOP;
END $$;

-- Testdaten für Link_Order_Customer
DO $$
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO Link_Order_Customer (Order_ID, Customer_ID, Load_Date, Record_Source)
        VALUES (
            'ORDER' || LPAD(i::TEXT, 4, '0'),
            'CUST' || LPAD(i::TEXT, 4, '0'),
            NOW(),
            'ERP System'
        );
    END LOOP;
END $$;

-- Testdaten für Link_Order_Product
DO $$
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO Link_Order_Product (Order_ID, Product_ID, Load_Date, Record_Source)
        VALUES (
            'ORDER' || LPAD(i::TEXT, 4, '0'),
            'PROD' || LPAD(i::TEXT, 4, '0'),
            NOW(),
            'ERP System'
        );
    END LOOP;
END $$;

-- Testdaten für Link_Product_Supplier
DO $$
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO Link_Product_Supplier (Product_ID, Supplier_ID, Load_Date, Record_Source)
        VALUES (
            'PROD' || LPAD(i::TEXT, 4, '0'),
            'SUPP' || LPAD(i::TEXT, 4, '0'),
            NOW(),
            'ERP System'
        );
    END LOOP;
END $$;

-- Testdaten für Link_Product_Warehouse
DO $$
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO Link_Product_Warehouse (Product_ID, Warehouse_ID, Load_Date, Record_Source)
        VALUES (
            'PROD' || LPAD(i::TEXT, 4, '0'),
            'WH' || LPAD(i::TEXT, 4, '0'),
            NOW(),
            'ERP System'
        );
    END LOOP;
END $$;

-- Testdaten für Link_Order_Shipment
DO $$
BEGIN
    FOR i IN 1..1000 LOOP
        INSERT INTO Link_Order_Shipment (Order_ID, Shipment_ID, Load_Date, Record_Source)
        VALUES (
            'ORDER' || LPAD(i::TEXT, 4, '0'),
            'SHIP' || LPAD(i::TEXT, 4, '0'),
            NOW(),
            'Logistiksystem'
        );
    END LOOP;
END $$;