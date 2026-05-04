package pe.edu.upc.clearair.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.clearair.entities.AlertasGeneradas;
import pe.edu.upc.clearair.repositories.IAlertasGeneradasRepository;
import pe.edu.upc.clearair.servicesinterfaces.IAlertasGeneradasService;

import java.util.List;
import java.util.Optional;

@Service
public class AlertasGeneradasServiceImpl implements IAlertasGeneradasService {

    @Autowired
    private IAlertasGeneradasRepository repo;

    @Override
    public List<AlertasGeneradas> list() { return repo.findAll(); }

    @Override
    public AlertasGeneradas insert(AlertasGeneradas a) { return repo.save(a); }

    @Override
    public Optional<AlertasGeneradas> listId(Integer id) { return repo.findById(id); }

    @Override
    public void delete(Integer id) { repo.deleteById(id); }

    @Override
    public List<AlertasGeneradas> findByUsuario(Integer idUsuario) {
        System.out.println(">>> [QUERY 1 - AlertasGeneradas] Ejecutando query JPQL: findByUsuario -> Buscando alertas generadas para el usuario id: " + idUsuario);
        return repo.findByUsuario(idUsuario);
    }

    @Override
    public List<AlertasGeneradas> findByUbicacion(Integer idUbicacion) {
        System.out.println(">>> [QUERY 2 - AlertasGeneradas] Ejecutando query JPQL: findByUbicacion -> Buscando alertas generadas en la ubicacion id: " + idUbicacion);
        return repo.findByUbicacion(idUbicacion);
    }
}
