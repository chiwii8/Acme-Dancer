package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.Solicitud;
import domain.Enumeraciones.SolicitudEstado;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
    @Query("select s from Solicitud s where s.id=:id")
    Solicitud findById(@Param("id") int id);

    @Query("select s from Solicitud s where s.estado=:estado")
    Collection<Solicitud> findByEstado(@Param("estado") SolicitudEstado estado);

}