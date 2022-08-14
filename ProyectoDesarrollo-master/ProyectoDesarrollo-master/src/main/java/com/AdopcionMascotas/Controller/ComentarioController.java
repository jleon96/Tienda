package com.AdopcionMascotas.Controller;

import com.AdopcionMascotas.Entity.Comentario;
import com.AdopcionMascotas.Service.IComentarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ComentarioController {

    @Autowired
    private IComentarioService ICS;

    @GetMapping("/leercomentarios")
    public String Index(Model model) {
        List<Comentario> listaComentario = ICS.getAllComentario();
        model.addAttribute("titulo", "Lista de Comentarios");
        model.addAttribute("comentario", listaComentario);
         model.addAttribute("comentario2", new Comentario());
        return "leercomentarios";
    }

    @GetMapping("crearcomentarioN")
    public String CrearComentario(Model model) {
        model.addAttribute("comentario", new Comentario());
        return "leercomentarios";
    }

    @PostMapping("/saveC")
    public String GuardarComentario(@ModelAttribute Comentario C) {
        ICS.saveComentario(C);
        return "redirect:/leercomentarios";
    }

    @GetMapping("/EliminarComentario/{ID}")
    public String EliminarComentario(Comentario C) {
        ICS.EliminarComentario(C.getID());
        return "redirect:/leercomentarios";
    }

}
