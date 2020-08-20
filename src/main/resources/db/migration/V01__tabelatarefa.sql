create table tarefa (
  id bigint auto_increment not null,
  nome varchar(80) not null,
  descricao varchar(200) not null,
  concluido boolean default 0,
  primary key (id)
);