package br.com.fiap.bootcamp.dto;

import br.com.fiap.bootcamp.entity.EspacoEntity;

public class EspacoDto {
    Long id;
    String logradouro;
    int numero;
    int cep;
    int capacidadePessoas;

    public EspacoDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public int getCapacidadePessoas() {
        return capacidadePessoas;
    }

    public void setCapacidadePessoas(int capacidadePessoas) {
        this.capacidadePessoas = capacidadePessoas;
    }

    public EspacoEntity toEntity() {
        EspacoEntity entity = new EspacoEntity();
        entity.setLogradouro(this.logradouro);
        entity.setNumero(this.numero);
        entity.setCep(this.cep);
        entity.setCapacidadePessoas(this.capacidadePessoas);
        return entity;
    }
}
