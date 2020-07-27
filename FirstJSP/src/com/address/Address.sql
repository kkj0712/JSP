CREATE TABLE address (
    num NUMBER,
    name VARCHAR2(50),
    zipcode VARCHAR2(20),
    tel VARCHAR2(20),
    addr VARCHAR2(2000),
    primary key(num)
);
commit;

DROP TABLE address;
  
DESC address;  

CREATE SEQUENCE address_seq;

SELECT COUNT (*) 
FROM address;

commit;

SELECT * FROM JMEMBER;

SELECT pwd FROM JMEMBER
WHERE USERID='ff';

select * from (select * from JMEMBER
where userid='cc');