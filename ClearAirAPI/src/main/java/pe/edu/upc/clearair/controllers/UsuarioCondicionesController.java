package pe.edu.upc.clearair.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.clearair.entities.CondicionesMedicas;
import pe.edu.upc.clearair.entities.UsuarioCondiciones;
import pe.edu.upc.clearair.entities.UsuarioCondicionesId;
import pe.edu.upc.clearair.entities.Usuarios;
import pe.edu.upc.clearair.servicesinterfaces.ICondicionesMedicasService;
import pe.edu.upc.clearair.servicesinterfaces.IUsuarioCondicionesService;
import pe.edu.upc.clearair.servicesinterfaces.IUsuariosService;

import java.util.List;
import java.util.Optional;

// HU-05: Configurar condiciones medicas del usuario
@RestController
@CrossOrigin
@RequestMapping("/api/usuario-condiciones")
@Tag(name = "UsuarioCondiciones", description = "HU-05: Asociacion de condiciones medicas a usuarios para alertas personalizadas")
public class UsuarioCondicionesController {

    @Autowired
    private IUsuarioCondicionesService service;

    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
    private ICondicionesMedicasService condicionesService;

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "QUERY 1 - Obtener condiciones medicas de un usuario | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> condicionesPorUsuario(@PathVariable Integer idUsuario) {
        List<UsuarioCondiciones> lista = service.findCondicionesByUsuario(idUsuario);
        if (lista.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay condiciones medicas registradas para el usuario " + idUsuario);
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA')")
    @Operation(summary = "QUERY 2 - Obtener usuarios con una condicion medica especifica | Acceso: ADMIN, ESPECIALISTA")
    @GetMapping("/condicion/{idCondicion}")
    public ResponseEntity<?> usuariosPorCondicion(@PathVariable Integer idCondicion) {
        List<UsuarioCondiciones> lista = service.findUsuariosByCondicion(idCondicion);
        if (lista.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay usuarios con la condicion medica " + idCondicion);
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "HU-05: Agregar condicion medica a un usuario | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @PostMapping("/{idUsuario}/{idCondicion}")
    public ResponseEntity<?> agregar(@PathVariable Integer idUsuario, @PathVariable Integer idCondicion) {
        Optional<Usuarios> u = usuariosService.listId(idUsuario);
        if (u.isEmpty()) return ResponseEntity.badRequest().body("Usuario no encontrado");
        Optional<CondicionesMedicas> c = condicionesService.listId(idCondicion);
        if (c.isEmpty()) return ResponseEntity.badRequest().body("Condicion medica no encontrada");
        UsuarioCondicionesId id = new UsuarioCondicionesId(idUsuario, idCondicion);
        UsuarioCondiciones uc = new UsuarioCondiciones();
        uc.setId(id);
        uc.setUsuarios(u.get());
        uc.setCondicionesMedicas(c.get());
        service.insert(uc);
        return ResponseEntity.status(HttpStatus.CREATED).body("Condicion medica asociada al usuario correctamente");
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "Remover condicion medica de un usuario | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @DeleteMapping("/{idUsuario}/{idCondicion}")
    public ResponseEntity<?> remover(@PathVariable Integer idUsuario, @PathVariable Integer idCondicion) {
        UsuarioCondicionesId id = new UsuarioCondicionesId(idUsuario, idCondicion);
        service.delete(id);
        return ResponseEntity.ok("Condicion medica removida del usuario correctamente");
    }
}
