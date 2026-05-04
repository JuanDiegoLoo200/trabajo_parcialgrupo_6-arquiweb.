package pe.edu.upc.clearair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.clearair.entities.RangosCalidadAire;

import java.util.List;

@Repository
public interface IRangosCalidadAireRepository extends JpaRepository<RangosCalidadAire, Integer> {

    // QUERY 1 - Metodo derivado: buscar rangos por id del tipo de contaminante
    List<RangosCalidadAire> findByTiposContaminantes_IdTiposContaminantes(Integer idTiposContaminantes);

    // QUERY 2 - JPQL: buscar rangos por etiqueta AQI (ej: "Bueno", "Moderado")
    @Query("SELECT r FROM RangosCalidadAire r WHERE r.etiquetaAqi = :etiqueta")
    List<RangosCalidadAire> findByEtiquetaAqi(@Param("etiqueta") String etiqueta);
}
