package pe.edu.upc.clearair.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Ubicaciones")
public class Ubicaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicaciones")
    private Integer idUbicaciones;

    @Column(name = "nombre_zona", length = 150)
    private String nombreZona;

    @Column(name = "latitud", precision = 10, scale = 7)
    private BigDecimal latitud;

    @Column(name = "longitud", precision = 10, scale = 7)
    private BigDecimal longitud;

    @Column(name = "zona_horaria", length = 50)
    private String zonaHoraria;

    public Integer getIdUbicaciones() { return idUbicaciones; }
    public void setIdUbicaciones(Integer idUbicaciones) { this.idUbicaciones = idUbicaciones; }
    public String getNombreZona() { return nombreZona; }
    public void setNombreZona(String nombreZona) { this.nombreZona = nombreZona; }
    public BigDecimal getLatitud() { return latitud; }
    public void setLatitud(BigDecimal latitud) { this.latitud = latitud; }
    public BigDecimal getLongitud() { return longitud; }
    public void setLongitud(BigDecimal longitud) { this.longitud = longitud; }
    public String getZonaHoraria() { return zonaHoraria; }
    public void setZonaHoraria(String zonaHoraria) { this.zonaHoraria = zonaHoraria; }
}
