package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Actores.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

    @Query("select a from Alumno a where a.id=?1")
    Alumno findById(int id);

    @Query("select a from Alumno a where a.nombre=?1")
    Alumno findByNombre(String nombre);
}
