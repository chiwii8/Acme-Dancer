package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    @Query("select c from Comentario c where c.id=?1")
    Comentario findById(int id);

    @Query("select c from Comentario c where c.fechaRealización=?1")
    public Comentario findByFechaRealizacion();
}
