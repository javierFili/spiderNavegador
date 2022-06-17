package spider.tests;

import spider.navegador.PedidoHTTP;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoHTTPTest {
  PedidoHTTP pedido = new PedidoHTTP("GET", "servidores.www.google.com:index.html");

  @Test
  void getMetodo() {

    assertEquals(pedido.getMetodo(), "GET");
  }

  @Test
  void getPeticion() {
    assertEquals(pedido.getuRL(), "servidores.www.google.com:index.html");
  }
}