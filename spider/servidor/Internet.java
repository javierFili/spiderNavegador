package spider.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Internet {
  private List<ServidorWeb> servidores;
  static final int PUERTO = 8080;

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
      if (server == null) {
        return 500 + ";<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Mensaje de error.</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "    500 - Server error\n" +
            "</body>\n" +
            "</html>";
      }
      if (metodo.equals("GET")) {
        RespuestaHTTP res = server.obtenerRespuesta(new PedidoHTTP("GET", recurso));
        return res.getCodigoRespuesta() + ";" + res.getRecurso();
      }
    } catch (Exception e) {
      return 400 + ";<!DOCTYPE html>\n" +
          "<html lang=\"en\">\n" +
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

  public void run() {
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
}

