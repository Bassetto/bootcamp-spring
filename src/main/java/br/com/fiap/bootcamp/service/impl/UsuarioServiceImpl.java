package br.com.fiap.bootcamp.service.impl;

import br.com.fiap.bootcamp.dto.UsuarioDto;
import br.com.fiap.bootcamp.entity.BootcampEntity;
import br.com.fiap.bootcamp.entity.UsuarioEntity;
import br.com.fiap.bootcamp.repository.UsuarioRepository;
import br.com.fiap.bootcamp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioDto> listUsuarios() {
        List<UsuarioEntity> entities = new ArrayList<UsuarioEntity>();
        usuarioRepository.findAll().forEach(entities::add);
        return usuarioToDto(entities);
    }

    @Override
    public UsuarioEntity save(UsuarioDto dto) {
        Optional<UsuarioEntity> opt = Optional.ofNullable(findByEmail(dto.getEmail()));
        if (opt.isEmpty()) {
            return usuarioRepository.save(dto.toEntity());
        }
        return opt.get();
    }

    @Override
    public List<UsuarioEntity> save(List<UsuarioDto> dtos) {
        return dtos.stream().map(this::save).collect(Collectors.toList());
    }

    @Override
    public UsuarioDto register(UsuarioDto dto) {
        Optional<UsuarioEntity> opt = Optional.ofNullable(save(dto));
        return opt.map(this::usuarioToDto).orElse(null);
    }

    @Override
    public List<UsuarioDto> register(List<UsuarioDto> dtos) {
        return dtos.stream().map(this::register).collect(Collectors.toList());
    }

    @Override
    public UsuarioEntity update(UsuarioDto dto) {
        Optional<UsuarioEntity> opt = Optional.ofNullable(findByEmail(dto.getEmail()));
        if (opt.isPresent()) {
            UsuarioEntity entity = opt.get();
            entity.setNome(dto.getNome());
            entity.setEmail(dto.getEmail());
            entity.setSenha(dto.getSenha().hashCode());
            entity.setTipo(dto.getTipo());
            return usuarioRepository.save(entity);
        }
        return null;
    }

    @Override
    public List<UsuarioEntity> update(List<UsuarioDto> dtos) {
        return dtos.stream().map(this::update).collect(Collectors.toList());
    }

    @Override
    public UsuarioEntity findById(Long id) {
        Optional<UsuarioEntity> opt = usuarioRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public String deleteById(Long id) {
        Optional<UsuarioEntity> opt = usuarioRepository.findById(id);
        if (opt.isPresent()) {
            return "Usuario deletado";
        }
        return "Usuário não encontrado";
    }

    @Override
    public UsuarioDto authenticate(UsuarioDto dto) {
        Optional<UsuarioEntity> opt = Optional.ofNullable(findByEmail(dto.getEmail()));
        if (opt.isPresent()) {
            if (opt.get().getSenha() == dto.getSenha().hashCode()) {
                return usuarioToDto(opt.get());
            }
        }
        return null;
    }

    @Override
    public UsuarioEntity findByEmail(String email) {
        try {
            Future<UsuarioEntity> entity = usuarioRepository.findByEmail(email);
            if (entity.isDone()) {
                return entity.get();
            } else {
                return null;
            }
        } catch (InterruptedException | ExecutionException ignored) {
//            ignored.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UsuarioEntity> findByEmail(List<String> emails) {
        return emails.stream().map(this::findByEmail).collect(Collectors.toList());
    }

    private UsuarioDto usuarioToDto(UsuarioEntity entity) {
        UsuarioDto dto = new UsuarioDto();
        dto.setId(entity.getUsuarioId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setSenha(null);
        dto.setTipo(entity.getTipo());
        return dto;
    }

    private List<UsuarioDto> usuarioToDto(List<UsuarioEntity> entities) {
        return entities.stream().map(this::usuarioToDto).collect(Collectors.toList());
    }
}
