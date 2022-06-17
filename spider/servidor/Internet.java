package spider.servidor;

import spider.navegador.PedidoHTTP;
import spider.navegador.RespuestaHTTP;

import java.util.ArrayList;
import java.util.List;

public class Internet {
  private List<ServidorWeb> servidores;

  public Internet() {
    servidores = new ArrayList<>();
  }

  public boolean registrar(ServidorWeb servidorWeb) {
    boolean res = false;
    if (!servidores.contains(servidorWeb)) {
      servidores.add(servidorWeb);
      res = true;
    }
    return res;
  }

  public ServidorWeb obtenerServidor(String nombreServidor) {
    ServidorWeb servidor = null;
    for (int i = 0; i < servidores.size(); i++) {
      if (servidores.get(i).getNombre().equals(nombreServidor)) {
        servidor = servidores.get(i);
      }
    }
    return servidor;
  }

  private int obtenerPosicionDeCaracter(String cadena, char caracter){
    int res = 0 ;
    while(res<cadena.length()){
      if(cadena.charAt(res)==caracter){
        break;
      }
      res++;
    }
    return  res;
  }

  public RespuestaHTTP ejecutarPedido(PedidoHTTP pedido) {
    int finDeCortacion = obtenerPosicionDeCaracter(pedido.getuRL(),';');
    String nombreServer = pedido.getuRL().substring(0,finDeCortacion);
    ServidorWeb server = obtenerServidor(nombreServer);
    if(pedido.getMetodo().equals("GET")){
      return server.get(pedido);
    }
    //aun no procesamos "POST","PUT";
    return null;
  }
}

