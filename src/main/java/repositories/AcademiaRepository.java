package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.actores.Academia;

@Repository
public interface AcademiaRepository extends JpaRepository<Academia, Integer> {

    @Query("select a from Academia a where a.id=:id")
    public Academia findById(@Param("id") int id);

    @Query("select a from Academia a where a.nombre=:nombre")
    public Collection<Academia> findByNombre(@Param("nombre") String nombre);

    @Query("select a from Academia a where a.nombreComercial=:nombreComercial")
    public Academia findByNombreComercial(@Param("nombreComercial") String nombreComercial);

    @Query("Select a from Academia a where a.nombreComercial like '%:nombreComercial%'")
    public Collection<Academia> findByParcialNombreComercial(@Param("nombreComercial") String nombreComercial);

    @Query("select c from Academia a join a.cursos c where c.id=:id")
    public Academia findByCursoId(@Param("id") int id);
}
