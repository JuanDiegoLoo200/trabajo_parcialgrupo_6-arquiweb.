package pe.edu.upc.clearair.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.clearair.entities.ContenidoInformativo;
import pe.edu.upc.clearair.repositories.IContenidoInformativoRepository;
import pe.edu.upc.clearair.servicesinterfaces.IContenidoInformativoService;

import java.util.List;
import java.util.Optional;

@Service
public class ContenidoInformativoServiceImpl implements IContenidoInformativoService {

    @Autowired
    private IContenidoInformativoRepository repo;

    @Override
    public List<ContenidoInformativo> list() { return repo.findAll(); }

    @Override
    public ContenidoInformativo insert(ContenidoInformativo c) { return repo.save(c); }

    @Override
    public Optional<ContenidoInformativo> listId(Integer id) { return repo.findById(id); }

    @Override
    public void update(ContenidoInformativo c) { repo.save(c); }

    @Override
    public void delete(Integer id) { repo.deleteById(id); }

    @Override
    public List<ContenidoInformativo> findByCategoria(String categoria) {
        System.out.println(">>> [QUERY 1 - ContenidoInformativo] Ejecutando query: findByCategoria -> Buscando contenido por categoria: " + categoria);
        return repo.findByCategoria(categoria);
    }

    @Override
    public List<ContenidoInformativo> findByTitulo(String titulo) {
        System.out.println(">>> [QUERY 2 - ContenidoInformativo] Ejecutando query: findByTituloContainingIgnoreCase -> Buscando contenido por titulo: " + titulo);
        return repo.findByTituloContainingIgnoreCase(titulo);
    }
}
