package com.AdopcionMascotas.Controller;

import com.AdopcionMascotas.Entity.Usuario;
import com.AdopcionMascotas.Service.IUsuarioService;
import com.AdopcionMascotas.Service.PersonaReportService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String guardarUsuario(@ModelAttribute Usuario U, @RequestParam(name = "file", required = false) MultipartFile imagen, RedirectAttributes flash) {
        U.setActive(1);
        U.setPermisos("USER");
        U.setRoles("USER");

        if (!imagen.isEmpty()) {
            String ruta = "C://temp//fotos";

            try {
                byte[] bytes = imagen.getBytes();
                Path rutaAbsoluta = Paths.get(ruta + "//" + imagen.getOriginalFilename());
                Files.write(rutaAbsoluta, bytes);
                U.setImagen(imagen.getOriginalFilename());

            } catch (Exception e) {

            }

        }

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

    /*Metodo para eliminar un usuario*/
    @GetMapping("/EliminarUsuario/{ID}")
    public String EliminarUsuario(@PathVariable("ID") Long ID) {
        usuarioService.EliminarUsuario(ID);
        return "redirect:/leerusuarios";
    }

    @Autowired
    private PersonaReportService PReportService;

    @GetMapping(path = "/leerusuarios/Usuarios", produces = MediaType.APPLICATION_PDF_VALUE)
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
