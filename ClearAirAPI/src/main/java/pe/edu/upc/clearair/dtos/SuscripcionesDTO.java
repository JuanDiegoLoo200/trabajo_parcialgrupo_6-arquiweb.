package pe.edu.upc.clearair.dtos;

import java.time.LocalDateTime;

public class SuscripcionesDTO {
    private Integer idSuscripcion;
    private Integer idUsuario;
    private Integer idUbicacion;
    private String tipoPlan;
    private LocalDateTime fechaInicioSuscripcion;
    private String estadoSuscripcion;

    public Integer getIdSuscripcion() { return idSuscripcion; }
    public void setIdSuscripcion(Integer idSuscripcion) { this.idSuscripcion = idSuscripcion; }
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
    public Integer getIdUbicacion() { return idUbicacion; }
    public void setIdUbicacion(Integer idUbicacion) { this.idUbicacion = idUbicacion; }
    public String getTipoPlan() { return tipoPlan; }
    public void setTipoPlan(String tipoPlan) { this.tipoPlan = tipoPlan; }
    public LocalDateTime getFechaInicioSuscripcion() { return fechaInicioSuscripcion; }
    public void setFechaInicioSuscripcion(LocalDateTime fechaInicioSuscripcion) { this.fechaInicioSuscripcion = fechaInicioSuscripcion; }
    public String getEstadoSuscripcion() { return estadoSuscripcion; }
    public void setEstadoSuscripcion(String estadoSuscripcion) { this.estadoSuscripcion = estadoSuscripcion; }
}
