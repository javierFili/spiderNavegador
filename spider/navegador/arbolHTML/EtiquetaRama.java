package spider.navegador.arbolHTML;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EtiquetaRama implements EtiquetaHTML {
  private EtiquetaEnum tipo;
  private List<EtiquetaHTML> hijos;
  private String texto = "";

  public EtiquetaRama(EtiquetaEnum etiqueta) {
    super();
    this.tipo = etiqueta;
    hijos = new ArrayList<>();
  }

  @Override
  public JComponent graficar() {
    ElementoGrafico creadorElementoGrafico = new ElementoGrafico();
    JPanel jPanel = (JPanel) creadorElementoGrafico.crearElementoGraficoSinContenido(tipo);
    for (EtiquetaHTML etiquetaHTML : hijos) {
      JComponent nuevo = etiquetaHTML.graficar();
      anadirNuevaEtiquetaAUnPanel(jPanel, nuevo);
    }
    return jPanel;
  }

  private void anadirNuevaEtiquetaAUnPanel(JPanel panel, JComponent nuevaEtiqueta) {
    int posYpanel = panel.getHeight();
    int anchoPanel = panel.getWidth();
    int tamanioNuevaEtiqueta = nuevaEtiqueta.getHeight();
    panel.setSize(anchoPanel, posYpanel + tamanioNuevaEtiqueta + 5);
    nuevaEtiqueta.setLocation(5, posYpanel + 5);
    panel.add(nuevaEtiqueta);
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
        contenido += "\n\t" + hijos.get(i).toString();
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
