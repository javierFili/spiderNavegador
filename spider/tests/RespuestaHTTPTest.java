package spider.tests;

import spider.navegador.RespuestaHTTP;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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