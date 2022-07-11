package spider.navegador.frontend;

import spider.navegador.arbolHTML.EtiquetaHTML;
import spider.navegador.backend.CreadorArbol;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscadorGUI extends JFrame {
  private JLabel documentoShow;
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
        documentoShow.setText(con.getToolTipText());
      }
    });

    contenido1Button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String entrada = "GET;www.javierfiligrana.com.bo;contenido1.html";
        String respuesta = nav.ejecutarPedido(entrada);
        CreadorArbol creador = new CreadorArbol();
        int poss = respuesta.indexOf(';');
        respuesta = respuesta.substring(poss + 1, respuesta.length());
        EtiquetaHTML etiquetaHTML = creador.crearDOM(respuesta);
        JComponent con = etiquetaHTML.graficar();
        documentoShow.setText(con.getToolTipText());
      }
    });

    contenido2Button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String entrada = "GET;www.javierfiligrana.com.bo;contenido2.html";
        String respuesta = nav.ejecutarPedido(entrada);
        CreadorArbol creador = new CreadorArbol();
        int poss = respuesta.indexOf(';');
        respuesta = respuesta.substring(poss + 1, respuesta.length());
        EtiquetaHTML etiquetaHTML = creador.crearDOM(respuesta);
        JComponent con = etiquetaHTML.graficar();
        documentoShow.setText(con.getToolTipText());
      }
    });
  }

  private void crearBotones() {
    if (datosDeEntrada.getText().equals("www.javierfiligrana.com.bo;index.html")) {
      contenido1Button.setVisible(true);
      contenido2Button.setVisible(true);
    } else {
      contenido1Button.setVisible(false);
      contenido2Button.setVisible(false);
    }
  }
  /*private String obtenerCadena(String cadena) {
    String res = "";
    for (int i = 0; i < cadena.length(); i++) {
      if (cadena.charAt(i) == '<') {
        res += cadena.substring(0, i);
        for (int j = i; j < cadena.length(); j++) {
          if (cadena.charAt(j) == '>') {
            cadena = cadena.substring(j + 1, cadena.length());
            i = 0;
            j = cadena.length() + 1;
          }
        }
      }
    }
    System.out.println(res);
    return res;
  }*/
}
