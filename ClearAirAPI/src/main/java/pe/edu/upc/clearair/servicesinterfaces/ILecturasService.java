package pe.edu.upc.clearair.servicesinterfaces;

import pe.edu.upc.clearair.entities.Lecturas;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ILecturasService {
    List<Lecturas> list();
    Lecturas insert(Lecturas l);
    Optional<Lecturas> listId(Integer id);
    void delete(Integer id);
    List<Lecturas> findBySensor(Integer idSensor);
    List<Lecturas> findByRangoFechas(LocalDateTime inicio, LocalDateTime fin);
    List<Lecturas> findUltimasLecturasPorUbicacion(Integer idUbicacion);
}
