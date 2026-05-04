package pe.edu.upc.clearair.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.clearair.entities.ConfiguracionAlertas;
import pe.edu.upc.clearair.repositories.IConfiguracionAlertasRepository;
import pe.edu.upc.clearair.servicesinterfaces.IConfiguracionAlertasService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ConfiguracionAlertasServiceImpl implements IConfiguracionAlertasService {

    @Autowired
    private IConfiguracionAlertasRepository repo;

    @Override
    public List<ConfiguracionAlertas> list() { return repo.findAll(); }

    @Override
    public ConfiguracionAlertas insert(ConfiguracionAlertas c) { return repo.save(c); }

    @Override
    public Optional<ConfiguracionAlertas> listId(Integer id) { return repo.findById(id); }

    @Override
    public void update(ConfiguracionAlertas c) { repo.save(c); }

    @Override
    public void delete(Integer id) { repo.deleteById(id); }

    @Override
    public List<ConfiguracionAlertas> findByUsuario(Integer idUsuario) {
        System.out.println(">>> [QUERY 1 - ConfiguracionAlertas] Ejecutando query JPQL: findByUsuario -> Buscando configuracion de alertas del usuario id: " + idUsuario);
        return repo.findByUsuario(idUsuario);
    }

    @Override
    public List<ConfiguracionAlertas> findBySensibilidadMayorA(BigDecimal minSensibilidad) {
        System.out.println(">>> [QUERY 2 - ConfiguracionAlertas] Ejecutando query JPQL: findBySensibilidadMayorA -> Buscando alertas con sensibilidad mayor o igual a: " + minSensibilidad);
        return repo.findBySensibilidadMayorA(minSensibilidad);
    }
}
