package pe.edu.upc.clearair.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import pe.edu.upc.clearair.entities.Rol;

@Entity
@Table(name = "Usuarios")
public class Usuarios implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Usuarios")
    private Integer idUsuarios;

    @Column(name = "nombre_completo", length = 150, nullable = false)
    private String nombreCompleto;

    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", length = 255, nullable = false)
    private String passwordHash;

    @Column(name = "reset_token", length = 255)
    private String resetToken;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @Column(name = "modo_oscuro")
    private Boolean modoOscuro = false;

    @Column(name = "unidad_medida", length = 20)
    private String unidadMedida;

    @Column(name = "metodo_pago_defecto", length = 50)
    private String metodoPagoDefecto;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @Column(name = "esta_activo")
    private Boolean estaActivo = true;

    public Integer getIdUsuarios() { return idUsuarios; }
    public void setIdUsuarios(Integer idUsuarios) { this.idUsuarios = idUsuarios; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getResetToken() { return resetToken; }
    public void setResetToken(String resetToken) { this.resetToken = resetToken; }
    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
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
