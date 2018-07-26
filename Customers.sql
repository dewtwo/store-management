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

insert into CUSTOMERS values (1, '최승민', '서울시 성동구 마장동', '010-2147-7183', 805400, 'VIP', '차앤박');
insert into CUSTOMERS values (2, '이민지', '서울시 중구 서소문동', '010-7845-6172', 126000, '일반', '페리페라');
insert into CUSTOMERS values (3, '민정한', '서울시 구로구 개봉동', '010-9852-0125', 240700, 'Bronze', '존바바토스');
insert into CUSTOMERS values (4, '윤지원', '서울시 관악구 봉천동', '010-6528-8451', 97000, '일반', '디에이치씨');
insert into CUSTOMERS values (5, '강경민', '서울시 서초구 방배동', '010-5630-0478', 186540, '일반', '아벤느');
insert into CUSTOMERS values (6, '한민영', '서울시 서대문구 연희동', '010-3647-2549', 65700, 'Gold', '해피바스');
insert into CUSTOMERS values (7, '오정윤', '서울시 송파구 잠실동', '010-2017-8569', 883200, 'VIP', '크리니크');
insert into CUSTOMERS values (8, '문정인', '서울시 중구 무교동', '010-8874-1578', 307700, 'Bronze', '바이오더마');
insert into CUSTOMERS values (9, '김진혜', '서울시 성북구 돈암동', '010-0254-6984', 457000, 'Silver', '메디힐');
insert into CUSTOMERS values (10, '이명은', '서울시 강남구 논현동', '010-2317-8504', 613200, 'Gold', '랑방');
insert into CUSTOMERS values (11, '박미진', '서울시 서대문구 홍은동', '010-8526-5696', 286100, 'Bronze', '아로마티카');
insert into CUSTOMERS values (12, '최영은', '서울시 강북구 수유동', '010-5430-0629', 57200, '일반', '아이소이');
insert into CUSTOMERS values (13, '김민찬', '서울시 구로구 가리봉동', '010-3781-0277', 107450, '일반', '불가리');
insert into CUSTOMERS values (14, '이마리', '서울시 관악구 신림동', '010-6694-1571', 342500, 'Bronze', '더마비');
insert into CUSTOMERS values (15, '하정미', '서울시 송파구 방이동', '010-1278-8015', 710600, 'Gold', '이브로쉐');
insert into CUSTOMERS values (16, '이성광', '서울시 강남구 압구정동', '010-4885-9630', 520040, 'Silver', '카밀');