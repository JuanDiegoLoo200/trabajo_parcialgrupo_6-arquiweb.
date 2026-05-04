package pe.edu.upc.clearair.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.clearair.dtos.UsuarioDTO;
import pe.edu.upc.clearair.dtos.UsuarioRegistroDTO;
import pe.edu.upc.clearair.entities.Rol;
import pe.edu.upc.clearair.entities.Usuarios;
import pe.edu.upc.clearair.repositories.IRolRepository;
import pe.edu.upc.clearair.servicesinterfaces.IUsuariosService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// HUAPI-01: Registro | HUAPI-03: Perfil | HUAPI-04: Actualizacion de perfil
@RestController
@CrossOrigin
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "HUAPI-01, HUAPI-03, HUAPI-04: Gestion de usuarios")
public class UsuariosController {

    @Autowired
    private IUsuariosService service;

    @Autowired
    private IRolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "HUAPI-01: Registrar nuevo usuario | Acceso: PUBLICO", description = "Solo permite roles USUARIO o ESPECIALISTA. El rol ADMIN se asigna directamente en la base de datos")
    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody UsuarioRegistroDTO registroDTO) {
        if (registroDTO.getNombreCompleto() == null || registroDTO.getNombreCompleto().isBlank())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre completo es obligatorio");
        if (registroDTO.getEmail() == null || registroDTO.getEmail().isBlank())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El email es obligatorio");
        if (registroDTO.getPassword() == null || registroDTO.getPassword().isBlank())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La contrasena es obligatoria");

        String rolNombre = registroDTO.getRol() != null ? registroDTO.getRol() : "USUARIO";
        if (!rolNombre.equals("USUARIO") && !rolNombre.equals("ESPECIALISTA"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Rol invalido. Solo se permite USUARIO o ESPECIALISTA en el registro. El rol ADMIN es asignado directamente en la base de datos");

        if (service.buscarPorEmail(registroDTO.getEmail()) != null)
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El email ya esta registrado. Utiliza otro email o inicia sesion");

        Optional<Rol> rolOpt = rolRepository.findByNombre(rolNombre);
        if (rolOpt.isEmpty())
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("El rol '" + rolNombre + "' no existe en el sistema. Contacta al administrador");

        Usuarios u = new Usuarios();
        u.setNombreCompleto(registroDTO.getNombreCompleto());
        u.setEmail(registroDTO.getEmail());
        u.setPasswordHash(passwordEncoder.encode(registroDTO.getPassword()));
        u.setRol(rolOpt.get());
        u.setEstaActivo(true);
        Usuarios saved = service.insert(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(saved));
    }

    @Operation(summary = "HUAPI-03: Obtener perfil del usuario | Acceso: PUBLICO", description = "Requiere token valido, retorna informacion del usuario por id")
    @GetMapping("/perfil/{id}")
    public ResponseEntity<?> getPerfil(@PathVariable Integer id) {
        Optional<Usuarios> opt = service.listId(id);
        if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        return ResponseEntity.ok(toDTO(opt.get()));
    }

    @Operation(summary = "HUAPI-04: Actualizar perfil del usuario | Acceso: PUBLICO", description = "Valida autorizacion, actualiza campos permitidos")
    @PutMapping("/perfil")
    public ResponseEntity<?> updatePerfil(@RequestBody UsuarioDTO dto) {
        Optional<Usuarios> opt = service.listId(dto.getIdUsuarios());
        if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        Usuarios u = opt.get();
        u.setNombreCompleto(dto.getNombreCompleto());
        u.setModoOscuro(dto.getModoOscuro());
        u.setUnidadMedida(dto.getUnidadMedida());
        u.setMetodoPagoDefecto(dto.getMetodoPagoDefecto());
        service.update(u);
        return ResponseEntity.ok("Perfil actualizado correctamente");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Listar todos los usuarios | Acceso: ADMIN")
    @GetMapping("/lista")
    public ResponseEntity<?> lista() {
        List<UsuarioDTO> lista = service.list()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Contar usuarios por rol (QUERY 2) | Acceso: ADMIN")
    @GetMapping("/count-rol")
    public ResponseEntity<?> contarPorRol(@RequestParam String rol) {
        long total = service.contarPorRol(rol);
        return ResponseEntity.ok("Total de usuarios con rol '" + rol + "': " + total);
    }

    private UsuarioDTO toDTO(Usuarios u) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuarios(u.getIdUsuarios());
        dto.setNombreCompleto(u.getNombreCompleto());
        dto.setEmail(u.getEmail());
        dto.setRol(u.getRol() != null ? u.getRol().getNombre() : null);
        dto.setModoOscuro(u.getModoOscuro());
        dto.setUnidadMedida(u.getUnidadMedida());
        dto.setMetodoPagoDefecto(u.getMetodoPagoDefecto());
        dto.setFechaRegistro(u.getFechaRegistro());
        dto.setEstaActivo(u.getEstaActivo());
        return dto;
    }
}
