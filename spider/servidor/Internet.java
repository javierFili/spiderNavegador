package spider.servidor;

import java.util.ArrayList;
import java.util.List;

public class Internet {
  private List<ServidorWeb> servidores;

  public Internet() {
    servidores = new ArrayList<>();
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

  public RespuestaHTTP ejecutarPedido(PedidoHTTP pedido) {
    int finDeCortacion = obtenerPosicionDeCaracter(pedido.getUrl(), ';');
    String nombreServer = pedido.getUrl().substring(0, finDeCortacion);
    ServidorWeb server = obtenerServidor(nombreServer);
    if (server == null) {
      return new RespuestaHTTP(500, "<!DOCTYPE html>\n" +
          "<html lang=\"en\">\n" +
          "<head>\n" +
          "    <meta charset=\"UTF-8\">\n" +
          "    <title>Title</title>\n" +
          "</head>\n" +
          "<body>\n" +
          "    500 - Server error\n" +
          "</body>\n" +
          "</html>");
    }
    if (pedido.getMetodo().equals("GET")) {
      return server.obtenerRespuesta(pedido);
    }
    //aun no procesamos "POST","PUT";
    return null;
  }
}

