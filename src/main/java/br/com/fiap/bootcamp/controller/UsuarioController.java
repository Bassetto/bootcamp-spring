package br.com.fiap.bootcamp.controller;

import br.com.fiap.bootcamp.dto.UsuarioDto;
import br.com.fiap.bootcamp.entity.TipoUsuario;
import br.com.fiap.bootcamp.entity.UsuarioEntity;
import br.com.fiap.bootcamp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView view = new ModelAndView("login");
        UsuarioDto usuario = new UsuarioDto();
        usuario.setNome("");
        usuario.setTipo(TipoUsuario.CANDIDATO);
        usuario.setId(0L);
        view.addObject("usuario", usuario);
        return view;
    }

    @PostMapping(value = "/authenticate")
    public String authenticate(@ModelAttribute("usuario") UsuarioDto usuario, BindingResult result) {
        if (!result.hasErrors()) {
            if (usuarioService.authenticate(usuario) != null) {
//                ModelAndView view = new ModelAndView("painel");
//                view.addObject("usuario", entity);
//                return view;
                return "redirect:/";
            }
        }
        return "redirect:/login";
    }

    @GetMapping(value = "/cadastro")
    public ModelAndView cadastro() {
        ModelAndView view = new ModelAndView("cadastro");
        view.addObject("usuario", new UsuarioDto());
        return view;
    }

    @PostMapping(value = "/registrar")
    public String registrar(@ModelAttribute("usuario") UsuarioDto usuario, BindingResult result) {
        if (!result.hasErrors()) {
//                ModelAndView view = new ModelAndView("painel");
//                view.addObject("usuario", entity);
//                return view;
//            UsuarioEntity entity =
            usuarioService.register(usuario);
            return "redirect:/";
        }
        return "redirect:/login";
    }
}
