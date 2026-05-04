package pe.edu.upc.clearair.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.clearair.entities.CondicionesMedicas;
import pe.edu.upc.clearair.repositories.ICondicionesMedicasRepository;
import pe.edu.upc.clearair.servicesinterfaces.ICondicionesMedicasService;

import java.util.List;
import java.util.Optional;

@Service
public class CondicionesMedicasServiceImpl implements ICondicionesMedicasService {

    @Autowired
    private ICondicionesMedicasRepository repo;

    @Override
    public List<CondicionesMedicas> list() { return repo.findAll(); }

    @Override
    public CondicionesMedicas insert(CondicionesMedicas c) { return repo.save(c); }

    @Override
    public Optional<CondicionesMedicas> listId(Integer id) { return repo.findById(id); }

    @Override
    public void update(CondicionesMedicas c) { repo.save(c); }

    @Override
    public void delete(Integer id) { repo.deleteById(id); }

    @Override
    public List<CondicionesMedicas> buscarPorNombre(String nombre) {
        System.out.println(">>> [QUERY 1 - CondicionesMedicas] Ejecutando query: findByNombreCondicionContainingIgnoreCase -> Buscando condicion medica por nombre: " + nombre);
        return repo.findByNombreCondicionContainingIgnoreCase(nombre);
    }

    @Override
    public long contarTotal() {
        System.out.println(">>> [QUERY 2 - CondicionesMedicas] Ejecutando query Native SQL: contarTotalCondiciones -> Contando total de condiciones medicas registradas");
        return repo.contarTotalCondiciones();
    }
}
