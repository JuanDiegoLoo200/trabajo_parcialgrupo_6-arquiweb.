package pe.edu.upc.clearair.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.clearair.entities.ContenidoInformativo;

import java.util.List;

@Repository
public interface IContenidoInformativoRepository extends JpaRepository<ContenidoInformativo, Integer> {

    // QUERY 1 - Metodo derivado: buscar contenido por categoria exacta
    List<ContenidoInformativo> findByCategoria(String categoria);

    // QUERY 2 - Metodo derivado: buscar contenido por titulo (contiene, ignorar mayusculas)
    List<ContenidoInformativo> findByTituloContainingIgnoreCase(String titulo);
}
