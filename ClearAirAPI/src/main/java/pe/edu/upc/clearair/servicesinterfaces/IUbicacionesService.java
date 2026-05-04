package pe.edu.upc.clearair.servicesinterfaces;

import pe.edu.upc.clearair.entities.Ubicaciones;

import java.util.List;
import java.util.Optional;

public interface IUbicacionesService {
    List<Ubicaciones> list();
    Ubicaciones insert(Ubicaciones u);
    Optional<Ubicaciones> listId(Integer id);
    void update(Ubicaciones u);
    void delete(Integer id);
    List<Ubicaciones> buscarPorNombreZona(String nombre);
    List<Ubicaciones> buscarPorZonaHoraria(String zonaHoraria);
}
