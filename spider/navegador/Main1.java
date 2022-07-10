package spider.navegador;

import spider.navegador.backend.CreadorArbol;
import spider.navegador.arbolHTML.EtiquetaHTML;

public class Main1 {
  public static void main(String args[]) {
    String cadena = "<html>\n" +
        "<head>\n" +
        "    <meta>\n" +
        "    <title>Title</title>\n" +
        "</head>\n" +
        "<body>\n" +
        "<div>akjflkj</div>\n" +
        "<div>\n" +
        "    Claramente se ingreso al html del spider.servidor google mas\n" +
        "    especificamente el index.html\n" +
        "</div>\n" +
        "</body>\n" +
        "</html>";
    CreadorArbol compo = new CreadorArbol();
    EtiquetaHTML et = compo.crearDOM(cadena);
    System.out.println(et.toString());
  }
}
