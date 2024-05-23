package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Comentario;
import repositories.ComentarioRepository;

@Service
@Transactional
public class ComentarioService {
    /// Repositorio propio
    private ComentarioRepository comentarioRepository;

    /// Servicio apoyo
    private ActorService actorService;

    @Autowired
    public ComentarioService(ComentarioRepository comentarioRepository, ActorService actorService) {
        this.comentarioRepository = comentarioRepository;
        this.actorService = actorService;
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

    public Collection<Comentario> findAllComentariosByActorId(int idActor) {
        Assert.isTrue(idActor != 0);

        /// Realizamos una búsqueda por id para verificar que existe dicho cliente
        actorService.findById(idActor);

        Collection<Comentario> result = comentarioRepository.findAllComentariosByActorId(idActor);
        Assert.notNull(result);

        return result;
    }
}
