package pe.edu.upc.clearair.dtos;

import java.time.LocalDateTime;

public class LogIntegracionAPIDTO {
    private Integer idLogIntegracionAPI;
    private Integer idSensorRelacionado;
    private LocalDateTime fechaEvento;
    private String estadoRespuesta;
    private String mensajeDetalle;

    public Integer getIdLogIntegracionAPI() { return idLogIntegracionAPI; }
    public void setIdLogIntegracionAPI(Integer idLogIntegracionAPI) { this.idLogIntegracionAPI = idLogIntegracionAPI; }
    public Integer getIdSensorRelacionado() { return idSensorRelacionado; }
    public void setIdSensorRelacionado(Integer idSensorRelacionado) { this.idSensorRelacionado = idSensorRelacionado; }
    public LocalDateTime getFechaEvento() { return fechaEvento; }
    public void setFechaEvento(LocalDateTime fechaEvento) { this.fechaEvento = fechaEvento; }
    public String getEstadoRespuesta() { return estadoRespuesta; }
    public void setEstadoRespuesta(String estadoRespuesta) { this.estadoRespuesta = estadoRespuesta; }
    public String getMensajeDetalle() { return mensajeDetalle; }
    public void setMensajeDetalle(String mensajeDetalle) { this.mensajeDetalle = mensajeDetalle; }
}
