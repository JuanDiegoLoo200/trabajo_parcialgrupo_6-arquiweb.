package pe.edu.upc.clearair.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.clearair.entities.Rol;
import pe.edu.upc.clearair.entities.Usuarios;
import pe.edu.upc.clearair.repositories.IRolRepository;
import pe.edu.upc.clearair.repositories.IUsuariosRepository;
import pe.edu.upc.clearair.servicesinterfaces.IUsuariosService;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosServiceImpl implements IUsuariosService {

    @Autowired
    private IUsuariosRepository repo;

    @Autowired
    private IRolRepository rolRepo;

    @Override
    public List<Usuarios> list() { return repo.findAll(); }

    @Override
    public Usuarios insert(Usuarios u) { return repo.save(u); }

    @Override
    public Optional<Usuarios> listId(Integer id) { return repo.findById(id); }

    @Override
    public void update(Usuarios u) { repo.save(u); }

    @Override
    public void delete(Integer id) { repo.deleteById(id); }

    @Override
    public Usuarios buscarPorEmail(String email) {
        System.out.println(">>> [QUERY 1 - Usuarios] Ejecutando query: findByEmail -> Buscando usuario con email: " + email);
        return repo.findByEmail(email);
    }

    @Override
    public long contarPorRol(String rol) {
        System.out.println(">>> [QUERY 2 - Usuarios] Ejecutando query JPQL: contarUsuariosPorRol -> Contando usuarios con rol: " + rol);
        return repo.contarUsuariosPorRol(rol);
    }

    @Override
    public void actualizarRol(String rolNombre, Integer idUsuario) {
        System.out.println(">>> [QUERY 3 - Usuarios] Actualizando rol a '" + rolNombre + "' para usuario id: " + idUsuario);
        Rol rol = rolRepo.findByNombre(rolNombre)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + rolNombre));
        repo.findById(idUsuario).ifPresent(u -> {
            u.setRol(rol);
            repo.save(u);
        });
    }
}
