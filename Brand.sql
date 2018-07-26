create table Brand(
B_Name Varchar2(15) constraint Brand_pk primary key, 
B_Manager Varchar2(10),
B_Number Varchar2(15)
);

insert into Brand values ('아벤느','박서준','010-1111-2222');
insert into Brand values ('존바바토스','정우성','010-3333-4444');
insert into Brand values ('끌로에','배수지','010-4646-5555');
insert into Brand values ('닥터지','송중기','010-5555-7897');
insert into Brand values ('더마비','이보영','010-6455-7891');
insert into Brand values ('듀이트리','지창욱','010-5555-7644');
insert into Brand values ('디에이치씨','박수진','010-7410-6464');
insert into Brand values ('라포슈포제','김수현','010-4646-6666');
insert into Brand values ('랑방','마동석','010-7775-6134');
insert into Brand values ('메디힐','유재석','010-4442-3335');
insert into Brand values ('바이오더마','김지원','010-5555-7777');
insert into Brand values ('불가리','박시연','010-9874-5123');
insert into Brand values ('아로마티카','공효진','010-1254-5557');
insert into Brand values ('아이소이','공승연','010-5557-8127');
insert into Brand values ('유리아쥬','하지원','010-2047-0014');

insert into Brand values ('이브로쉐','신혜선','010-8811-3131');
insert into Brand values ('쥬시꾸뛰르','윤계상','010-7420-0005');
insert into Brand values ('지미추','한효주','010-5630-9520');
insert into Brand values ('차앤박','강동원','010-2410-7865');
insert into Brand values ('카밀','이연희','010-3665-1204');
insert into Brand values ('캘빈클라인','박희순','010-7474-2304');
insert into Brand values ('크리니크','이하늬','010-6965-1357');
insert into Brand values ('투쿨포스쿨','이민정','010-5124-1120');
insert into Brand values ('페리페라','한지민','010-9877-5204');
insert into Brand values ('폴스미스','문정혁','010-7522-4874');
insert into Brand values ('해피바스','하연수','010-4646-5050');

select * from brand;
