package pe.edu.upc.clearair.servicesinterfaces;

import pe.edu.upc.clearair.entities.RangosCalidadAire;

import java.util.List;
import java.util.Optional;

public interface IRangosCalidadAireService {
    List<RangosCalidadAire> list();
    RangosCalidadAire insert(RangosCalidadAire r);
    Optional<RangosCalidadAire> listId(Integer id);
    void update(RangosCalidadAire r);
    void delete(Integer id);
    List<RangosCalidadAire> buscarPorContaminante(Integer idContaminante);
    List<RangosCalidadAire> buscarPorEtiqueta(String etiqueta);
}
