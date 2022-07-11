package spider.navegador;

import spider.navegador.arbolHTML.EtiquetaRama;
import spider.navegador.backend.CreadorArbol;
import spider.navegador.arbolHTML.EtiquetaHTML;

import javax.swing.*;

public class Main1 {
  public static void main(String args[]) {
    /*String cadena = "<HTML>\n" +
        "<BODY>\n" +
        "<H1>Documento 1</H1>\n" +
        "<P>Este es el texto de la primera seccion.</P>\n" +
        "<H2>Subseccion</H2>\n" +
        "<P>Contenido de la subseccion 1.</P>\n" +
        "<H2>Subseccion</H2>\n" +
        "<P>Contenido de la subseccion 2</P>\n" +
        "</BODY>\n" +
        "</HTML>\n";*/
    /*String cadena = "<HTML>\n" +
        "<BODY>\n" +
        "<H1>doc2</H1>\n" +
        "<P>primSec</P>\n" +
        "<H2>Subsec</H2>\n" +
        "<P>ConSubsec.</P>\n" +
        "<H2>Subsec</H2>\n" +
        "<P>Contesubsec2</P>\n" +
        "</BODY>\n" +
        "</HTML>\n";*/
    String cadena = "<HTML>\n" +
        "<BODY>\n" +
        "<P>Ir a </P><A>Contenido 1</A><P> sigue el texto 1.</P>\n" +
        "<P>Ir a </P><A>Contenido 2</A><P> sigue el texto 2.</P>\n" +
        "</BODY>\n" +
        "</HTML>\n";
    CreadorArbol creador = new CreadorArbol();
   // EtiquetaHTML eti = creador.crearDOM(cadena);
    EtiquetaRama etii = (EtiquetaRama) creador.crearDOM(cadena);
   /* String salida1 = eti.toString();
    JComponent con = eti.graficar();
    String salida = eti.desplegar();
    System.out.println(salida);*/
    System.out.println(etii.obtenerCadena(cadena));
  }
}
