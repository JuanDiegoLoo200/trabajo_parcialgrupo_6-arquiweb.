package pe.edu.upc.clearair.servicesinterfaces;

import pe.edu.upc.clearair.entities.Sensores;

import java.util.List;
import java.util.Optional;

public interface ISensoresService {
    List<Sensores> list();
    Sensores insert(Sensores s);
    Optional<Sensores> listId(Integer id);
    void update(Sensores s);
    void delete(Integer id);
    List<Sensores> findByUsuario(Integer idUsuario);
    List<Sensores> findByUbicacion(Integer idUbicacion);
}
