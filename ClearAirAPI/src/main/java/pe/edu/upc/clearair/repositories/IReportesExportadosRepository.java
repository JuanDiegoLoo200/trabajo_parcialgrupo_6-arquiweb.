package pe.edu.upc.clearair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.clearair.entities.ReportesExportados;

import java.util.List;

@Repository
public interface IReportesExportadosRepository extends JpaRepository<ReportesExportados, Integer> {

    // QUERY 1 - JPQL: obtener reportes exportados de un usuario especifico
    @Query("SELECT r FROM ReportesExportados r WHERE r.usuarios.idUsuarios = :idUsuario ORDER BY r.fechaGeneracion DESC")
    List<ReportesExportados> findByUsuario(@Param("idUsuario") Integer idUsuario);

    // QUERY 2 - Metodo derivado: buscar reportes por tipo de formato (ej: PDF, CSV, EXCEL)
    List<ReportesExportados> findByTipoFormato(String tipoFormato);
}
