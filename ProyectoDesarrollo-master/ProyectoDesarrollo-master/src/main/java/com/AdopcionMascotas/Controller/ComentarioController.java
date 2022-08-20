package com.AdopcionMascotas.Controller;

import com.AdopcionMascotas.Entity.Comentario;
import com.AdopcionMascotas.Service.ComentarioReportService;
import com.AdopcionMascotas.Service.IComentarioService;
import com.AdopcionMascotas.Service.PerroReportService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ComentarioController {

    @Autowired
    private IComentarioService ICS;

    @GetMapping("/leercomentarios")
    public String Index(Model model) {
        List<Comentario> listaComentario = ICS.getAllComentario();
        model.addAttribute("titulo", "Lista de Comentarios");
        model.addAttribute("comentario", listaComentario);
        model.addAttribute("comentarioN", new Comentario());
        return "leercomentarios";
    }

    @GetMapping("crearcomentarioN")
    public String CrearComentario(Model model) {
        model.addAttribute("comentarioN", new Comentario());
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
    
        @Autowired
    private ComentarioReportService PReportService;

    @GetMapping(path = "/leerusuarios/Comentarios", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody
    byte[] getFile() throws IOException {
        try {
            FileInputStream fis = new FileInputStream(new File(PReportService.generateReport()));
            byte[] targetArray = new byte[fis.available()];
            fis.read(targetArray);
            return targetArray;
        } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
