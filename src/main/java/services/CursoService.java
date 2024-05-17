package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Curso;
import domain.Actores.Actor;
import repositories.CursoRepository;

@Service
@Transactional
public class CursoService {
    /// Repositorio propio
    @Autowired
    CursoRepository cursoRepository;

    /// Servicio de apoyo
    @Autowired
    SolicitudService solicitudService;
    @Autowired
    EstiloService estiloService;

    public CursoService() {
        super();
    }

    /// Metodos base
    public Collection<Curso> findAll() {
        Collection<Curso> result;

        result = cursoRepository.findAll();
        Assert.notNull(result);
        return result;
    }

    public Curso findById(int id) {
        Assert.isTrue(id != 0);

        Curso result;

        result = cursoRepository.findById(id);

        return result;
    }

    public Curso save(Curso curso) {
        Assert.notNull(curso);
        Curso result;
        result = cursoRepository.save(curso);

        return result;
    }

    public void delete(Curso curso) {
        Assert.notNull(curso);
        Assert.isTrue(curso.getId() != 0);
        Assert.isTrue(cursoRepository.exists(curso.getId()));

        cursoRepository.delete(curso);
    }

    /// Otros métodos
}
