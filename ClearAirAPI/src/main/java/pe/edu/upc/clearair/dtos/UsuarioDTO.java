package pe.edu.upc.clearair.dtos;

import java.time.LocalDateTime;

public class UsuarioDTO {
    private Integer idUsuarios;
    private String nombreCompleto;
    private String email;
    private String rol;
    private Boolean modoOscuro;
    private String unidadMedida;
    private String metodoPagoDefecto;
    private LocalDateTime fechaRegistro;
    private Boolean estaActivo;

    public Integer getIdUsuarios() { return idUsuarios; }
    public void setIdUsuarios(Integer idUsuarios) { this.idUsuarios = idUsuarios; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    public Boolean getModoOscuro() { return modoOscuro; }
    public void setModoOscuro(Boolean modoOscuro) { this.modoOscuro = modoOscuro; }
    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }
    public String getMetodoPagoDefecto() { return metodoPagoDefecto; }
    public void setMetodoPagoDefecto(String metodoPagoDefecto) { this.metodoPagoDefecto = metodoPagoDefecto; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public Boolean getEstaActivo() { return estaActivo; }
    public void setEstaActivo(Boolean estaActivo) { this.estaActivo = estaActivo; }
}
