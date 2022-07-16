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

    @GetMapping("leerperros")
    public String Index(Model model) {
        List<Perro> listaPerro = perroService.getAllPerro();
        model.addAttribute("titulo", "Lista de Perros");
        model.addAttribute("perros", listaPerro);
        return "leerperros";
    }

    @GetMapping("perrosN")
    public String CrearPerro(Model model) {
        model.addAttribute("perros", new Perro());
        return "leerperros";
    }

//    @PostMapping("/save")
//    public String GuardarPerro(@ModelAttribute Perro P) {
//        perroService.savePerro(P);
//        return "redirect:/index.html";
//    }

    @GetMapping("/EditarPerro/{idPerro}")
    public String EditarPerro(@PathVariable("idPerro") Long idPerro, Model model) {
        Perro P = perroService.getPerroById(idPerro);
        model.addAttribute("perros", P);
        return "leerperros";
    }
//
//    @GetMapping("/delete/{idPerro}")
//    public String EliminarPerro(Perro P) {
//        perroService.delete(P.getIdPerro());
//        return "redirect:/index";
//    }
}
