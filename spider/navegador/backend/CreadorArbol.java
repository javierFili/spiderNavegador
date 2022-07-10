package spider.navegador.backend;

import spider.navegador.arbolHTML.*;
public class CreadorArbol {
  private EtiquetaRama raiz;
  private String cadena;

  public CreadorArbol() {
    this.raiz = new EtiquetaRama(EtiquetaEnum.HTML);
  }

  public EtiquetaHTML crearDOM(String mensajeIn) {
    this.cadena = mensajeIn;
    generarArbol(cadena);
    EtiquetaRama eti = new EtiquetaRama(EtiquetaEnum.H1);

    return eti;
  }

  private void generarArbol(String cadena) {
    this.cadena = cadena;
    generarArbol(cadena, raiz);
  }

  private void generarArbol(String cadena, EtiquetaHTML nodo) {
    String res = "";
    for (int i = 0; i < cadena.length(); i++) {
      if (cadena.charAt(i) == '<') {
        for (int j = 0; j < cadena.length(); j++) {
          if (cadena.charAt(j) == '>') {
            String padre = cadena.substring(i + 1, j);
            this.cadena = cadena.substring(j + 1, cadena.length());
            String contenido = obtencionDeContenido(this.cadena);
            /*Nodo hijo = new Nodo(padre, contenido);
            nodo.agregarHijo(hijo); */
            if (contenido != "") {
              EtiquetaHTML hijo = new EtiquetaRama(EtiquetaEnum.A);
              crearHijos(hijo, EtiquetaEnum.H1);
            } else {
              EtiquetaHTML hijoFinal = new EtiquetaHoja(EtiquetaEnum.H2, contenido);
            }
            i = cadena.length();
            break;
          }
        }
      }
    }
  }

  private boolean crearHijos(EtiquetaHTML nodoPadre1, EtiquetaEnum tipo) {
    boolean termino = false;
    for (int i = 0; i < cadena.length(); i++) {
      if (cadena.charAt(i) == '<' && cadena.charAt(i + 1) == '/') {
        for (int j = i; j < cadena.length(); j++) {
          if (cadena.charAt(j) == '>') {
            try {
              String padreDe = cadena.substring(i + 2, j);
              //para entrar y romper el ciclo.
              if (tipo.name().equals(padreDe)) {
                i = cadena.length();
                cadena = cadena.substring(j + 1, cadena.length());
                break;
              }
            } catch (Exception e) {
              System.out.println("de tamanio:" + cadena.length() + " tu i:" + i + " tu j:" + j);
            }
          }
        }
      } else {
        if (cadena.charAt(i) == '<' && cadena.charAt(i + 1) != '/') {
          generarArbol(cadena, nodoPadre1);
        }
      }
    }
    return termino;
  }

  private String obtencionDeContenido(String cadena) {
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
    this.cadena = cadena;
    return contenido;
  }
}
