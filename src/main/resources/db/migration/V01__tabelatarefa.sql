create table tarefa (
  id bigint auto_increment not null,
  nome varchar(80) not null,
  descricao varchar(200) not null,
  primary key (id)
);