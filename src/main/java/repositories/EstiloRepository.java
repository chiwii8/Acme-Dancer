package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Estilo;

@Repository
public interface EstiloRepository extends JpaRepository<Estilo, Integer> {

    @Query("select e from Estilo e where e.id=?1")
    Estilo findById(int id);

    @Query("select e from Estilo e where e.nombre=?1")
    Estilo findByNombre(String nombre);

}