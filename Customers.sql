create table Customers(
C_Id number(5) constraint customers_pk primary key, 
C_Name Varchar2(10), 
Address Varchar2(50), 
PhoneNum Varchar2(15),
Sum number(10),
Grade Varchar2(10), 
B_Favorite Varchar2(100)
);

select * from customers;
drop table customers;

insert into CUSTOMERS values (1, '�ֽ¹�', '����� ������ ���嵿', '010-2147-7183', 805400, 'VIP', '���ع�');
insert into CUSTOMERS values (2, '�̹���', '����� �߱� ���ҹ���', '010-7845-6172', 126000, '�Ϲ�', '�丮���');
insert into CUSTOMERS values (3, '������', '����� ���α� ������', '010-9852-0125', 240700, 'Bronze', '���ٹ��佺');
insert into CUSTOMERS values (4, '������', '����� ���Ǳ� ��õ��', '010-6528-8451', 97000, '�Ϲ�', '����ġ��');
insert into CUSTOMERS values (5, '�����', '����� ���ʱ� ��赿', '010-5630-0478', 186540, '�Ϲ�', '�ƺ���');
insert into CUSTOMERS values (6, '�ѹο�', '����� ���빮�� ����', '010-3647-2549', 65700, 'Gold', '���ǹٽ�');
insert into CUSTOMERS values (7, '������', '����� ���ı� ��ǵ�', '010-2017-8569', 883200, 'VIP', 'ũ����ũ');
insert into CUSTOMERS values (8, '������', '����� �߱� ������', '010-8874-1578', 307700, 'Bronze', '���̿�����');
insert into CUSTOMERS values (9, '������', '����� ���ϱ� ���ϵ�', '010-0254-6984', 457000, 'Silver', '�޵���');
insert into CUSTOMERS values (10, '�̸���', '����� ������ ������', '010-2317-8504', 613200, 'Gold', '����');
insert into CUSTOMERS values (11, '�ڹ���', '����� ���빮�� ȫ����', '010-8526-5696', 286100, 'Bronze', '�Ʒθ�Ƽī');
insert into CUSTOMERS values (12, '�ֿ���', '����� ���ϱ� ������', '010-5430-0629', 57200, '�Ϲ�', '���̼���');
insert into CUSTOMERS values (13, '�����', '����� ���α� ��������', '010-3781-0277', 107450, '�Ϲ�', '�Ұ���');
insert into CUSTOMERS values (14, '�̸���', '����� ���Ǳ� �Ÿ���', '010-6694-1571', 342500, 'Bronze', '������');
insert into CUSTOMERS values (15, '������', '����� ���ı� ���̵�', '010-1278-8015', 710600, 'Gold', '�̺�ν�');
insert into CUSTOMERS values (16, '�̼���', '����� ������ �б�����', '010-4885-9630', 520040, 'Silver', 'ī��');