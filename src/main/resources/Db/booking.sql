create table booking (
id serial primary key,
department text,
license_type text,
license_code text,
slot text,
booked boolean,
booked_by text
);

insert into booking (department, license_code, slot, booked, license_type)
    values ('athlone',  'c1', 'Monday 12h00 - 13h00', false, 'drivers');

insert into booking (department, license_code, slot, booked, license_type)
    values ('athlone',  'c1', 'Monday 13h00 - 14h00', false, 'drivers');

insert into booking (department, license_code, slot, booked, license_type)
    values ('athlone',  'c1', 'Monday 15h00 - 16h00', false, 'learners');

insert into booking (department, license_code, slot, booked, license_type)
    values ('greenpoint', 'c8','Tuesday 09h00 - 11h00', false, 'learners');

insert into booking (department, license_code, slot, booked, license_type)
    values ('greenpoint', 'c8','Tuesday 11h00 - 13h00', false, 'learners');

--insert into booking (department, license_type, booked)
--    values ("bellville", "c2","Friday 10h00 - 14h00", false);



select * from booking where booked = false and department = ? and license_code = ? and license_type = ?;

update booking set booked = true where id = ?;

--false and department = ? and license_code = ? and license_type = ?;