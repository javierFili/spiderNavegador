package spider.navegador;

import spider.navegador.backend.CreadorArbol;
import spider.navegador.arbolHTML.EtiquetaHTML;

public class Main1 {
  public static void main(String args[]) {
    String cadena = "<HTML>\n" +
        "<BODY>\n" +
        "<H1>Documento 1</H1>\n" +
        "<P>Este es el texto de la primera seccion.</P>\n" +
        "<H2>Subseccion</H2>\n" +
        "<P>Contenido de la subseccion 1.</P>\n" +
        "<H2>Subseccion</H2>\n" +
        "<P>Contenido de la subseccion 2</P>\n" +
        "</BODY>\n" +
        "</HTML>\n";
    CreadorArbol creador = new CreadorArbol();
    EtiquetaHTML eti = creador.crearDOM(cadena);
  }
}
