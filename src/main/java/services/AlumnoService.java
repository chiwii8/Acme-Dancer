package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actores.Alumno;
import repositories.AlumnoRepository;

@Service
@Transactional
public class AlumnoService {

    AlumnoRepository alumnoRepository;

    /// Servicios de apoyo

    @Autowired
    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    /// Métodos base
    public Collection<Alumno> findAll() {
        Collection<Alumno> result;

        result = alumnoRepository.findAll();
        Assert.notNull(result);

        return result;
    }

    public Alumno findById(int id) {
        Assert.isTrue(id != 0);

        Alumno result = alumnoRepository.findById(id);
        Assert.notNull(result);

        return result;
    }

    public Collection<Alumno> findByNombre(String nombre) {
        Assert.hasText(nombre);
        Collection<Alumno> result;

        result = alumnoRepository.findByNombre(nombre);

        return result;
    }

    public Alumno save(Alumno alumno) {
        Assert.notNull(alumno);
        Alumno result;

        result = alumnoRepository.save(alumno);

        return result;
    }

    public void delete(Alumno alumno) {
        Assert.notNull(alumno);
        Assert.isTrue(alumno.getId() != 0);
        Assert.isTrue(alumnoRepository.exists(alumno.getId()));

        alumnoRepository.delete(alumno);
    }
}
