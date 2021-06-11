package br.com.fiap.bootcamp.dto;

import br.com.fiap.bootcamp.entity.BootcampEntity;
import br.com.fiap.bootcamp.entity.StatusBootcamp;
import br.com.fiap.bootcamp.entity.UsuarioEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BootcampDto {
//    enum status {
//        PLANEJAMENTO(0),
//        ANDAMENTO(1),
//        VALIDACAO(2),
//        RECRUTAMENTO(3);
//
//        int value;
//
//        status(int value) {
//            this.value = value;
//        }
//
//        public void setValue(int value) {
//            this.value = value;
//        }
//
//        public int getValue() {
//            return this.value;
//        }
//    }

    Long Id;
    String nome;
    String area;
    StatusBootcamp status;
    String planoTreinamento;
    String conteudo;
    EspacoDto espaco;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date data;
    UsuarioDto coach;
    List<UsuarioDto> professores = new ArrayList<UsuarioDto>();;
    List<UsuarioDto> candidatos = new ArrayList<UsuarioDto>();

    public BootcampDto() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public UsuarioDto getCoach() {
        return coach;
    }

    public void setCoach(UsuarioDto coach) {
        this.coach = coach;
    }

    public EspacoDto getEspaco() {
        return espaco;
    }

    public void setEspaco(EspacoDto espaco) {
        this.espaco = espaco;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<UsuarioDto> getProfessores() {
        return professores;
    }

    public void setProfessores(List<UsuarioDto> professores) {
        this.professores = professores;
    }

    public List<UsuarioDto> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<UsuarioDto> candidatos) {
        this.candidatos = candidatos;
    }

    public BootcampEntity toEntity() {
        BootcampEntity entity = new BootcampEntity();
        entity.setNome(this.nome);
        entity.setArea(this.area);
        entity.setStatus(this.status);
        entity.setPlanoTreinamento(this.planoTreinamento);
        entity.setConteudo(this.conteudo);
        if (this.professores != null) {
            entity.setProfessores(this.professores.stream().map(UsuarioDto::toEntity).collect(Collectors.toList()));
        } else {
            entity.setProfessores(new ArrayList<UsuarioEntity>());
        }
        if (this.candidatos != null) {
            entity.setCandidatos(this.candidatos.stream().map(UsuarioDto::toEntity).collect(Collectors.toList()));
        } else {
            entity.setCandidatos(new ArrayList<UsuarioEntity>());
        }
        entity.setCoach(this.coach.toEntity());
        entity.setEspaco(this.espaco.toEntity());
        entity.setData(this.data);
        return entity;
    }
}
