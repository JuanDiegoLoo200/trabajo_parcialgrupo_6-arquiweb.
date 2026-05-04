package pe.edu.upc.clearair.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.clearair.entities.ReportesExportados;
import pe.edu.upc.clearair.repositories.IReportesExportadosRepository;
import pe.edu.upc.clearair.servicesinterfaces.IReportesExportadosService;

import java.util.List;
import java.util.Optional;

@Service
public class ReportesExportadosServiceImpl implements IReportesExportadosService {

    @Autowired
    private IReportesExportadosRepository repo;

    @Override
    public List<ReportesExportados> list() { return repo.findAll(); }

    @Override
    public ReportesExportados insert(ReportesExportados r) { return repo.save(r); }

    @Override
    public Optional<ReportesExportados> listId(Integer id) { return repo.findById(id); }

    @Override
    public void delete(Integer id) { repo.deleteById(id); }

    @Override
    public List<ReportesExportados> findByUsuario(Integer idUsuario) {
        System.out.println(">>> [QUERY 1 - ReportesExportados] Ejecutando query JPQL: findByUsuario -> Buscando reportes exportados del usuario id: " + idUsuario);
        return repo.findByUsuario(idUsuario);
    }

    @Override
    public List<ReportesExportados> findByTipoFormato(String tipoFormato) {
        System.out.println(">>> [QUERY 2 - ReportesExportados] Ejecutando query: findByTipoFormato -> Buscando reportes con formato: " + tipoFormato);
        return repo.findByTipoFormato(tipoFormato);
    }
}
