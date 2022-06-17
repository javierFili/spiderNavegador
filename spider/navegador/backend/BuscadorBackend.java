package spider.navegador.backend;

import spider.navegador.PedidoHTTP;
import spider.navegador.RespuestaHTTP;
import spider.servidor.Internet;
import spider.servidor.ServidorWeb;

public class BuscadorBackend {
  PedidoHTTP pedidoHTTP;
  RespuestaHTTP respuestaHTTP;
  Internet internet;
  public BuscadorBackend(Internet internet) {
    this.internet = internet;
  }

  public String busqueda(String consulta) {
    pedidoHTTP = new PedidoHTTP("GET", consulta);
    int i = obtenerPosicionDeCaracter(consulta, ';');
    consulta = consulta.substring(0, i);
    ServidorWeb servidorWeb = internet.obtenerServidor(consulta);
    if (servidorWeb == null) {
      return "<!DOCTYPE html>\n" +
          "<html lang=\"en\">\n" +
          "<head>\n" +
          "    <meta charset=\"UTF-8\">\n" +
          "    <title>Title</title>\n" +
          "</head>\n" +
          "<body>\n" +
          "    Error 500 xD\n" +
          "</body>\n" +
          "</html>";
    }
    respuestaHTTP = servidorWeb.get(pedidoHTTP);
    consulta = respuestaHTTP.getRecurso();
    return consulta;
  }

  private int obtenerPosicionDeCaracter(String cadena, char caracter) {
    int res = 0;
    while (res < cadena.length()) {
      if (cadena.charAt(res) == caracter) {
        break;
      }
      res++;
    }
    return res;
  }
}
