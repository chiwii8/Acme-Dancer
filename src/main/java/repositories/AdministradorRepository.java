package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actores.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {

    @Query("select a from Administrador a where a.id=?1")
    Administrador findById(int id);

    @Query("select a from Administrador a where a.nombre=?1")
    Administrador findByNombre(String nombre);
}