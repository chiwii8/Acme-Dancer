package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.actores.Actor;

import java.util.Collection;

import repositories.ActorRepository;
import security.UserAccount;
import security.UserAccountService;

@Service
@Transactional
public class ActorService {
    /// Repositorio propio
    private ActorRepository actorRepository;

    /// Servicios de apoyo
    private UserAccountService userAccountService;

    @Autowired
    public ActorService(ActorRepository actorRepository, UserAccountService userAccountService) {
        this.actorRepository = actorRepository;
        this.userAccountService = userAccountService;
    }

    public Collection<Actor> findAll() {
        Collection<Actor> result;

        result = actorRepository.findAll();
        Assert.notNull(result);
        return result;
    }

    public Actor findById(int actorId) {
        Assert.isTrue(actorId != 0);

        Actor result;

        result = actorRepository.findById(actorId);

        return result;
    }

    public Actor save(Actor actor) {
        Assert.notNull(actor);
        Actor result;
        result = actorRepository.save(actor);

        return result;
    }

    public void delete(Actor actor) {
        Assert.notNull(actor);
        Assert.isTrue(actor.getId() != 0);
        Assert.isTrue(actorRepository.exists(actor.getId()));

        actorRepository.delete(actor);
    }

    // Otros métodos

    public UserAccount findUserAccount(Actor actor) {
        Assert.notNull(actor);
        UserAccount result;

        result = userAccountService.findByActor(actor);

        return result;
    }

    public Collection<Actor> findByNombre(String nombre) {
        Assert.hasText(nombre);
        Collection<Actor> result = actorRepository.findByNombre(nombre);
        Assert.notEmpty(result);

        return result;
    }

    public Collection<Actor> findByNombreyApellidos(String nombre, String apellidos) {
        Assert.hasText(nombre);
        Assert.hasText(apellidos);

        Collection<Actor> result = actorRepository.findByNombreyApellidos(nombre, apellidos);
        Assert.notEmpty(result);
        return result;
    }

    public Collection<Actor> findByCodigoPostal(String codigoPostal) {
        Assert.hasText(codigoPostal);

        Collection<Actor> result = actorRepository.findByCodigoPostal(codigoPostal);

        Assert.notEmpty(result);
        return result;
    }

}