package br.com.fiap.bootcamp.dto;

import br.com.fiap.bootcamp.entity.TipoUsuario;
import br.com.fiap.bootcamp.entity.UsuarioEntity;

public class UsuarioDto {

    Long id;
    String nome;
    String email;
    String senha;
    TipoUsuario tipo;

    public UsuarioDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public UsuarioEntity toEntity() {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNome(this.nome);
        entity.setEmail(this.email);
        entity.setSenha(this.senha.hashCode());
        entity.setTipo(this.tipo);
        return entity;
    }
}
