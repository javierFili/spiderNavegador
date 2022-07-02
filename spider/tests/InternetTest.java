package spider.tests;

import spider.servidor.Internet;
import spider.servidor.PedidoHTTP;
import spider.servidor.RespuestaHTTP;
import spider.servidor.ServidorWeb;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InternetTest {

 /* @Test
  void registrarServidor() {
    ServidorWeb google = new ServidorWeb("www.google.com");
    Internet internet = new Internet();
    boolean exito = internet.registrar(google);
    assertEquals(exito, true);
  }

  @Test
  void errorDeRegistroServidorExiste() {
    ServidorWeb google = new ServidorWeb("www.google.com");
    Internet internet = new Internet();
    internet.registrar(google);
    boolean exito = internet.registrar(google);
    assertEquals(exito, false);
  }*/

/*
  @Test
  void hallarServidor() {
    ServidorWeb google = new ServidorWeb("www.google.com");
    ServidorWeb fcyt = new ServidorWeb("www.fcyt.umss.edu.bo");
    ServidorWeb csumss = new ServidorWeb("www.cs.umss.edu.bo");
    Internet internet = new Internet();
    internet.registrar(google);
    internet.registrar(fcyt);
    internet.registrar(csumss);
    ServidorWeb encontrado = internet.obtenerServidor("www.fcyt.umss.edu.bo");
    assertEquals(encontrado, fcyt);
  }

  @Test
  void noHallarServidor() {
    ServidorWeb google = new ServidorWeb("www.google.com");
    ServidorWeb fcyt = new ServidorWeb("www.fcyt.umss.edu.bo");
    ServidorWeb csumss = new ServidorWeb("www.cs.umss.edu.bo");
    Internet internet = new Internet();
    internet.registrar(google);
    internet.registrar(fcyt);
    internet.registrar(csumss);
    ServidorWeb encontrado = internet.obtenerServidor("www.google.con");
    assertEquals(encontrado, null);
  }
*/

 /* @Test
  void ejecutarPedidoValido() {
    ServidorWeb google = new ServidorWeb("www.google.com");
    ServidorWeb fcyt = new ServidorWeb("www.fcyt.umss.edu.bo");
    ServidorWeb csumss = new ServidorWeb("www.cs.umss.edu.bo");
    Internet internet = new Internet();
    internet.registrar(google);
    internet.registrar(fcyt);
    internet.registrar(csumss);
    PedidoHTTP pedido = new PedidoHTTP("GET", "www.google.com;index.html");
    RespuestaHTTP respuesta = internet.ejecutarPedido(pedido);
    int codRespuesta = respuesta.getCodigoRespuesta();
    System.out.println(respuesta.getRecurso());
    assertEquals(codRespuesta, 200);
  }

  @Test
  void ejecutarPedidoInValido() {
    ServidorWeb google = new ServidorWeb("www.google.com");
    ServidorWeb fcyt = new ServidorWeb("www.fcyt.umss.edu.bo");
    ServidorWeb csumss = new ServidorWeb("www.cs.umss.edu.bo");
    Internet internet = new Internet();
    internet.registrar(google);
    internet.registrar(fcyt);
    internet.registrar(csumss);
    PedidoHTTP pedido = new PedidoHTTP("GET", "www.google.com;indeex.html");
    RespuestaHTTP respuesta = internet.ejecutarPedido(pedido);
    int codRespuesta = respuesta.getCodigoRespuesta();
    System.out.println(respuesta.getRecurso());
    assertEquals(codRespuesta, 404);
  }
*/

  @Test
  void ejecutarPedidoValido() {
    ServidorWeb google = new ServidorWeb("www.google.com");
    ServidorWeb fcyt = new ServidorWeb("www.fcyt.umss.edu.bo");
    ServidorWeb csumss = new ServidorWeb("www.cs.umss.edu.bo");
    Internet internet = new Internet();
    internet.registrar(google);
    internet.registrar(fcyt);
    internet.registrar(csumss);
    String res = internet.ejecutarPedido("GET;www.fcyt.umss.edu.bo;index.html");
    System.out.println(res);

  }
}
