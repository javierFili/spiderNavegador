package spider.navegador.backend;

import spider.navegador.arbolHTML.EtiquetaEnum;

public class CreadorHijo {
  private String cadena;

  public String obtencionDeContenido(String cad) {
    this.cadena = cad;
    String contenido = "";
    for (int i = 0; i < cadena.length(); i++) {
      if (cadena.charAt(i) != '<') {
        for (int j = 0; j < cadena.length(); j++) {
          if (cadena.charAt(j) == '<' && cadena.charAt(j++) != '/') {
            contenido = cadena.substring(i, j - 1);
            cadena = cadena.substring(j - 1, cadena.length());
            i = -1;
            break;
          }
        }
      } else {
        break;
      }
    }
    return contenido;
  }

  public String actualizarCadena() {
    return cadena;
  }

  public int creaHijo(EtiquetaEnum tipo, int i, String cad) {
    this.cadena = cad;
    for (int j = i; j < cadena.length(); j++) {
      if (cadena.charAt(j) == '>') {
        String padreDe = cadena.substring(i + 2, j);
        if (tipo.name().equals(padreDe)) {
          i = cadena.length();
          cadena = cadena.substring(j + 1, cadena.length());
          break;
        }
      }
    }
    return i;
  }
}
