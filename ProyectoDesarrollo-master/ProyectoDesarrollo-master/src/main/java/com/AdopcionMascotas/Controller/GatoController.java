package com.AdopcionMascotas.Controller;

import com.AdopcionMascotas.Entity.Gato;
import com.AdopcionMascotas.Service.IGatoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GatoController {

    @Autowired
    private IGatoService gatoService;

    @GetMapping("/leergatos")
    public String Index(Model model) {
        List<Gato> listaGato = gatoService.getAllGato();
        model.addAttribute("titulo", "Lista de Gatos");
        model.addAttribute("gatos", listaGato);
        return "leergatos";
    }

    @GetMapping("creargatoN")
    public String CrearGato(Model model) {
        model.addAttribute("gatos", new Gato());
        return "creargato";
    }

    @PostMapping("/saveG")
    public String GuardarGato(@ModelAttribute Gato G) {
        gatoService.saveGato(G);
        return "redirect:/leergatos";
    }
    @GetMapping("/EditarGato/{ID}")
    public String EditarGato(@PathVariable("ID") Long IDGato, Model model) {
        Gato G = gatoService.getGatoById(IDGato);
        model.addAttribute("gatos", G);
        return "/creargato";
    }

    @GetMapping("/EliminarGato/{ID}")
    public String EliminarGato(Gato G) {
        gatoService.EliminarGato(G.getID());
        return "redirect:/leergatos";
    }
}
