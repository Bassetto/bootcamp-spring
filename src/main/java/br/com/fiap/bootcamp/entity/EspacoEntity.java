package br.com.fiap.bootcamp.entity;

import javax.persistence.*;

@Entity(name = "espaco")
@Table(name = "espaco")
public class EspacoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long espacoId;

    private String logradouro;
    private int numero;
    private int cep;
    private int capacidadePessoas;

    public Long getEspacoId() {
        return espacoId;
    }

    public void setEspacoId(Long id) {
        this.espacoId = id;
    }

    public EspacoEntity() {
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

    public void setCapacidadePessoas(int numeroCadeirasDisponiveis) {
        this.capacidadePessoas = numeroCadeirasDisponiveis;
    }
}
