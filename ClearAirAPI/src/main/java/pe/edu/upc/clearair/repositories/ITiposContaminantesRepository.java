package pe.edu.upc.clearair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.clearair.entities.TiposContaminantes;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITiposContaminantesRepository extends JpaRepository<TiposContaminantes, Integer> {

    // QUERY 1 - Metodo derivado: buscar por nombre (contiene, ignorar mayusculas)
    List<TiposContaminantes> findByNombreContaminanteContainingIgnoreCase(String nombreContaminante);

    // QUERY 2 - JPQL: buscar por siglas exactas
    @Query("SELECT t FROM TiposContaminantes t WHERE t.siglas = :siglas")
    Optional<TiposContaminantes> findBySiglas(@Param("siglas") String siglas);
}
