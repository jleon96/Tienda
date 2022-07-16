package com.AdopcionMascotas.Service;

import com.AdopcionMascotas.Entity.Gato;
import java.util.List;

public interface IGatoService {

    public List<Gato> getAllGato();

    public void saveGato(Gato G);

    public void delete(long idGato);

    public Gato getGatoById(long idGato);
}
