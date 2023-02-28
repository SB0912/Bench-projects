drop database if exists wireless_guardian_test;
create database wireless_guardian_test;
use wireless_guardian_test;

create table business (
    business_id int primary key auto_increment,
    business_name varchar(50) not null,
    address varchar(50) not null,
    city varchar(50) not null,
    state varchar(50) not null,
    zip_code int(20) not null,
    contact_email varchar(50) null,
    contact_phone varchar(20) null
);

create table reseller_org (
    reseller_org_id int primary key auto_increment,
    `name` varchar(50) not null,
    parent_org_id int null,
    child_org_id int null
);

create table sales_rep (
    sales_rep_id int primary key auto_increment,
    first_name varchar(25) not null,
    last_name varchar(125) not null,
    city varchar(50) not null,
	state varchar(25) null,
    reseller_org_id int not null,
    constraint fk_sales_rep_reseller_org_id
        foreign key (reseller_org_id)
        references reseller_org(reseller_org_id)
);

create table site (
    business_id int not null,
    reseller_org_id int null,
    latitude int not null,
    longitude int not null,
    services_sold varchar(50) not null,
    revenue int null,
    multiple_sites boolean not null,
	constraint fk_site_business_id
        foreign key (business_id)
        references business(business_id),
	constraint fk_site_reseller_org_id
        foreign key (reseller_org_id)
        references reseller_org(reseller_org_id)
);

delimiter //
create procedure set_known_good_state()
begin

    delete from site;
    alter table site auto_increment = 1;
	delete from sales_rep;
	alter table sales_rep auto_increment = 1;
	delete from reseller_org;
    alter table reseller_org auto_increment = 1;
	delete from business;
    alter table business auto_increment = 1;
    
	insert into business (business_id, business_name, address, city, state, zip_code, contact_email, contact_phone)
	values
	(1, 'Business1', '123 Elm', 'Des Moines', 'IA', '55555', 'test@email.com', 6121234567),
    (2, 'Business2', 'A One Ave.', 'Walla Walla', 'WA', '54321', 'test@email.com', 6121234567),
    (3, 'Business3', '123 Elm', 'Test', 'WI', '55555', 'test@email.com', 6121234567),
	(4, 'Business4', '999 Nine St.', 'Test', 'WI', '55555', 'test@email.com', 6121234567),
	(5, 'Business5', '123 Elm', 'Test', 'WI', '55555', 'test@email.com', 6121234567),
	(6, 'Business6', '999 Nine St.', 'Test', 'WI', '55555', 'test@email.com', 6121234567);
    
	insert into reseller_org 
	(reseller_org_id, `name`, parent_org_id, child_org_id) 
	values
		(1,'reseller group A', null,2),
		(2,'reseller group B',1,3),
		(3,'reseller group C',2,4),
		(4,'reseller group D',3,null);

    insert into sales_rep(sales_rep_id, first_name, last_name, city, state, reseller_org_id) 
    values
        (1, 'Slater', 'Bernstein', 'Minneapolis', 'MN', 1),
        (2, 'John', 'Doe', 'New York', 'NY', 2),
        (3, 'Jane', 'Doe', 'Madison', 'WI', 3);
        
	insert into site
		(business_id, reseller_org_id, latitude, longitude, services_sold, revenue, multiple_sites)
	values
		(1, 1, 10.12345,-01.54321, 'wifi', 321000, 1),
		(2, 1, 10.12345,-01.54321, 'wifi/traffic', 432000, 0),
		(3, 2, 10.12345,-01.54321, 'traffic', 543000, 1),
		(4, 3, 10.12345,-01.54321, 'all', 654000, 0);

end //
-- 4. Change the statement terminator back to the original.
delimiter ;