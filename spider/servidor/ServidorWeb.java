package spider.servidor;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServidorWeb {
  private ArrayList<String> recursos = new ArrayList<>();
  private File recursosDisponibles = new File("RecursosDisponibles.txt");
  private String nombre;

  public ServidorWeb(String nombreServidor) {
    this.nombre = nombreServidor;
  }

  public RespuestaHTTP obtenerRespuesta(PedidoHTTP p) {
    String recurso = p.getUrl();
    recurso = obtenerRecurso(recurso);
    RespuestaHTTP respuesta = new RespuestaHTTP(404, para404);
    Pattern pattern = Pattern.compile("^[a-zA-Z.]*");
    Matcher matcher = pattern.matcher(recurso);
    boolean b = matcher.matches();
    if (!b) {
      return new RespuestaHTTP(400, para400);
    }
    if (true) {
      //String nombre = obtenerNombre(this.nombre);
      recurso = obtenerRecursoHtml(this.nombre, recurso);
      if (recurso.length() <= 1) {
        return respuesta;
      }
      respuesta = new RespuestaHTTP(200, recurso);
    }
    return respuesta;
  }

  private String obtenerRecursoHtml(String nombre, String recurso) {
    FileReader leer;
    BufferedReader almacenamiento;
    System.out.println("buscando el recurso:"+nombre+"y "+recurso);
    String html = "", informacionSacada = "";
    //nombre = nombre.replace('.', '/');
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
    if(i==-1){
       return "2111";
    }
    recurso = recurso.substring(i + 1, recurso.length());
    return recurso;
  }

  private int obtenerPosChar(String cadena, char subCad, int ini) {
    int res = ini;
    int ress= 0;
    while (res < cadena.length()) {
      if (cadena.charAt(res) == subCad) {
        ress= res;
        break;
      }else{
        ress= -1;
      }
      res++;
    }
    return ress;
  }

  public String getNombre() {
    return nombre;
  }

  private String para400= "<!DOCTYPE html>\n" +
      "<html lang=\"en\">\n" +
      "<head>\n" +
      "    <meta charset=\"UTF-8\">\n" +
      "    <title>Title</title>\n" +
      "</head>\n" +
      "<body>\n" +
      "   <div>\n" +
      "       400 - Bad request\n" +
      "   </div>\n" +
      "</body>\n" +
      "</html>";
  private  String para404= "<!DOCTYPE html>\n" +
      "<html lang=\"en\">\n" +
      "<head>\n" +
      "    <meta charset=\"UTF-8\">\n" +
      "    <title>Title</title>\n" +
      "</head>\n" +
      "<body>\n" +
      "    404 - Not found\n" +
      "</body>\n" +
      "</html>";

}
