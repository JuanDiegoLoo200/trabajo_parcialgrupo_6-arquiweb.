package pe.edu.upc.clearair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.clearair.entities.LogIntegracionAPI;

import java.util.List;

@Repository
public interface ILogIntegracionAPIRepository extends JpaRepository<LogIntegracionAPI, Integer> {

    // QUERY 1 - JPQL: obtener logs de integracion de un sensor especifico
    @Query("SELECT l FROM LogIntegracionAPI l WHERE l.sensorRelacionado.idSensores = :idSensor ORDER BY l.fechaEvento DESC")
    List<LogIntegracionAPI> findBySensor(@Param("idSensor") Integer idSensor);

    // QUERY 2 - Metodo derivado: buscar logs por estado de respuesta
    List<LogIntegracionAPI> findByEstadoRespuesta(String estadoRespuesta);
}
