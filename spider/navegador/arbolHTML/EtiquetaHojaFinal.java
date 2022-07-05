package spider.navegador.arbolHTML;

public class EtiquetaHojaFinal implements EtiquetaHTML {
  private EtiquetaEnum tipo;
  private String contenido;

  public EtiquetaHojaFinal(EtiquetaEnum tipo, String contenido) {
    super();
    this.tipo = tipo;
    this.contenido = contenido;
  }

  public String toString() {
    return contenido;
  }

  @Override
  public EtiquetaEnum getTipoEtiquetaHTML() {
    return null;
  }
}
