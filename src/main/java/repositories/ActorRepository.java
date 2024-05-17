package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.Actores.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    @Query("select a from Actor a where a.id=:id")
    Actor findById(@Param("id") int id);

    @Query("select a from Actor a where a.nombre=:nombre")
    Collection<Actor> findByNombre(@Param("nombre") String nombre);

    @Query("select a from Actor a where a.nombre=:nombre and a.apellidos=:apellidos")
    Collection<Actor> findByNombreyApellidos(@Param("nombre") String nombre, @Param("apellidos") String apellidos);

    @Query("select a from Actor a where a.codigoPostal=:codigoPostal")
    Collection<Actor> findByCodigoPostal(@Param("codigoPostal") String codigoPostal);
}