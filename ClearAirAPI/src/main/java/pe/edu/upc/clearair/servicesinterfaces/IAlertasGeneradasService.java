package pe.edu.upc.clearair.servicesinterfaces;

import pe.edu.upc.clearair.entities.AlertasGeneradas;

import java.util.List;
import java.util.Optional;

public interface IAlertasGeneradasService {
    List<AlertasGeneradas> list();
    AlertasGeneradas insert(AlertasGeneradas a);
    Optional<AlertasGeneradas> listId(Integer id);
    void delete(Integer id);
    List<AlertasGeneradas> findByUsuario(Integer idUsuario);
    List<AlertasGeneradas> findByUbicacion(Integer idUbicacion);
}
