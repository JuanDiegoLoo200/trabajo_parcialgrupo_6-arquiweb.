package pe.edu.upc.clearair.servicesinterfaces;

import pe.edu.upc.clearair.entities.UsuarioCondiciones;
import pe.edu.upc.clearair.entities.UsuarioCondicionesId;

import java.util.List;

public interface IUsuarioCondicionesService {
    UsuarioCondiciones insert(UsuarioCondiciones uc);
    void delete(UsuarioCondicionesId id);
    List<UsuarioCondiciones> findCondicionesByUsuario(Integer idUsuario);
    List<UsuarioCondiciones> findUsuariosByCondicion(Integer idCondicion);
}
