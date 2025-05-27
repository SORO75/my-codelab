select*
from products
order by price desc;


(
select * from products
order by price desc
limit 4
)
intersect
(
select*
from products
order by price/weight desc
limit 4
);


(
SELECT *
FROM products
ORDER BY Price DESC
LIMIT 4
)
UNION
(
select*
from products
order by price/weight desc
limit 4
)



###########################
--Subquery

SELECT paid
FROM orders
WHERE id IN (3, 4, 5);


select avg(order_count)
from(
select user_id, count(*) as order_count
from orders
group by user_id
)

select distinct department
from products;

select count(distinct department)
from products;

select greatest(50, 35, 75, 214)

select name, weight, greatest(30,2*weight)
from products;

select least(1000, 20, 50, 100)

select name, price, least(price*0,5, 400)
from products;

select
name, price, 
case
	when price > 600 then 'high'
	when price > 300 then 'medium'
	else 'cheap'
end
from products;



select (2.0 :: integer)
	