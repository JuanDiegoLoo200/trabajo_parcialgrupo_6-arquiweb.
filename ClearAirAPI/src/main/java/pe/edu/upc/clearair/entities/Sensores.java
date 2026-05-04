package pe.edu.upc.clearair.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Sensores")
public class Sensores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sensores")
    private Integer idSensores;

    @ManyToOne
    @JoinColumn(name = "id_usuarios")
    private Usuarios usuarios;

    @ManyToOne
    @JoinColumn(name = "id_ubicacion")
    private Ubicaciones ubicacion;

    @Column(name = "modelo_lot", length = 100)
    private String modeloLot;

    @Column(name = "es_personal")
    private Boolean esPersonal = false;

    @Column(name = "estado_sincronizacion", length = 50)
    private String estadoSincronizacion;

    public Integer getIdSensores() { return idSensores; }
    public void setIdSensores(Integer idSensores) { this.idSensores = idSensores; }
    public Usuarios getUsuarios() { return usuarios; }
    public void setUsuarios(Usuarios usuarios) { this.usuarios = usuarios; }
    public Ubicaciones getUbicacion() { return ubicacion; }
    public void setUbicacion(Ubicaciones ubicacion) { this.ubicacion = ubicacion; }
    public String getModeloLot() { return modeloLot; }
    public void setModeloLot(String modeloLot) { this.modeloLot = modeloLot; }
    public Boolean getEsPersonal() { return esPersonal; }
    public void setEsPersonal(Boolean esPersonal) { this.esPersonal = esPersonal; }
    public String getEstadoSincronizacion() { return estadoSincronizacion; }
    public void setEstadoSincronizacion(String estadoSincronizacion) { this.estadoSincronizacion = estadoSincronizacion; }
}
