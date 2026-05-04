package pe.edu.upc.clearair.dtos;

import java.math.BigDecimal;

public class TiposContaminantesDTO {
    private Integer idTiposContaminantes;
    private String nombreContaminante;
    private String siglas;
    private String unidadEstandar;
    private BigDecimal umbralCritico;

    public Integer getIdTiposContaminantes() { return idTiposContaminantes; }
    public void setIdTiposContaminantes(Integer idTiposContaminantes) { this.idTiposContaminantes = idTiposContaminantes; }
    public String getNombreContaminante() { return nombreContaminante; }
    public void setNombreContaminante(String nombreContaminante) { this.nombreContaminante = nombreContaminante; }
    public String getSiglas() { return siglas; }
    public void setSiglas(String siglas) { this.siglas = siglas; }
    public String getUnidadEstandar() { return unidadEstandar; }
    public void setUnidadEstandar(String unidadEstandar) { this.unidadEstandar = unidadEstandar; }
    public BigDecimal getUmbralCritico() { return umbralCritico; }
    public void setUmbralCritico(BigDecimal umbralCritico) { this.umbralCritico = umbralCritico; }
}
