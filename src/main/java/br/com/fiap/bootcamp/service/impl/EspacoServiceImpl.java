package br.com.fiap.bootcamp.service.impl;

import br.com.fiap.bootcamp.dto.EspacoDto;
import br.com.fiap.bootcamp.dto.UsuarioDto;
import br.com.fiap.bootcamp.entity.EspacoEntity;
import br.com.fiap.bootcamp.repository.EspacoRepository;
import br.com.fiap.bootcamp.service.EspacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class EspacoServiceImpl implements EspacoService {

    @Autowired
    EspacoRepository espacoRepository;

    @Override
    public EspacoEntity save(EspacoDto dto) {
        Optional<EspacoEntity> opt = Optional.ofNullable(findByCep(dto.getCep()));
        if (opt.isEmpty()) {
            return espacoRepository.save(dto.toEntity());
        }
        return opt.get();
    }

    @Override
    public EspacoEntity update(EspacoDto dto) {
        Optional<EspacoEntity> opt = Optional.ofNullable(findByCep(dto.getCep()));
        if (opt.isPresent()) {
            EspacoEntity entity = opt.get();
            entity.setLogradouro(dto.getLogradouro());
            entity.setNumero(dto.getNumero());
            entity.setCep(dto.getCep());
            entity.setCapacidadePessoas(dto.getCapacidadePessoas());
            return espacoRepository.save(entity);
        }
        return null;
    }

    @Override
    public EspacoEntity findById(Long id) {
        Optional<EspacoEntity> opt = espacoRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public EspacoEntity findByCep(int cep) {
        try {
            Future<EspacoEntity> entity = espacoRepository.findByCep(cep);
            if (entity.isDone()) {
                return entity.get();
            } else {
                return null;
            }
        } catch (InterruptedException | ExecutionException ignored) {
            ignored.printStackTrace();
            return null;
        }
    }

    @Override
    public String deleteById(Long id) {
        Optional<EspacoEntity> opt = espacoRepository.findById(id);
        if (opt.isPresent()) {
            espacoRepository.delete(opt.get());
            return "Bootcamp deletado";
        }
        return "Bootcamp não encontrado";
    }

    private EspacoDto espacoToDto(EspacoEntity entity) {
        EspacoDto dto = new EspacoDto();
        dto.setId(entity.getEspacoId());
        dto.setLogradouro(entity.getLogradouro());
        dto.setNumero(entity.getNumero());
        dto.setCep(entity.getCep());
        dto.setCapacidadePessoas(entity.getCapacidadePessoas());
        return dto;
    }
}
