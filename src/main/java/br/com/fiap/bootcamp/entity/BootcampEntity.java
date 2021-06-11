package br.com.fiap.bootcamp.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "bootcamp")
@Table(name = "bootcamp")
public class BootcampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bootcampId;

    private String nome;
    private String area;

    @Enumerated(EnumType.ORDINAL)
    private StatusBootcamp status;
    private String planoTreinamento;
    private String conteudo;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = UsuarioEntity.class)
    @JoinTable(
            name = "professoresBootcamp",
            joinColumns = @JoinColumn(name = "bootcampId"),
            inverseJoinColumns = @JoinColumn(name = "usuarioId")
    )
    @NotFound(action = NotFoundAction.IGNORE)
    private List<UsuarioEntity> professores = new ArrayList<UsuarioEntity>();;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = UsuarioEntity.class)
    @JoinTable(
            name = "candidatosBootcamp",
            joinColumns = @JoinColumn(name = "bootcampId"),
            inverseJoinColumns = @JoinColumn(name = "usuarioId")
    )
    @NotFound(action = NotFoundAction.IGNORE)
    private List<UsuarioEntity> candidatos = new ArrayList<UsuarioEntity>();

    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private UsuarioEntity coach;

    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private EspacoEntity espaco;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;

    public BootcampEntity() {
    }

    public Long getBootcampId() {
        return bootcampId;
    }

    public void setBootcampId(Long id) {
        this.bootcampId = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public StatusBootcamp getStatus() {
        return status;
    }

    public void setStatus(StatusBootcamp status) {
        this.status = status;
    }

    public String getPlanoTreinamento() {
        return planoTreinamento;
    }

    public void setPlanoTreinamento(String planoTreinamento) {
        this.planoTreinamento = planoTreinamento;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public ArrayList<UsuarioEntity> getProfessores() {
        return new ArrayList<UsuarioEntity>(professores);
    }

    public void setProfessores(List<UsuarioEntity> professores) {
        this.professores = professores;
    }

    public ArrayList<UsuarioEntity> getCandidatos() {
        return new ArrayList<>(this.candidatos);
    }

    public void setCandidatos(List<UsuarioEntity> candidatos) {
        this.candidatos = candidatos;
    }

    public UsuarioEntity getCoach() {
        return coach;
    }

    public void setCoach(UsuarioEntity coach) {
        this.coach = coach;
    }

    public EspacoEntity getEspaco() {
        return espaco;
    }

    public void setEspaco(EspacoEntity espaco) {
        this.espaco = espaco;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
