package com.AdopcionMascotas.Controller;

import com.AdopcionMascotas.Entity.Perro;
import com.AdopcionMascotas.Service.IPerroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PerroController {

    @Autowired
    private IPerroService perroService;

    @GetMapping("/leerperros")
    public String Index(Model model) {
        List<Perro> listaPerro = perroService.getAllPerro();
        model.addAttribute("titulo", "Lista de Perros");
        model.addAttribute("perros", listaPerro);
        return "leerperros";
    }

    @GetMapping("crearperroN")
    public String CrearPerro(Model model) {
        model.addAttribute("perros", new Perro());
        return "crearperro";
    }

    @PostMapping("/saveP")
    public String GuardarPerro(@ModelAttribute Perro P) {
        perroService.savePerro(P);
        return "redirect:/leerperros";
    }

    @GetMapping("/EditarPerro/{ID}")
    public String EditarPerro(@PathVariable("ID") Long IDPerro, Model model) {
        Perro P = perroService.getPerroById(IDPerro);
        model.addAttribute("perros", P);
        return "crearperro";
    }

    @GetMapping("/EliminarPerro/{ID}")
    public String EliminarPerro(Perro P) {
        perroService.EliminarPerro(P.getID());
        return "redirect:/leerperros";
    }
}
