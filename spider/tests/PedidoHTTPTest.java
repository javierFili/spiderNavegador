package spider.tests;

import org.junit.jupiter.api.Test;
import spider.servidor.PedidoHTTP;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PedidoHTTPTest {
  PedidoHTTP pedido = new PedidoHTTP("GET", "servidores.www.google.com:index.html");

  @Test
  void getMetodo() {

    assertEquals(pedido.getMetodo(), "GET");
  }

  @Test
  void getPeticion() {
    assertEquals(pedido.getUrl(), "servidores.www.google.com:index.html");
  }
}