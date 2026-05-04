package pe.edu.upc.clearair.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.clearair.servicesinterfaces.IAlertasGeneradasService;
import pe.edu.upc.clearair.servicesinterfaces.ILecturasService;
import pe.edu.upc.clearair.servicesinterfaces.ISensoresService;
import pe.edu.upc.clearair.servicesinterfaces.IUsuariosService;

import java.util.HashMap;
import java.util.Map;

// HUAPI-15: Estadisticas generales del sistema ClearAir
@RestController
@CrossOrigin
@RequestMapping("/api/estadisticas")
@Tag(name = "Estadisticas", description = "HUAPI-15: Panel analitico con estadisticas generales del sistema")
public class EstadisticasController {

    @Autowired
    private ILecturasService lecturasService;

    @Autowired
    private IAlertasGeneradasService alertasService;

    @Autowired
    private ISensoresService sensoresService;

    @Autowired
    private IUsuariosService usuariosService;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "HUAPI-15: Resumen general del sistema | Acceso: ADMIN", description = "Retorna metricas clave: total de lecturas, alertas, sensores y usuarios. Solo ADMIN")
    @GetMapping("/resumen")
    public ResponseEntity<?> resumenGeneral() {
        Map<String, Object> estadisticas = new HashMap<>();
        estadisticas.put("totalLecturas", lecturasService.list().size());
        estadisticas.put("totalAlertas", alertasService.list().size());
        estadisticas.put("totalSensores", sensoresService.list().size());
        estadisticas.put("totalUsuarios", usuariosService.contarPorRol("USUARIO"));
        estadisticas.put("totalEspecialistas", usuariosService.contarPorRol("ESPECIALISTA"));
        estadisticas.put("totalAdmins", usuariosService.contarPorRol("ADMIN"));
        return ResponseEntity.ok(estadisticas);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "HUAPI-15: Lecturas AQI en tiempo real por ubicacion | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/calidad-aire/{idUbicacion}")
    public ResponseEntity<?> calidadAirePorUbicacion(@PathVariable Integer idUbicacion) {
        var lecturas = lecturasService.findUltimasLecturasPorUbicacion(idUbicacion);
        if (lecturas.isEmpty())
            return ResponseEntity.ok("No hay lecturas disponibles para la ubicacion " + idUbicacion);
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("ubicacionId", idUbicacion);
        resultado.put("totalLecturas", lecturas.size());
        resultado.put("ultimaLectura", lecturas.get(0));
        return ResponseEntity.ok(resultado);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESPECIALISTA','USUARIO')")
    @Operation(summary = "HUAPI-15: Alertas generadas por ubicacion | Acceso: ADMIN, ESPECIALISTA, USUARIO")
    @GetMapping("/alertas-ubicacion/{idUbicacion}")
    public ResponseEntity<?> alertasPorUbicacion(@PathVariable Integer idUbicacion) {
        var alertas = alertasService.findByUbicacion(idUbicacion);
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("ubicacionId", idUbicacion);
        resultado.put("totalAlertas", alertas.size());
        resultado.put("alertas", alertas);
        return ResponseEntity.ok(resultado);
    }
}
