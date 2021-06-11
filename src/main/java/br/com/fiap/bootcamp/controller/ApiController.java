package br.com.fiap.bootcamp.controller;

import br.com.fiap.bootcamp.dto.BootcampDto;
import br.com.fiap.bootcamp.dto.UsuarioDto;
import br.com.fiap.bootcamp.service.BootcampService;
import br.com.fiap.bootcamp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    BootcampService bootcampService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping(value = "/bootcamp/list")
    public List<BootcampDto> listBootcamps() {
        return bootcampService.listBootcamps();
    }

    @PostMapping(value = "/bootcamp/create")
    public BootcampDto createBootcamp(@RequestBody BootcampDto bootcamp) {
        return bootcampService.saveBootcamp(bootcamp);
    }

    @PutMapping(value = "/bootcamp/update")
    public String updateBootcamp(@RequestBody BootcampDto bootcamp) {
        return bootcampService.update(bootcamp);
    }

    @PostMapping(value = "bootcamp/professores/add")
    public String addProfessores(@RequestParam Long bootcampId, @RequestBody List<UsuarioDto> professores) {
        return bootcampService.addProfessores(bootcampId, professores);
    }

    @PostMapping(value = "bootcamp/professores/remove")
    public String removeProfessores(@RequestParam Long bootcampId, @RequestBody List<UsuarioDto> professores) {
        return bootcampService.removeProfessores(bootcampId, professores);
    }

    @PostMapping(value = "bootcamp/candidatos/add")
    public String addCandidatos(@RequestParam Long bootcampId, @RequestBody List<UsuarioDto> candidatos) {
        return bootcampService.addCandidatos(bootcampId, candidatos);
    }

    @PostMapping(value = "bootcamp/candidatos/remove")
    public String removeCandidatos(@RequestParam Long bootcampId, @RequestBody List<UsuarioDto> candidatos) {
        return bootcampService.removeCandidatos(bootcampId, candidatos);
    }

    @DeleteMapping(value = "/bootcamp/delete")
    public String deleteBootcamp(@RequestParam Long id) {
        return bootcampService.deleteById(id);
    }

    @GetMapping(value = "usuario/list")
    public List<UsuarioDto> listUsuarios() {
        return usuarioService.listUsuarios();
    }

    @PutMapping(value = "/usuario/update")
    public String updateUsuario(@RequestBody UsuarioDto usuario) {
        return (usuarioService.update(usuario) != null) ? "Usuario alterado" : "Usuário não encontrado";
    }

    @DeleteMapping(value = "/usuario/delete")
    public String deleteUsuario(@RequestParam(name = "id") Long id) {
        return usuarioService.deleteById(id);
    }

    @PostMapping(value = "/login")
    public UsuarioDto login(@RequestBody UsuarioDto usuario) {
        return usuarioService.authenticate(usuario);
    }

    @PostMapping(value = "/register")
    public UsuarioDto register(@RequestBody UsuarioDto usuario) {
        return usuarioService.register(usuario);
    }
}
