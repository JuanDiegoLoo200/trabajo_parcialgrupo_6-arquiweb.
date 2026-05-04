package pe.edu.upc.clearair.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.clearair.dtos.SensoresDTO;
import pe.edu.upc.clearair.entities.Sensores;
import pe.edu.upc.clearair.entities.Ubicaciones;
import pe.edu.upc.clearair.entities.Usuarios;
import pe.edu.upc.clearair.servicesinterfaces.ISensoresService;
import pe.edu.upc.clearair.servicesinterfaces.IUbicacionesService;
import pe.edu.upc.clearair.servicesinterfaces.IUsuariosService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// HUAPI-05: Registro de sensores | HUAPI-32-34: IoT
@RestController
@CrossOrigin
@RequestMapping("/api/sensores")
@Tag(name = "Sensores", description = "HUAPI-05: Registro y consulta de sensores ambientales IoT")
public class SensoresController {

    @Autowired
    private ISensoresService service;

    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
    private IUbicacionesService ubicacionesService;

    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA')")
    @Operation(summary = "Listar todos los sensores | Acceso: ADMIN, ESPECIALISTA")
    @GetMapping("/lista")
    public ResponseEntity<?> lista() {
        List<SensoresDTO> lista = service.list()
                .stream().map(s -> toDTO(s)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA')")
    @Operation(summary = "HUAPI-05: Registrar nuevo sensor | Acceso: ADMIN, ESPECIALISTA", description = "Registra sensor, asocia usuario y ubicacion. ADMIN o ESPECIALISTA")
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@RequestBody SensoresDTO dto) {
        Sensores sensor = new Sensores();
        sensor.setModeloLot(dto.getModeloLot());
        sensor.setEsPersonal(dto.getEsPersonal());
        sensor.setEstadoSincronizacion(dto.getEstadoSincronizacion());
        if (dto.getIdUsuarios() != null) {
            Optional<Usuarios> u = usuariosService.listId(dto.getIdUsuarios());
            if (u.isEmpty()) return ResponseEntity.badRequest().body("Usuario no encontrado");
            sensor.setUsuarios(u.get());
        }
        if (dto.getIdUbicacion() != null) {
            Optional<Ubicaciones> ub = ubicacionesService.listId(dto.getIdUbicacion());
            if (ub.isEmpty()) return ResponseEntity.badRequest().body("Ubicacion no encontrada");
            sensor.setUbicacion(ub.get());
        }
        Sensores saved = service.insert(sensor);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(saved));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "Obtener sensor por ID | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Optional<Sensores> opt = service.listId(id);
        if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sensor no encontrado");
        return ResponseEntity.ok(toDTO(opt.get()));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "QUERY 1 - Buscar sensores por usuario | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> porUsuario(@PathVariable Integer idUsuario) {
        List<SensoresDTO> lista = service.findByUsuario(idUsuario)
                .stream().map(s -> toDTO(s)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay sensores para este usuario");
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "QUERY 2 - Buscar sensores por ubicacion | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/ubicacion/{idUbicacion}")
    public ResponseEntity<?> porUbicacion(@PathVariable Integer idUbicacion) {
        List<SensoresDTO> lista = service.findByUbicacion(idUbicacion)
                .stream().map(s -> toDTO(s)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay sensores en esta ubicacion");
        return ResponseEntity.ok(lista);
    }

    private SensoresDTO toDTO(Sensores s) {
        SensoresDTO dto = new SensoresDTO();
        dto.setIdSensores(s.getIdSensores());
        dto.setModeloLot(s.getModeloLot());
        dto.setEsPersonal(s.getEsPersonal());
        dto.setEstadoSincronizacion(s.getEstadoSincronizacion());
        if (s.getUsuarios() != null) dto.setIdUsuarios(s.getUsuarios().getIdUsuarios());
        if (s.getUbicacion() != null) dto.setIdUbicacion(s.getUbicacion().getIdUbicaciones());
        return dto;
    }
}
