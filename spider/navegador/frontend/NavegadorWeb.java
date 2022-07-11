package spider.navegador.frontend;

import spider.navegador.Internet;
import spider.navegador.ServerNameNotFound;

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
             frame.setSize(500, 650);
             frame.setVisible(true);
             frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
           }
         }
        );
  }

  public String ejecutarPedido(String pedido) {
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
    } catch (Exception e) {
      return servidorNoExiste();
    } catch (ServerNameNotFound e) {
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
    String salida = "<HTML>\n" +
        "<BODY>\n" +
        "<P>500 - Server error</P>\n" +
        "</BODY>\n" +
        "</HTML>";
    return salida;
  }

  private String parSear(String url) throws ServerNameNotFound {
    String nombre = internet.resolverNombre(url);
    return nombre;
  }

}