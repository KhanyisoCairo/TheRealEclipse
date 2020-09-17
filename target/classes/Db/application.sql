create table IF NOT EXISTS application(
id serial not null primary key,
    first_name text not null,
	last_name text not null,
	cellphone int not null,
	ID int not null,
	email text
);

insert into application(first_name,last_name,cellphone,ID) values ('Theo','Nukwayo','062 895 4882','94856723694589','Theo@gmail.com');
insert into application(first_name,last_name,cellphone,ID) values ('Daniel','Mabutho','078 258 1478','8025963214784','Mthandeni@gmail.com');
insert into application(first_name,last_name,cellphone,ID) values ('Khanyiso','Yezo','062 269 4004','9807285206081','Khanyiso@gmail.com');
insert into application(first_name,last_name,cellphone,ID) values ('Simbongele','Tusha','085 236 1694','9925698745632','Tusha@gmail.com');