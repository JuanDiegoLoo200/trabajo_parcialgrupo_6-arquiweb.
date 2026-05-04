package pe.edu.upc.clearair.dtos;

import java.time.LocalDateTime;

public class ReportesExportadosDTO {
    private Integer idReportesExportados;
    private Integer idUsuarios;
    private String tipoFormato;
    private String urlDescarga;
    private LocalDateTime fechaGeneracion;

    public Integer getIdReportesExportados() { return idReportesExportados; }
    public void setIdReportesExportados(Integer idReportesExportados) { this.idReportesExportados = idReportesExportados; }
    public Integer getIdUsuarios() { return idUsuarios; }
    public void setIdUsuarios(Integer idUsuarios) { this.idUsuarios = idUsuarios; }
    public String getTipoFormato() { return tipoFormato; }
    public void setTipoFormato(String tipoFormato) { this.tipoFormato = tipoFormato; }
    public String getUrlDescarga() { return urlDescarga; }
    public void setUrlDescarga(String urlDescarga) { this.urlDescarga = urlDescarga; }
    public LocalDateTime getFechaGeneracion() { return fechaGeneracion; }
    public void setFechaGeneracion(LocalDateTime fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; }
}
