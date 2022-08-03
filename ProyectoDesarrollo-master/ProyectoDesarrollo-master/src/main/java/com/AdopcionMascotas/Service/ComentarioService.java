package com.AdopcionMascotas.Service;

import com.AdopcionMascotas.Entity.Comentario;
import com.AdopcionMascotas.Repository.ComentarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService implements IComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public List<Comentario> getAllComent() {
        return (List<Comentario>) comentarioRepository.findAll();
    }

    @Override
    public void saveComent(Comentario C) {
        comentarioRepository.save(C);
    }

    @Override
    public void EliminarComent(long id) {
        comentarioRepository.deleteById(id);
    }

    @Override
    public Comentario getComentById(long id) {
        return comentarioRepository.findById(id).orElse(null);
    }

}
