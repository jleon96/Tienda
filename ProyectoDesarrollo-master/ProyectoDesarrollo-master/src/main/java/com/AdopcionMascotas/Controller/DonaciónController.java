package com.AdopcionMascotas.Controller;


import com.AdopcionMascotas.Entity.Donación;
import com.AdopcionMascotas.Service.IDonaciónService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DonaciónController {

    @Autowired
    private IDonaciónService donaciónService;


    @GetMapping("/Consulta")
    public String Index(Model model) {
        List<Donación> listaDonación = donaciónService.getAllDonacion();
        model.addAttribute("titulo", "Lista de donaciones");
        model.addAttribute("donación", listaDonación);
        return "Consulta";
    }

    @GetMapping("/donaciones")
    public String AgregarDonacion(Model model) {
        model.addAttribute("Donación", new Donación());
        return "donaciones";
    }

    @PostMapping("/save")
    public String GuardarDonacion(@ModelAttribute Donación donación) {
        donaciónService.saveDonacion(donación);
        return "redirect:/donaciones";
    }

//    @GetMapping("/editVerdura/{id}")
//    public String EditarVerdura(@PathVariable("id") Long idVerdura, Model model) {
//        Verdura verdura = verduraService.getVerduraById(idVerdura);
//        List<Local> listaLocal = localService.listCountry();
//        model.addAttribute("Verdura", verdura);
//        model.addAttribute("local", listaLocal);
//        return "Agregar";
//    }
//    
//      @GetMapping("/delete/{id}")
//    public String EliminarVerdura(Verdura verdura) {
//        verduraService.delete(verdura.getId());
//        return "redirect:/Consulta";
//    }
}
