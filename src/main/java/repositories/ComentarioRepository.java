package repositories;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    @Query("select c from Comentario c where c.id=:id")
    Comentario findById(@Param("id") int id);

    @Query("select c from Comentario c where DATE(c.fechaRealización)=DATE(:fechaRealizacion)")
    public Comentario findByFechaRealizacion(@Param("fechaRealizacion") Date fechaRealizacion);
}
