package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.Solicitud;
import domain.enumeraciones.SolicitudEstado;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
    @Query("select s from Solicitud s where s.id=:id")
    public Solicitud findById(@Param("id") int id);

    @Query("select s from Solicitud s where s.estado=:estado")
    public Collection<Solicitud> findByEstado(@Param("estado") SolicitudEstado estado);

    /// Extra
    @Query("select a.solicitudes from Alumno a where a.id=:id")
    public Collection<Solicitud> findAllByAlumnoId(@Param("id") int id);

    @Query("select c.solicitudes from Curso c where c.id=:id")
    public Collection<Solicitud> findAllByCursoId(@Param("id") int id);
}