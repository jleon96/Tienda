/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AdopcionMascotas.Controller;

import com.AdopcionMascotas.Entity.Contacto;
import com.AdopcionMascotas.Entity.Usuario;
import com.AdopcionMascotas.Service.IContactoService;
import com.AdopcionMascotas.Service.IUsuarioService;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author lyonc
 */
@Controller
public class ContactoController {



    @Autowired
    private IContactoService contactoService;

    @GetMapping("/contactoN")
    public String crearContacto(Model model) {
        model.addAttribute("contactoform", new Contacto());
        return "contacto";
    }

    @PostMapping("/saveContact")
    public String guardarContacto(@ModelAttribute Contacto C, RedirectAttributes flash) {
        contactoService.saveContacto(C);
          flash.addFlashAttribute("success","Hola tu mensaje ha sido enviado, en breve nos pondremos en contacto!");
        return "redirect:/contactoN";
    }

}
