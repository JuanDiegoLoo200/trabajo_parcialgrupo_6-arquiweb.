package pe.edu.upc.clearair.servicesinterfaces;

import pe.edu.upc.clearair.entities.TiposContaminantes;

import java.util.List;
import java.util.Optional;

public interface ITiposContaminantesService {
    List<TiposContaminantes> list();
    TiposContaminantes insert(TiposContaminantes t);
    Optional<TiposContaminantes> listId(Integer id);
    void update(TiposContaminantes t);
    void delete(Integer id);
    List<TiposContaminantes> buscarPorNombre(String nombre);
    Optional<TiposContaminantes> buscarPorSiglas(String siglas);
}
