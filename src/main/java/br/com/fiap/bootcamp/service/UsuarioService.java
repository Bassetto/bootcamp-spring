package br.com.fiap.bootcamp.service;

import br.com.fiap.bootcamp.entity.UsuarioEntity;
import br.com.fiap.bootcamp.dto.UsuarioDto;

import java.util.List;

public interface UsuarioService {

    List<UsuarioDto> listUsuarios();

    UsuarioEntity save(UsuarioDto dto);

    List<UsuarioEntity> save(List<UsuarioDto> dtos);

    UsuarioDto register(UsuarioDto dto);

    List<UsuarioDto> register(List<UsuarioDto> dtos);

    UsuarioEntity update(UsuarioDto dto);

    List<UsuarioEntity> update(List<UsuarioDto> dtos);

    UsuarioEntity findById(Long id);

    UsuarioEntity findByEmail(String email);

    List<UsuarioEntity> findByEmail(List<String> emails);

    UsuarioDto authenticate(UsuarioDto dto);

    String deleteById(Long id);
}
