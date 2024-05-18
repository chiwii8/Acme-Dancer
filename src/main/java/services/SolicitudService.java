package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Solicitud;
import repositories.SolicitudRepository;

@Service
@Transactional
public class SolicitudService {

    /// Repositorio propio
    SolicitudRepository solicitudRepository;

    /// Servicios de apoyo

    @Autowired
    public SolicitudService(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
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
}
