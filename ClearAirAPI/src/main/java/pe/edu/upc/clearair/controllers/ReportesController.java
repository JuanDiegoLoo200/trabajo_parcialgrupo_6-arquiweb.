package pe.edu.upc.clearair.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.clearair.dtos.ReportesExportadosDTO;
import pe.edu.upc.clearair.entities.ReportesExportados;
import pe.edu.upc.clearair.entities.Usuarios;
import pe.edu.upc.clearair.servicesinterfaces.IReportesExportadosService;
import pe.edu.upc.clearair.servicesinterfaces.IUsuariosService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// HUAPI-12: Generar y exportar reportes ambientales
@RestController
@CrossOrigin
@RequestMapping("/api/reportes")
@Tag(name = "ReportesExportados", description = "HUAPI-12, HU-15: Generacion y exportacion de reportes ambientales")
public class ReportesController {

    @Autowired
    private IReportesExportadosService service;

    @Autowired
    private IUsuariosService usuariosService;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Listar todos los reportes exportados | Acceso: ADMIN")
    @GetMapping("/lista")
    public ResponseEntity<?> lista() {
        List<ReportesExportadosDTO> lista = service.list()
                .stream().map(r -> toDTO(r)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "HUAPI-12: Generar reporte ambiental | Acceso: ADMIN, ESPECIALISTA, USUARIO", description = "Genera reporte con rango de fechas y habilita exportacion")
    @PostMapping("/generar")
    public ResponseEntity<?> generar(@RequestBody ReportesExportadosDTO dto) {
        Optional<Usuarios> u = usuariosService.listId(dto.getIdUsuarios());
        if (u.isEmpty()) return ResponseEntity.badRequest().body("Usuario no encontrado");
        ReportesExportados r = new ReportesExportados();
        r.setUsuarios(u.get());
        r.setTipoFormato(dto.getTipoFormato() != null ? dto.getTipoFormato() : "PDF");
        r.setUrlDescarga(dto.getUrlDescarga());
        r.setFechaGeneracion(LocalDateTime.now());
        ReportesExportados saved = service.insert(r);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(saved));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "QUERY 1 - Reportes por usuario | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> porUsuario(@PathVariable Integer idUsuario) {
        List<ReportesExportadosDTO> lista = service.findByUsuario(idUsuario)
                .stream().map(r -> toDTO(r)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay reportes para este usuario");
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "QUERY 2 - Reportes por formato (ej: PDF, CSV, EXCEL) | Acceso: ADMIN")
    @GetMapping("/formato/{tipo}")
    public ResponseEntity<?> porFormato(@PathVariable String tipo) {
        List<ReportesExportadosDTO> lista = service.findByTipoFormato(tipo)
                .stream().map(r -> toDTO(r)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    private ReportesExportadosDTO toDTO(ReportesExportados r) {
        ReportesExportadosDTO dto = new ReportesExportadosDTO();
        dto.setIdReportesExportados(r.getIdReportesExportados());
        dto.setTipoFormato(r.getTipoFormato());
        dto.setUrlDescarga(r.getUrlDescarga());
        dto.setFechaGeneracion(r.getFechaGeneracion());
        if (r.getUsuarios() != null) dto.setIdUsuarios(r.getUsuarios().getIdUsuarios());
        return dto;
    }
}
