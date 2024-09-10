
insert into country_master values(1, 'India');
insert into country_master values(2, 'USA');

insert into state_master(state_Id, state_name, country_Id) values(1, 'AP', 1);
insert into state_master(state_Id, state_name, country_Id) values(2, 'TG', 1);
insert into state_master(state_Id, state_name, country_Id) values(3, 'Newyork', 2);
insert into state_master(state_Id, state_name, country_Id) values(4, 'dalls', 2);

insert into city_master(city_Id, city_name, state_Id) values(1, 'Ongole', 1);
insert into city_master(city_Id, city_name, state_Id) values(2, 'krishna', 1);

insert into city_master(city_Id, city_name, state_Id) values(3, 'Hyd', 2);
insert into city_master(city_Id, city_name, state_Id) values(4, 'Khm', 2);

insert into city_master(city_Id, city_name, state_Id) values(5, 'newyork-1', 3);
insert into city_master(city_Id, city_name, state_Id) values(6, 'newyork-2', 3);

insert into city_master(city_Id, city_name, state_Id) values(7, 'dallas-1', 4);
insert into city_master(city_Id, city_name, state_Id) values(8, 'dallas-2', 4);



