package spider.servidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServidorWeb {
  private ArrayList<String> recursos = new ArrayList<>();
  private List<ServidorWeb> servidores;
  private File recursosDisponibles = new File("RecursosDisponibles.txt");
  private String nombre;

  public ServidorWeb(String nombreServidor) {
    servidores = new ArrayList<>();
    this.nombre = nombreServidor;
  }

  private static final int PUERTO = 8080;

  public void iniciar() {
    try {
      ServerSocket skServidor = new ServerSocket(PUERTO);
      System.out.println("Escucho el puerto " + PUERTO);
      int n = 1;
      while (n > 0) {
        n--;
        Socket skCliente = skServidor.accept();
        DataInputStream in = new DataInputStream(skCliente.getInputStream());
        DataOutputStream out = new DataOutputStream(skCliente.getOutputStream());
        String mesajeIn = "";
        String mesajeOut = "";
        int a = 0;
        while (a < 5) {
          System.out.println("Consulta numero" + a);
          mesajeIn = in.readUTF();
          mesajeOut = ejecutarPedido(mesajeIn);
          out.writeUTF(mesajeOut);
          a++;
        }
        skCliente.close();
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }


  //este debe tener salida de String
  public String obtenerRespuesta(String p) {
    String recurso = p;
    recurso = obtenerRecurso(recurso);
    String respuesta = "404;" + para404;
    Pattern pattern = Pattern.compile("^[a-zA-Z.]*");
    Matcher matcher = pattern.matcher(recurso);
    boolean b = matcher.matches();
    if (!b) {
      return "400;" + para400;
    }
    if (true) {
      recurso = obtenerRecursoHtml(this.nombre, recurso);
      if (recurso.length() <= 1) {
        return respuesta;
      }
      respuesta = "200;" + recurso;
    }
    return respuesta;
  }

  private String obtenerRecursoHtml(String nombre, String recurso) {
    FileReader leer;
    BufferedReader almacenamiento;
    System.out.println("buscando el recurso:" + nombre + "y " + recurso);
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

  private String obtenerRecurso(String recurso) {
    int i = obtenerPosChar(recurso, ';', 0);
    if (i == -1) {
      return "2111";
    }
    recurso = recurso.substring(i + 1, recurso.length());
    return recurso;
  }

  private int obtenerPosChar(String cadena, char subCad, int ini) {
    int res = ini;
    int ress = 0;
    while (res < cadena.length()) {
      if (cadena.charAt(res) == subCad) {
        ress = res;
        break;
      } else {
        ress = -1;
      }
      res++;
    }
    return ress;
  }

  public void registrar(ServidorWeb servidorWeb) {
    if (!servidores.contains(servidorWeb)) {
      servidores.add(servidorWeb);
    }
  }

  private ServidorWeb obtenerServidor(String nombreServidor) {
    ServidorWeb servidor = null;
    for (int i = 0; i < servidores.size(); i++) {
      if (servidores.get(i).getNombre().equals(nombreServidor)) {
        servidor = servidores.get(i);
      }
    }
    return servidor;
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

  public String ejecutarPedido(String pedido) {
    int i = 0;
    int j = 0;
    try {
      j = obtenerPosicionDeCaracter(pedido, ';', 0);
      String metodo = pedido.substring(0, j);

      i = obtenerPosicionDeCaracter(pedido, ';', 0);
      j = obtenerPosicionDeCaracter(pedido, ';', 1);
      String servidor = pedido.substring(i + 1, j);

      i = obtenerPosicionDeCaracter(pedido, ';', 1);
      j = pedido.length();
      String recurso = pedido.substring(i, j);
      ServidorWeb server = obtenerServidor(servidor);
      if (metodo.equals("GET")) {
        String res = obtenerRespuesta(recurso);
        return res;
      }
      if (server == null) {
        return 500 + ";\n" +
            "<html>\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Mensaje de error.</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "    500 - Server error\n" +
            "</body>\n" +
            "</html>";
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

  public String getNombre() {
    return nombre;
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
