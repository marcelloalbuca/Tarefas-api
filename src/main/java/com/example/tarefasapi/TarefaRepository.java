package com.example.tarefasapi;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

    Optional<Tarefa> findByDescricaoAndNome(String descricao, String nome);

}

