package spider.navegador.arbolHTML;

import javax.swing.*;

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
  public JComponent graficar() {
    ElementoGrafico creador = new ElementoGrafico();
    return creador.crearElementoGraficoConContenido(tipo, contenido);
  }

  @Override
  public String desplegar() {
    return contenido;
  }

}
