CREATE TABLE t1 (
nr NUMBER (5),
text varchar2(50),
action varchar2(1));


INSERT INTO t1 VALUES (1, 'Hallo', NULL);
INSERT INTO t1 VALUES (2, 'Welt', NULL);

CREATE TABLE t2 (
nr NUMBER (5),
text varchar2(50));

INSERT INTO t2 VALUES (2, 'World');
INSERT INTO t2 VALUES (3, '!');

commit;


merge into t1
using t2
on (t1.nr = t2.nr)
when matched then 
    update set t1.text = t2.text,
    t1.action = 'U'
when not matched then 
    insert (nr, text, action)
    values (t2.nr, t2.text, 'I');
    
commit;



select *from t1;


create table rich_emloyees (
emloeyees_id number(6));

create table poor_emloyees (
emloeyees_id number(6));

insert all
    when salary >= 10000 then 
      into rich_emloyees values (employee_id)
    when salary < 10000 then 
    into poor_empoloyees values (employee_id)
    select * from employees
    
    








