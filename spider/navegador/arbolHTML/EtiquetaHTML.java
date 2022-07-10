package spider.navegador.arbolHTML;

import javax.swing.*;

public interface EtiquetaHTML {
  public String toString();     // Para mostrar el contenido del
  // componente en texto plano
  public JComponent graficar(); // Para desplegar la GUI
  public String desplegar();    // Para desplegar la consola

}
