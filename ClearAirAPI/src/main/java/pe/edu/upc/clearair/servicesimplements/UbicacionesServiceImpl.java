package pe.edu.upc.clearair.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.clearair.entities.Ubicaciones;
import pe.edu.upc.clearair.repositories.IUbicacionesRepository;
import pe.edu.upc.clearair.servicesinterfaces.IUbicacionesService;

import java.util.List;
import java.util.Optional;

@Service
public class UbicacionesServiceImpl implements IUbicacionesService {

    @Autowired
    private IUbicacionesRepository repo;

    @Override
    public List<Ubicaciones> list() { return repo.findAll(); }

    @Override
    public Ubicaciones insert(Ubicaciones u) { return repo.save(u); }

    @Override
    public Optional<Ubicaciones> listId(Integer id) { return repo.findById(id); }

    @Override
    public void update(Ubicaciones u) { repo.save(u); }

    @Override
    public void delete(Integer id) { repo.deleteById(id); }

    @Override
    public List<Ubicaciones> buscarPorNombreZona(String nombre) {
        System.out.println(">>> [QUERY 1 - Ubicaciones] Ejecutando query: findByNombreZonaContainingIgnoreCase -> Buscando ubicacion por nombre de zona: " + nombre);
        return repo.findByNombreZonaContainingIgnoreCase(nombre);
    }

    @Override
    public List<Ubicaciones> buscarPorZonaHoraria(String zonaHoraria) {
        System.out.println(">>> [QUERY 2 - Ubicaciones] Ejecutando query JPQL: findByZonaHoraria -> Buscando ubicaciones con zona horaria: " + zonaHoraria);
        return repo.findByZonaHoraria(zonaHoraria);
    }
}
