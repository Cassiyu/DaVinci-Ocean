create table fiapdv_usuario (
    id number(10) primary key,
    nome varchar2(255) not null,
    email varchar2(255) not null unique,
    senha varchar2(255) not null
);


create table fiapdv_sensor (
    sensor_id number(10) primary key,
    data_hora varchar2(21) not null,
    localizacao varchar2(255) not null,
    temperatura number(19,17) not null
);
 
create table fiapdv_relatorio (
    relatorio_id number(10) primary key,
    data_inicio varchar2(21) not null,
    data_fim varchar2(21) not null,
    localizacao varchar2(255) not null,
    temperatura_media number,
    temperatura_maxima number,
    temperatura_minima number
);

create sequence fiapdv_seq
start with 1
increment by 1
nomaxvalue;

create or replace trigger fiapdv_usuario_trigger
before insert on fiapdv_usuario
for each row
begin
  :new.id := fiapdv_seq.nextval;
end;

create or replace trigger fiapdv_sensor_trigger
before insert on fiapdv_sensor
for each row
begin
  :new.sensor_id := fiapdv_seq.nextval;
end;

create or replace trigger fiapdv_relatorio_trigger
before insert on fiapdv_relatorio
for each row
begin
  :new.relatorio_id := fiapdv_seq.nextval;
end;