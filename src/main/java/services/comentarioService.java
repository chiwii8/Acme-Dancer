package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Comentario;
import domain.Actores.Actor;
import repositories.ComentarioRepository;

@Service
@Transactional
public class ComentarioService {
    /// Repositorio propio
    ComentarioRepository comentarioRepository;

    public ComentarioService() {
        super();
    }

    /// Métodos base
    public Collection<Comentario> findAll() {
        Collection<Comentario> result;

        result = comentarioRepository.findAll();
        Assert.notNull(result);
        return result;
    }

    public Comentario findById(int id) {
        Assert.isTrue(id != 0);

        Comentario result;

        result = comentarioRepository.findById(id);

        return result;
    }

    public Comentario save(Comentario comentario) {
        Assert.notNull(comentario);
        Comentario result;
        result = comentarioRepository.save(comentario);

        return result;
    }

    public void delete(Comentario comentario) {
        Assert.notNull(comentario);
        Assert.isTrue(comentario.getId() != 0);
        Assert.isTrue(comentarioRepository.exists(comentario.getId()));

        comentarioRepository.delete(comentario);
    }

    //// Otros métodos
    public Collection<Comentario> findByFechaRealizacion(Date fechaRealizacion) {
        Assert.notNull(fechaRealizacion);

        Collection<Comentario> result;
        result = comentarioRepository.findByFechaRealizacion(fechaRealizacion);
        Assert.notNull(result);

        return result;
    }
}
