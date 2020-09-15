
create table trafficDepartments(
	id serial not null primary key,
	Name text not null,
	address text not null,
	latitude text not null,
	longitude text not null
);

insert into trafficDepartments(Name,address,latitude,longitude) values('Ashton Traffic department','Abbatoir Street, Industrial Area, Ashton, 6715','-33.8357828','20.0751507');
insert into trafficDepartments(Name,address,latitude,longitude) values('Atlantis Traffic department','Cnr Charel Uys Street and Hampshire Place, Atlantis ','-33.5882551','18.48129');
insert into trafficDepartments(Name,address,latitude,longitude) values('Beaufort West Municipal Traffic department','88 New Street, Beaufort West, 6970','-32.3488494','22.5789532');
insert into trafficDepartments(Name,address,latitude,longitude) values('Bellrail(Bellville) Traffic department','Bellrail Road, Bellville','-33.9047626','18.6362645');
insert into trafficDepartments(Name,address,latitude,longitude) values('Brackenfell Traffic department','C/o Kruisfontein Road and Reservoir Road, Protea Heights, Brackenfell, 7560','-33.8779234','18.7023345');
insert into trafficDepartments(Name,address,latitude,longitude) values('Cape Agulhas Municipal Traffic department','C/o Fabrieksweg Ou Meule Street, Bredasdorp, 7280','-34.5174355','19.7065315');
insert into trafficDepartments(Name,address,latitude,longitude) values('Drakenstein Municipal Traffic department','Berg River Boulevard, Paarl, 7622','-33.6894862','18.9131341');