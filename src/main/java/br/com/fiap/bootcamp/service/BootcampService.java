package br.com.fiap.bootcamp.service;

import br.com.fiap.bootcamp.dto.BootcampDto;
import br.com.fiap.bootcamp.dto.UsuarioDto;
import br.com.fiap.bootcamp.entity.BootcampEntity;

import java.util.List;

public interface BootcampService {

    List<BootcampDto> listBootcamps();

    BootcampDto saveBootcamp(BootcampDto dto);

    String addProfessores(Long bootcampId, List<UsuarioDto> professores);

    String removeProfessores(Long bootcampId, List<UsuarioDto> professores);

    String addCandidatos(Long bootcampId, List<UsuarioDto> candidatos);

    String removeCandidatos(Long bootcampId, List<UsuarioDto> candidatos);

    String update(BootcampDto dto);

    BootcampEntity findById(Long id);

    BootcampEntity findByName(String nome);

    String deleteById(Long id);
}
