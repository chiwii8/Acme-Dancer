package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.Curso;
import domain.enumeraciones.CursoNivel;
import domain.enumeraciones.DiaSemana;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
    @Query("select c from Curso where c.id=:id")
    public Curso findById(@Param("id") int id);

    @Query("select c from Curso where c.titulo=:titulo")
    public Collection<Curso> findByTitulo(@Param("titulo") String titulo);

    @Query("select c from Curso where DATE(c.fechaInicio)=DATE(:fechaInicio)")
    public Collection<Curso> findByFechaInicio(@Param("fechaInicio") Date fechaInicio);

    @Query("select c from Curso where c.diaSemana=:diaSemana")
    public Collection<Curso> findByDiaSemana(@Param("diaSemana") DiaSemana diaSemana);

    @Query("select c from Curso c.nivel=:nivel")
    public Collection<Curso> findByNivel(@Param("nivel") CursoNivel nivel);

    /// Extras
    @Query("select a.cursos from Academia a where a.id=:id")
    public Collection<Curso> findAllByAcademiaId(@Param("id") int id);

    @Query("select c from Curso c where c.estilo.id=:id")
    public Collection<Curso> findAllByEstiloId(@Param("id") int id);
}
