package spider.navegador.arbolHTML;

public class EtiquetaHoja implements EtiquetaHTML {
  private EtiquetaEnum tipo;
  private String contenido;

  public EtiquetaHoja(EtiquetaEnum tipo, String contenido) {
    super();
    this.tipo = tipo;
    this.contenido = contenido;
  }

  public String toString() {
    return contenido;
  }

  @Override
  public Tipo metodo() {
    return null;
  }
}
