select table_name from user_tables ORDER BY table_name;


select count(*) from ot.contacts;

select name from ot.customers;


SELECT
    customer_id,
    name,
    address,
    website,
    credit_limit
FROM
    ot.customers;
    
    
    SELECT
    name,
    address,
    website,
    credit_limit
FROM
    ot.customers;
    
    
    
    
    SELECT
    name,
    credit_limit
FROM
    ot.customers
ORDER BY
    2 DESC,
    1;
    
    
    
    SELECT
    country_id,
    city,
    state
FROM
    ot.locations
ORDER BY
    state ASC NULLS FIRST;