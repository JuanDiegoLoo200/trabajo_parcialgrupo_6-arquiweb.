package pe.edu.upc.clearair.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Lecturas")
public class Lecturas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Lecturas")
    private Integer idLecturas;

    @ManyToOne
    @JoinColumn(name = "id_Sensores", nullable = false)
    private Sensores sensores;

    @ManyToOne
    @JoinColumn(name = "id_TiposContaminantes", nullable = false)
    private TiposContaminantes tiposContaminantes;

    @Column(name = "valor_crudo", precision = 10, scale = 4)
    private BigDecimal valorCrudo;

    @Column(name = "valor_aqi_calculado", precision = 10, scale = 4)
    private BigDecimal valorAqiCalculado;

    @Column(name = "es_pronostico")
    private Boolean esPronostico = false;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora = LocalDateTime.now();

    public Integer getIdLecturas() { return idLecturas; }
    public void setIdLecturas(Integer idLecturas) { this.idLecturas = idLecturas; }
    public Sensores getSensores() { return sensores; }
    public void setSensores(Sensores sensores) { this.sensores = sensores; }
    public TiposContaminantes getTiposContaminantes() { return tiposContaminantes; }
    public void setTiposContaminantes(TiposContaminantes tiposContaminantes) { this.tiposContaminantes = tiposContaminantes; }
    public BigDecimal getValorCrudo() { return valorCrudo; }
    public void setValorCrudo(BigDecimal valorCrudo) { this.valorCrudo = valorCrudo; }
    public BigDecimal getValorAqiCalculado() { return valorAqiCalculado; }
    public void setValorAqiCalculado(BigDecimal valorAqiCalculado) { this.valorAqiCalculado = valorAqiCalculado; }
    public Boolean getEsPronostico() { return esPronostico; }
    public void setEsPronostico(Boolean esPronostico) { this.esPronostico = esPronostico; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
}
