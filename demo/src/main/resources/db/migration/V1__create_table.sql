-- drop table if exists flyway_schema_history;
-- drop table if exists students;
-- drop table if exists classes;

create table classes (
                         id int primary key AUTO_INCREMENT,
                         name varchar(50)

);
create table students (
                          id int primary key AUTO_INCREMENT,
                          name varchar(50),
                          class_id int,
    foreign key (class_id) references classes(id)
);
insert into classes(name) values ('C07');
insert into classes(name) values ('C08');
insert into classes(name) values ('C09');
insert into students(name,class_id) values ('chánh',1);
insert into students(name,class_id) values ('chánh2',1);
insert into students(name,class_id) values ('chánh3',1);
insert into students(name,class_id) values ('chánh4',2);
insert into students(name,class_id) values ('chánh5',2);
