package spider.navegador;

public class Main1 {
  public static void main(String args[]) {
    Convertidor con = new Convertidor();
    String cadena = "<!DOCTYPE html>\n" +
        "<html lang=\"en\">\n" +
        "<head>\n" +
        "    <meta charset=\"UTF-8\">\n" +
        "    <title>Title</title>\n" +
        "</head>\n" +
        "<body>\n" +
        "<div>akjflkj</div>\n" +
        "<form action=\"\">\n" +
        "    Claramente se ingreso al html del spider.servidor google mas\n" +
        "    especificamente el index.html\n" +
        "</form>\n" +
        "</body>\n" +
        "</html>";
    con.generarArbol(cadena);
    con.rearmarCadena();
    System.out.println(con.rearmarCadena());
  }
}
