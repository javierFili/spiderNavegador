package spider.navegador.frontend;

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
  private JButton contenido1Button;
  private JButton contenido2Button;

  private NavegadorWeb nav;

  public BuscadorGUI(NavegadorWeb nav) {
    super("Navegador Spider");
    setContentPane(panel1);
    contenido2Button.setVisible(false);
    contenido1Button.setVisible(false);
    arrancarListener();
    this.nav = nav;
  }

  public void arrancarListener() {
    botonDeBuscador.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String entrada = "GET;";
        entrada += datosDeEntrada.getText();
        crearBotones();
        String respuesta = nav.ejecutarPedido(entrada);
        CreadorArbol creador = new CreadorArbol();
        int poss = respuesta.indexOf(';');
        respuesta = respuesta.substring(poss + 1, respuesta.length());
        EtiquetaHTML etiquetaHTML = creador.crearDOM(respuesta);
        JComponent con = etiquetaHTML.graficar();
        String cad = con.getToolTipText();
        documentoShow.setText(cad);
      }
    });

    contenido1Button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String entrada = "GET;www.javierfiligrana.as;contenido1.html";
        String respuesta = nav.ejecutarPedido(entrada);
        CreadorArbol creador = new CreadorArbol();
        int poss = respuesta.indexOf(';');
        respuesta = respuesta.substring(poss + 1, respuesta.length());
        EtiquetaHTML etiquetaHTML = creador.crearDOM(respuesta);
        JComponent con = etiquetaHTML.graficar();
        String cad = con.getToolTipText();
        documentoShow.setText(cad);
      }
    });

    contenido2Button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String entrada = "GET;www.javierfiligrana.as;contenido2.html";
        String respuesta = nav.ejecutarPedido(entrada);
        CreadorArbol creador = new CreadorArbol();
        int poss = respuesta.indexOf(';');
        respuesta = respuesta.substring(poss + 1, respuesta.length());
        EtiquetaHTML etiquetaHTML = creador.crearDOM(respuesta);
        JComponent con = etiquetaHTML.graficar();
        String cad = con.getToolTipText();
        documentoShow.setText(cad);
      }
    });
  }

  private void crearBotones() {
    if (datosDeEntrada.getText().contains("index.html")) {
      contenido1Button.setVisible(true);
      contenido2Button.setVisible(true);
    } else {
      contenido1Button.setVisible(false);
      contenido2Button.setVisible(false);
    }
  }
}
