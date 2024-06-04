package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Curso;
import domain.Solicitud;
import domain.actores.Alumno;
import domain.enumeraciones.SolicitudEstado;
import repositories.SolicitudRepository;

@Service
@Transactional
public class SolicitudService {

    /// Repositorio propio
    SolicitudRepository solicitudRepository;

    /// Servicios de apoyo
    AlumnoService alumnoService;
    CursoService cursoService;

    @Autowired
    public SolicitudService(SolicitudRepository solicitudRepository, AlumnoService alumnoService,
            CursoService cursoService) {
        this.solicitudRepository = solicitudRepository;
        this.alumnoService = alumnoService;
        this.cursoService = cursoService;
    }

    /// Metodos base
    public Collection<Solicitud> findAll() {
        Collection<Solicitud> result;

        result = solicitudRepository.findAll();
        Assert.notNull(result);
        return result;
    }

    public Solicitud findById(int id) {
        Assert.isTrue(id != 0);

        Solicitud result;

        result = solicitudRepository.findById(id);

        return result;
    }

    public Solicitud create() {
        Solicitud result;

        result = new Solicitud();

        return result;
    }

    public Solicitud save(Solicitud solicitud) {
        Assert.notNull(solicitud);
        Solicitud result;
        result = solicitudRepository.save(solicitud);

        return result;
    }

    public void delete(Solicitud solicitud) {
        Assert.notNull(solicitud);
        Assert.isTrue(solicitud.getId() != 0);
        Assert.isTrue(solicitudRepository.exists(solicitud.getId()));

        solicitudRepository.delete(solicitud);
    }

    /// Otros
    public Collection<Solicitud> findAllByAlumnoId(int id) {
        Alumno alumno = alumnoService.findById(id);

        Collection<Solicitud> result;
        result = solicitudRepository.findAllByAlumnoId(alumno.getId());

        Assert.notNull(result);

        return result;
    }

    public Collection<Solicitud> findAllByCursoId(int id) {
        Curso curso = cursoService.findById(id);

        Collection<Solicitud> result;
        result = solicitudRepository.findAllByCursoId(curso.getId());

        Assert.notNull(result);
        return result;
    }

    public Collection<Solicitud> findAllByCursoIdAndEstado(int id, SolicitudEstado estado) {
        Collection<Solicitud> solicitud;

        solicitud = this.solicitudRepository.findAllByCursoIdAndState(id, estado);

        return solicitud;
    }
}
