package pe.edu.upc.clearair.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LogIntegracionAPI")
public class LogIntegracionAPI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_LogIntegracionAPI")
    private Integer idLogIntegracionAPI;

    @ManyToOne
    @JoinColumn(name = "id_sensor_relacionado")
    private Sensores sensorRelacionado;

    @Column(name = "fecha_evento")
    private LocalDateTime fechaEvento = LocalDateTime.now();

    @Column(name = "estado_respuesta", length = 50)
    private String estadoRespuesta;

    @Column(name = "mensaje_detalle", columnDefinition = "TEXT")
    private String mensajeDetalle;

    public Integer getIdLogIntegracionAPI() { return idLogIntegracionAPI; }
    public void setIdLogIntegracionAPI(Integer idLogIntegracionAPI) { this.idLogIntegracionAPI = idLogIntegracionAPI; }
    public Sensores getSensorRelacionado() { return sensorRelacionado; }
    public void setSensorRelacionado(Sensores sensorRelacionado) { this.sensorRelacionado = sensorRelacionado; }
    public LocalDateTime getFechaEvento() { return fechaEvento; }
    public void setFechaEvento(LocalDateTime fechaEvento) { this.fechaEvento = fechaEvento; }
    public String getEstadoRespuesta() { return estadoRespuesta; }
    public void setEstadoRespuesta(String estadoRespuesta) { this.estadoRespuesta = estadoRespuesta; }
    public String getMensajeDetalle() { return mensajeDetalle; }
    public void setMensajeDetalle(String mensajeDetalle) { this.mensajeDetalle = mensajeDetalle; }
}
