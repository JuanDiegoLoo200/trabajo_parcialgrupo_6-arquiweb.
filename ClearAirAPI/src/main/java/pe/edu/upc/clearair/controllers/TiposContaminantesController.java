package pe.edu.upc.clearair.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.clearair.dtos.TiposContaminantesDTO;
import pe.edu.upc.clearair.entities.TiposContaminantes;
import pe.edu.upc.clearair.servicesinterfaces.ITiposContaminantesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// HUAPI-13: Filtrar contaminantes
@RestController
@CrossOrigin
@RequestMapping("/api/contaminantes")
@Tag(name = "TiposContaminantes", description = "HUAPI-13: Gestion y filtrado de tipos de contaminantes")
public class TiposContaminantesController {

    @Autowired
    private ITiposContaminantesService service;

    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "Listar todos los tipos de contaminantes | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/lista")
    public ResponseEntity<?> lista() {
        List<TiposContaminantesDTO> lista = service.list()
                .stream().map(t -> modelMapper.map(t, TiposContaminantesDTO.class))
                .collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Registrar nuevo tipo de contaminante | Acceso: ADMIN")
    @PostMapping("/nuevo")
    public ResponseEntity<TiposContaminantesDTO> nuevo(@RequestBody TiposContaminantesDTO dto) {
        TiposContaminantes saved = service.insert(modelMapper.map(dto, TiposContaminantes.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(saved, TiposContaminantesDTO.class));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "Obtener contaminante por ID | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Optional<TiposContaminantes> opt = service.listId(id);
        if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contaminante no encontrado");
        return ResponseEntity.ok(modelMapper.map(opt.get(), TiposContaminantesDTO.class));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Actualizar tipo de contaminante | Acceso: ADMIN")
    @PutMapping("/actualiza")
    public ResponseEntity<?> actualiza(@RequestBody TiposContaminantesDTO dto) {
        if (service.listId(dto.getIdTiposContaminantes()).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contaminante no encontrado");
        service.update(modelMapper.map(dto, TiposContaminantes.class));
        return ResponseEntity.ok("Contaminante actualizado correctamente");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar tipo de contaminante | Acceso: ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> elimina(@PathVariable Integer id) {
        if (service.listId(id).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contaminante no encontrado");
        service.delete(id);
        return ResponseEntity.ok("Contaminante eliminado correctamente");
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "QUERY 1 - Buscar contaminantes por nombre | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/busqueda/nombre")
    public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre) {
        List<TiposContaminantesDTO> lista = service.buscarPorNombre(nombre)
                .stream().map(t -> modelMapper.map(t, TiposContaminantesDTO.class))
                .collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "QUERY 2 - Buscar contaminante por siglas (ej: PM2.5, CO2) | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/busqueda/siglas")
    public ResponseEntity<?> buscarPorSiglas(@RequestParam String siglas) {
        Optional<TiposContaminantes> opt = service.buscarPorSiglas(siglas);
        if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro contaminante con siglas: " + siglas);
        return ResponseEntity.ok(modelMapper.map(opt.get(), TiposContaminantesDTO.class));
    }
}
