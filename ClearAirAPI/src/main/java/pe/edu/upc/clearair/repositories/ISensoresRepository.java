package pe.edu.upc.clearair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.clearair.entities.Sensores;

import java.util.List;

@Repository
public interface ISensoresRepository extends JpaRepository<Sensores, Integer> {

    // QUERY 1 - JPQL: obtener sensores de un usuario por su id
    @Query("SELECT s FROM Sensores s WHERE s.usuarios.idUsuarios = :idUsuario")
    List<Sensores> findByUsuario(@Param("idUsuario") Integer idUsuario);

    // QUERY 2 - JPQL: obtener sensores ubicados en una zona especifica
    @Query("SELECT s FROM Sensores s WHERE s.ubicacion.idUbicaciones = :idUbicacion")
    List<Sensores> findByUbicacion(@Param("idUbicacion") Integer idUbicacion);
}
