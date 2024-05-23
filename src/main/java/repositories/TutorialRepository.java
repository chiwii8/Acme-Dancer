
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Integer> {

	@Query("select t from Tutorial t where t.id=:id")
	public Tutorial findById(@Param("id") int id);

	@Query("select t from Tutorial t where t.nombre=:nombre")
	public Collection<Tutorial> findByNombre(@Param("nombre") String nombre);

	@Query("select t from Tutorial t where t.descripcion like '%:descripcion%'")
	public Collection<Tutorial> findByParcialDescripcion(@Param("descripcion") String descripcion);

	@Query("select a.cursos from Academia a where a.id=:id")
	public Collection<Tutorial> findAllTutorialByIdAcademia(@Param("id") int id);

}
