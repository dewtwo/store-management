create table Products(
	P_Id number(5) constraint products_pk primary key, 
	P_Name Varchar2(100) not null, 
	B_Name Varchar2(15) not null, 
	P_Category Varchar2(15) not null, 
	Price Number(10) not null, 
	Location Varchar2(10), 
	Amount Number(5)
);

insert into Products values (1,'���ٹ��佺 ��Ƽ�� EDT 125ml', '���ٹ��佺', 'PERFUME', 113000,'P1-1',10);
insert into Products values (2,'�ƺ��� �������� 150ml', '�ƺ���', 'SKIN CARE', 17000,'S1-1',25);

insert into Products values (5,'������', '�ƺ���', 'SKIN CARE', 17000,'S1-1',25);
insert into Products values (6,'�ƺ��� �������� 150ml', '�ƺ���', 'SKIN CARE', 17000,'S1-1',25);
insert into Products values (7,'�ƺ��� �������� 150ml', '�ƺ���', 'SKIN CARE', 17000,'S1-1',25);
insert into Products values (8,'�ƺ��� �������� 150ml', '�ƺ���', 'SKIN CARE', 17000,'S1-1',25);

update products set p_category = 'PERFUME' where p_id = 1;

select * from products;

select * from products where p_name like '%���ٹ��佺%';

drop table products;