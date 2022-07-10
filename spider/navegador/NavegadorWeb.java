package spider.navegador;

import spider.navegador.backend.CreadorArbol;
import spider.navegador.arbolHTML.EtiquetaHTML;
import spider.navegador.frontend.BuscadorGUI;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
    SwingUtilities.invokeLater
        (new Runnable() {
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
//preguntar a que se refiere con la exepcion de ServerNameNotFound.
  public String ejecutarPedido(String pedido) {
    //GET;servidor;recurso
    String servidor = "";
    String HOST = "";
    String recurso = "";
    try {
      int j, i = 0;
      i = pedido.indexOf(';');
      servidor = pedido.substring(i + 1, pedido.length());
      j = servidor.indexOf(';');
      recurso = servidor.substring(j + 1, servidor.length());
      servidor = servidor.substring(0, j);
      HOST = parSear(servidor);
      if (HOST == null) {
        return servidorNoExiste();
      }
    } catch (Exception e) {
      return servidorNoExiste();
    }
    int PUERTO = 8080;
    String mensajeIn = "";
    try {
      Socket skCliente = new Socket(HOST, PUERTO);
      in = new DataInputStream(skCliente.getInputStream());
      out = new DataOutputStream(skCliente.getOutputStream());
      mensajeOut = "GET;" + recurso;
      out.writeUTF(mensajeOut);
      mensajeIn = in.readUTF();

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return mensajeIn;
  }

  private String servidorNoExiste() {
    String salida = "<!DOCTYPE html>\n" +
        "<html>\n" +
        "<head>\n" +
        "    <meta>\n" +
        "    <title>Mensaje de error.</title>\n" +
        "</head>\n" +
        "<body>\n" +
        "    500 - Server error\n" +
        "</body>\n" +
        "</html>";
    CreadorArbol creador = new CreadorArbol();
    EtiquetaHTML etiquetaHTML = creador.crearDOM(salida);
    return etiquetaHTML.toString();
  }

  private String parSear(String url) {
    String nombre = internet.resolverNombre(url);
    return nombre;//es un tipo 198.127.1.0..algo asi o localhost.
  }

}