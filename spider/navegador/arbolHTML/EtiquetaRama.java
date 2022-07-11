package spider.navegador.arbolHTML;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EtiquetaRama implements EtiquetaHTML {
  private EtiquetaEnum tipo;
  private List<EtiquetaHTML> hijos;
  private List<JLabel> hojas;
  private String texto = "";

  public EtiquetaRama(EtiquetaEnum etiqueta) {
    super();
    this.tipo = etiqueta;
    hijos = new ArrayList<>();
    hojas = new ArrayList<>();
  }

  @Override
  public JComponent graficar() {
    JLabel label = new JLabel();
    String cadenaTexto = toString();
    label.setToolTipText(cadenaTexto);
    return label;
  }


  @Override
  public String desplegar() {
    return toString();
  }

  public void insertarHijo(EtiquetaHTML hijo) {
    hijos.add(hijo);
  }

  private String obtenerTextos() {
    String contenido = "";
    for (int i = 0; i < hijos.size(); i++) {
      if (hijos.get(i) instanceof EtiquetaHoja) {
        contenido += "\n" + hijos.get(i).toString();
      } else {
        contenido += "\n    " + hijos.get(i).toString();
      }
    }
    return contenido;
  }

  @Override
  public String toString() {
    String contenido = obtenerTextos();
    return contenido;
  }
}
