use master
drop database school
create database manager;
go

use manager;

--管理员表
create table admininfo(
	aid int identity(1,1) primary key not null,
	aname varchar(20),
	apsw varchar(20),
	aremark varchar(200)
);
insert into admininfo values('admin','123','超级用户');

--部门表
create table deptinfo(
	did int identity(1,1) primary key not null,
	dname varchar(20),
	dremark varchar(200)
);

insert into  deptinfo values('教务部','专门管理教务');
insert into  deptinfo values('行政部','专门管理行政');
insert into  deptinfo values('后勤部','专门管理后勤');

--岗位表
create table postinfo(
	pid int identity(1,1) primary key not null,
	pname varchar(20),
	premark varchar(200),
	did int references deptinfo(did)--引用岗位表

);
insert into postinfo values('教员','主要做教员',1);
insert into postinfo values('行政','主要做行政',2);
insert into postinfo values('采购','主要做采购',3);

--职位表
create table zwinfo(
	zid int identity(1,1) primary key not null,
	zname varchar(20),
	zremark varchar(200),
	pid int references postinfo(pid)--引用岗位表
);

insert into zwinfo values('普通老师','我是普通老师',1);
insert into zwinfo values('行政老师','我是职位行政老师',2);
insert into zwinfo values('采购员','我是采购员',3);

--员工表
create table emplo(
	eid int identity(1,1) primary key not null,
	ename varchar(20),
	esex char(2),
	eage int,
	eedu varchar(200),--学历
	zid int references zwinfo(zid),--所在职位
);
insert into emplo values('Tom','男',30,'博士',1);
insert into emplo values('Rose','女',36,'博士',1);

insert into emplo values('MiMi','女',36,'博士',2);
insert into emplo values('Jack','男',32,'博士',2);

insert into emplo values('小王','男',25,'本科',3);
insert into emplo values('小李','男',32,'大专',3);



	
--科目表
create table subjects(
	sid int identity(1,1) primary key not null,
	sname varchar(20),--科目名称
	sremark varchar(200)
);
insert into subjects values('语文','语文还可以');
insert into subjects values('Java','java还可以');
insert into subjects values('毛概','毛概还可以');
insert into subjects values('体育','体育还可以');

--系别表
create table xb(
	xid int identity(1,1) primary key not null,
	xname varchar(20),--系别
	xremark varchar(200)
)
insert into xb values('人文系','人文真不错');
insert into xb values('体育系','体育真不错');
insert into xb values('计算机系','计算机真不错');

--年级表
create table grade(
	gid int  identity(1,1) primary key not null,
	gname varchar(20),
	gremark varchar(200),
	xid int references xb(xid)--引用系别表
);
insert into grade values('人文2016级','good',1);
insert into grade values('人文2017级','good',1);
insert into grade values('体育2016级','good',2);
insert into grade values('体育2017级','good',2);
insert into grade values('计算机2016级','good',3);
insert into grade values('计算机2017级','good',3);

--班级表
create table classes(
	cid int  identity(1,1) primary key not null,
	cname varchar(20),
	cremark varchar(200),
	gid int references grade(gid)--引用年级表
);
insert into classes values('(1)班','one',1);
insert into classes values('(2)班','two',1);
insert into classes values('(3)班','three',1);
insert into classes values('(4)班','four',1);

insert into classes values('(1)班','one',2);
insert into classes values('(2)班','two',2);
insert into classes values('(3)班','three',2);
insert into classes values('(4)班','four',2);

insert into classes values('(1)班','one',3);
insert into classes values('(2)班','two',3);
insert into classes values('(3)班','three',3);
insert into classes values('(4)班','four',3);

insert into classes values('(1)班','one',4);
insert into classes values('(2)班','two',4);
insert into classes values('(3)班','three',4);
insert into classes values('(4)班','four',4);

insert into classes values('(1)班','one',5);
insert into classes values('(2)班','two',5);
insert into classes values('(3)班','three',5);
insert into classes values('(4)班','four',5);

insert into classes values('(1)班','one',6);
insert into classes values('(2)班','two',6);
insert into classes values('(3)班','three',6);
insert into classes values('(4)班','four',6);

--课程安排表
create table curr(
	rid int  identity(1,1) primary key not null,
	rcount int,--课时量
	eid int references emplo(eid),--引用员工
	cid int references classes(cid),--引用班级
	sid int references subjects(sid)--引用科目
);
insert into curr values(80,1,1,1);


drop database manager
use master