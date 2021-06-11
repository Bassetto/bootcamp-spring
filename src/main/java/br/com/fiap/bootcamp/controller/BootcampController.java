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

@Controller
@RequestMapping("/bootcamp")
public class BootcampController {

    @Autowired
    BootcampService bootcampService;

    @GetMapping("/listar")
    public ModelAndView listar() {
        ModelAndView view = new ModelAndView("lista-bootcamps");
        ArrayList<BootcampDto> bootcamps = new ArrayList<BootcampDto>(bootcampService.listBootcamps());
        view.addObject("bootcamps", bootcamps);
        return view;
    }

    @GetMapping("/cadastrar")
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

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("bootcamp") BootcampDto bootcamp, BindingResult result) {
        if (!result.hasErrors()) {

            System.out.println(bootcamp.getCoach().getTipo());
            System.out.println(bootcamp.getStatus());
            System.out.println(bootcamp.getProfessores().size());
            System.out.println(bootcamp.getCandidatos().size());

            UsuarioDto coach = bootcamp.getCoach();
            coach.setTipo(TipoUsuario.COACH);
            bootcamp.setCoach(coach);

            bootcampService.saveBootcamp(bootcamp);
            return "redirect:/bootcamp/listar";
        }
        return "redirect:/error";
    }

    @PostMapping("/update/")
    public String update(@ModelAttribute("bootcamp") BootcampDto bootcamp, BindingResult result) {
        if (!result.hasErrors()) {
            System.out.println(bootcamp.getId());
            bootcampService.update(bootcamp);
        }
        return "redirect:/bootcamp/listar";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        if (id >= 0) {
            ModelAndView view = new ModelAndView("editar-bootcamp");
            BootcampEntity bootcamp = bootcampService.findById(id);
            view.addObject("bootcamp", bootcamp);
            return view;
        }
        return listar();
    }

    @DeleteMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        bootcampService.deleteById(id);
        return "redirect:/bootcamp/listar";
    }

    @GetMapping("/error")
    public ModelAndView error() {
        return new ModelAndView("error");
    }
}
