package pe.edu.upc.clearair.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "CondicionesMedicas")
public class CondicionesMedicas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_CondicionesMedicas")
    private Integer idCondicionesMedicas;

    @Column(name = "nombre_condicion", length = 100, nullable = false)
    private String nombreCondicion;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;



    public Integer getIdCondicionesMedicas() { return idCondicionesMedicas; }
    public void setIdCondicionesMedicas(Integer idCondicionesMedicas) { this.idCondicionesMedicas = idCondicionesMedicas; }
    public String getNombreCondicion() { return nombreCondicion; }
    public void setNombreCondicion(String nombreCondicion) { this.nombreCondicion = nombreCondicion; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
