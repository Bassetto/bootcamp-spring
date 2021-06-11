package br.com.fiap.bootcamp.entity;


import javax.persistence.*;

@Entity(name = "usuario")
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(unique=true)
    private Long usuarioId;

    private String nome;

    @Column(unique=true)
    private String email;
    private int senha;
    @Enumerated(EnumType.ORDINAL)
    private TipoUsuario tipo;

    public UsuarioEntity() {
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long id) {
        this.usuarioId = id;
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

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }
}

