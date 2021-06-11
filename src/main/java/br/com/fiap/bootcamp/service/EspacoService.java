package br.com.fiap.bootcamp.service;

import br.com.fiap.bootcamp.dto.EspacoDto;
import br.com.fiap.bootcamp.entity.EspacoEntity;
import org.springframework.stereotype.Service;

@Service
public interface EspacoService {

    EspacoEntity save(EspacoDto dto);

    EspacoEntity update(EspacoDto dto);

    EspacoEntity findById(Long id);

    EspacoEntity findByCep(int id);

    String deleteById(Long id);
}
