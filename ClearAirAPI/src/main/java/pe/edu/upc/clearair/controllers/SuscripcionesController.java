package pe.edu.upc.clearair.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.clearair.dtos.SuscripcionesDTO;
import pe.edu.upc.clearair.entities.Suscripciones;
import pe.edu.upc.clearair.entities.Ubicaciones;
import pe.edu.upc.clearair.entities.Usuarios;
import pe.edu.upc.clearair.servicesinterfaces.ISuscripcionesService;
import pe.edu.upc.clearair.servicesinterfaces.IUbicacionesService;
import pe.edu.upc.clearair.servicesinterfaces.IUsuariosService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// HUAPI-10: Gestion de suscripciones
@RestController
@CrossOrigin
@RequestMapping("/api/suscripciones")
@Tag(name = "Suscripciones", description = "HUAPI-10: Activar/desactivar suscripciones de alertas por ubicacion")
public class SuscripcionesController {

    @Autowired
    private ISuscripcionesService service;

    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
    private IUbicacionesService ubicacionesService;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Listar todas las suscripciones | Acceso: ADMIN")
    @GetMapping("/lista")
    public ResponseEntity<?> lista() {
        List<SuscripcionesDTO> lista = service.list()
                .stream().map(s -> toDTO(s)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "HUAPI-10: Crear suscripcion de alertas | Acceso: ADMIN, ESPECIALISTA, USUARIO", description = "Guarda preferencias y actualiza estado de suscripcion")
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@RequestBody SuscripcionesDTO dto) {
        Optional<Usuarios> u = usuariosService.listId(dto.getIdUsuario());
        if (u.isEmpty()) return ResponseEntity.badRequest().body("Usuario no encontrado");
        Optional<Ubicaciones> ub = ubicacionesService.listId(dto.getIdUbicacion());
        if (ub.isEmpty()) return ResponseEntity.badRequest().body("Ubicacion no encontrada");
        Suscripciones s = new Suscripciones();
        s.setUsuario(u.get());
        s.setUbicacion(ub.get());
        s.setTipoPlan(dto.getTipoPlan());
        s.setEstadoSuscripcion(dto.getEstadoSuscripcion() != null ? dto.getEstadoSuscripcion() : "ACTIVA");
        Suscripciones saved = service.insert(s);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(saved));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "Actualizar estado de suscripcion | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @PutMapping("/actualiza")
    public ResponseEntity<?> actualiza(@RequestBody SuscripcionesDTO dto) {
        Optional<Suscripciones> opt = service.listId(dto.getIdSuscripcion());
        if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Suscripcion no encontrada");
        Suscripciones s = opt.get();
        s.setEstadoSuscripcion(dto.getEstadoSuscripcion());
        s.setTipoPlan(dto.getTipoPlan());
        service.update(s);
        return ResponseEntity.ok("Suscripcion actualizada correctamente");
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "QUERY 1 - Suscripciones por usuario | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> porUsuario(@PathVariable Integer idUsuario) {
        List<SuscripcionesDTO> lista = service.findByUsuario(idUsuario)
                .stream().map(s -> toDTO(s)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay suscripciones para este usuario");
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "QUERY 2 - Suscripciones por estado (ej: ACTIVA, INACTIVA) | Acceso: ADMIN")
    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> porEstado(@PathVariable String estado) {
        List<SuscripcionesDTO> lista = service.findByEstado(estado)
                .stream().map(s -> toDTO(s)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    private SuscripcionesDTO toDTO(Suscripciones s) {
        SuscripcionesDTO dto = new SuscripcionesDTO();
        dto.setIdSuscripcion(s.getIdSuscripcion());
        dto.setTipoPlan(s.getTipoPlan());
        dto.setFechaInicioSuscripcion(s.getFechaInicioSuscripcion());
        dto.setEstadoSuscripcion(s.getEstadoSuscripcion());
        if (s.getUsuario() != null) dto.setIdUsuario(s.getUsuario().getIdUsuarios());
        if (s.getUbicacion() != null) dto.setIdUbicacion(s.getUbicacion().getIdUbicaciones());
        return dto;
    }
}
