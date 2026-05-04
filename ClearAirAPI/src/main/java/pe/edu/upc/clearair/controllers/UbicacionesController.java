package pe.edu.upc.clearair.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.clearair.dtos.UbicacionesDTO;
import pe.edu.upc.clearair.entities.Ubicaciones;
import pe.edu.upc.clearair.servicesinterfaces.IUbicacionesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// HUAPI-14: Gestion de ubicaciones monitoreadas
@RestController
@CrossOrigin
@RequestMapping("/api/ubicaciones")
@Tag(name = "Ubicaciones", description = "HUAPI-14: Registro y consulta de ubicaciones geograficas")
public class UbicacionesController {

    @Autowired
    private IUbicacionesService service;

    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "Listar todas las ubicaciones | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/lista")
    public ResponseEntity<?> lista() {
        List<UbicacionesDTO> lista = service.list()
                .stream().map(u -> modelMapper.map(u, UbicacionesDTO.class))
                .collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "HUAPI-14: Registrar nueva ubicacion | Acceso: ADMIN", description = "Guarda coordenadas y zona horaria correctamente. Solo ADMIN")
    @PostMapping("/nuevo")
    public ResponseEntity<UbicacionesDTO> nuevo(@RequestBody UbicacionesDTO dto) {
        Ubicaciones saved = service.insert(modelMapper.map(dto, Ubicaciones.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(saved, UbicacionesDTO.class));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "Obtener ubicacion por ID | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Optional<Ubicaciones> opt = service.listId(id);
        if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ubicacion no encontrada");
        return ResponseEntity.ok(modelMapper.map(opt.get(), UbicacionesDTO.class));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Actualizar ubicacion | Acceso: ADMIN")
    @PutMapping("/actualiza")
    public ResponseEntity<?> actualiza(@RequestBody UbicacionesDTO dto) {
        if (service.listId(dto.getIdUbicaciones()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ubicacion no encontrada");
        service.update(modelMapper.map(dto, Ubicaciones.class));
        return ResponseEntity.ok("Ubicacion actualizada correctamente");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar ubicacion | Acceso: ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> elimina(@PathVariable Integer id) {
        if (service.listId(id).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ubicacion no encontrada");
        service.delete(id);
        return ResponseEntity.ok("Ubicacion eliminada correctamente");
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "QUERY 1 - Buscar ubicaciones por nombre de zona | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/busqueda/zona")
    public ResponseEntity<?> buscarPorZona(@RequestParam String nombre) {
        List<UbicacionesDTO> lista = service.buscarPorNombreZona(nombre)
                .stream().map(u -> modelMapper.map(u, UbicacionesDTO.class))
                .collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "QUERY 2 - Buscar ubicaciones por zona horaria (ej: America/Lima) | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/busqueda/zona-horaria")
    public ResponseEntity<?> buscarPorZonaHoraria(@RequestParam String zonaHoraria) {
        List<UbicacionesDTO> lista = service.buscarPorZonaHoraria(zonaHoraria)
                .stream().map(u -> modelMapper.map(u, UbicacionesDTO.class))
                .collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }
}
