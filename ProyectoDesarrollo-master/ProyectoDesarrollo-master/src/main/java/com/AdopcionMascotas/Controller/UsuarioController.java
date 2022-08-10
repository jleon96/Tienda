package com.AdopcionMascotas.Controller;

import com.AdopcionMascotas.Entity.Usuario;
import com.AdopcionMascotas.Service.IUsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    /*Metodo para leer los usuario*/
    @GetMapping("/leerusuarios")
    public String Index(Model model) {
        List<Usuario> listaUsuario = usuarioService.getAllUsuario();
        model.addAttribute("titulo", "Lista de Usuarios");
        model.addAttribute("usuario", listaUsuario);
        return "leerusuarios";
    }

    /*Metodo para crear una persona*/
    @GetMapping("/usuarioN")
    public String crearUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "nuevoUsuario";
    }

    @PostMapping("/saveU")
    public String guardarUsuario(@ModelAttribute Usuario U) {
        U.setActive(1);
        U.setPermisos("USER");
        U.setRoles("USER");
        usuarioService.saveUsuario(U);
        return "redirect:/leerusuarios";
    }

    /*Metodo para editar un usuario*/
    @GetMapping("/EditarUsuario/{ID}")
    public String EditarUsuario(@PathVariable("ID") Long IDUsuario, Model model) {
        Usuario U = usuarioService.getUsuarioById(IDUsuario);
        model.addAttribute("usuario", U);
        return "nuevoUsuario";
    }

    /*Metodo para eliminar un usuario*/
    @GetMapping("/EliminarUsuario/{ID}")
    public String EliminarUsuario(Usuario U) {
        usuarioService.EliminarUsuario(U.getId());
        return "redirect:/leerusuarios";
    }
}
