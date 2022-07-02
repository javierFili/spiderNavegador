package spider.navegador.backend;
public class BuscadorBackend {
  public String obtenerCadena(String cadena) {
    String res = "";
    for (int i = 0; i < cadena.length(); i++) {
      if (cadena.charAt(i) == '<') {
        res += cadena.substring(0,i);
        for (int j = i; j < cadena.length(); j++) {
          if (cadena.charAt(j) == '>') {
            cadena = cadena.substring(j + 1, cadena.length());
            i = 0;
            j = cadena.length() + 1;
          }
        }
      }
    }
    return res;
  }
}
