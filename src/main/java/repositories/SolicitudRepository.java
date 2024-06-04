
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.Solicitud;
import domain.enumeraciones.SolicitudEstado;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

	@Query("select s from Solicitud s where s.id=:id")
	public Solicitud findById(@Param("id") int id);

	@Query("select s from Solicitud s where s.estado=:estado")
	public Collection<Solicitud> findByEstado(@Param("estado") SolicitudEstado estado);

	/// Extra
	@Query("select s from Solicitud s where s.alumno.id=:id")
	public Collection<Solicitud> findAllByAlumnoId(@Param("id") int id);

	@Query("select s from Solicitud s where s.curso.id=:id")
	public Collection<Solicitud> findAllByCursoId(@Param("id") int id);

	/// TODO: Revisar que funciona correctamente
	@Query("select c.solicitudes from Curso c join c.solicitudes s where c.id=:id AND s.estado = :estado")
	public Collection<Solicitud> findAllByCursoIdAndState(@Param("id") int id, @Param("estado") SolicitudEstado estado);
}
