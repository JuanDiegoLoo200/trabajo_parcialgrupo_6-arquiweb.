package pe.edu.upc.clearair.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.clearair.dtos.AlertasGeneradasDTO;
import pe.edu.upc.clearair.entities.AlertasGeneradas;
import pe.edu.upc.clearair.entities.TiposContaminantes;
import pe.edu.upc.clearair.entities.Ubicaciones;
import pe.edu.upc.clearair.entities.Usuarios;
import pe.edu.upc.clearair.servicesinterfaces.IAlertasGeneradasService;
import pe.edu.upc.clearair.servicesinterfaces.ITiposContaminantesService;
import pe.edu.upc.clearair.servicesinterfaces.IUbicacionesService;
import pe.edu.upc.clearair.servicesinterfaces.IUsuariosService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// HUAPI-09: Alertas cuando niveles superan limites
@RestController
@CrossOrigin
@RequestMapping("/api/alertas")
@Tag(name = "AlertasGeneradas", description = "HUAPI-09, HUAPI-16, HUAPI-20: Gestion de alertas ambientales")
public class AlertasController {

    @Autowired
    private IAlertasGeneradasService service;

    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
    private IUbicacionesService ubicacionesService;

    @Autowired
    private ITiposContaminantesService contaminantesService;

    @PreAuthorize("hasRole('ADMIN')")//
    @Operation(summary = "Listar todas las alertas generadas | Acceso: ADMIN")
    @GetMapping("/lista")
    public ResponseEntity<?> lista() {
        List<AlertasGeneradasDTO> lista = service.list()
                .stream().map(a -> toDTO(a)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "HUAPI-09: Generar nueva alerta ambiental | Acceso: ADMIN", description = "Detecta umbral critico y genera alerta para el usuario. Solo ADMIN")
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@RequestBody AlertasGeneradasDTO dto) {
        AlertasGeneradas alerta = new AlertasGeneradas();
        alerta.setValorDisparador(dto.getValorDisparador());
        alerta.setFechaEnvio(LocalDateTime.now());
        if (dto.getIdUsuario() != null) {
            Optional<Usuarios> u = usuariosService.listId(dto.getIdUsuario());
            if (u.isEmpty()) return ResponseEntity.badRequest().body("Usuario no encontrado");
            alerta.setUsuario(u.get());
        }
        if (dto.getIdUbicacion() != null) {
            Optional<Ubicaciones> ub = ubicacionesService.listId(dto.getIdUbicacion());
            if (ub.isEmpty()) return ResponseEntity.badRequest().body("Ubicacion no encontrada");
            alerta.setUbicacion(ub.get());
        }
        if (dto.getIdContaminante() != null) {
            Optional<TiposContaminantes> tc = contaminantesService.listId(dto.getIdContaminante());
            if (tc.isEmpty()) return ResponseEntity.badRequest().body("Contaminante no encontrado");
            alerta.setContaminante(tc.get());
        }
        AlertasGeneradas saved = service.insert(alerta);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(saved));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "Obtener alerta por ID | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Optional<AlertasGeneradas> opt = service.listId(id);
        if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alerta no encontrada");
        return ResponseEntity.ok(toDTO(opt.get()));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "QUERY 1 - Alertas por usuario (HUAPI-16) | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> porUsuario(@PathVariable Integer idUsuario) {
        List<AlertasGeneradasDTO> lista = service.findByUsuario(idUsuario)
                .stream().map(a -> toDTO(a)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay alertas para este usuario");
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "QUERY 2 - Alertas por ubicacion | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/ubicacion/{idUbicacion}")
    public ResponseEntity<?> porUbicacion(@PathVariable Integer idUbicacion) {
        List<AlertasGeneradasDTO> lista = service.findByUbicacion(idUbicacion)
                .stream().map(a -> toDTO(a)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay alertas para esta ubicacion");
        return ResponseEntity.ok(lista);
    }

    private AlertasGeneradasDTO toDTO(AlertasGeneradas a) {
        AlertasGeneradasDTO dto = new AlertasGeneradasDTO();
        dto.setIdAlertasGeneradas(a.getIdAlertasGeneradas());
        dto.setValorDisparador(a.getValorDisparador());
        dto.setFechaEnvio(a.getFechaEnvio());
        if (a.getUsuario() != null) dto.setIdUsuario(a.getUsuario().getIdUsuarios());
        if (a.getUbicacion() != null) dto.setIdUbicacion(a.getUbicacion().getIdUbicaciones());
        if (a.getContaminante() != null) dto.setIdContaminante(a.getContaminante().getIdTiposContaminantes());
        return dto;
    }
}
