package spider.navegador;

import spider.navegador.backend.CreadorComposite;
import spider.navegador.arbolHTML.EtiquetaHTML;
import spider.navegador.frontend.BuscadorGUI;
import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NavegadorWeb {
  private Internet internet;
  private String mensajeOut = "";
  private DataInputStream in;
  private DataOutputStream out;
  private NavegadorWeb nav;

  public NavegadorWeb(Internet internet) {
    this.internet = internet;
    this.nav = this;
  }

  public void run() {
    SwingUtilities.invokeLater(new Runnable() {
                                 @Override
                                 public void run() {
                                   JFrame frame = new BuscadorGUI(nav);
                                   frame.setSize(500, 550);
                                   frame.setVisible(true);
                                   frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                                 }
                               }
    );
  }


  public String ejecutarPedido(String pedido) {
    int i = 0;
    int j = 0;
    j = obtenerPosicionDeCaracter(pedido, ';', 0);
    String metodo = pedido.substring(0, j);

    i = obtenerPosicionDeCaracter(pedido, ';', 0);
    j = obtenerPosicionDeCaracter(pedido, ';', 1);
    String servidor = pedido.substring(i + 1, j);

    i = obtenerPosicionDeCaracter(pedido, ';', 1);
    j = pedido.length();
    String recurso = pedido.substring(i, j);//ojo!!
    String ip = parSear(servidor);
    String HOST = ip;
    int PUERTO = 8080;
    String mensajeIn = "";
    try {
      Socket skCliente = new Socket(HOST, PUERTO);
      in = new DataInputStream(skCliente.getInputStream());
      out = new DataOutputStream(skCliente.getOutputStream());

      try {
        mensajeOut = pedido;
        out.writeUTF(mensajeOut);
        mensajeIn = in.readUTF();

      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    CreadorComposite creador = new CreadorComposite();
    int poss = mensajeIn.indexOf(';');
    mensajeIn = mensajeIn.substring(poss + 1, mensajeIn.length());
    EtiquetaHTML etiquetaHTML = creador.crearDOM(mensajeIn);
    return etiquetaHTML.toString();
  }

  private String parSear(String url) {
    String nombre = internet.resolverNombre(url);
    return nombre;//es un tipo 198.127.1.0..algo asi o localhost.
  }

  private int obtenerPosicionDeCaracter(String cadena, char caracter, int n) {
    int res = 0;
    int a = n;
    while (res < cadena.length()) {
      if (cadena.charAt(res) == caracter) {
        if (n == 0) {
          break;
        }
        n--;
      }
      res++;
    }
    return res;
  }

}