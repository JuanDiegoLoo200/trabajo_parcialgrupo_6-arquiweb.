package pe.edu.upc.clearair.servicesinterfaces;

import pe.edu.upc.clearair.entities.Usuarios;

import java.util.List;
import java.util.Optional;

public interface IUsuariosService {
    List<Usuarios> list();
    Usuarios insert(Usuarios u);
    Optional<Usuarios> listId(Integer id);
    void update(Usuarios u);
    void delete(Integer id);
    Usuarios buscarPorEmail(String email);
    long contarPorRol(String rol);
    void actualizarRol(String rol, Integer idUsuario);
}
