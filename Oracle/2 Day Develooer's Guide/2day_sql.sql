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

--Substituting a Strin for a NULL Value
















