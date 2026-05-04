package pe.edu.upc.clearair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.clearair.entities.Usuarios;

@Repository
public interface IUsuariosRepository extends JpaRepository<Usuarios, Integer> {

    // QUERY 1 - Metodo derivado: buscar usuario por email (usado para autenticacion JWT)
    Usuarios findByEmail(String email);

    // QUERY 2 - JPQL: contar usuarios por nombre de rol
    @Query("SELECT COUNT(u) FROM Usuarios u WHERE u.rol.nombre = :rol")
    long contarUsuariosPorRol(@Param("rol") String rol);
}
