package spider.navegador.frontend;

import spider.navegador.Internet;
import spider.navegador.ServerNameNotFound;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class NavegadorWeb {
  private Internet internet;
  private NavegadorWeb nav;

  public NavegadorWeb(Internet internet) {
    this.nav = this;
    this.internet = internet;
  }

  public String ejecutarPedido(String url) {
    String recurso = "<HTML><H1>CODIGO 500 </H1><H2>Server error</H2></HTML>";
    if (!url.contains(";")) {
      return "400;<HTML><H1> CODIGO 400 </H1><H2>Bad Request</H2></HTML>";
    }
    try {
      int firstCut = url.indexOf(";");
      String server = url.substring(0, firstCut);
      String ip = internet.resolverNombre(server);
      Socket skCliente = new Socket(ip, 8080);
      DataInputStream in = new DataInputStream(skCliente.getInputStream());
      DataOutputStream out = new DataOutputStream(skCliente.getOutputStream());
      String[] partirTraduccion = url.split(";");
      String recursoPartido = "index.html";
      if (partirTraduccion.length > 1) {
        recursoPartido = partirTraduccion[1];
      }
      out.writeUTF("GET" + ";" + recursoPartido);
      String result = in.readUTF();
      String[] partesRespuesta = result.split(";", 2);
      String codigo = partesRespuesta[0];
      recurso = partesRespuesta[1];
      skCliente.close();


    } catch (Exception e) {
      System.out.println(e);
    } catch (ServerNameNotFound e) {
      return recurso;
    }
    return recurso;
  }

  public void run() {
    BuscadorGUI gui = new BuscadorGUI(internet, nav);
  }
}
