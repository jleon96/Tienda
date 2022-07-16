package com.AdopcionMascotas.Service;

import com.AdopcionMascotas.Entity.Perro;
import java.util.List;

public interface IPerroService {

    public List<Perro> getAllPerro();

    public void savePerro(Perro P);

    public void delete(long idPerro);

    public Perro getPerroById(long idPerro);

}
