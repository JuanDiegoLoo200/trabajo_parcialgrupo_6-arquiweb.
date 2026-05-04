package pe.edu.upc.clearair.dtos;

public class CondicionesMedicasDTO {
    private Integer idCondicionesMedicas;
    private String nombreCondicion;
    private String descripcion;

    public Integer getIdCondicionesMedicas() { return idCondicionesMedicas; }
    public void setIdCondicionesMedicas(Integer idCondicionesMedicas) { this.idCondicionesMedicas = idCondicionesMedicas; }
    public String getNombreCondicion() { return nombreCondicion; }
    public void setNombreCondicion(String nombreCondicion) { this.nombreCondicion = nombreCondicion; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
