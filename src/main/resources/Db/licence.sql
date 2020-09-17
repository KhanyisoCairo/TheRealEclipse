create table IF NOT EXISTS licence(
    id serial not null primary key,
    Code_Type text not null,
	LicenceType text not null
);

insert into licence( Code_Type, LicenceType) values ('Code A1','Learners Licence');
insert into licence( Code_Type, LicenceType) values ('Code C1','Learners Licence');
insert into licence( Code_Type, LicenceType) values ('Code B','Drivers Licence');
insert into licence( Code_Type, LicenceType) values ('Code A1','Learners Licence');
insert into licence( Code_Type, LicenceType) values ('Code C1','Drivers Licence');
insert into licence( Code_Type, LicenceType) values ('Code A1','Drivers Licence');