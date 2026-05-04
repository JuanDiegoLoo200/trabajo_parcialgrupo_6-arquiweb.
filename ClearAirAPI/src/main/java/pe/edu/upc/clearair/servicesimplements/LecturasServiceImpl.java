package pe.edu.upc.clearair.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.clearair.entities.Lecturas;
import pe.edu.upc.clearair.repositories.ILecturasRepository;
import pe.edu.upc.clearair.servicesinterfaces.ILecturasService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LecturasServiceImpl implements ILecturasService {

    @Autowired
    private ILecturasRepository repo;

    @Override
    public List<Lecturas> list() { return repo.findAll(); }

    @Override
    public Lecturas insert(Lecturas l) { return repo.save(l); }

    @Override
    public Optional<Lecturas> listId(Integer id) { return repo.findById(id); }

    @Override
    public void delete(Integer id) { repo.deleteById(id); }

    @Override
    public List<Lecturas> findBySensor(Integer idSensor) {
        System.out.println(">>> [QUERY 1 - Lecturas] Ejecutando query JPQL: findBySensor -> Buscando lecturas del sensor id: " + idSensor);
        return repo.findBySensor(idSensor);
    }

    @Override
    public List<Lecturas> findByRangoFechas(LocalDateTime inicio, LocalDateTime fin) {
        System.out.println(">>> [QUERY 2 - Lecturas] Ejecutando query JPQL: findByRangoFechas -> Buscando lecturas entre " + inicio + " y " + fin);
        return repo.findByRangoFechas(inicio, fin);
    }

    @Override
    public List<Lecturas> findUltimasLecturasPorUbicacion(Integer idUbicacion) {
        System.out.println(">>> [QUERY EXTRA - Lecturas] Ejecutando query Native SQL: findUltimasLecturasPorUbicacion -> Ultimas lecturas AQI de ubicacion id: " + idUbicacion);
        return repo.findUltimasLecturasPorUbicacion(idUbicacion);
    }
}
