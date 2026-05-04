package pe.edu.upc.clearair.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.clearair.dtos.JwtResponseDTO;
import pe.edu.upc.clearair.entities.HistorialSesiones;
import pe.edu.upc.clearair.entities.Usuarios;
import pe.edu.upc.clearair.servicesinterfaces.IHistorialSesionesService;
import pe.edu.upc.clearair.servicesinterfaces.IUsuariosService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/historial-sesiones")
@Tag(name = "HistorialSesiones", description = "Gestion del historial de sesiones de usuarios")
public class HistorialSesionesController {

    @Autowired
    private IHistorialSesionesService service;

    @Autowired
    private IUsuariosService usuariosService;

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "Registrar nueva sesion | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@RequestBody pe.edu.upc.clearair.dtos.HistorialSesionesDTO dto) {
        Optional<Usuarios> u = usuariosService.listId(dto.getIdUsuarios());
        if (u.isEmpty()) return ResponseEntity.badRequest().body("Usuario no encontrado");
        HistorialSesiones h = new HistorialSesiones();
        h.setUsuarios(u.get());
        h.setTokenAuth(dto.getTokenAuth());
        h.setDispositivoInfo(dto.getDispositivoInfo());
        h.setNavegadorInfo(dto.getNavegadorInfo());
        h.setFechaInicio(LocalDateTime.now());
        HistorialSesiones saved = service.insert(h);
        return ResponseEntity.status(HttpStatus.CREATED).body("Sesion registrada con id: " + saved.getIdHistorialSesiones());
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA')")
    @Operation(summary = "QUERY 1 - Obtener sesiones de un usuario | Acceso: ADMIN, ESPECIALISTA")
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> porUsuario(@PathVariable Integer idUsuario) {
        List<HistorialSesiones> lista = service.findSesionesByUsuario(idUsuario);
        if (lista.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay sesiones para este usuario");
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA')")
    @Operation(summary = "QUERY 2 - Contar sesiones de un usuario | Acceso: ADMIN, ESPECIALISTA")
    @GetMapping("/count/{idUsuario}")
    public ResponseEntity<?> contarSesiones(@PathVariable Integer idUsuario) {
        long total = service.contarSesionesByUsuario(idUsuario);
        return ResponseEntity.ok("Total de sesiones del usuario " + idUsuario + ": " + total);
    }
}
