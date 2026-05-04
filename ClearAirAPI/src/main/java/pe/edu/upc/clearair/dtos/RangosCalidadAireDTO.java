package pe.edu.upc.clearair.dtos;

import java.math.BigDecimal;

public class RangosCalidadAireDTO {
    private Integer idRangosCalidadAire;
    private Integer idTiposContaminantes;
    private BigDecimal minValor;
    private BigDecimal maxValor;
    private String etiquetaAqi;
    private String colorHex;
    private String mensajeSalud;
    private String mensajeActividad;

    public Integer getIdRangosCalidadAire() { return idRangosCalidadAire; }
    public void setIdRangosCalidadAire(Integer idRangosCalidadAire) { this.idRangosCalidadAire = idRangosCalidadAire; }
    public Integer getIdTiposContaminantes() { return idTiposContaminantes; }
    public void setIdTiposContaminantes(Integer idTiposContaminantes) { this.idTiposContaminantes = idTiposContaminantes; }
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
