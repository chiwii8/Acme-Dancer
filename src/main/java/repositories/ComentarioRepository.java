
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

	@Query("select c from Comentario c where c.id=:id")
	public Comentario findById(@Param("id") int id);

	@Query("select c from Comentario c where DATE(c.fechaRealizacion)=DATE(:fechaRealizacion)")
	public Collection<Comentario> findByFechaRealizacion(@Param("fechaRealizacion") Date fechaRealizacion);

	/// Extra
	@Query("select distinct c from Actor a join a.comentarios c where a.id=:id")
	public Collection<Comentario> findAllComentariosByActorId(@Param("id") int id);
}
