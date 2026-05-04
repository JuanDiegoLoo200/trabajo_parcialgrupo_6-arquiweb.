package pe.edu.upc.clearair.dtos;

public class ContenidoInformativoDTO {
    private Integer idContenidoInformativo;
    private String categoria;
    private Integer idAutorAdmin;
    private String titulo;
    private String cuerpoTexto;

    public Integer getIdContenidoInformativo() { return idContenidoInformativo; }
    public void setIdContenidoInformativo(Integer idContenidoInformativo) { this.idContenidoInformativo = idContenidoInformativo; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public Integer getIdAutorAdmin() { return idAutorAdmin; }
    public void setIdAutorAdmin(Integer idAutorAdmin) { this.idAutorAdmin = idAutorAdmin; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getCuerpoTexto() { return cuerpoTexto; }
    public void setCuerpoTexto(String cuerpoTexto) { this.cuerpoTexto = cuerpoTexto; }
}
