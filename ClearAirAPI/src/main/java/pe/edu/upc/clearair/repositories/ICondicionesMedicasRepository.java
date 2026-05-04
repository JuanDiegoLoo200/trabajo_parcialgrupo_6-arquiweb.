package pe.edu.upc.clearair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.clearair.entities.CondicionesMedicas;

import java.util.List;

@Repository
public interface ICondicionesMedicasRepository extends JpaRepository<CondicionesMedicas, Integer> {

    // QUERY 1 - Metodo derivado: buscar condicion medica por nombre (contiene, ignorar mayusculas)
    List<CondicionesMedicas> findByNombreCondicionContainingIgnoreCase(String nombreCondicion);

    // QUERY 2 - Native SQL: contar el total de condiciones medicas registradas
    @Query(value = "SELECT COUNT(*) FROM CondicionesMedicas", nativeQuery = true)
    long contarTotalCondiciones();
}
