package pe.edu.upc.clearair.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ContenidoInformativo")
public class ContenidoInformativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ContenidoInformativo")
    private Integer idContenidoInformativo;

    @Column(name = "categoria", length = 100)
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "id_autor_admin")
    private Usuarios autorAdmin;

    @Column(name = "titulo", length = 255, nullable = false)
    private String titulo;

    @Column(name = "cuerpo_texto", columnDefinition = "TEXT")
    private String cuerpoTexto;

    public Integer getIdContenidoInformativo() { return idContenidoInformativo; }
    public void setIdContenidoInformativo(Integer idContenidoInformativo) { this.idContenidoInformativo = idContenidoInformativo; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public Usuarios getAutorAdmin() { return autorAdmin; }
    public void setAutorAdmin(Usuarios autorAdmin) { this.autorAdmin = autorAdmin; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getCuerpoTexto() { return cuerpoTexto; }
    public void setCuerpoTexto(String cuerpoTexto) { this.cuerpoTexto = cuerpoTexto; }
}
