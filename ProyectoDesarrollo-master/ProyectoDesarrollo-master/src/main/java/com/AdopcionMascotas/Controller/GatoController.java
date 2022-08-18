package com.AdopcionMascotas.Controller;

import com.AdopcionMascotas.Entity.Gato;
import com.AdopcionMascotas.Service.IGatoService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String GuardarGato(@ModelAttribute Gato G, @RequestParam(name = "file", required = false) MultipartFile imagen, RedirectAttributes flash) {
        if (!imagen.isEmpty()) {
            String ruta = "C://temp//fotos";
            try {
                byte[] bytes = imagen.getBytes();
                Path rutaAbsoluta = Paths.get(ruta + "//" + imagen.getOriginalFilename());
                Files.write(rutaAbsoluta, bytes);
                G.setImagen(imagen.getOriginalFilename());
            } catch (Exception e) {
            }
        }
        gatoService.saveGato(G);
//        flash.addFlashAttribute("Gato creado con exito!");
        return "redirect:/leergatos";
    }

    @GetMapping("/EditarGato/{ID}")
    public String EditarGato(@PathVariable("ID") Long IDGato, Model model) {
        Gato G = gatoService.getGatoById(IDGato);
        model.addAttribute("gatos", G);
        return "creargato";
    }

    @GetMapping("/EliminarGato/{ID}")
    public String EliminarGato(Gato G) {
        gatoService.EliminarGato(G.getID());
        return "redirect:/leergatos";
    }
}
