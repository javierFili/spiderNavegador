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
    cadena = cadena.replaceAll("\\t", "");
    generarArbol(cadena);
    return raiz;
  }

  private void generarArbol(String cadena) {
    this.cadena = cadena;
    generarArbol(raiz);
  }

  private void generarArbol(EtiquetaRama nodo) {
    for (int i = 0; i < this.cadena.length(); i++) {
      if (this.cadena.charAt(i) == '<') {
        for (int j = 0; j < this.cadena.length(); j++) {
          if (this.cadena.charAt(j) == '>' || this.cadena.charAt(j) == ' ') {
            String padre = this.cadena.substring(i + 1, j);
            if (this.cadena.charAt(j) == ' ') {
              j += 2;
            }
            this.cadena = this.cadena.substring(j + 1, this.cadena.length());
            String contenido = obtencionDeContenido(this.cadena);
            try {
              EtiquetaEnum convertir = EtiquetaEnum.valueOf(padre);
              if (!contenido.equals("\n")) {
                EtiquetaHoja hijoFinal = new EtiquetaHoja(convertir, contenido);
                nodo.insertarHijo(hijoFinal);
                eliminarTagSalida();
              } else {
                EtiquetaRama hijo = new EtiquetaRama(convertir);
                nodo.insertarHijo(hijo);
                crearHijos(hijo, convertir);
              }
              i = this.cadena.length();
              break;
            } catch (Exception e) {
              System.out.println("Etiqueta no valida, no se mostrara el contenido");
            }
          }
        }
      }
    }
  }

  private void eliminarTagSalida() {
    for (int i = 0; i < cadena.length(); i++) {
      if (cadena.charAt(i) == '<' && cadena.charAt(i + 1) == '/') {
        for (int j = 0; j < cadena.length(); j++) {
          if (cadena.charAt(j) == '>') {
            this.cadena = cadena.substring(j + 1, cadena.length());
            i = cadena.length();
            break;
          }
        }
      }
    }
  }

  //algo esta haciendo mal en aqui. no sacamos todos los hijo hoja.
  private boolean crearHijos(EtiquetaRama nodoPadre1, EtiquetaEnum tipo) {
    boolean termino = false;
    for (int i = 0; i < cadena.length(); i++) {
      if (cadena.charAt(i) == '<' && cadena.charAt(i + 1) == '/') {
        for (int j = i; j < cadena.length(); j++) {
          if (cadena.charAt(j) == '>') {
            try {
              String padreDe = cadena.substring(i + 2, j);
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
          i = 0;
          generarArbol(nodoPadre1);
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