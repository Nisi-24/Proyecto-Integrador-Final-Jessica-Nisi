drop database if exists tpfinal;
create database tpfinal;
use tpfinal;

create table usuario(
	id int primary key auto_increment,
    nombre varchar (20),
    apellido varchar (20),
    dni int (8),
    telefono int(20),
    email varchar(100)
);

create table biblioteca(
	id int primary key auto_increment,
    nombre varchar (100),
    direccion varchar (100)
);

create table libro(
	id  int primary key auto_increment,
    nombre varchar (100),
    autor varchar (40),
    editorial varchar (20),
    tipo varchar (20),
    id_biblioteca int,
    foreign key (id_biblioteca) references biblioteca(id)
);

create table prestamos(
	id  int primary key auto_increment,
	estado varchar (10),
	precio double,
	dias int,
	precioTotal double,
    id_usuario int,
    id_libro int,
	foreign key (id_usuario) references usuario(id), 
    foreign key (id_libro) references libro(id)
);

insert into biblioteca (nombre, direccion) values
("El Ateneo Grand Splendid", "Av. Santa Fe 1860"),
("Yenny", "Acoyte 44"),
("Edipo Libros", "Av. Corrientes 1686"),
("Cúspide", "Av. Cabildo 1927");

select * from biblioteca;

insert into libro (nombre, autor, editorial, tipo, id_biblioteca) values
("Inuyasha Tomo.01", "Rumiko Takahashi", "Ivrea", "Manga", 1),
("Naruto Tomo.02", "Masashi Kishimoto", "Panini", "Manga", 2),
("Hunter x Hunter Tomo.03", "Yoshihiro Togashi", "Ivrea", "Manga", 4),
("Dragon Ball Z Tomo.17", "Akira Toriyama", "Ivrea", "Manga", 2),
("One punch Man Tomo.20", "Yusuke Murata", "Panini", "Manga", 1),
("Card Captor Sakura Clear Card Tomo.01", "CLAMP", "Ivrea", "Manga", 4),
("Banana Fish Tomo.09", "Akimi Yoshida", "Panini", "Manga", 4),
("Harry Potter y el prisionero de Azkaban", "J.K. Rowling", "Minalima", "Literatura", 1),
("El ladrón del rayo (Percy Jackson y los dioses del Olimpo 1)", "Rick Riordan", "Salamandra", "Literatura", 3),
("Una corte de rosas y espinas", "Sarah J. Maas", "Planeta", "Literatura", 2),
("El chico de las estrellas", "Chris Pueyo", "Planeta", "Literatura", 4),
("El Fantasma de la Opera", "Gaston Leroux", "Alma", "Literatura", 1),
("It", "Stephen King", "DeBolsillo", "Literatura", 2),
("El bazar de los malos sueños", "Stephen King", "DeBolsillo", "Literatura", 3),
("The Amazing Spider-Man Vol.01", "Nick Spencer", "Gárgola", "Comic", 1),
("Old Man Logan", "Ed Brisson", "Panini", "Comic", 1),
("Deadpool kills the Marvel Universe Vol.01", "Cullen Bunn", "Panini", "Comic", 2),
("Moon Knight Vol.01: The Midnight Mission", "Jed MacKay", "Panini", "Comic", 4),
("Tony Stark Iron Man Vol.08: Los libros de Korvac parte 01", "Christopher Cantwell", "Panini", "Comic", 2),
("X-Men Milestones: Operation Zero Tolerance", "Scott Lobdell", "Marvel", "Comic", 2),
("Fantastic Four Vol.06: Empyre", "Dan Slott", "Gárgola", "Comic", 1);

select * from libro;
select * from biblioteca;
select * from usuario;
select * from prestamos;


-- Elimina la restricción de clave foránea actual
ALTER TABLE prestamos
DROP FOREIGN KEY prestamos_ibfk_1;

