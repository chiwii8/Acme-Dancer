package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Curso;
import domain.enumeraciones.CursoNivel;
import domain.enumeraciones.DiaSemana;
import repositories.CursoRepository;

@Service
@Transactional
public class CursoService {
    /// Repositorio propio
    private CursoRepository cursoRepository;

    /// Servicio de apoyo

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
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
    public Collection<Curso> findByTitulo(String titulo) {
        Assert.hasText(titulo);

        Collection<Curso> result;

        result = cursoRepository.findByTitulo(titulo);
        Assert.notNull(result);

        return result;
    }

    public Collection<Curso> findByFechaInicio(Date fechaInicio) {
        Assert.notNull(fechaInicio);

        Collection<Curso> result;

        result = cursoRepository.findByFechaInicio(fechaInicio);
        Assert.notNull(result);

        return result;
    }

    public Collection<Curso> findByDiaSemana(DiaSemana diaSemana) {
        Assert.notNull(diaSemana);

        Collection<Curso> result;

        result = cursoRepository.findByDiaSemana(diaSemana);
        Assert.notNull(result);

        return result;
    }

    public Collection<Curso> findByNivel(CursoNivel nivel) {
        Assert.notNull(nivel);

        Collection<Curso> result;

        result = cursoRepository.findByNivel(nivel);
        Assert.notNull(result);

        return result;
    }

    public Collection<Curso> findAllByAcademiaId(int id) {
        Assert.isTrue(id != 0);

        Collection<Curso> result;

        result = cursoRepository.findAllByAcademiaId(id);
        Assert.notNull(result);

        return result;
    }

}
