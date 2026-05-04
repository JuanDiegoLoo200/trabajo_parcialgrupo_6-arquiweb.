package pe.edu.upc.clearair.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioCondicionesId implements Serializable {

    @Column(name = "id_Usuarios")
    private Integer idUsuarios;

    @Column(name = "id_CondicionesMedicas")
    private Integer idCondicionesMedicas;

    public UsuarioCondicionesId() {}

    public UsuarioCondicionesId(Integer idUsuarios, Integer idCondicionesMedicas) {
        this.idUsuarios = idUsuarios;
        this.idCondicionesMedicas = idCondicionesMedicas;
    }

    public Integer getIdUsuarios() { return idUsuarios; }
    public void setIdUsuarios(Integer idUsuarios) { this.idUsuarios = idUsuarios; }
    public Integer getIdCondicionesMedicas() { return idCondicionesMedicas; }
    public void setIdCondicionesMedicas(Integer idCondicionesMedicas) { this.idCondicionesMedicas = idCondicionesMedicas; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioCondicionesId)) return false;
        UsuarioCondicionesId that = (UsuarioCondicionesId) o;
        return Objects.equals(idUsuarios, that.idUsuarios) &&
                Objects.equals(idCondicionesMedicas, that.idCondicionesMedicas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuarios, idCondicionesMedicas);
    }
}
