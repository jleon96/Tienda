package com.AdopcionMascotas.Service;

import com.AdopcionMascotas.Entity.Comentario;
import java.util.List;

public interface IComentarioService {

    public List<Comentario> getAllComent();

    public void saveComent(Comentario C);

    public void EliminarComent(long id);

    public Comentario getComentById(long id);
}
