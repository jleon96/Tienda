package com.AdopcionMascotas.Service;


import com.AdopcionMascotas.Entity.Donación;
import com.AdopcionMascotas.Repository.DonaciónRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonaciónService implements IDonaciónService {

    @Autowired
    private DonaciónRepository donaciónRepository;

    @Override
    public List<Donación> getAllDonacion() {
         return (List<Donación>) donaciónRepository.findAll();
    }

    @Override
    public void saveDonacion(Donación donación) {
         donaciónRepository.save(donación);
    }

    @Override
    public void delete(long id) {
           donaciónRepository.deleteById(id);
    }

    @Override
    public Donación getDonaciónById(long id) {
         return donaciónRepository.findById(id).orElse(null);
    }

}
