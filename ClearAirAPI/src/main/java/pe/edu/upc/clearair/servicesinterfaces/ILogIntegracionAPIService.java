package pe.edu.upc.clearair.servicesinterfaces;

import pe.edu.upc.clearair.entities.LogIntegracionAPI;

import java.util.List;
import java.util.Optional;

public interface ILogIntegracionAPIService {
    List<LogIntegracionAPI> list();
    LogIntegracionAPI insert(LogIntegracionAPI l);
    Optional<LogIntegracionAPI> listId(Integer id);
    List<LogIntegracionAPI> findBySensor(Integer idSensor);
    List<LogIntegracionAPI> findByEstado(String estado);
}
