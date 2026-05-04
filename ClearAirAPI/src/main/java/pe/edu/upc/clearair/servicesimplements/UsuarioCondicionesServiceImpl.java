package pe.edu.upc.clearair.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.clearair.entities.UsuarioCondiciones;
import pe.edu.upc.clearair.entities.UsuarioCondicionesId;
import pe.edu.upc.clearair.repositories.IUsuarioCondicionesRepository;
import pe.edu.upc.clearair.servicesinterfaces.IUsuarioCondicionesService;

import java.util.List;

@Service
public class UsuarioCondicionesServiceImpl implements IUsuarioCondicionesService {

    @Autowired
    private IUsuarioCondicionesRepository repo;

    @Override
    public UsuarioCondiciones insert(UsuarioCondiciones uc) { return repo.save(uc); }

    @Override
    public void delete(UsuarioCondicionesId id) { repo.deleteById(id); }

    @Override
    public List<UsuarioCondiciones> findCondicionesByUsuario(Integer idUsuario) {
        System.out.println(">>> [QUERY 1 - UsuarioCondiciones] Ejecutando query JPQL: findCondicionesByUsuario -> Buscando condiciones medicas del usuario id: " + idUsuario);
        return repo.findCondicionesByUsuario(idUsuario);
    }

    @Override
    public List<UsuarioCondiciones> findUsuariosByCondicion(Integer idCondicion) {
        System.out.println(">>> [QUERY 2 - UsuarioCondiciones] Ejecutando query JPQL: findUsuariosByCondicion -> Buscando usuarios con condicion medica id: " + idCondicion);
        return repo.findUsuariosByCondicion(idCondicion);
    }
}
