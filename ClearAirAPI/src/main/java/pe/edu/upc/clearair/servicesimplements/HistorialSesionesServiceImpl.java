package pe.edu.upc.clearair.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.clearair.entities.HistorialSesiones;
import pe.edu.upc.clearair.repositories.IHistorialSesionesRepository;
import pe.edu.upc.clearair.servicesinterfaces.IHistorialSesionesService;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialSesionesServiceImpl implements IHistorialSesionesService {

    @Autowired
    private IHistorialSesionesRepository repo;

    @Override
    public HistorialSesiones insert(HistorialSesiones h) { return repo.save(h); }

    @Override
    public Optional<HistorialSesiones> listId(Integer id) { return repo.findById(id); }

    @Override
    public List<HistorialSesiones> findSesionesByUsuario(Integer idUsuario) {
        System.out.println(">>> [QUERY 1 - HistorialSesiones] Ejecutando query JPQL: findSesionesByUsuario -> Buscando sesiones del usuario id: " + idUsuario);
        return repo.findSesionesByUsuario(idUsuario);
    }

    @Override
    public long contarSesionesByUsuario(Integer idUsuario) {
        System.out.println(">>> [QUERY 2 - HistorialSesiones] Ejecutando query JPQL: contarSesionesByUsuario -> Contando sesiones del usuario id: " + idUsuario);
        return repo.contarSesionesByUsuario(idUsuario);
    }
}
