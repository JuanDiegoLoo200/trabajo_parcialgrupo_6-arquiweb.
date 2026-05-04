package pe.edu.upc.clearair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.clearair.entities.Ubicaciones;

import java.util.List;

@Repository
public interface IUbicacionesRepository extends JpaRepository<Ubicaciones, Integer> {

    // QUERY 1 - Metodo derivado: buscar ubicaciones por nombre de zona (contiene, ignorar mayusculas)
    List<Ubicaciones> findByNombreZonaContainingIgnoreCase(String nombreZona);

    // QUERY 2 - JPQL: buscar ubicaciones por zona horaria exacta
    @Query("SELECT u FROM Ubicaciones u WHERE u.zonaHoraria = :zonaHoraria")
    List<Ubicaciones> findByZonaHoraria(@Param("zonaHoraria") String zonaHoraria);
}
