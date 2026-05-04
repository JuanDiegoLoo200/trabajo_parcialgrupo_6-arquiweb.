package pe.edu.upc.clearair.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TiposContaminantes")
public class TiposContaminantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_TiposContaminantes")
    private Integer idTiposContaminantes;

    @Column(name = "nombre_contaminante", length = 100, nullable = false)
    private String nombreContaminante;

    @Column(name = "siglas", length = 20)
    private String siglas;

    @Column(name = "unidad_estandar", length = 50)
    private String unidadEstandar;

    @Column(name = "umbral_critico", precision = 10, scale = 4)
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
