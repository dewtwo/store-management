create table Sales(
	S_Date date not null, 
	P_Id Number(5) not null,  
	P_Name Varchar2(100) not null, 
	B_Name Varchar2(15) not null, 
	P_Category Varchar2(15) not null,
	DaySales Number(10)
);

select * from SALES;

drop table sales;

select * from sales where p_category = 'SKIN CARE' and s_date>='20170601' and s_date<='20170627' order by s_date;