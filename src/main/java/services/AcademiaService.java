package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Actores.Academia;
import repositories.AcademiaRepository;

@Service
@Transactional
public class AcademiaService {

    // Repositorio propio
    @Autowired
    AcademiaRepository academiaRepository;

    /// Servicios de apoyo
    @Autowired
    CursoService cursoService;

    @Autowired
    TutorialService tutorialService;

    public AcademiaService() {
        super();
    }

    /// Operaciones de Guardado y Borrado
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
}
