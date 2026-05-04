package pe.edu.upc.clearair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.clearair.entities.UsuarioCondiciones;
import pe.edu.upc.clearair.entities.UsuarioCondicionesId;

import java.util.List;

@Repository
public interface IUsuarioCondicionesRepository extends JpaRepository<UsuarioCondiciones, UsuarioCondicionesId> {

    // QUERY 1 - JPQL: obtener todas las condiciones medicas de un usuario
    @Query("SELECT uc FROM UsuarioCondiciones uc WHERE uc.usuarios.idUsuarios = :idUsuario")
    List<UsuarioCondiciones> findCondicionesByUsuario(@Param("idUsuario") Integer idUsuario);

    // QUERY 2 - JPQL: obtener todos los usuarios que tienen una condicion medica especifica
    @Query("SELECT uc FROM UsuarioCondiciones uc WHERE uc.condicionesMedicas.idCondicionesMedicas = :idCondicion")
    List<UsuarioCondiciones> findUsuariosByCondicion(@Param("idCondicion") Integer idCondicion);
}
