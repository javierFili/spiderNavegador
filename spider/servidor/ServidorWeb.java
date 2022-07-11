package spider.servidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServidorWeb {
  private File recursosDisponibles;
  private String nombre;
  private static final int PUERTO = 8080;

  public ServidorWeb(String nombreServidor) {
    this.nombre = nombreServidor;
  }

  public void iniciar() {
    try {
      ServerSocket skServidor = new ServerSocket(PUERTO);
      System.out.println("Arrancando el servidor.");
      while (true) {
        Socket skCliente = skServidor.accept();
        DataInputStream in = new DataInputStream(skCliente.getInputStream());
        DataOutputStream out = new DataOutputStream(skCliente.getOutputStream());
        String mesajeIn = "";
        String mesajeOut = "";
        mesajeIn = in.readUTF();
        mesajeOut = ejecutarPedido(mesajeIn);
        out.writeUTF(mesajeOut);
        skCliente.close();
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private String obtenerRespuesta(String p) {
    String recurso = p;
    String respuesta = para404;
    Pattern pattern = Pattern.compile("^[1-9-a-zA-Z.]*");
    Matcher matcher = pattern.matcher(recurso);
    boolean b = matcher.matches();
    if (!b) {
      return para400;
    }
    recurso = obtenerRecursoHtml(this.nombre, recurso);
    if (recurso.length() <= 1) {
      return respuesta;
    }
    respuesta = "200;" + recurso;
    return respuesta;
  }

  private String obtenerRecursoHtml(String nombre, String recurso) {
    FileReader leer;
    BufferedReader almacenamiento;
    System.out.println("buscando el recurso:" + nombre + " y recurso: " + recurso);
    String html = "", informacionSacada = "";
    String pathName = "servidores/" + nombre + "/" + recurso;
    recursosDisponibles = new File(pathName);
    long hayContenido = recursosDisponibles.length();
    if (hayContenido != 0) {
      try {
        leer = new FileReader(recursosDisponibles);
        almacenamiento = new BufferedReader(leer);
        while (informacionSacada != null) {
          informacionSacada = almacenamiento.readLine();
          if (informacionSacada != null) {
            html += informacionSacada + "\n";
          }
        }
        almacenamiento.close();
      } catch (Exception e) {
        System.out.println(e.toString());
      }
    }
    return html;
  }

  public String ejecutarPedido(String pedido) {
    int i = 0;
    String recurso = "";
    String metodo = "";
    try {
      i = pedido.indexOf(';');
      metodo = pedido.substring(0, i);
      recurso = pedido.substring(i + 1, pedido.length());
      if (metodo.equals("GET")) {
        String res = obtenerRespuesta(recurso);
        return res;
      }
    } catch (Exception e) {
      return para400;
    }
    return null;
  }

  private String para400 = 400 + ";" +
      "<HTML>\n" +
      "<BODY>\n" +
      "<P>400 - Bad-request</P>\n" +
      "</BODY>\n" +
      "</HTML>";
  private String para404 = 404 +
      ";<HTML>\n" +
      "<BODY>\n" +
      "<P> 404 - Not found</P>\n" +
      "</BODY>\n" +
      "</HTML>";
}
