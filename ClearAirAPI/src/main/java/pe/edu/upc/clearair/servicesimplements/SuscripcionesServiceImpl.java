package pe.edu.upc.clearair.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.clearair.entities.Suscripciones;
import pe.edu.upc.clearair.repositories.ISuscripcionesRepository;
import pe.edu.upc.clearair.servicesinterfaces.ISuscripcionesService;

import java.util.List;
import java.util.Optional;

@Service
public class SuscripcionesServiceImpl implements ISuscripcionesService {

    @Autowired
    private ISuscripcionesRepository repo;

    @Override
    public List<Suscripciones> list() { return repo.findAll(); }

    @Override
    public Suscripciones insert(Suscripciones s) { return repo.save(s); }

    @Override
    public Optional<Suscripciones> listId(Integer id) { return repo.findById(id); }

    @Override
    public void update(Suscripciones s) { repo.save(s); }

    @Override
    public void delete(Integer id) { repo.deleteById(id); }

    @Override
    public List<Suscripciones> findByUsuario(Integer idUsuario) {
        System.out.println(">>> [QUERY 1 - Suscripciones] Ejecutando query JPQL: findByUsuario -> Buscando suscripciones del usuario id: " + idUsuario);
        return repo.findByUsuario(idUsuario);
    }

    @Override
    public List<Suscripciones> findByEstado(String estado) {
        System.out.println(">>> [QUERY 2 - Suscripciones] Ejecutando query: findByEstadoSuscripcion -> Buscando suscripciones con estado: " + estado);
        return repo.findByEstadoSuscripcion(estado);
    }
}
