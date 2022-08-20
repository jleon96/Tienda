package com.AdopcionMascotas.Controller;

import com.AdopcionMascotas.Entity.Alimentos;
import com.AdopcionMascotas.Service.IAlimentosService;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.hibernate.criterion.Projections.id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AlimentosController {

    @Autowired
    private IAlimentosService alimentosService;

    @GetMapping("/leerAlimentos")
    public String Index(Model model) {
        List<Alimentos> listaAlimentos = alimentosService.getAllAlimentos();
        model.addAttribute("titulo", "Lista de Alimentos");
        model.addAttribute("alimentos", listaAlimentos);
        return "leerAlimentos";
    }

    @GetMapping("/leerAlimentosU")
    public String leerAlimentosU(Model model) {
        List<Alimentos> listaAlimentos = alimentosService.getAllAlimentos();
        model.addAttribute("titulo", "Lista de Alimentos");
        model.addAttribute("alimentos", listaAlimentos);
        return "leerAlimentosU";
    }

    @GetMapping("agregarAlimentoN")
    public String AgregarAlimento(Model model) {
        model.addAttribute("alimentos", new Alimentos());
        return "agregarAlimento";
    }

    @PostMapping("/saveA")
    public String GuardarAlimentos(@ModelAttribute Alimentos A, @RequestParam(name = "file", required = false) MultipartFile imagen, RedirectAttributes flash) {
        if (!imagen.isEmpty()) {
            String ruta = "C://temp//fotos";
            try {
                byte[] bytes = imagen.getBytes();
                Path rutaAbsoluta = Paths.get(ruta + "//" + imagen.getOriginalFilename());
                Files.write(rutaAbsoluta, bytes);
                A.setImagen(imagen.getOriginalFilename());
            } catch (Exception e) {
            }
        }
        alimentosService.saveAlimentos(A);
//        flash.addFlashAttribute("Alimento agregado con exito!");
      flash.addFlashAttribute("success", "Alimento Creado con Exito! ");
        return "redirect:/leerAlimentos";
    }

    @GetMapping("/EditarAlimento/{id}")
    public String EditarAlimento(@PathVariable("id") Long id, Model model) {
        Alimentos A = alimentosService.getAlimentosById(id);
        model.addAttribute("alimentos", A);
        return "agregarAlimento";
    }

    @GetMapping("/EliminarAlimento/{id}")
    public String EliminarAlimento(Alimentos A) {
        alimentosService.EliminarAlimentos(A.getId());
        return "redirect:/leerAlimentos";
    }
}
