package pe.edu.upc.clearair.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.clearair.dtos.CondicionesMedicasDTO;
import pe.edu.upc.clearair.entities.CondicionesMedicas;
import pe.edu.upc.clearair.servicesinterfaces.ICondicionesMedicasService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// HU-05: Configurar condiciones medicas
@RestController
@CrossOrigin
@RequestMapping("/api/condiciones")
@Tag(name = "CondicionesMedicas", description = "HU-05: Gestion de condiciones medicas para alertas personalizadas")
public class CondicionesMedicasController {

    @Autowired
    private ICondicionesMedicasService service;

    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "Listar todas las condiciones medicas | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/lista")
    public ResponseEntity<?> lista() {
        List<CondicionesMedicasDTO> lista = service.list()
                .stream().map(c -> modelMapper.map(c, CondicionesMedicasDTO.class))
                .collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA')")
    @Operation(summary = "Crear nueva condicion medica | Acceso: ADMIN, ESPECIALISTA")
    @PostMapping("/nuevo")
    public ResponseEntity<CondicionesMedicasDTO> nuevo(@RequestBody CondicionesMedicasDTO dto) {
        CondicionesMedicas saved = service.insert(modelMapper.map(dto, CondicionesMedicas.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(saved, CondicionesMedicasDTO.class));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "Obtener condicion medica por ID | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Optional<CondicionesMedicas> opt = service.listId(id);
        if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Condicion medica no encontrada");
        return ResponseEntity.ok(modelMapper.map(opt.get(), CondicionesMedicasDTO.class));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "QUERY 1 - Buscar condiciones medicas por nombre | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/busqueda")
    public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre) {
        List<CondicionesMedicasDTO> lista = service.buscarPorNombre(nombre)
                .stream().map(c -> modelMapper.map(c, CondicionesMedicasDTO.class))
                .collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA')")
    @Operation(summary = "QUERY 2 - Contar total de condiciones medicas registradas | Acceso: ADMIN, ESPECIALISTA")
    @GetMapping("/count")
    public ResponseEntity<?> contarTotal() {
        long total = service.contarTotal();
        return ResponseEntity.ok("Total de condiciones medicas: " + total);
    }
}
