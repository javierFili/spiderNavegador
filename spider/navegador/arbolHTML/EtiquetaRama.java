package spider.navegador.arbolHTML;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EtiquetaRama implements EtiquetaHTML {
  private EtiquetaEnum tipo;
  private List<EtiquetaHTML> hijos;

  public EtiquetaRama(EtiquetaEnum etiqueta) {
    super();
    this.tipo = etiqueta;
    hijos = new ArrayList<>();
  }

  @Override
  public JComponent graficar() {
    return null;
  }

  @Override
  public String desplegar() {
    return null;
  }

  public void insertarHijo(EtiquetaHTML hijo) {
    hijos.add(hijo);
  }
}
