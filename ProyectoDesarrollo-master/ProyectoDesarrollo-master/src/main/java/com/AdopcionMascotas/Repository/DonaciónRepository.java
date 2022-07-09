package com.AdopcionMascotas.Repository;


import com.AdopcionMascotas.Entity.Donación;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DonaciónRepository extends CrudRepository<Donación, Long> {

}
