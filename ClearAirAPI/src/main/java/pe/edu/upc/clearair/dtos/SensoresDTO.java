package pe.edu.upc.clearair.dtos;

public class SensoresDTO {
    private Integer idSensores;
    private Integer idUsuarios;
    private Integer idUbicacion;
    private String modeloLot;
    private Boolean esPersonal;
    private String estadoSincronizacion;

    public Integer getIdSensores() { return idSensores; }
    public void setIdSensores(Integer idSensores) { this.idSensores = idSensores; }
    public Integer getIdUsuarios() { return idUsuarios; }
    public void setIdUsuarios(Integer idUsuarios) { this.idUsuarios = idUsuarios; }
    public Integer getIdUbicacion() { return idUbicacion; }
    public void setIdUbicacion(Integer idUbicacion) { this.idUbicacion = idUbicacion; }
    public String getModeloLot() { return modeloLot; }
    public void setModeloLot(String modeloLot) { this.modeloLot = modeloLot; }
    public Boolean getEsPersonal() { return esPersonal; }
    public void setEsPersonal(Boolean esPersonal) { this.esPersonal = esPersonal; }
    public String getEstadoSincronizacion() { return estadoSincronizacion; }
    public void setEstadoSincronizacion(String estadoSincronizacion) { this.estadoSincronizacion = estadoSincronizacion; }
}
