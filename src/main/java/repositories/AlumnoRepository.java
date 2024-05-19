package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.actores.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

    @Query("select a from Alumno a where a.id=:id")
    public Alumno findById(@Param("id") int id);

    @Query("select a from Alumno a where a.nombre=:nombre")
    public Collection<Alumno> findByNombre(@Param("nombre") String nombre);
}
