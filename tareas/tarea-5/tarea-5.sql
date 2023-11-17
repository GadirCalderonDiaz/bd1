-- CREATE DATABASE
DROP DATABASE IF EXISTS  blockbuster;
create database if not exists  blockbuster;
use blockbuster;


drop table if exists customer;
drop table if exists movie_category;
drop table if exists movie;
drop table if exists rental;


create table if not exists customer (
	id int primary key auto_increment, -- cedula
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    cell_phone varchar(15) not null,
    address varchar(200) default 'N/A'
);

create table if not exists movie_category(
 id int primary key auto_increment,
 category_name varchar (30) not null
);

create table  if not exists movie (
	id int primary key auto_increment,
    title varchar(100) not null,
    release_date timestamp not null,
    category_id int not null,
    constraint category_id_positive_value check (category_id > 0),
    constraint movie_category_id_fk foreign key (category_id) references movie_category (id)
);

create table if not exists rental (
	id int not null auto_increment unique,
    customer_id int not null,
    movie_id int not null unique,
    rental_date timestamp not null,
    return_date timestamp not null,
    primary key (customer_id, movie_id),
    constraint customer_id_positive_value check (customer_id > 0),
    constraint movie_id_positive_value check (movie_id > 0), 
	constraint rental_customer_id_fk foreign key (customer_id) references customer (id),
	constraint rental_movie_id_fk foreign key (movie_id) references movie(id)
);


-- Datos
insert into customer(first_name, last_name, cell_phone) values ('Juan', 'Perez', '4444444');
insert into customer(first_name, last_name, cell_phone) values ('Maria', 'Rodríguez', '4444444');
insert into customer(first_name, last_name, cell_phone, address) values ('Gadyr', 'Calderon', '4444444', 'Limon');
insert into customer(first_name, last_name, cell_phone, address) values ('Jareck', 'Levell', '4444444', 'Guanacaste');
insert into customer(first_name, last_name, cell_phone, address) values ('Martin', 'Flores', '4444444', 'Cartago');
insert into customer(first_name, last_name, cell_phone, address) values ('David', 'Martinez', '4444444', 'Limon');
insert into customer(first_name, last_name, cell_phone, address) values ('Rafael', 'Calderon', '4444444', 'Cartago');
insert into customer(first_name, last_name, cell_phone, address) values ('Daniel', 'Calderon', '4444444', 'San Jose');
insert into customer(first_name, last_name, cell_phone, address) values ('Andrey', 'Salamanca', '4444444', 'Limon');
insert into customer(first_name, last_name, cell_phone, address) values ('Jhonner', 'Rojas', '4444444', 'Limon');



insert into movie_category(category_name) values ('Comedy');
insert into movie_category(category_name) values ('Drama');
insert into movie_category(category_name) values ('Sci-Fi');
insert into movie_category(category_name) values ('Terror');
insert into movie_category(category_name) values ('Animation');
insert into movie_category(category_name) values ('Romantic comedy');
insert into movie_category(category_name) values ('Documentary');
insert into movie_category(category_name) values ('Romance');
insert into movie_category(category_name) values ('Adventure');
insert into movie_category(category_name) values ('Action – thriller');

insert into movie(title, release_date, category_id) values ('Ace Ventura', '1998-11-11 12:00:00', 1);
insert into movie(title, release_date, category_id) values ('Oppenheimer', '2023-08-11 12:00:00', 2);
insert into movie(title, release_date, category_id) values ('The Matrix', '1999-11-11 12:00:00', 3);
insert into movie(title, release_date, category_id) values ('Paranormal Activity', '2005-11-11 12:00:00', 4);
insert into movie(title, release_date, category_id) values ('Super Mario Bros. La película', '2023-04-05 12:00:00', 5);
insert into movie(title, release_date, category_id) values ('Ustedes', '2023-01-20 12:00:00', 6);
insert into movie(title, release_date, category_id) values ('Pumping Iron', '1977-01-18 12:00:00', 7);
insert into movie(title, release_date, category_id) values ('Culpa mía', '2023-11-11 12:00:00', 8);
insert into movie(title, release_date, category_id) values ('65: Al borde de la extinción', '2023-11-11 12:00:00', 9);
insert into movie(title, release_date, category_id) values ('The Covenant', '2023-11-11 12:00:00', 10);



insert into rental(customer_id, movie_id, rental_date, return_date) values(1, 1, now(), now());
insert into rental(customer_id, movie_id, rental_date, return_date) values(3, 2, '2023-09-17 17:00:00', now());
insert into rental(customer_id, movie_id, rental_date, return_date) values(2, 3, '2023-08-17 17:00:00', now());
insert into rental(customer_id, movie_id, rental_date, return_date) values(4, 4, '2023-08-17 17:00:00', now());
insert into rental(customer_id, movie_id, rental_date, return_date) values(5, 5, '2023-08-17 17:00:00', now());
insert into rental(customer_id, movie_id, rental_date, return_date) values(6, 6, '2023-08-17 17:00:00', now());
insert into rental(customer_id, movie_id, rental_date, return_date) values(8, 7, '2023-08-17 17:00:00', now());
insert into rental(customer_id, movie_id, rental_date, return_date) values(9, 8, '2023-08-17 17:00:00', now());
insert into rental(customer_id, movie_id, rental_date, return_date) values(10, 9, '2023-08-17 17:00:00', now());
insert into rental(customer_id, movie_id, rental_date, return_date) values(7, 10, '2023-08-17 17:00:00', now());



-- Mostrar la Cédula, Nombre, Apellido y Teléfono Celular de los clientes que han pedido prestada una película al video, no importa si el préstamo está activo o no.

select 
c.id, c.first_name, c.last_name, c.cell_phone, r.movie_id, m.title
from 
customer c 
inner join rental r on c.id = r.customer_id
left join movie m on r.movie_id = m.id;

-- Mostrar la cantidad de préstamos activos por cédula.
select 
c.id, 
r.rental_date,
m.title,
datediff(now(), r.rental_date) as days_in_rental
from 
rental as r
inner join customer c on r.customer_id = c.id
inner join movie m on r.movie_id = m.id
where 
datediff(now(), r.rental_date) <= 3;

-- Mostrar la cantidad de préstamos inactivos existentes por cédula.
select 
c.id, 
r.rental_date,
m.title,
datediff(now(), r.rental_date) as days_in_rental
from 
rental as r
inner join customer c on r.customer_id = c.id
inner join movie m on r.movie_id = m.id
where 
datediff(now(), r.rental_date) > 3;

-- Mostrar el total de préstamos inactivos existentes .
select 
count(*)
from 
rental r 
where
datediff(now(), r.rental_date) > 3;

-- Mostrar a todos aquellos clientes que nunca han realizado un préstamo.
select 
c.id, 
c.first_name, 
c.last_name, 
c.cell_phone, 
r.id, 
r.movie_id
from 
customer c 
left join rental r on c.id = r.customer_id 
where r.id is null;

-- Actualizar el campo Dirección y poner Guanacaste, del cliente con cédula 10.
UPDATE customer SET address = 'Guanacaste' WHERE id = 10;

-- Mostrar la cédula, nombre, apellido de los clientes que tienen entre 1 y 3 préstamos activos.
select
c.id,
concat(c.first_name, ' ', c.last_name) as customer,
count(*) as number_of_rentals
from 
customer c
inner join rental r on c.id = r.customer_id
group by 
r.customer_id
having
number_of_rentals >= 0 and number_of_rentals <= 3;

-- Listar todas las películas de la categoría “Comedia”.
select
* 
from 
movie as m inner join movie_category as c on m.category_id = c.id 
where c.category_name = 'Comedy';

-- Listas todas las películas prestadas de la categoría “Comedia”.
select
* 
from 
rental as r inner join movie m on r.movie_id = m.id 
inner join movie_category as c on m.category_id = c.id 
where c.category_name = 'Comedy';
 
-- Listar cuántas películas existen por categoría, por ejemplo: 3 de Comedia, 2 de Drama, 4 de Ciencia Ficción, etc.
SELECT * FROM movie_category;
SELECT * FROM movie;

SELECT movie_category.category_name as nombreCategory, COUNT(title) as cantidadPeliculas FROM movie as m 
INNER JOIN movie_category ON movie_category.id = m.category_id
GROUP BY  movie_category.category_name;





