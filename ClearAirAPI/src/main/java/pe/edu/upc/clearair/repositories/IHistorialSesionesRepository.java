package pe.edu.upc.clearair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.clearair.entities.HistorialSesiones;

import java.util.List;

@Repository
public interface IHistorialSesionesRepository extends JpaRepository<HistorialSesiones, Integer> {

    // QUERY 1 - JPQL: obtener todas las sesiones de un usuario por su id
    @Query("SELECT h FROM HistorialSesiones h WHERE h.usuarios.idUsuarios = :idUsuario")
    List<HistorialSesiones> findSesionesByUsuario(@Param("idUsuario") Integer idUsuario);

    // QUERY 2 - JPQL: contar el numero de sesiones de un usuario
    @Query("SELECT COUNT(h) FROM HistorialSesiones h WHERE h.usuarios.idUsuarios = :idUsuario")
    long contarSesionesByUsuario(@Param("idUsuario") Integer idUsuario);
}
