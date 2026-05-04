package pe.edu.upc.clearair.dtos;

import java.math.BigDecimal;

public class UbicacionesDTO {
    private Integer idUbicaciones;
    private String nombreZona;
    private BigDecimal latitud;
    private BigDecimal longitud;
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
