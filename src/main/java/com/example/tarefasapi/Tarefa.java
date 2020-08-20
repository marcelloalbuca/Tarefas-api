package com.example.tarefasapi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(name="nome")
    private String nome;

    @Column(name="descricao")
    private String descricao;

    
    @Column(name="concluido")
    private Boolean concluido;

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        return result = prime * result + ((id == null) ? 0 : id.hashCode());
    }

    public boolean equals(final Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Tarefa other = (Tarefa) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(final Boolean concluido) {
        this.concluido = concluido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}