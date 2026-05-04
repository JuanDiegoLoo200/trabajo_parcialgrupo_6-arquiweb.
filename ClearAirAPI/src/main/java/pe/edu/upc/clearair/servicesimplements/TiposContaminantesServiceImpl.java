package pe.edu.upc.clearair.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.clearair.entities.TiposContaminantes;
import pe.edu.upc.clearair.repositories.ITiposContaminantesRepository;
import pe.edu.upc.clearair.servicesinterfaces.ITiposContaminantesService;

import java.util.List;
import java.util.Optional;

@Service
public class TiposContaminantesServiceImpl implements ITiposContaminantesService {

    @Autowired
    private ITiposContaminantesRepository repo;

    @Override
    public List<TiposContaminantes> list() {
        return repo.findAll();
    }

    @Override
    public TiposContaminantes insert(TiposContaminantes t) {
        return repo.save(t);
    }

    @Override
    public Optional<TiposContaminantes> listId(Integer id) {
        return repo.findById(id);
    }

    @Override
    public void update(TiposContaminantes t) {
        repo.save(t);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<TiposContaminantes> buscarPorNombre(String nombre) {
        System.out.println(">>> [QUERY 1 - TiposContaminantes] Ejecutando query: findByNombreContaminanteContainingIgnoreCase -> Buscando contaminantes por nombre: " + nombre);
        return repo.findByNombreContaminanteContainingIgnoreCase(nombre);
    }

    @Override
    public Optional<TiposContaminantes> buscarPorSiglas(String siglas) {
        System.out.println(">>> [QUERY 2 - TiposContaminantes] Ejecutando query JPQL: findBySiglas -> Buscando contaminante con siglas: " + siglas);
        return repo.findBySiglas(siglas);
    }
}
