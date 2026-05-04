package pe.edu.upc.clearair.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Suscripciones")
public class Suscripciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suscripcion")
    private Integer idSuscripcion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "id_ubicacion", nullable = false)
    private Ubicaciones ubicacion;

    @Column(name = "tipo_plan", length = 50)
    private String tipoPlan;

    @Column(name = "fecha_inicio_suscripcion")
    private LocalDateTime fechaInicioSuscripcion = LocalDateTime.now();

    @Column(name = "estado_suscripcion", length = 50)
    private String estadoSuscripcion;

    public Integer getIdSuscripcion() { return idSuscripcion; }
    public void setIdSuscripcion(Integer idSuscripcion) { this.idSuscripcion = idSuscripcion; }
    public Usuarios getUsuario() { return usuario; }
    public void setUsuario(Usuarios usuario) { this.usuario = usuario; }
    public Ubicaciones getUbicacion() { return ubicacion; }
    public void setUbicacion(Ubicaciones ubicacion) { this.ubicacion = ubicacion; }
    public String getTipoPlan() { return tipoPlan; }
    public void setTipoPlan(String tipoPlan) { this.tipoPlan = tipoPlan; }
    public LocalDateTime getFechaInicioSuscripcion() { return fechaInicioSuscripcion; }
    public void setFechaInicioSuscripcion(LocalDateTime fechaInicioSuscripcion) { this.fechaInicioSuscripcion = fechaInicioSuscripcion; }
    public String getEstadoSuscripcion() { return estadoSuscripcion; }
    public void setEstadoSuscripcion(String estadoSuscripcion) { this.estadoSuscripcion = estadoSuscripcion; }
}
