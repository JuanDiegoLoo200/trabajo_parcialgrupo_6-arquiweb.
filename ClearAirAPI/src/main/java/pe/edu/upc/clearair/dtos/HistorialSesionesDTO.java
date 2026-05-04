package pe.edu.upc.clearair.dtos;

import java.time.LocalDateTime;

public class HistorialSesionesDTO {
    private Integer idHistorialSesiones;
    private Integer idUsuarios;
    private String tokenAuth;
    private String dispositivoInfo;
    private String navegadorInfo;
    private LocalDateTime fechaInicio;

    public Integer getIdHistorialSesiones() { return idHistorialSesiones; }
    public void setIdHistorialSesiones(Integer idHistorialSesiones) { this.idHistorialSesiones = idHistorialSesiones; }
    public Integer getIdUsuarios() { return idUsuarios; }
    public void setIdUsuarios(Integer idUsuarios) { this.idUsuarios = idUsuarios; }
    public String getTokenAuth() { return tokenAuth; }
    public void setTokenAuth(String tokenAuth) { this.tokenAuth = tokenAuth; }
    public String getDispositivoInfo() { return dispositivoInfo; }
    public void setDispositivoInfo(String dispositivoInfo) { this.dispositivoInfo = dispositivoInfo; }
    public String getNavegadorInfo() { return navegadorInfo; }
    public void setNavegadorInfo(String navegadorInfo) { this.navegadorInfo = navegadorInfo; }
    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }
}
