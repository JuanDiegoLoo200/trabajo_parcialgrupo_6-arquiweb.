package pe.edu.upc.clearair.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.clearair.dtos.LecturasDTO;
import pe.edu.upc.clearair.entities.Lecturas;
import pe.edu.upc.clearair.entities.Sensores;
import pe.edu.upc.clearair.entities.TiposContaminantes;
import pe.edu.upc.clearair.servicesinterfaces.ILecturasService;
import pe.edu.upc.clearair.servicesinterfaces.ISensoresService;
import pe.edu.upc.clearair.servicesinterfaces.ITiposContaminantesService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// HUAPI-06: AQI actual | HUAPI-07: Historial | HUAPI-08: Calculo AQI
@RestController
@CrossOrigin
@RequestMapping("/api/lecturas")
@Tag(name = "Lecturas", description = "HUAPI-06, HUAPI-07, HUAPI-08: Lecturas AQI en tiempo real e historico")
public class LecturasController {

    @Autowired
    private ILecturasService service;

    @Autowired
    private ISensoresService sensoresService;

    @Autowired
    private ITiposContaminantesService tiposService;

    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA')")
    @Operation(summary = "Listar todas las lecturas | Acceso: ADMIN, ESPECIALISTA")
    @GetMapping("/lista")
    public ResponseEntity<?> lista() {
        List<LecturasDTO> lista = service.list()
                .stream().map(l -> toDTO(l)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA')")
    @Operation(summary = "HUAPI-08: Registrar nueva lectura AQI | Acceso: ADMIN, ESPECIALISTA", description = "Procesa valor del sensor y guarda resultado en base de datos. ADMIN o ESPECIALISTA")
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@RequestBody LecturasDTO dto) {
        Optional<Sensores> sensor = sensoresService.listId(dto.getIdSensores());
        if (sensor.isEmpty()) return ResponseEntity.badRequest().body("Sensor no encontrado");
        Optional<TiposContaminantes> tipo = tiposService.listId(dto.getIdTiposContaminantes());
        if (tipo.isEmpty()) return ResponseEntity.badRequest().body("Tipo de contaminante no encontrado");
        Lecturas l = new Lecturas();
        l.setSensores(sensor.get());
        l.setTiposContaminantes(tipo.get());
        l.setValorCrudo(dto.getValorCrudo());
        l.setValorAqiCalculado(dto.getValorAqiCalculado());
        l.setEsPronostico(dto.getEsPronostico() != null && dto.getEsPronostico());
        l.setFechaHora(dto.getFechaHora() != null ? dto.getFechaHora() : LocalDateTime.now());
        Lecturas saved = service.insert(l);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(saved));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "HUAPI-06: Obtener lectura AQI actual por ID | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Optional<Lecturas> opt = service.listId(id);
        if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lectura no encontrada");
        return ResponseEntity.ok(toDTO(opt.get()));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "QUERY 1 - Historial de lecturas por sensor (HUAPI-07) | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/sensor/{idSensor}")
    public ResponseEntity<?> porSensor(@PathVariable Integer idSensor) {
        List<LecturasDTO> lista = service.findBySensor(idSensor)
                .stream().map(l -> toDTO(l)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay lecturas para este sensor");
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "QUERY 2 - Lecturas por rango de fechas (HUAPI-10, HUAPI-14) | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/rango-fechas")
    public ResponseEntity<?> porRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        List<LecturasDTO> lista = service.findByRangoFechas(inicio, fin)
                .stream().map(l -> toDTO(l)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "HUAPI-06: Ultimas lecturas AQI de una ubicacion (tiempo real) | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/calidad-actual/{idUbicacion}")
    public ResponseEntity<?> calidadActual(@PathVariable Integer idUbicacion) {
        List<LecturasDTO> lista = service.findUltimasLecturasPorUbicacion(idUbicacion)
                .stream().map(l -> toDTO(l)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay datos de calidad para esta ubicacion");
        return ResponseEntity.ok(lista);
    }

    private LecturasDTO toDTO(Lecturas l) {
        LecturasDTO dto = new LecturasDTO();
        dto.setIdLecturas(l.getIdLecturas());
        dto.setValorCrudo(l.getValorCrudo());
        dto.setValorAqiCalculado(l.getValorAqiCalculado());
        dto.setEsPronostico(l.getEsPronostico());
        dto.setFechaHora(l.getFechaHora());
        if (l.getSensores() != null) dto.setIdSensores(l.getSensores().getIdSensores());
        if (l.getTiposContaminantes() != null) dto.setIdTiposContaminantes(l.getTiposContaminantes().getIdTiposContaminantes());
        return dto;
    }
}
