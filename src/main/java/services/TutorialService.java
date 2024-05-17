package services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Tutorial;
import repositories.TutorialRepository;

@Service
@Transactional
public class TutorialService {
    /// Repositorio propio
    TutorialRepository tutorialRepository;

    /// Métodos base
    public Collection<Tutorial> findAll() {
        Collection<Tutorial> result;

        result = tutorialRepository.findAll();
        Assert.notNull(result);
        return result;
    }

    public Tutorial findById(int id) {
        Assert.isTrue(id != 0);

        Tutorial result;

        result = tutorialRepository.findById(id);

        return result;
    }

    public Tutorial save(Tutorial tutorial) {
        Assert.notNull(tutorial);
        Tutorial result;
        result = tutorialRepository.save(tutorial);

        return result;
    }

    public void delete(Tutorial tutorial) {
        Assert.notNull(tutorial);
        Assert.isTrue(tutorial.getId() != 0);
        Assert.isTrue(tutorialRepository.exists(tutorial.getId()));

        tutorialRepository.delete(tutorial);
    }
}
