package pe.edu.upc.clearair.dtos;

import java.math.BigDecimal;
import java.time.LocalTime;

public class ConfiguracionAlertasDTO {
    private Integer idConfiguracionAlertas;
    private Integer idUsuarios;
    private BigDecimal sensibilidadUmbral;
    private LocalTime horarioInicio;
    private LocalTime horarioFin;

    public Integer getIdConfiguracionAlertas() { return idConfiguracionAlertas; }
    public void setIdConfiguracionAlertas(Integer idConfiguracionAlertas) { this.idConfiguracionAlertas = idConfiguracionAlertas; }
    public Integer getIdUsuarios() { return idUsuarios; }
    public void setIdUsuarios(Integer idUsuarios) { this.idUsuarios = idUsuarios; }
    public BigDecimal getSensibilidadUmbral() { return sensibilidadUmbral; }
    public void setSensibilidadUmbral(BigDecimal sensibilidadUmbral) { this.sensibilidadUmbral = sensibilidadUmbral; }
    public LocalTime getHorarioInicio() { return horarioInicio; }
    public void setHorarioInicio(LocalTime horarioInicio) { this.horarioInicio = horarioInicio; }
    public LocalTime getHorarioFin() { return horarioFin; }
    public void setHorarioFin(LocalTime horarioFin) { this.horarioFin = horarioFin; }
}
