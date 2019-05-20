use master
drop database school
create database manager;
go

use manager;

--����Ա��
create table admininfo(
	aid int identity(1,1) primary key not null,
	aname varchar(20),
	apsw varchar(20),
	aremark varchar(200)
);
insert into admininfo values('admin','123','�����û�');

--���ű�
create table deptinfo(
	did int identity(1,1) primary key not null,
	dname varchar(20),
	dremark varchar(200)
);

insert into  deptinfo values('����','ר�Ź������');
insert into  deptinfo values('������','ר�Ź�������');
insert into  deptinfo values('���ڲ�','ר�Ź������');

--��λ��
create table postinfo(
	pid int identity(1,1) primary key not null,
	pname varchar(20),
	premark varchar(200),
	did int references deptinfo(did)--���ø�λ��

);
insert into postinfo values('��Ա','��Ҫ����Ա',1);
insert into postinfo values('����','��Ҫ������',2);
insert into postinfo values('�ɹ�','��Ҫ���ɹ�',3);

--ְλ��
create table zwinfo(
	zid int identity(1,1) primary key not null,
	zname varchar(20),
	zremark varchar(200),
	pid int references postinfo(pid)--���ø�λ��
);

insert into zwinfo values('��ͨ��ʦ','������ͨ��ʦ',1);
insert into zwinfo values('������ʦ','����ְλ������ʦ',2);
insert into zwinfo values('�ɹ�Ա','���ǲɹ�Ա',3);

--Ա����
create table emplo(
	eid int identity(1,1) primary key not null,
	ename varchar(20),
	esex char(2),
	eage int,
	eedu varchar(200),--ѧ��
	zid int references zwinfo(zid),--����ְλ
);
insert into emplo values('Tom','��',30,'��ʿ',1);
insert into emplo values('Rose','Ů',36,'��ʿ',1);

insert into emplo values('MiMi','Ů',36,'��ʿ',2);
insert into emplo values('Jack','��',32,'��ʿ',2);

insert into emplo values('С��','��',25,'����',3);
insert into emplo values('С��','��',32,'��ר',3);



	
--��Ŀ��
create table subjects(
	sid int identity(1,1) primary key not null,
	sname varchar(20),--��Ŀ����
	sremark varchar(200)
);
insert into subjects values('����','���Ļ�����');
insert into subjects values('Java','java������');
insert into subjects values('ë��','ë�Ż�����');
insert into subjects values('����','����������');

--ϵ���
create table xb(
	xid int identity(1,1) primary key not null,
	xname varchar(20),--ϵ��
	xremark varchar(200)
)
insert into xb values('����ϵ','�����治��');
insert into xb values('����ϵ','�����治��');
insert into xb values('�����ϵ','������治��');

--�꼶��
create table grade(
	gid int  identity(1,1) primary key not null,
	gname varchar(20),
	gremark varchar(200),
	xid int references xb(xid)--����ϵ���
);
insert into grade values('����2016��','good',1);
insert into grade values('����2017��','good',1);
insert into grade values('����2016��','good',2);
insert into grade values('����2017��','good',2);
insert into grade values('�����2016��','good',3);
insert into grade values('�����2017��','good',3);

--�༶��
create table classes(
	cid int  identity(1,1) primary key not null,
	cname varchar(20),
	cremark varchar(200),
	gid int references grade(gid)--�����꼶��
);
insert into classes values('(1)��','one',1);
insert into classes values('(2)��','two',1);
insert into classes values('(3)��','three',1);
insert into classes values('(4)��','four',1);

insert into classes values('(1)��','one',2);
insert into classes values('(2)��','two',2);
insert into classes values('(3)��','three',2);
insert into classes values('(4)��','four',2);

insert into classes values('(1)��','one',3);
insert into classes values('(2)��','two',3);
insert into classes values('(3)��','three',3);
insert into classes values('(4)��','four',3);

insert into classes values('(1)��','one',4);
insert into classes values('(2)��','two',4);
insert into classes values('(3)��','three',4);
insert into classes values('(4)��','four',4);

insert into classes values('(1)��','one',5);
insert into classes values('(2)��','two',5);
insert into classes values('(3)��','three',5);
insert into classes values('(4)��','four',5);

insert into classes values('(1)��','one',6);
insert into classes values('(2)��','two',6);
insert into classes values('(3)��','three',6);
insert into classes values('(4)��','four',6);

--�γ̰��ű�
create table curr(
	rid int  identity(1,1) primary key not null,
	rcount int,--��ʱ��
	eid int references emplo(eid),--����Ա��
	cid int references classes(cid),--���ð༶
	sid int references subjects(sid)--���ÿ�Ŀ
);
insert into curr values(80,1,1,1);


drop database manager
use master