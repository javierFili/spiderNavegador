package spider.navegador.arbolHTML;

import javax.swing.*;

public interface EtiquetaHTML {
  String toString();     // Para mostrar el contenido del

  // componente en texto plano
  JComponent graficar(); // Para desplegar la GUI

  String desplegar();    // Para desplegar la consola
}
