SELECT LAST_NAME, FIRST_NAME, PHONE_NUMBER FROM EMPLOYEES ORDER BY LAST_NAME;

SELECT * FROM EMPLOYEES;

SELECT FIRST_NAME, LAST_NAME, DEPARTMENT_ID FROM EMPLOYEES;


SELECT FIRST_NAME, LAST_NAME
FROM EMPLOYEES
WHERE LAST_NAME LIKE 'Ma%';


SELECT FIRST_NAMe, LAST_NAME, HIRE_DATE
FROM EMPLOYEES
ORDER BY LAST_NAME;


SELECT FIRST_NAMe, HIRE_DATE
FROM EMPLOYEES
ORDER BY LAST_NAME;


SELECT LAST_NAME,
ROUND((SALARY*12/365), 2) "Daily Pay"
FROM EMPLOYEES
WHERE DEPARTMENT_ID =100
ORDER BY LAST_NAME;



SELECT LAST_NAME,
TRUNC(SALARY*12/365) "Daily Pay"
FROM EMPLOYEES
WHERE DEPARTMENT_ID =100
ORDER BY LAST_NAME;


SELECT FIRST_NAME ||' '|| LAST_NAME "Name"
FROM EMPLOYEES
WHERE DEPARTMENT_ID = 100
ORDER BY LAST_NAME;


SELECT UPPER (LAST_NAME) "Last",
INITCAP(FIRST_NAME) "First",
LOWER(EMAIL) "E-Mail"
FROM EMPLOYEES
WHERE DEPARTMENT_ID = 100
ORDER BY EMAIL;

--Converting Characters to Numbers

SELECT CITY,
POSTAL_CODE "Old Code",
TO_NUMBER(POSTAL_CODE) + 1 "New Code"
FROM LOCATIONS
WHERE COUNTRY_ID = 'US'
ORDER BY POSTAL_CODE;

--Counting the number of rows in each group

select manager_id "Maanger",
count(*) "Numbers of Reports"
from employees
group by manager_id,
order by manager_id;


--Limiting Aggregate Functions to rows that Satisfy a Condition

select department_id "Department",
sum(salary*12) "All Salaries"
from employees
having sum(salary*12)> 1000000
group by department_id;

-- Using Aggregate Functions for statistical information 

select job_id,
count(*) "#",
min(salary) "Minimum",
round(avg(salary),0) "Average",
median(salary) "Median",
max(salary) "Maximum", 
round(stddev(salary)) "Std Dev"
from employees
group by job_id
order by job_id;

--Substituting a Strin for a NULL Value (NVL function)

select last_name, 
nvl(to_char(commission_pct), 'Not Applicable') "Commission"
from employees
where last_name like 'B%'
order by last_name;

--Specifying deiffernt expressions for NULL and NOT NULL values

select last_name, salary, 
nvl2(commission_pct, salary + (salary*commission_pct), salary) income
from employees where last_name like 'B%'
order by last_name;


--using a simple cas expression in a query

select unique country_id id,
    case country_id
        when 'AU' then 'Australia'
        when 'BR' then 'Brasil'
        when 'US' then 'United States'
        when 'DE' then 'Germany'
    else 'Unksnown'
    end country
from locations
order by country_id;
    
--Using a Searced CASE Expession in a Query

SELECT LAST_NAME "Name", HIRE_DATE "Started", SALARY "Salary",
CASE
WHEN HIRE_DATE < TO_DATE('01-Jan-03', 'dd-mon-yy') THEN TRUNC(SALARY*1.15, 0)
WHEN HIRE_DATE >= TO_DATE('01-Jan-03', 'dd-mon-yy') AND HIRE_DATE < TO_DATE('01-Jan-06', 'dd-mon-yy')
THEN TRUNC(SALARY*1.10, 0)
WHEN HIRE_DATE >= TO_DATE('01-Jan-06', 'dd-mon-yy') AND
HIRE_DATE < TO_DATE('01-Jan-07', 'dd-mon-yy') THEN TRUNC(SALARY*1.05, 0)
ELSE SALARY
END "Proposed Salary" FROM EMPLOYEES
WHERE DEPARTMENT_ID = 100 ORDER BY HIRE_DATE;


--Using the DECODE Function in a Query

select last_name, job_id, salary,
decode (job_id, 
        'PU_CLERK', salary * 1.10,
        'SH_CLERK', salary * 1.15,
        'ST_CLERK', salary * 1.20,
        salary) "Proposed salary"
from employees
where job_id like '%_CLERK'
and last_name < 'E'
order by last_name


-- iformation about employees table

describe employees;

--insert
--update
--delete

--Transactions

select * from regions
order by region_id;

insert into regions (region_id, region_name) values(5, 'Africa')

commit;


/*

Rolling BAck Transaction

To roll back the entire current transaction, use either the ROLLBACK statement without the TO SAVEPOINT clause, or (in the SQL Developer environment) the Rollback Changes icon.
Rolling back the entire current transaction:
• Ends the transaction
• Reverses all of its changes
• Erases all of its savepoints
• Releases any transaction locks
Rolling back the current transaction only to the specified savepoint:
• Does not end the transaction
• Reverses only the changes made after the specified savepoint
• Erases only the savepoints set after the specified savepoint (excluding the specified savepoint itself)
• Releases all table and row locks acquired after the specified savepoint
Other transactions that have requested access to rows locked after the specified savepoint must continue
to wait until the transaction is either committed or rolled back.
Other transactions that have not requested the rows can request and access the rows immediately.

*/

update regions
set region_name = 'Middle East'
where region_name = 'Middle Eas and Africa';


rollback;

--Setting Savepoint in transactions

select country_name, country_id, region_id
from countries
where region_id = 4
order by country_name;


select country_name, country_id, region_id
from countries
where region_id = 5
order by country_name;


update regions
set region_name = 'Middle East'
where region_name = 'Middle East and Africa';

update countries
set region_id = 5
where country_id = 'ZM';
savepoint zambia;

update countries
set region_id = 5
where country_id = 'NG';
savepoint nigeria;

update countries
set region_id = 5
where country_id = 'ZW';
savepoint zimbabwe;

update countries
set region_id = 5
where country_id = 'EG';
savepoint egypt;


select * from regions
order by region_id;

select country_name, country_id, region_id
from countries
where region_id = 4
order by country_name;


select country_name, country_id, region_id
from countries
where region_id = 5
order by country_name;

rollback to savepoint nigeria;

select * from regions
order by region_id;



create view emp_locations as
select e.employee_id, 
    e.last_name || ',' || e.first_name name,
    d.department_name department,
    l.city city,
    c.country_name country
from employees e, departments d, locations l, countries c
where e.department_id = d.department_id and
d.location_id = l.location_id and 
l.country_id = c.country_id
order by last_name;

--Changing the Query in teh salesforce view

commit;
create  view salesforce as 
select firts_name ||' '|| last_name "Name",
salary*12 "Annual salary"
from employees
where department_id = 80 or department_id =20;






