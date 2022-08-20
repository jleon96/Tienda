/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AdopcionMascotas.Service;

import com.AdopcionMascotas.Entity.Contacto;

import java.util.List;

/**
 *
 * @author lyonc
 */
public interface IContactoService {
     public List<Contacto> getAllContacto();

    public void saveContacto(Contacto C);

    public void EliminarContacto(long ID);

    public Contacto getUsuarioById(long ID);

    public Contacto findByNombre(String nombre);
}
