package com.AdopcionMascotas.Controller;

import com.AdopcionMascotas.Entity.Usuario;
import com.AdopcionMascotas.Service.IUsuarioService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "redirect:/login";
    }

    /*Metodo para editar un usuario*/
    @GetMapping("/EditarUsuario/{ID}")
    public String EditarUsuario(@PathVariable("ID") Long ID, Model model) {
        Usuario U = usuarioService.getUsuarioById(ID);
        model.addAttribute("usuario", U);
        return "nuevoUsuario";
    }

    @RequestMapping("/ConsultarUsuario/{ID}")
    public String ConsultarUsuario(@PathVariable("ID") Long ID, Model model) {
        Usuario U = usuarioService.getUsuarioById(ID);
        model.addAttribute("usuario", U);
        return "perfil";
    }
    @GetMapping("/perfil")
    public String Consultar(Model model, @PathVariable("ID") Long id) {
       Usuario U = usuarioService.getUsuarioById(id);
        model.addAttribute("usuario", U);
        return "perfil";
    }

    /*Metodo para eliminar un usuario*/
    @GetMapping("/EliminarUsuario/{ID}")
    public String EliminarUsuario(@PathVariable("ID") Long ID) {
        usuarioService.EliminarUsuario(ID);
        return "redirect:/leerusuarios";
    }

//    @Controller
//    public class ScopesController {
//
//        @Resource(name = "usuario")
//        Usuario sessionScopedBean;
//
//        @RequestMapping("/EditarUsuario/session")
//        public String getSessionScopeMessage(final Model model, @PathVariable("ID") Long ID) {
//            model.addAttribute("usuario", sessionScopedBean.getNombre());
//            usuarioService.getUsuarioById(ID);
//            model.addAttribute("usuario", sessionScopedBean.getPassword());
//            return "scopesExample";
//        }
//    }
}
