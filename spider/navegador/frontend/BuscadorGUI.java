package spider.navegador.frontend;

import spider.navegador.NavegadorWeb;
import spider.navegador.arbolHTML.EtiquetaHTML;
import spider.navegador.backend.CreadorArbol;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscadorGUI extends JFrame {
  private JTextArea documentoShow;
  private JTextField datosDeEntrada;
  private JButton botonDeBuscador;
  private JPanel panel1;

  private NavegadorWeb nav;

  public BuscadorGUI(NavegadorWeb nav) {
    super("Navegador Spider");
    setContentPane(panel1);
    arrancarListener();
    this.nav = nav;
  }

  public void arrancarListener() {
    botonDeBuscador.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String entrada = "GET;";
        entrada += datosDeEntrada.getText();
        String respuesta = nav.ejecutarPedido(entrada);
        CreadorArbol creador = new CreadorArbol();
        int poss = respuesta.indexOf(';');
        respuesta = respuesta.substring(poss + 1, respuesta.length());
        EtiquetaHTML etiquetaHTML = creador.crearDOM(respuesta);
        respuesta = etiquetaHTML.toString();
        documentoShow.setText(respuesta);
      }
    });
  }
}
