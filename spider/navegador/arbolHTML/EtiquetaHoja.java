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
    JLabel label = new JLabel();
    label.setText(toString());
    return label;
  }

  @Override
  public String desplegar() {
    return contenido;
  }

}
