package pe.edu.upc.clearair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.clearair.entities.ConfiguracionAlertas;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IConfiguracionAlertasRepository extends JpaRepository<ConfiguracionAlertas, Integer> {

    // QUERY 1 - JPQL: obtener configuracion de alertas de un usuario
    @Query("SELECT c FROM ConfiguracionAlertas c WHERE c.usuarios.idUsuarios = :idUsuario")
    List<ConfiguracionAlertas> findByUsuario(@Param("idUsuario") Integer idUsuario);

    // QUERY 2 - JPQL: buscar configuraciones con sensibilidad mayor a un valor minimo
    @Query("SELECT c FROM ConfiguracionAlertas c WHERE c.sensibilidadUmbral >= :minSensibilidad")
    List<ConfiguracionAlertas> findBySensibilidadMayorA(@Param("minSensibilidad") BigDecimal minSensibilidad);
}
