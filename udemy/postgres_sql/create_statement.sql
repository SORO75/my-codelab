CREATE TABLE cities (
	name VARCHAR(50), 
  country VARCHAR(50),
  population INTEGER,
  area INTEGER
);

INSERT INTO cities (name, country, population, area)
VALUES ('Tokyo', 'Japan', 38505000, 8223);

INSERT INTO cities (name, country, population, area)
VALUES 
	('Delhi', 'India', 28125000, 2240),
  ('Shanghai', 'China', 22125000, 4015),
  ('Sao Paulo', 'Brazil', 20935000, 3043);

SELECT name || country FROM cities;

SELECT name || ', ' || country FROM cities;

SELECT name || ', ' || country AS location FROM cities;

SELECT CONCAT(name, country) AS location FROM cities;

SELECT CONCAT(name, ', ', country) AS location FROM cities;

SELECT
  CONCAT(UPPER(name), ', ', UPPER(country)) AS location
FROM
  cities;

SELECT
  UPPER(CONCAT(name, ', ', country)) AS location
FROM
  cities;





  SELECT name, area FROM cities WHERE area > 4000;

SELECT name, price FROM phones
WHERE units_sold > 5000

SELECT name, manufacturer FROM phones
WHERE manufacturer IN ('Apple','Samsung')

SELECT name,(price*units_sold) AS total_revenue FROM phones
WHERE (price*units_sold) > 1000000

-- Write query here to update the 'units_sold' of the phone with name 'N8' to 8543
UPDATE phones
SET units_sold = 8543
WHERE name = 'N8';
-- Write query here to select all rows and columns of the 'phones' table
SELECT * FROM phones;

-- Write your delete SQL here
DELETE FROM phones
WHERE manufacturer IN ('Samsung');

-- Write query here to select all rows and columns from phones
