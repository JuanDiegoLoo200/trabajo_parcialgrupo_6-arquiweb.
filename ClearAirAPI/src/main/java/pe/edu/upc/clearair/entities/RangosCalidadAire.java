package pe.edu.upc.clearair.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "RangosCalidadAire")
public class RangosCalidadAire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_RangosCalidadAire")
    private Integer idRangosCalidadAire;

    @ManyToOne
    @JoinColumn(name = "id_TiposContaminantes", nullable = false)
    private TiposContaminantes tiposContaminantes;

    @Column(name = "min_valor", precision = 10, scale = 4)
    private BigDecimal minValor;

    @Column(name = "max_valor", precision = 10, scale = 4)
    private BigDecimal maxValor;

    @Column(name = "etiqueta_aqi", length = 50)
    private String etiquetaAqi;

    @Column(name = "color_hex", length = 7)
    private String colorHex;

    @Column(name = "mensaje_salud", columnDefinition = "TEXT")
    private String mensajeSalud;

    @Column(name = "mensaje_actividad", columnDefinition = "TEXT")
    private String mensajeActividad;

    public Integer getIdRangosCalidadAire() { return idRangosCalidadAire; }
    public void setIdRangosCalidadAire(Integer idRangosCalidadAire) { this.idRangosCalidadAire = idRangosCalidadAire; }
    public TiposContaminantes getTiposContaminantes() { return tiposContaminantes; }
    public void setTiposContaminantes(TiposContaminantes tiposContaminantes) { this.tiposContaminantes = tiposContaminantes; }
    public BigDecimal getMinValor() { return minValor; }
    public void setMinValor(BigDecimal minValor) { this.minValor = minValor; }
    public BigDecimal getMaxValor() { return maxValor; }
    public void setMaxValor(BigDecimal maxValor) { this.maxValor = maxValor; }
    public String getEtiquetaAqi() { return etiquetaAqi; }
    public void setEtiquetaAqi(String etiquetaAqi) { this.etiquetaAqi = etiquetaAqi; }
    public String getColorHex() { return colorHex; }
    public void setColorHex(String colorHex) { this.colorHex = colorHex; }
    public String getMensajeSalud() { return mensajeSalud; }
    public void setMensajeSalud(String mensajeSalud) { this.mensajeSalud = mensajeSalud; }
    public String getMensajeActividad() { return mensajeActividad; }
    public void setMensajeActividad(String mensajeActividad) { this.mensajeActividad = mensajeActividad; }
}
