package pe.edu.upc.clearair.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.clearair.entities.RangosCalidadAire;
import pe.edu.upc.clearair.repositories.IRangosCalidadAireRepository;
import pe.edu.upc.clearair.servicesinterfaces.IRangosCalidadAireService;

import java.util.List;
import java.util.Optional;

@Service
public class RangosCalidadAireServiceImpl implements IRangosCalidadAireService {

    @Autowired
    private IRangosCalidadAireRepository repo;

    @Override
    public List<RangosCalidadAire> list() { return repo.findAll(); }

    @Override
    public RangosCalidadAire insert(RangosCalidadAire r) { return repo.save(r); }

    @Override
    public Optional<RangosCalidadAire> listId(Integer id) { return repo.findById(id); }

    @Override
    public void update(RangosCalidadAire r) { repo.save(r); }

    @Override
    public void delete(Integer id) { repo.deleteById(id); }

    @Override
    public List<RangosCalidadAire> buscarPorContaminante(Integer idContaminante) {
        System.out.println(">>> [QUERY 1 - RangosCalidadAire] Ejecutando query: findByTiposContaminantes_IdTiposContaminantes -> Buscando rangos por id contaminante: " + idContaminante);
        return repo.findByTiposContaminantes_IdTiposContaminantes(idContaminante);
    }

    @Override
    public List<RangosCalidadAire> buscarPorEtiqueta(String etiqueta) {
        System.out.println(">>> [QUERY 2 - RangosCalidadAire] Ejecutando query JPQL: findByEtiquetaAqi -> Buscando rangos con etiqueta AQI: " + etiqueta);
        return repo.findByEtiquetaAqi(etiqueta);
    }
}
