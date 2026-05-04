package pe.edu.upc.clearair.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ReportesExportados")
public class ReportesExportados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ReportesExportados")
    private Integer idReportesExportados;

    @ManyToOne
    @JoinColumn(name = "id_Usuarios")
    private Usuarios usuarios;

    @Column(name = "tipo_formato", length = 50)
    private String tipoFormato;

    @Column(name = "url_descarga", length = 500)
    private String urlDescarga;

    @Column(name = "fecha_generacion")
    private LocalDateTime fechaGeneracion = LocalDateTime.now();

    public Integer getIdReportesExportados() { return idReportesExportados; }
    public void setIdReportesExportados(Integer idReportesExportados) { this.idReportesExportados = idReportesExportados; }
    public Usuarios getUsuarios() { return usuarios; }
    public void setUsuarios(Usuarios usuarios) { this.usuarios = usuarios; }
    public String getTipoFormato() { return tipoFormato; }
    public void setTipoFormato(String tipoFormato) { this.tipoFormato = tipoFormato; }
    public String getUrlDescarga() { return urlDescarga; }
    public void setUrlDescarga(String urlDescarga) { this.urlDescarga = urlDescarga; }
    public LocalDateTime getFechaGeneracion() { return fechaGeneracion; }
    public void setFechaGeneracion(LocalDateTime fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; }
}
