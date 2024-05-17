package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.Curso;
import domain.Enumeraciones.CursoNivel;
import domain.Enumeraciones.DiaSemana;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
    @Query("select c from Curso where c.id=:id")
    Curso findById(@Param("id") int id);

    @Query("select c from Curso where c.titulo=:titulo")
    Curso findByTitulo(@Param("titulo") String titulo);

    @Query("select c from Curso where DATE(c.fechaInicio)=DATE(:fechaInicio)")
    Collection<Curso> findByFechaInicio(@Param("fechaInicio") Date fechaInicio);

    @Query("select c from Curso where c.diaSemana=:diaSemana")
    Collection<Curso> findByDiaSemana(@Param("diaSemana") DiaSemana diaSemana);

    @Query("select c from Curso c.nivel=:nivel")
    Collection<Curso> findByNivel(@Param("nivel") CursoNivel nivel);

}
