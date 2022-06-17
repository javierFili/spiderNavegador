package spider.servidor;

import spider.navegador.PedidoHTTP;
import spider.navegador.RespuestaHTTP;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServidorWeb {
  private ArrayList<String> recursos = new ArrayList<>();
  private File recursosDisponibles = new File("RecursosDisponibles.txt");
  private String nombre;

  public ServidorWeb(String nombreServidor) {
    agregarRecursosDisponiblesALista(nombreServidor);
    this.nombre = nombreServidor;
  }

  private void agregarRecursosDisponiblesALista(String nombreServidor) {
    FileReader leer;
    BufferedReader almacenamiento;
    nombreServidor = nombreServidor.replace('.', '/');
    String nombreRecurso = "", informacionSacada = "";
    String pathName = "servidores/" + nombreServidor + "/recursosDisponibles.txt";
    recursosDisponibles = new File(pathName);
    long hayContenido = recursosDisponibles.length();
    if (hayContenido != 0) {
      try {
        leer = new FileReader(recursosDisponibles);
        almacenamiento = new BufferedReader(leer);
        while (informacionSacada != null) {
          informacionSacada = almacenamiento.readLine();
          if (informacionSacada != null) {
            nombreRecurso = informacionSacada;
            recursos.add(nombreRecurso);
          }
        }
        almacenamiento.close();
      } catch (Exception e) {
        System.out.println(e.toString());
      }
    }
  }

  public RespuestaHTTP get(PedidoHTTP p) {
    String recurso = p.getuRL();
    recurso = obtenerRecurso(recurso);
    String siNoHubiera = obtenerRecursoHtml("errores", "404.html");
    RespuestaHTTP respuesta = new RespuestaHTTP(404, siNoHubiera);
    //if (recursos.contains(recurso)) {
    Pattern pattern = Pattern.compile("^[a-zA-Z.]*");
    Matcher matcher = pattern.matcher(recurso);
    boolean b = matcher.matches();
    if (!b) {
      siNoHubiera = obtenerRecursoHtml("errores", "400.html");
      return new RespuestaHTTP(400, siNoHubiera);
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
    String html = "", informacionSacada = "";
    nombre = nombre.replace('.', '/');
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
    recurso = recurso.substring(i + 1, recurso.length());
    return recurso;
  }

  private int obtenerPosChar(String cadena, char subCad, int ini) {
    int res = ini;
    while (res < cadena.length()) {
      if (cadena.charAt(res) == subCad) {
        break;
      }
      res++;
    }
    return res;
  }
}
