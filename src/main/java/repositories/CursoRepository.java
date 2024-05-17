package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Curso;
import domain.Enumeraciones.CursoNivel;
import domain.Enumeraciones.DiaSemana;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
    @Query("select c from Curso where c.titulo=?1")
    Curso findByTitulo(String titulo);

    @Query("select c from Curso where DATE(c.fechaInicio)=DATE(?1)")
    Collection<Curso> findByFechaInicio(Date fechaInicio);

    @Query("select c from Curso where c.diaSemana=?1")
    Collection<Curso> findByDiaSemana(DiaSemana diaSemana);

    @Query("select c from Curso c.nivel=?1")
    Collection<Curso> findByNivel(CursoNivel nivel);

}
