package pe.edu.upc.clearair.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "HistorialSesiones")
public class HistorialSesiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_HistorialSesiones")
    private Integer idHistorialSesiones;

    @ManyToOne
    @JoinColumn(name = "id_Usuarios", nullable = false)
    private Usuarios usuarios;

    @Column(name = "token_auth", length = 500)
    private String tokenAuth;

    @Column(name = "dispositivo_info", length = 255)
    private String dispositivoInfo;

    @Column(name = "navegador_info", length = 255)
    private String navegadorInfo;

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio = LocalDateTime.now();

    public Integer getIdHistorialSesiones() { return idHistorialSesiones; }
    public void setIdHistorialSesiones(Integer idHistorialSesiones) { this.idHistorialSesiones = idHistorialSesiones; }
    public Usuarios getUsuarios() { return usuarios; }
    public void setUsuarios(Usuarios usuarios) { this.usuarios = usuarios; }
    public String getTokenAuth() { return tokenAuth; }
    public void setTokenAuth(String tokenAuth) { this.tokenAuth = tokenAuth; }
    public String getDispositivoInfo() { return dispositivoInfo; }
    public void setDispositivoInfo(String dispositivoInfo) { this.dispositivoInfo = dispositivoInfo; }
    public String getNavegadorInfo() { return navegadorInfo; }
    public void setNavegadorInfo(String navegadorInfo) { this.navegadorInfo = navegadorInfo; }
    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }
}
