package pe.edu.upc.clearair.servicesinterfaces;

import pe.edu.upc.clearair.entities.HistorialSesiones;

import java.util.List;
import java.util.Optional;

public interface IHistorialSesionesService {
    HistorialSesiones insert(HistorialSesiones h);
    Optional<HistorialSesiones> listId(Integer id);
    List<HistorialSesiones> findSesionesByUsuario(Integer idUsuario);
    long contarSesionesByUsuario(Integer idUsuario);
}
