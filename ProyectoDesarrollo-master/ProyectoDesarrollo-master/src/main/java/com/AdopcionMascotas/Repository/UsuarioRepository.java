
package com.AdopcionMascotas.Repository;

import com.AdopcionMascotas.Entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario findByNombre (String nombre);
}
