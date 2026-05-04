package pe.edu.upc.clearair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.clearair.entities.Lecturas;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ILecturasRepository extends JpaRepository<Lecturas, Integer> {

    // QUERY 1 - JPQL: obtener lecturas de un sensor por su id
    @Query("SELECT l FROM Lecturas l WHERE l.sensores.idSensores = :idSensor ORDER BY l.fechaHora DESC")
    List<Lecturas> findBySensor(@Param("idSensor") Integer idSensor);

    // QUERY 2 - JPQL: obtener lecturas dentro de un rango de fechas
    @Query("SELECT l FROM Lecturas l WHERE l.fechaHora BETWEEN :inicio AND :fin ORDER BY l.fechaHora DESC")
    List<Lecturas> findByRangoFechas(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

    // QUERY EXTRA - Native SQL: obtener las ultimas lecturas AQI por ubicacion
    @Query(value = "SELECT l.* FROM Lecturas l " +
            "INNER JOIN Sensores s ON l.id_Sensores = s.id_sensores " +
            "WHERE s.id_ubicacion = :idUbicacion " +
            "ORDER BY l.fecha_hora DESC LIMIT 10", nativeQuery = true)
    List<Lecturas> findUltimasLecturasPorUbicacion(@Param("idUbicacion") Integer idUbicacion);
}
