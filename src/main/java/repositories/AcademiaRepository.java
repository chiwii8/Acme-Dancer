package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actores.Academia;

@Repository
public interface AcademiaRepository extends JpaRepository<Academia, Integer> {

    @Query("select a from Academia a where a.id=?1")
    Academia findById(int id);

    @Query("select a from Academia a where a.nombre=?1")
    Academia findByNombre(String nombre);

    @Query("select a from Academia a where a.nombreComercial=?1")
    public Academia findByNombreComercial(String nombreComercial);

    @Query("Select a from Academia a where a.nombreComercial like '?1'")
    public Collection<Academia> findByParcialNombreComercial(String nombreComercial);
}
