create table booking (
id serial primary key,
department text,
license_type text,
license_code text,
slot text,
booked boolean,
booked_by text
);

insert into booking (department, license_type, slot, booked)
values ("Ashton Traffic department",  "lmv", "Monday 12h00 - 13h00", false);

insert into booking (department, license_type, booked)
values ("Atlantis Traffic department", "lmv","Tuesday 09h00 - 15h00", false);

insert into booking (department, license_type, booked)
values ("Beaufort West Municipal Traffic department", "lmv","Friday 10h00 - 14h00", false);

insert into booking (department, license_type, booked)
values ("Bellrail(Bellville) Traffic department", "lmv","Thursday 11h00 - 15h00", false);

insert into booking (department, license_type, booked)
values ("Brackenfell Traffic department", "lmv","Wednesday 08h00 - 16h00", false);

insert into booking (department, license_type, booked)
values ("Cape Agulhas Municipal Traffic department", "lmv","Monday 07h00 - 14h00", false);

insert into booking (department, license_type, booked)
values ("Drakenstein Municipal Traffic department", "lmv","Friday 11h00 - 17h00", false);

select * from booking where booked = false and department = ? and license_code = ? and license_type = ?;