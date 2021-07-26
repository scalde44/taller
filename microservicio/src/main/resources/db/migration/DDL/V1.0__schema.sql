create table usuario (
 id serial,
 nombre varchar(100) not null,
 clave varchar(45) not null,
 fecha_creacion timestamp null,
 primary key (id)
);