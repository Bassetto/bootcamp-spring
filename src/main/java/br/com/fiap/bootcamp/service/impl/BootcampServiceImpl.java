package br.com.fiap.bootcamp.service.impl;

import br.com.fiap.bootcamp.dto.BootcampDto;
import br.com.fiap.bootcamp.dto.EspacoDto;
import br.com.fiap.bootcamp.dto.UsuarioDto;
import br.com.fiap.bootcamp.entity.*;
import br.com.fiap.bootcamp.repository.*;
import br.com.fiap.bootcamp.service.BootcampService;
import br.com.fiap.bootcamp.service.EspacoService;
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
public class BootcampServiceImpl implements BootcampService {

    @Autowired
    BootcampRepository bootcampRepository;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    EspacoService espacoService;

    @Override
    public List<BootcampDto> listBootcamps() {
        List<BootcampEntity> result = new ArrayList<>();
        bootcampRepository.findAll().forEach(result::add);
        return bootcampToDto(result);
    }

    @Override
    public BootcampDto saveBootcamp(BootcampDto dto) {
        Optional<BootcampEntity> opt = Optional.ofNullable(findByName(dto.getNome()));
        if (opt.isEmpty()) {
            BootcampEntity entity = dto.toEntity();
            entity.setCoach(usuarioService.save(dto.getCoach()));
            entity.setEspaco(espacoService.save(dto.getEspaco()));
            return bootcampToDto(bootcampRepository.save(entity));
        }
        return bootcampToDto(opt.get());
    }

    @Override
    public String addProfessores(Long bootcampId, List<UsuarioDto> professores) {
        List<Optional<UsuarioEntity>> professoresOpts = professores.stream().map(professor -> Optional.ofNullable(usuarioService.findByEmail(professor.getEmail()))).collect(Collectors.toList());
        Optional<BootcampEntity> bootcampOpt = bootcampRepository.findById(bootcampId);
        if (bootcampOpt.isPresent()) {
            BootcampEntity entity = bootcampOpt.get();
            List<UsuarioEntity> professoresEntities = entity.getProfessores();
            UsuarioEntity professor;
            for (Optional<UsuarioEntity> professoresOpt : professoresOpts) {
                if (professoresOpt.isPresent()) {
                    professor = professoresOpt.get();
                    if (!professoresEntities.contains(professor)) {
                        professoresEntities.add(professor);
                    }
                }
            }
            if (entity.getProfessores().equals(professoresEntities)) {
                return "Professores não encontrados";
            }
            entity.setProfessores(professoresEntities);
            bootcampRepository.save(entity);
            return "Professores adicionados";
        }
        return "Bootcamp não encontrado";
    }

    @Override
    public String removeProfessores(Long bootcampId, List<UsuarioDto> professores) {
        List<Optional<UsuarioEntity>> professoresOpts = professores.stream().map(professor -> Optional.ofNullable(usuarioService.findByEmail(professor.getEmail()))).collect(Collectors.toList());
        Optional<BootcampEntity> bootcampOpt = bootcampRepository.findById(bootcampId);
        if (bootcampOpt.isPresent()) {
            BootcampEntity entity = bootcampOpt.get();
            List<UsuarioEntity> professoresEntities = entity.getProfessores();
            professoresOpts.forEach(professor -> {
                professor.ifPresent(professoresEntities::remove);
            });
            entity.setProfessores(professoresEntities);
            bootcampRepository.save(entity);
            return "Professores removidos";
        }
        return "Bootcamp não encontrado";
    }

    @Override
    public String addCandidatos(Long bootcampId, List<UsuarioDto> candidatos) {
        assert (bootcampId != null);
        assert (candidatos != null);
        List<Optional<UsuarioEntity>> candidatosOpts = candidatos.stream().map(candidato -> Optional.ofNullable(usuarioService.findByEmail(candidato.getEmail()))).collect(Collectors.toList());
        Optional<BootcampEntity> bootcampOpt = bootcampRepository.findById(bootcampId);
        if (bootcampOpt.isPresent()) {
            BootcampEntity entity = bootcampOpt.get();
            List<UsuarioEntity> candidatosEntities = entity.getCandidatos();
            UsuarioEntity candidato;
            for (Optional<UsuarioEntity> candidatosOpt : candidatosOpts) {
                if (candidatosOpt.isPresent()) {
                    candidato = candidatosOpt.get();
                    if (!candidatosEntities.contains(candidato)) {
                        candidatosEntities.add(candidato);
                    }
                }
            }
            if (entity.getCandidatos().equals(candidatosEntities)) {
                return "Candidatos não encontrados";
            }
            entity.setCandidatos(candidatosEntities);
            bootcampRepository.save(entity);
            return "Candidatos adicionados";
        }
        return "Bootcamp não encontrado";
    }

    @Override
    public String removeCandidatos(Long bootcampId, List<UsuarioDto> candidatos) {
        List<Optional<UsuarioEntity>> candidatosOpts = candidatos.stream().map(candidato -> Optional.ofNullable(usuarioService.findByEmail(candidato.getEmail()))).collect(Collectors.toList());
        Optional<BootcampEntity> bootcampOpt = bootcampRepository.findById(bootcampId);
        if (bootcampOpt.isPresent()) {
            BootcampEntity entity = bootcampOpt.get();
            List<UsuarioEntity> candidatosEntities = entity.getCandidatos();
            candidatosOpts.forEach(candidato -> {
                candidato.ifPresent(candidatosEntities::remove);
            });
            entity.setCandidatos(candidatosEntities);
            bootcampRepository.save(entity);
            return "Candidatos removidos";
        }
        return "Bootcamp não encontrado";
    }

    @Override
    public String update(BootcampDto dto) {
        Optional<BootcampEntity> opt = bootcampRepository.findById(dto.getId());
        if (opt.isPresent()) {
            BootcampEntity entity = opt.get();
            entity.setNome(dto.getNome());
            entity.setArea(dto.getArea());
            entity.setStatus(dto.getStatus());
            entity.setPlanoTreinamento(dto.getPlanoTreinamento());
            entity.setConteudo(dto.getConteudo());
            entity.setData(dto.getData());
            entity.setCoach(usuarioService.update(dto.getCoach()));
            entity.setEspaco(espacoService.update(dto.getEspaco()));
            bootcampToDto(bootcampRepository.save(entity));
            return "Bootcamp editado com sucesso";
        }
        return "Bootcamp não encontrado";
    }

    @Override
    public BootcampDto findById(Long id) {
        Optional<BootcampEntity> opt = bootcampRepository.findById(id);
        return opt.map(this::bootcampToDto).orElse(null);
    }

    @Override
    public BootcampEntity findByName(String nome) {
        try {
            Future<BootcampEntity> entity = bootcampRepository.findByName(nome);
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
    public String deleteById(Long id) {
        Optional<BootcampEntity> opt = bootcampRepository.findById(id);
        if (opt.isPresent()) {
            bootcampRepository.deleteById(opt.get().getBootcampId());
            return "Bootcamp deletado";
        }
        return "Bootcamp não encontrado";
    }

    private BootcampDto bootcampToDto(BootcampEntity entity) {
        BootcampDto dto = new BootcampDto();
        dto.setId(entity.getBootcampId());
        dto.setNome(entity.getNome());
        dto.setArea(entity.getArea());
        dto.setStatus(entity.getStatus());
        dto.setPlanoTreinamento(entity.getPlanoTreinamento());
        dto.setConteudo(entity.getConteudo());
        dto.setData(entity.getData());
        dto.setProfessores(entity.getProfessores().stream().map(this::usuarioToDto).collect(Collectors.toList()));
        dto.setCandidatos(entity.getCandidatos().stream().map(this::usuarioToDto).collect(Collectors.toList()));
        dto.setCoach(usuarioToDto(entity.getCoach()));
        dto.setEspaco(espacoToDto(entity.getEspaco()));
        return dto;
    }

    private List<BootcampDto> bootcampToDto(List<BootcampEntity> entities) {
        return entities.stream().map(this::bootcampToDto).collect(Collectors.toList());
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
