package pe.edu.upc.clearair.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.clearair.entities.Sensores;
import pe.edu.upc.clearair.repositories.ISensoresRepository;
import pe.edu.upc.clearair.servicesinterfaces.ISensoresService;

import java.util.List;
import java.util.Optional;

@Service
public class SensoresServiceImpl implements ISensoresService {

    @Autowired
    private ISensoresRepository repo;

    @Override
    public List<Sensores> list() { return repo.findAll(); }

    @Override
    public Sensores insert(Sensores s) { return repo.save(s); }

    @Override
    public Optional<Sensores> listId(Integer id) { return repo.findById(id); }

    @Override
    public void update(Sensores s) { repo.save(s); }

    @Override
    public void delete(Integer id) { repo.deleteById(id); }

    @Override
    public List<Sensores> findByUsuario(Integer idUsuario) {
        System.out.println(">>> [QUERY 1 - Sensores] Ejecutando query JPQL: findByUsuario -> Buscando sensores del usuario id: " + idUsuario);
        return repo.findByUsuario(idUsuario);
    }

    @Override
    public List<Sensores> findByUbicacion(Integer idUbicacion) {
        System.out.println(">>> [QUERY 2 - Sensores] Ejecutando query JPQL: findByUbicacion -> Buscando sensores en ubicacion id: " + idUbicacion);
        return repo.findByUbicacion(idUbicacion);
    }
}
