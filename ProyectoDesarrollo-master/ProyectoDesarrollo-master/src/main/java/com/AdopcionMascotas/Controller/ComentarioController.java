package com.AdopcionMascotas.Controller;

import com.AdopcionMascotas.Entity.Comentario;
import com.AdopcionMascotas.Service.IComentarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ComentarioController {

    @Autowired
    private IComentarioService comentarioService;

    @GetMapping("/leercoment")
    public String Index(Model model) {
        List<Comentario> listaComentario = comentarioService.getAllComent();
        model.addAttribute("titulo", "Lista de Comentarios");
        model.addAttribute("comentario", listaComentario);
        return "leercoment";
    }

    @GetMapping("crearComentN")
    public String CrearComent(Model model) {
        model.addAttribute("comentario", new Comentario());
        return "leercoment";
    }

    @PostMapping("/saveC")
    public String GuardarComent(@ModelAttribute Comentario C) {
        comentarioService.saveComent(C);
        return "redirect:/leercoment";
    }

    @GetMapping("/EliminarComent/{ID}")
    public String EliminarComent(Comentario C) {
        comentarioService.EliminarComent(C.getId());
        return "redirect:/leercoment";
    }
}
