package pe.edu.upc.clearair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.clearair.entities.Suscripciones;

import java.util.List;

@Repository
public interface ISuscripcionesRepository extends JpaRepository<Suscripciones, Integer> {

    // QUERY 1 - JPQL: obtener suscripciones de un usuario por su id
    @Query("SELECT s FROM Suscripciones s WHERE s.usuario.idUsuarios = :idUsuario")
    List<Suscripciones> findByUsuario(@Param("idUsuario") Integer idUsuario);

    // QUERY 2 - Metodo derivado: buscar suscripciones por estado
    List<Suscripciones> findByEstadoSuscripcion(String estadoSuscripcion);
}
