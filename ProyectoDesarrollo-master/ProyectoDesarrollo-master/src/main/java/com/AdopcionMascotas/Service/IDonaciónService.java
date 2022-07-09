package com.AdopcionMascotas.Service;


import com.AdopcionMascotas.Entity.Donación;
import java.util.List;

public interface IDonaciónService {

    public List<Donación> getAllDonacion();

    public void saveDonacion(Donación donación);

    public void delete(long id);

    public Donación getDonaciónById(long id);

}
