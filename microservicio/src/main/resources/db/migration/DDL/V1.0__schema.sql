create table mantenimiento (
 id serial not null,
 placa varchar(6) not null,
 cilindraje numeric not null,
 fecha_entrada timestamp not null,
 tarifa numeric not null,
 estado char(1) not null,
 primary key(id)
)