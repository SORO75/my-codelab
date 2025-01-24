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