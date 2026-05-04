package pe.edu.upc.clearair.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "UsuarioCondiciones")
public class UsuarioCondiciones {

    @EmbeddedId
    private UsuarioCondicionesId id;

    @ManyToOne
    @MapsId("idUsuarios")
    @JoinColumn(name = "id_Usuarios")
    private Usuarios usuarios;

    @ManyToOne
    @MapsId("idCondicionesMedicas")
    @JoinColumn(name = "id_CondicionesMedicas")
    private CondicionesMedicas condicionesMedicas;

    public UsuarioCondiciones() {}

    public UsuarioCondicionesId getId() { return id; }
    public void setId(UsuarioCondicionesId id) { this.id = id; }
    public Usuarios getUsuarios() { return usuarios; }
    public void setUsuarios(Usuarios usuarios) { this.usuarios = usuarios; }
    public CondicionesMedicas getCondicionesMedicas() { return condicionesMedicas; }
    public void setCondicionesMedicas(CondicionesMedicas condicionesMedicas) { this.condicionesMedicas = condicionesMedicas; }
}
