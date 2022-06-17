package spider.tests;

import spider.navegador.PedidoHTTP;
import spider.navegador.RespuestaHTTP;
import spider.servidor.ServidorWeb;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServidorWebTest {

  @Test
  void getValido() {
    ServidorWeb servidor = new ServidorWeb("www.fcyt.umss.edu.bo");
    PedidoHTTP pedido = new PedidoHTTP("GET", "www.fcyt.umss.edu.bo;index.html");
    RespuestaHTTP respuestaHTTP = servidor.get(pedido);
    System.out.println(respuestaHTTP.getRecurso());
    assertEquals(respuestaHTTP.getCodigoRespuesta(), 200);
  }

  @Test
  void getInvalido() {
    ServidorWeb servidor = new ServidorWeb("www.google.com");
    PedidoHTTP pedido = new PedidoHTTP("GET", "www.google.com;indexx.html");
    RespuestaHTTP respuestaHTTP = servidor.get(pedido);
    assertEquals(respuestaHTTP.getCodigoRespuesta(), 404);
  }

  @Test
  void getValido1() {
    ServidorWeb servidor = new ServidorWeb("www.google.com");
    PedidoHTTP pedido = new PedidoHTTP("GET", "www.google.com;index.html");
    RespuestaHTTP respuestaHTTP = servidor.get(pedido);
    assertEquals(respuestaHTTP.getCodigoRespuesta(), 200);
  }
}