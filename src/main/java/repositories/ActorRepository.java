package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actores.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    @Query("select a from Actor a where a.id=?1")
    Actor findById(int id);

    @Query("select a from Actor a where a.nombre=?1")
    Collection<Actor> findByNombre(String nombre);

    @Query("select a from Actor a where a.nombre=?1 and a.apellidos=?2")
    Actor findByNombreyApellidos(String nombre, String apellidos);

    @Query("select a from Actor a where a.codigoPostal=?1")
    Collection<Actor> findbyCodigoPostal(String codigoPostal);
}