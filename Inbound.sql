create table Inbound(
	I_Date date,
	P_Name Varchar2(100), 
	B_Name Varchar2(15), 
	Amount_in Number(10), 
	Cost Number(30)
);

select * from inbound;

drop table inbound;

delete from inbound;