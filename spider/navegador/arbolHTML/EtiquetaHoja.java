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
    /*JLabel label = new JLabel();
    label.setText(toString());*/
    ElementoGrafico creador = new ElementoGrafico();
    return creador.crearElementoGraficoConContenido(tipo, contenido);
  }

  /*@Override
  public JComponent graficar() {
    ElementoGrafico creadorElementoGrafico = new ElementoGrafico();
    JPanel jPanel = (JPanel) creadorElementoGrafico.crearElementoGraficoConContenido(tipo, contenido);
    return jPanel;
  }*/
  @Override
  public String desplegar() {
    return contenido;
  }

}
