package pe.edu.upc.clearair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.clearair.entities.AlertasGeneradas;

import java.util.List;

@Repository
public interface IAlertasGeneradasRepository extends JpaRepository<AlertasGeneradas, Integer> {

    // QUERY 1 - JPQL: obtener alertas generadas para un usuario especifico
    @Query("SELECT a FROM AlertasGeneradas a WHERE a.usuario.idUsuarios = :idUsuario ORDER BY a.fechaEnvio DESC")
    List<AlertasGeneradas> findByUsuario(@Param("idUsuario") Integer idUsuario);

    // QUERY 2 - JPQL: obtener alertas generadas en una ubicacion especifica
    @Query("SELECT a FROM AlertasGeneradas a WHERE a.ubicacion.idUbicaciones = :idUbicacion ORDER BY a.fechaEnvio DESC")
    List<AlertasGeneradas> findByUbicacion(@Param("idUbicacion") Integer idUbicacion);
}
