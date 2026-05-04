package pe.edu.upc.clearair.servicesinterfaces;

import pe.edu.upc.clearair.entities.Suscripciones;

import java.util.List;
import java.util.Optional;

public interface ISuscripcionesService {
    List<Suscripciones> list();
    Suscripciones insert(Suscripciones s);
    Optional<Suscripciones> listId(Integer id);
    void update(Suscripciones s);
    void delete(Integer id);
    List<Suscripciones> findByUsuario(Integer idUsuario);
    List<Suscripciones> findByEstado(String estado);
}
