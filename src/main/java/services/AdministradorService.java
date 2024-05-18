package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actores.Administrador;
import repositories.AdministradorRepository;

@Service
@Transactional
public class AdministradorService {

    /// Repositorio propio
    AdministradorRepository administradorRepository;

    /// Servicios de apoyo

    @Autowired
    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    /// Métodos base
    public Collection<Administrador> findAll() {
        Collection<Administrador> result;

        result = administradorRepository.findAll();

        Assert.notEmpty(result);

        return result;
    }

    public Administrador findById(int id) {
        Assert.isTrue(id != 0);
        Assert.isTrue(administradorRepository.exists(id));

        Administrador result;

        result = administradorRepository.findById(id);
        Assert.notNull(result);

        return result;
    }

    public Administrador save(Administrador administrador) {
        Assert.notNull(administrador);
        Administrador result;

        result = administradorRepository.save(administrador);

        return result;
    }

    public void delete(Administrador administrador) {
        Assert.notNull(administrador);
        Assert.isTrue(administrador.getId() != 0);
        Assert.isTrue(administradorRepository.exists(administrador.getId()));

        administradorRepository.delete(administrador);
    }
}
