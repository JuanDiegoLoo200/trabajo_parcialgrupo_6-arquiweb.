package pe.edu.upc.clearair.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.clearair.dtos.ConfiguracionAlertasDTO;
import pe.edu.upc.clearair.entities.ConfiguracionAlertas;
import pe.edu.upc.clearair.entities.Usuarios;
import pe.edu.upc.clearair.servicesinterfaces.IConfiguracionAlertasService;
import pe.edu.upc.clearair.servicesinterfaces.IUsuariosService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// HUAPI-10: Suscripciones de alertas | HU-17: Alertas personalizadas | HU-18: Sensibilidad | HU-19: Horarios
@RestController
@CrossOrigin
@RequestMapping("/api/config-alertas")
@Tag(name = "ConfiguracionAlertas", description = "HUAPI-10, HU-17, HU-18, HU-19: Configuracion personalizada de alertas")
public class ConfiguracionAlertasController {

    @Autowired
    private IConfiguracionAlertasService service;

    @Autowired
    private IUsuariosService usuariosService;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Listar todas las configuraciones de alertas | Acceso: ADMIN")
    @GetMapping("/lista")
    public ResponseEntity<?> lista() {
        List<ConfiguracionAlertasDTO> lista = service.list()
                .stream().map(c -> toDTO(c)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "HUAPI-10: Crear configuracion de alerta | Acceso: ADMIN, ESPECIALISTA, USUARIO", description = "Guarda preferencias del usuario y confirma operacion")
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@RequestBody ConfiguracionAlertasDTO dto) {
        Optional<Usuarios> u = usuariosService.listId(dto.getIdUsuarios());
        if (u.isEmpty()) return ResponseEntity.badRequest().body("Usuario no encontrado");
        ConfiguracionAlertas c = new ConfiguracionAlertas();
        c.setUsuarios(u.get());
        c.setSensibilidadUmbral(dto.getSensibilidadUmbral());
        c.setHorarioInicio(dto.getHorarioInicio());
        c.setHorarioFin(dto.getHorarioFin());
        ConfiguracionAlertas saved = service.insert(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(saved));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "HU-18: Actualizar sensibilidad y horarios de alerta | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @PutMapping("/actualiza")
    public ResponseEntity<?> actualiza(@RequestBody ConfiguracionAlertasDTO dto) {
        Optional<ConfiguracionAlertas> opt = service.listId(dto.getIdConfiguracionAlertas());
        if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Configuracion no encontrada");
        ConfiguracionAlertas c = opt.get();
        c.setSensibilidadUmbral(dto.getSensibilidadUmbral());
        c.setHorarioInicio(dto.getHorarioInicio());
        c.setHorarioFin(dto.getHorarioFin());
        service.update(c);
        return ResponseEntity.ok("Configuracion de alerta actualizada correctamente");
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "QUERY 1 - Obtener configuracion de alertas de un usuario | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> porUsuario(@PathVariable Integer idUsuario) {
        List<ConfiguracionAlertasDTO> lista = service.findByUsuario(idUsuario)
                .stream().map(c -> toDTO(c)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay configuracion para este usuario");
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "QUERY 2 - Buscar configuraciones con sensibilidad mayor a un valor | Acceso: ADMIN")
    @GetMapping("/sensibilidad")
    public ResponseEntity<?> porSensibilidad(@RequestParam BigDecimal min) {
        List<ConfiguracionAlertasDTO> lista = service.findBySensibilidadMayorA(min)
                .stream().map(c -> toDTO(c)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    private ConfiguracionAlertasDTO toDTO(ConfiguracionAlertas c) {
        ConfiguracionAlertasDTO dto = new ConfiguracionAlertasDTO();
        dto.setIdConfiguracionAlertas(c.getIdConfiguracionAlertas());
        dto.setSensibilidadUmbral(c.getSensibilidadUmbral());
        dto.setHorarioInicio(c.getHorarioInicio());
        dto.setHorarioFin(c.getHorarioFin());
        if (c.getUsuarios() != null) dto.setIdUsuarios(c.getUsuarios().getIdUsuarios());
        return dto;
    }
}
