package spider.tests;

import org.junit.jupiter.api.Test;
import spider.servidor.RespuestaHTTP;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RespuestaHTTPTest {
  RespuestaHTTP respuesta = new RespuestaHTTP(200,"index.html");

  @Test
  void getCodRespuesta() {
    assertEquals(respuesta.getCodigoRespuesta(), 200);
  }


  @Test
  void getContenido() {
    assertEquals(respuesta.getRecurso(), "index.html");
  }

}