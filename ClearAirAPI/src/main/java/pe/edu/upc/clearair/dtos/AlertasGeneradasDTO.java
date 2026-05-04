package pe.edu.upc.clearair.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AlertasGeneradasDTO {
    private Integer idAlertasGeneradas;
    private Integer idUsuario;
    private Integer idUbicacion;
    private Integer idContaminante;
    private BigDecimal valorDisparador;
    private LocalDateTime fechaEnvio;

    public Integer getIdAlertasGeneradas() { return idAlertasGeneradas; }
    public void setIdAlertasGeneradas(Integer idAlertasGeneradas) { this.idAlertasGeneradas = idAlertasGeneradas; }
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
    public Integer getIdUbicacion() { return idUbicacion; }
    public void setIdUbicacion(Integer idUbicacion) { this.idUbicacion = idUbicacion; }
    public Integer getIdContaminante() { return idContaminante; }
    public void setIdContaminante(Integer idContaminante) { this.idContaminante = idContaminante; }
    public BigDecimal getValorDisparador() { return valorDisparador; }
    public void setValorDisparador(BigDecimal valorDisparador) { this.valorDisparador = valorDisparador; }
    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }
}
