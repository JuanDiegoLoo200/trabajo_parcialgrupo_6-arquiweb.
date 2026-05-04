package pe.edu.upc.clearair.servicesinterfaces;

import pe.edu.upc.clearair.entities.CondicionesMedicas;

import java.util.List;
import java.util.Optional;

public interface ICondicionesMedicasService {
    List<CondicionesMedicas> list();
    CondicionesMedicas insert(CondicionesMedicas c);
    Optional<CondicionesMedicas> listId(Integer id);
    void update(CondicionesMedicas c);
    void delete(Integer id);
    List<CondicionesMedicas> buscarPorNombre(String nombre);
    long contarTotal();
}
