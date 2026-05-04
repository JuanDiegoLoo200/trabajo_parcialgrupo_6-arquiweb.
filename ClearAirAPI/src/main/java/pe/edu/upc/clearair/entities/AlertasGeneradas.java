package pe.edu.upc.clearair.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "AlertasGeneradas")
public class AlertasGeneradas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_AlertasGeneradas")
    private Integer idAlertasGeneradas;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "id_ubicacion")
    private Ubicaciones ubicacion;

    @ManyToOne
    @JoinColumn(name = "id_contaminante")
    private TiposContaminantes contaminante;

    @Column(name = "valor_disparador", precision = 10, scale = 4)
    private BigDecimal valorDisparador;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio = LocalDateTime.now();

    public Integer getIdAlertasGeneradas() { return idAlertasGeneradas; }
    public void setIdAlertasGeneradas(Integer idAlertasGeneradas) { this.idAlertasGeneradas = idAlertasGeneradas; }
    public Usuarios getUsuario() { return usuario; }
    public void setUsuario(Usuarios usuario) { this.usuario = usuario; }
    public Ubicaciones getUbicacion() { return ubicacion; }
    public void setUbicacion(Ubicaciones ubicacion) { this.ubicacion = ubicacion; }
    public TiposContaminantes getContaminante() { return contaminante; }
    public void setContaminante(TiposContaminantes contaminante) { this.contaminante = contaminante; }
    public BigDecimal getValorDisparador() { return valorDisparador; }
    public void setValorDisparador(BigDecimal valorDisparador) { this.valorDisparador = valorDisparador; }
    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }
}
