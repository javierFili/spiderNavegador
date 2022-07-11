package spider.servidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServidorWeb {
  private List<ServidorWeb> servidores;
  private File recursosDisponibles = new File("RecursosDisponibles.txt");
  private String nombre;
  private static final int PUERTO = 8080;

  public ServidorWeb(String nombreServidor) {
    servidores = new ArrayList<>();
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
    String respuesta = "404;" + para404;
    Pattern pattern = Pattern.compile("^[a-zA-Z.]*");
    Matcher matcher = pattern.matcher(recurso);
    boolean b = matcher.matches();
    if (!b) {
      return "400;" + para400;
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
      return 400 + ";\n" +
          "<html>\n" +
          "<head>\n" +
          "    <meta charset=\"UTF-8\">\n" +
          "    <title>Mensaje de error.</title>\n" +
          "</head>\n" +
          "<body>\n" +
          "    400 - Bad-request\n" +
          "</body>\n" +
          "</html>";
    }

    //aun no procesamos "POST","PUT";
    return null;
  }

  private String para400 = "\n" +
      "<html>\n" +
      "<head>\n" +
      "    <meta charset=\"UTF-8\">\n" +
      "    <title>Mensaje de error.</title>\n" +
      "</head>\n" +
      "<body>\n" +
      "   <div>\n" +
      "       400 - Bad request\n" +
      "   </div>\n" +
      "</body>\n" +
      "</html>";
  private String para404 = "" +
      "<html>\n" +
      "<head>\n" +
      "    <meta charset=\"UTF-8\">\n" +
      "    <title>Mensaje de error.</title>\n" +
      "</head>\n" +
      "<body>\n" +
      "    404 - Not found\n" +
      "</body>\n" +
      "</html>";

}
