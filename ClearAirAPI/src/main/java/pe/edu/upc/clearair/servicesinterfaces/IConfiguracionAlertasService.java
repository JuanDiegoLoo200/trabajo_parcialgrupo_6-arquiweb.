package pe.edu.upc.clearair.servicesinterfaces;

import pe.edu.upc.clearair.entities.ConfiguracionAlertas;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IConfiguracionAlertasService {
    List<ConfiguracionAlertas> list();
    ConfiguracionAlertas insert(ConfiguracionAlertas c);
    Optional<ConfiguracionAlertas> listId(Integer id);
    void update(ConfiguracionAlertas c);
    void delete(Integer id);
    List<ConfiguracionAlertas> findByUsuario(Integer idUsuario);
    List<ConfiguracionAlertas> findBySensibilidadMayorA(BigDecimal minSensibilidad);
}
