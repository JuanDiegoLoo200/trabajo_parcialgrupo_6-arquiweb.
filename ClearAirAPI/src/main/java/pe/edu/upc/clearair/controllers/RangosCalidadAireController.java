package pe.edu.upc.clearair.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.clearair.dtos.RangosCalidadAireDTO;
import pe.edu.upc.clearair.entities.RangosCalidadAire;
import pe.edu.upc.clearair.entities.TiposContaminantes;
import pe.edu.upc.clearair.servicesinterfaces.IRangosCalidadAireService;
import pe.edu.upc.clearair.servicesinterfaces.ITiposContaminantesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// HUAPI-08, HUAPI-11: Rangos AQI y recomendaciones ambientales
@RestController
@CrossOrigin
@RequestMapping("/api/rangos")
@Tag(name = "RangosCalidadAire", description = "HUAPI-08, HUAPI-11: Rangos AQI y mensajes de salud/actividad")
public class RangosCalidadAireController {

    @Autowired
    private IRangosCalidadAireService service;

    @Autowired
    private ITiposContaminantesService tiposService;

    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "Listar todos los rangos de calidad del aire | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/lista")
    public ResponseEntity<?> lista() {
        List<RangosCalidadAireDTO> lista = service.list()
                .stream().map(r -> toDTO(r)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Crear rango de calidad del aire | Acceso: ADMIN")
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@RequestBody RangosCalidadAireDTO dto) {
        Optional<TiposContaminantes> tipo = tiposService.listId(dto.getIdTiposContaminantes());
        if (tipo.isEmpty()) return ResponseEntity.badRequest().body("Tipo de contaminante no encontrado");
        RangosCalidadAire r = new RangosCalidadAire();
        r.setTiposContaminantes(tipo.get());
        r.setMinValor(dto.getMinValor());
        r.setMaxValor(dto.getMaxValor());
        r.setEtiquetaAqi(dto.getEtiquetaAqi());
        r.setColorHex(dto.getColorHex());
        r.setMensajeSalud(dto.getMensajeSalud());
        r.setMensajeActividad(dto.getMensajeActividad());
        RangosCalidadAire saved = service.insert(r);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(saved));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "QUERY 1 - Obtener rangos por tipo de contaminante | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/contaminante/{id}")
    public ResponseEntity<?> porContaminante(@PathVariable Integer id) {
        List<RangosCalidadAireDTO> lista = service.buscarPorContaminante(id)
                .stream().map(r -> toDTO(r)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "QUERY 2 - Buscar rangos por etiqueta AQI (HUAPI-11: Recomendaciones) | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/etiqueta/{etiqueta}")
    public ResponseEntity<?> porEtiqueta(@PathVariable String etiqueta) {
        List<RangosCalidadAireDTO> lista = service.buscarPorEtiqueta(etiqueta)
                .stream().map(r -> toDTO(r)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay rangos con etiqueta: " + etiqueta);
        return ResponseEntity.ok(lista);
    }

    private RangosCalidadAireDTO toDTO(RangosCalidadAire r) {
        RangosCalidadAireDTO dto = new RangosCalidadAireDTO();
        dto.setIdRangosCalidadAire(r.getIdRangosCalidadAire());
        dto.setMinValor(r.getMinValor());
        dto.setMaxValor(r.getMaxValor());
        dto.setEtiquetaAqi(r.getEtiquetaAqi());
        dto.setColorHex(r.getColorHex());
        dto.setMensajeSalud(r.getMensajeSalud());
        dto.setMensajeActividad(r.getMensajeActividad());
        if (r.getTiposContaminantes() != null) dto.setIdTiposContaminantes(r.getTiposContaminantes().getIdTiposContaminantes());
        return dto;
    }
}
