package spider.navegador.arbolHTML;

public class EtiquetaRama implements EtiquetaHTML {

  private EtiquetaEnum tipo;
  private String contenido;

  public EtiquetaRama(EtiquetaEnum tipo, String contenido) {
    super();
    this.tipo = tipo;
    this.contenido = contenido;
    generarArbol();
  }

  private void generarArbol() {
    Convertidor con = new Convertidor();
    con.generarArbol(contenido);
    ArbolNArio ar = con.getArbol();
  }

  public String toString() {
    return contenido;
  }

  public void insertarHijo(EtiquetaHTML hijo) {

  }

  @Override
  public Tipo metodo() {
    return null;
  }
}
