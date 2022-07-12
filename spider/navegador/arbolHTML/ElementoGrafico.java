package spider.navegador.arbolHTML;

import javax.swing.*;
import java.awt.*;

public class ElementoGrafico {
  public JComponent crearElementoGraficoSinContenido(EtiquetaEnum etiquetaEnum) {
    JComponent component = switch (etiquetaEnum) {
      case BODY, HTML -> crearComponenteContenedor();
      default -> null;
    };

    if (component != null) {
      component.setBackground(Color.WHITE);
    }
    return component;
  }

  public JComponent crearElementoGraficoConContenido(EtiquetaEnum etiquetaEnum, String contenido) {
    JComponent component = switch (etiquetaEnum) {
      case H1 -> crearComponenteH(contenido, 1);
      case H2 -> crearComponenteH(contenido, 2);
      case P -> crearComponenteP(contenido);
      case A -> crearComponenteA(contenido);
      default -> null;
    };

    if (component != null) {
      component.setBackground(Color.WHITE);
    }
    return component;
  }

  private JComponent crearComponenteContenedor() {
    JPanel jPanel = new JPanel();
    jPanel.setLayout(null);
    jPanel.setSize(1400, 1);
    return jPanel;
  }

  private JComponent crearComponenteH(String contenido, int tipoH) {
    int length = 60 - 5 * tipoH;
    int tamanioFuente = 40 - 3 * tipoH;

    Font font = new Font("Arial", Font.PLAIN, tamanioFuente);

    JLabel jLabel = new JLabel();
    jLabel.setText(contenido);
    jLabel.setSize(1400, length);
    jLabel.setFont(font);

    return jLabel;
  }

  private JComponent crearComponenteP(String contenido) {
    Font font = new Font("Arial", Font.PLAIN, 16);

    JLabel jLabel = new JLabel();
    jLabel.setText(contenido);
    jLabel.setSize(1400, 20);
    jLabel.setFont(font);

    return jLabel;
  }

  private JComponent crearComponenteA(String contenido) {
    Font font = new Font("Arial", Font.BOLD, 16);
    Color color = Color.blue;
    Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

    JButton jButton = new JButton();
    jButton.setText(contenido);
    jButton.setSize(contenido.length() * 10, 20);
    jButton.setFont(font);
    jButton.setForeground(color);
    jButton.setBorder(null);
    jButton.setHorizontalAlignment(SwingConstants.LEFT);
    jButton.setOpaque(true);
    jButton.setCursor(cursor);

    return jButton;
  }
}