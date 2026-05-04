package pe.edu.upc.clearair.servicesinterfaces;

import pe.edu.upc.clearair.entities.ReportesExportados;

import java.util.List;
import java.util.Optional;

public interface IReportesExportadosService {
    List<ReportesExportados> list();
    ReportesExportados insert(ReportesExportados r);
    Optional<ReportesExportados> listId(Integer id);
    void delete(Integer id);
    List<ReportesExportados> findByUsuario(Integer idUsuario);
    List<ReportesExportados> findByTipoFormato(String tipoFormato);
}
