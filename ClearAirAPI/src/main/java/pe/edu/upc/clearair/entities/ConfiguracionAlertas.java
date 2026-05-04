package pe.edu.upc.clearair.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "ConfiguracionAlertas")
public class ConfiguracionAlertas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ConfiguracionAlertas")
    private Integer idConfiguracionAlertas;

    @ManyToOne
    @JoinColumn(name = "id_Usuarios", nullable = false)
    private Usuarios usuarios;

    @Column(name = "sensibilidad_umbral", precision = 10, scale = 4)
    private BigDecimal sensibilidadUmbral;

    @Column(name = "horario_inicio")
    private LocalTime horarioInicio;

    @Column(name = "horario_fin")
    private LocalTime horarioFin;

    public Integer getIdConfiguracionAlertas() { return idConfiguracionAlertas; }
    public void setIdConfiguracionAlertas(Integer idConfiguracionAlertas) { this.idConfiguracionAlertas = idConfiguracionAlertas; }
    public Usuarios getUsuarios() { return usuarios; }
    public void setUsuarios(Usuarios usuarios) { this.usuarios = usuarios; }
    public BigDecimal getSensibilidadUmbral() { return sensibilidadUmbral; }
    public void setSensibilidadUmbral(BigDecimal sensibilidadUmbral) { this.sensibilidadUmbral = sensibilidadUmbral; }
    public LocalTime getHorarioInicio() { return horarioInicio; }
    public void setHorarioInicio(LocalTime horarioInicio) { this.horarioInicio = horarioInicio; }
    public LocalTime getHorarioFin() { return horarioFin; }
    public void setHorarioFin(LocalTime horarioFin) { this.horarioFin = horarioFin; }
}
