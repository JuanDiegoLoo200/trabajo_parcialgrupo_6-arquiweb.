package pe.edu.upc.clearair.servicesinterfaces;

import pe.edu.upc.clearair.entities.ContenidoInformativo;

import java.util.List;
import java.util.Optional;

public interface IContenidoInformativoService {
    List<ContenidoInformativo> list();
    ContenidoInformativo insert(ContenidoInformativo c);
    Optional<ContenidoInformativo> listId(Integer id);
    void update(ContenidoInformativo c);
    void delete(Integer id);
    List<ContenidoInformativo> findByCategoria(String categoria);
    List<ContenidoInformativo> findByTitulo(String titulo);
}
