package br.com.fiap.bootcamp.controller;

import br.com.fiap.bootcamp.dto.BootcampDto;
import br.com.fiap.bootcamp.dto.EspacoDto;
import br.com.fiap.bootcamp.dto.UsuarioDto;
import br.com.fiap.bootcamp.entity.*;
import br.com.fiap.bootcamp.service.BootcampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value  = "/bootcamp")
public class BootcampController {

    @Autowired
    BootcampService bootcampService;

    @GetMapping(value  = "/listar")
    public ModelAndView listar() {
        ModelAndView view = new ModelAndView("lista-bootcamps");
        ArrayList<BootcampDto> bootcamps = new ArrayList<BootcampDto>(bootcampService.listBootcamps());
        view.addObject("bootcamps", bootcamps);
        view.addObject("addProfessor", new UsuarioDto());
        view.addObject("addCandidato", new UsuarioDto());
        view.addObject("removeProfessor", new UsuarioDto());
        view.addObject("removeCandidato", new UsuarioDto());
        return view;
    }

    @GetMapping(value  = "/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView view = new ModelAndView("cadastrar-bootcamp");
        BootcampDto bootcamp = new BootcampDto();
        bootcamp.setProfessores(null);
        bootcamp.setCandidatos(null);
        bootcamp.setCoach(new UsuarioDto());
        bootcamp.setEspaco(new EspacoDto());
        view.addObject("bootcamp", bootcamp);
        return view;
    }

    @PostMapping(value  = "/salvar")
    public String salvar(@ModelAttribute("bootcamp") BootcampDto bootcamp, BindingResult result) {
        if (!result.hasErrors()) {

            UsuarioDto coach = bootcamp.getCoach();
            coach.setTipo(TipoUsuario.COACH);
            bootcamp.setCoach(coach);

            bootcampService.saveBootcamp(bootcamp);
            return "redirect:/bootcamp/listar";
        }
        return "redirect:/error";
    }

    @PostMapping(value  = "/update")
    public String update(@ModelAttribute("bootcamp") BootcampDto bootcamp, BindingResult result) {
        if (!result.hasErrors()) {
            System.out.println(bootcamp.getId());
            bootcampService.update(bootcamp);
        }
        return "redirect:/bootcamp/listar";
    }

    @GetMapping(value  = "/editar/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        if (id >= 0) {
            ModelAndView view = new ModelAndView("editar-bootcamp");
            BootcampDto bootcamp = bootcampService.findById(id);
            view.addObject("bootcamp", bootcamp);
            return view;
        }
        return listar();
    }

    @GetMapping(value  = "/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        bootcampService.deleteById(id);
        return "redirect:/bootcamp/listar";
    }

    @PostMapping(value  = "/professor/add")
    public String addProfessor(@ModelAttribute("addProfessor") UsuarioDto professor) {
        bootcampService.addProfessores(professor.getId(), List.of(professor));
        return "redirect:/bootcamp/listar";
    }

    @PostMapping(value = "/professor/remove")
    public String removeProfessor(@ModelAttribute("removeProfessor") UsuarioDto professor) {
        bootcampService.removeProfessores(professor.getId(), List.of(professor));
        return "redirect:/bootcamp/listar";
    }

    @PostMapping(value = "/candidato/add")
    public String addCandidato(@ModelAttribute("addCandidato") UsuarioDto candidato) {
        System.out.println("Id: " + candidato.getId() + " Email: " + candidato.getEmail());
        bootcampService.addCandidatos(candidato.getId(), List.of(candidato));
        return "redirect:/bootcamp/listar";
    }

    @PostMapping(value = "/candidato/remove")
    public String removeCandidato(@ModelAttribute("removeCandidato") UsuarioDto candidato) {
        bootcampService.removeCandidatos(candidato.getId(), List.of(candidato));
        return "redirect:/bootcamp/listar";
    }

    @GetMapping(value  = "/error")
    public ModelAndView error() {
        return new ModelAndView("error");
    }
}
