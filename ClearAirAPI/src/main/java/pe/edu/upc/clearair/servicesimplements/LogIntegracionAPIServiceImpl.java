package pe.edu.upc.clearair.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.clearair.entities.LogIntegracionAPI;
import pe.edu.upc.clearair.repositories.ILogIntegracionAPIRepository;
import pe.edu.upc.clearair.servicesinterfaces.ILogIntegracionAPIService;

import java.util.List;
import java.util.Optional;

@Service
public class LogIntegracionAPIServiceImpl implements ILogIntegracionAPIService {

    @Autowired
    private ILogIntegracionAPIRepository repo;

    @Override
    public List<LogIntegracionAPI> list() { return repo.findAll(); }

    @Override
    public LogIntegracionAPI insert(LogIntegracionAPI l) { return repo.save(l); }

    @Override
    public Optional<LogIntegracionAPI> listId(Integer id) { return repo.findById(id); }

    @Override
    public List<LogIntegracionAPI> findBySensor(Integer idSensor) {
        System.out.println(">>> [QUERY 1 - LogIntegracionAPI] Ejecutando query JPQL: findBySensor -> Buscando logs de integracion del sensor id: " + idSensor);
        return repo.findBySensor(idSensor);
    }

    @Override
    public List<LogIntegracionAPI> findByEstado(String estado) {
        System.out.println(">>> [QUERY 2 - LogIntegracionAPI] Ejecutando query: findByEstadoRespuesta -> Buscando logs con estado de respuesta: " + estado);
        return repo.findByEstadoRespuesta(estado);
    }
}
