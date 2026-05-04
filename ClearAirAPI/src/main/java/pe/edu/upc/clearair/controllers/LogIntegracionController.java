package pe.edu.upc.clearair.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.clearair.dtos.LogIntegracionAPIDTO;
import pe.edu.upc.clearair.entities.LogIntegracionAPI;
import pe.edu.upc.clearair.entities.Sensores;
import pe.edu.upc.clearair.servicesinterfaces.ILogIntegracionAPIService;
import pe.edu.upc.clearair.servicesinterfaces.ISensoresService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/logs")
@Tag(name = "LogIntegracionAPI", description = "Registro de logs de integracion de sensores IoT")
public class LogIntegracionController {

    @Autowired
    private ILogIntegracionAPIService service;

    @Autowired
    private ISensoresService sensoresService;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Listar todos los logs de integracion | Acceso: ADMIN")
    @GetMapping("/lista")
    public ResponseEntity<?> lista() {
        List<LogIntegracionAPIDTO> lista = service.list()
                .stream().map(l -> toDTO(l)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA')")
    @Operation(summary = "Registrar nuevo log de integracion | Acceso: ADMIN, ESPECIALISTA")
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@RequestBody LogIntegracionAPIDTO dto) {
        LogIntegracionAPI log = new LogIntegracionAPI();
        log.setEstadoRespuesta(dto.getEstadoRespuesta());
        log.setMensajeDetalle(dto.getMensajeDetalle());
        log.setFechaEvento(LocalDateTime.now());
        if (dto.getIdSensorRelacionado() != null) {
            Optional<Sensores> s = sensoresService.listId(dto.getIdSensorRelacionado());
            if (s.isEmpty()) return ResponseEntity.badRequest().body("Sensor no encontrado");
            log.setSensorRelacionado(s.get());
        }
        LogIntegracionAPI saved = service.insert(log);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(saved));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA')")
    @Operation(summary = "QUERY 1 - Logs por sensor | Acceso: ADMIN, ESPECIALISTA")
    @GetMapping("/sensor/{idSensor}")
    public ResponseEntity<?> porSensor(@PathVariable Integer idSensor) {
        List<LogIntegracionAPIDTO> lista = service.findBySensor(idSensor)
                .stream().map(l -> toDTO(l)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay logs para este sensor");
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA')")
    @Operation(summary = "QUERY 2 - Logs por estado de respuesta (ej: SUCCESS, ERROR) | Acceso: ADMIN, ESPECIALISTA")
    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> porEstado(@PathVariable String estado) {
        List<LogIntegracionAPIDTO> lista = service.findByEstado(estado)
                .stream().map(l -> toDTO(l)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    private LogIntegracionAPIDTO toDTO(LogIntegracionAPI l) {
        LogIntegracionAPIDTO dto = new LogIntegracionAPIDTO();
        dto.setIdLogIntegracionAPI(l.getIdLogIntegracionAPI());
        dto.setEstadoRespuesta(l.getEstadoRespuesta());
        dto.setMensajeDetalle(l.getMensajeDetalle());
        dto.setFechaEvento(l.getFechaEvento());
        if (l.getSensorRelacionado() != null) dto.setIdSensorRelacionado(l.getSensorRelacionado().getIdSensores());
        return dto;
    }
}
