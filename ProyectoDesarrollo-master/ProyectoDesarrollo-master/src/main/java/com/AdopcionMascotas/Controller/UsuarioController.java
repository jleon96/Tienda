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

    @GetMapping("/leerusuarios")
    public String Index(Model model) {
        List<Usuario> listaUsuario = usuarioService.getAllUsuario();
        model.addAttribute("titulo", "Lista de Usuarios");
        model.addAttribute("usuario", listaUsuario);
        return "leerusuarios";
    }

    @GetMapping("/nuevoUsuario")
    public String CrearUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "nuevoUsuario";
    }
//

    @PostMapping("/saveU")
    public String GuardarUsuario(@ModelAttribute Usuario U) {
        usuarioService.saveUsuario(U);
        return "redirect:/login";
    }

    @GetMapping("/EditarUsuario/{ID}")
    public String EditarUsuario(@PathVariable("ID") Long IDUsuario, Model model) {
        Usuario U = usuarioService.getUsuarioById(IDUsuario);
        model.addAttribute("usuario", U);
        return "/crearusuario";
    }

    @GetMapping("/EliminarUsuario/{ID}")
    public String EliminarUsuario(Usuario U) {
        usuarioService.EliminarUsuario(U.getID());
        return "redirect:/leerusuarios";
    }
}
