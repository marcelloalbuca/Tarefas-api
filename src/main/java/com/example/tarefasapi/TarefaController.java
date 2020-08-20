package com.example.tarefasapi;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefas;

    @GetMapping
    public List<Tarefa> listar() {
        return tarefas.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscar(@PathVariable Long id) {
         Optional<Tarefa> tarefa = tarefas.findById(id);

        if (tarefa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tarefa.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tarefa adicionar(@Validated @RequestBody Tarefa tarefa){

        Optional<Tarefa> tarefaExistente = tarefas
        .findByDescricaoAndNome(tarefa.getDescricao(), 
        tarefa.getNome());

        if(tarefaExistente.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "já Existe uma Tarefa com a mesma descrição");
        }
        return tarefas.save(tarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        Optional<Tarefa> findById = tarefas.findById(id);
        return findById
           .map(record -> {
            tarefas.deleteById(id);
               return ResponseEntity.ok().build();
           }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                      @RequestBody Tarefa tarefa) {
   return tarefas.findById(id)
           .map(record -> {
               record.setNome(tarefa.getNome());
               record.setDescricao(tarefa.getDescricao());
               Tarefa updated = tarefas.save(record);
               return ResponseEntity.ok().body(updated);
           }).orElse(ResponseEntity.notFound().build());
    }   
}