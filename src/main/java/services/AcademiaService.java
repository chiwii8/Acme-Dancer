package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.actores.Academia;
import repositories.AcademiaRepository;

@Service
@Transactional
public class AcademiaService {

    // Repositorio propio
    AcademiaRepository academiaRepository;

    @Autowired
    public AcademiaService(AcademiaRepository academiaRepository) {
        this.academiaRepository = academiaRepository;
    }

    /// Operaciones basicas
    public Academia create() {
        Academia result;

        result = new Academia();

        return result;
    }

    public Collection<Academia> findAll() {
        Collection<Academia> result;

        result = academiaRepository.findAll();
        Assert.notEmpty(result);

        return result;
    }

    public Academia findById(int id) {
        Assert.isTrue(id != 0);
        Assert.isTrue(academiaRepository.exists(id));
        Academia result;

        result = academiaRepository.findById(id);

        return result;
    }

    public Academia save(Academia academia) {
        Assert.notNull(academia);
        Academia result;

        result = academiaRepository.save(academia);

        return result;
    }

    public void delete(Academia academia) {
        Assert.notNull(academia);
        Assert.isTrue(academia.getId() != 0);
        Assert.isTrue(academiaRepository.exists(academia.getId()));

        academiaRepository.delete(academia);
    }

    /// Otros
    public Academia findByNombreComercial(String nombreComercial) {
        Assert.hasLength(nombreComercial);
        Academia result;

        result = academiaRepository.findByNombreComercial(nombreComercial);

        return result;
    }

    public Collection<Academia> findByParcialNombreComercial(String nombreComercial) {
        Assert.hasLength(nombreComercial);
        Collection<Academia> result;

        result = academiaRepository.findByParcialNombreComercial(nombreComercial);

        return result;
    }

    public Collection<Academia> findByNombre(String nombre) {
        Assert.hasText(nombre);
        Collection<Academia> result;

        result = academiaRepository.findByNombre(nombre);

        return result;
    }

}
