
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.Curso;
import domain.enumeraciones.CursoNivel;
import domain.enumeraciones.DiaSemana;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

	@Query("select c from Curso c where c.id=:id")
	public Curso findById(@Param("id") int id);

	@Query("select c from Curso c where c.titulo=:titulo")
	public Collection<Curso> findByTitulo(@Param("titulo") String titulo);

	@Query("select c from Curso c where DATE(c.fechaInicio)=DATE(:fechaInicio)")
	public Collection<Curso> findByFechaInicio(@Param("fechaInicio") Date fechaInicio);

	@Query("select c from Curso c where c.diaSemana=:diaSemana")
	public Collection<Curso> findByDiaSemana(@Param("diaSemana") DiaSemana diaSemana);

	@Query("select c from Curso c where c.nivel=:nivel")
	public Collection<Curso> findByNivel(@Param("nivel") CursoNivel nivel);

	/// Extras
	@Query("select distinct c from Academia a join a.cursos c where a.id=:id")
	public Collection<Curso> findAllByAcademiaId(@Param("id") int id);

	@Query("select c from Curso c where c.estilo.id=:id")
	public Collection<Curso> findAllByEstiloId(@Param("id") int id);

	@Query("select distinct c from Curso c where c.titulo like %:buscar% OR c.estilo.nombre like %:buscar% OR c.estilo.descripcion like %:buscar%")
	public Collection<Curso> findByString(@Param("buscar") String buscar);
}
