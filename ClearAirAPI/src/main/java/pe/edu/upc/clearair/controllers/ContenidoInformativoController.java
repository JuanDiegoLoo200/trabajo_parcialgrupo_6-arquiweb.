package pe.edu.upc.clearair.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.clearair.dtos.ContenidoInformativoDTO;
import pe.edu.upc.clearair.entities.ContenidoInformativo;
import pe.edu.upc.clearair.entities.Usuarios;
import pe.edu.upc.clearair.servicesinterfaces.IContenidoInformativoService;
import pe.edu.upc.clearair.servicesinterfaces.IUsuariosService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// HUAPI-11: Recomendaciones ambientales por categoria
@RestController
@CrossOrigin
@RequestMapping("/api/contenido")
@Tag(name = "ContenidoInformativo", description = "HUAPI-11: Contenido educativo y recomendaciones ambientales")
public class ContenidoInformativoController {

    @Autowired
    private IContenidoInformativoService service;

    @Autowired
    private IUsuariosService usuariosService;

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "Listar todo el contenido informativo | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/lista")
    public ResponseEntity<?> lista() {
        List<ContenidoInformativoDTO> lista = service.list()
                .stream().map(c -> toDTO(c)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA')")
    @Operation(summary = "Crear nuevo contenido informativo | Acceso: ADMIN, ESPECIALISTA")
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@RequestBody ContenidoInformativoDTO dto) {
        ContenidoInformativo c = new ContenidoInformativo();
        c.setCategoria(dto.getCategoria());
        c.setTitulo(dto.getTitulo());
        c.setCuerpoTexto(dto.getCuerpoTexto());
        if (dto.getIdAutorAdmin() != null) {
            Optional<Usuarios> u = usuariosService.listId(dto.getIdAutorAdmin());
            u.ifPresent(c::setAutorAdmin);
        }
        ContenidoInformativo saved = service.insert(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(saved));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "Obtener contenido por ID | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Optional<ContenidoInformativo> opt = service.listId(id);
        if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contenido no encontrado");
        return ResponseEntity.ok(toDTO(opt.get()));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "QUERY 1 - Buscar contenido por categoria (HUAPI-11: Recomendaciones) | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<?> porCategoria(@PathVariable String categoria) {
        List<ContenidoInformativoDTO> lista = service.findByCategoria(categoria)
                .stream().map(c -> toDTO(c)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay contenido en la categoria: " + categoria);
        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "QUERY 2 - Buscar contenido por titulo | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/busqueda")
    public ResponseEntity<?> buscarPorTitulo(@RequestParam String titulo) {
        List<ContenidoInformativoDTO> lista = service.findByTitulo(titulo)
                .stream().map(c -> toDTO(c)).collect(Collectors.toList());
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    private ContenidoInformativoDTO toDTO(ContenidoInformativo c) {
        ContenidoInformativoDTO dto = new ContenidoInformativoDTO();
        dto.setIdContenidoInformativo(c.getIdContenidoInformativo());
        dto.setCategoria(c.getCategoria());
        dto.setTitulo(c.getTitulo());
        dto.setCuerpoTexto(c.getCuerpoTexto());
        if (c.getAutorAdmin() != null) dto.setIdAutorAdmin(c.getAutorAdmin().getIdUsuarios());
        return dto;
    }
}
