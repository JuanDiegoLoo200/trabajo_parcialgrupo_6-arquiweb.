package pe.edu.upc.clearair.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LecturasDTO {
    private Integer idLecturas;
    private Integer idSensores;
    private Integer idTiposContaminantes;
    private BigDecimal valorCrudo;
    private BigDecimal valorAqiCalculado;
    private Boolean esPronostico;
    private LocalDateTime fechaHora;

    public Integer getIdLecturas() { return idLecturas; }
    public void setIdLecturas(Integer idLecturas) { this.idLecturas = idLecturas; }
    public Integer getIdSensores() { return idSensores; }
    public void setIdSensores(Integer idSensores) { this.idSensores = idSensores; }
    public Integer getIdTiposContaminantes() { return idTiposContaminantes; }
    public void setIdTiposContaminantes(Integer idTiposContaminantes) { this.idTiposContaminantes = idTiposContaminantes; }
    public BigDecimal getValorCrudo() { return valorCrudo; }
    public void setValorCrudo(BigDecimal valorCrudo) { this.valorCrudo = valorCrudo; }
    public BigDecimal getValorAqiCalculado() { return valorAqiCalculado; }
    public void setValorAqiCalculado(BigDecimal valorAqiCalculado) { this.valorAqiCalculado = valorAqiCalculado; }
    public Boolean getEsPronostico() { return esPronostico; }
    public void setEsPronostico(Boolean esPronostico) { this.esPronostico = esPronostico; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
}
