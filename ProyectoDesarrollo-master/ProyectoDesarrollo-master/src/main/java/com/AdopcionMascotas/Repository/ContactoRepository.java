/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AdopcionMascotas.Repository;

import com.AdopcionMascotas.Entity.Contacto;
import com.AdopcionMascotas.Entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lyonc
 */
@Repository
public interface ContactoRepository extends CrudRepository<Contacto, Long> {
    Contacto findByNombre (String nombre);
    
}
