package spider.navegador.backend;

import spider.servidor.Internet;
import spider.servidor.PedidoHTTP;
import spider.servidor.RespuestaHTTP;
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
    respuestaHTTP = internet.ejecutarPedido(pedidoHTTP);
    consulta = respuestaHTTP.getRecurso();
    return consulta;
  }
}
