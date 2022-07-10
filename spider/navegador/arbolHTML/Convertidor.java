package spider.navegador.arbolHTML;

import java.util.ArrayList;

public class Convertidor {
  private String cadena;
  private Nodo nodoRaiz;
  private ArbolNArio arbol;

  public Convertidor() {
    nodoRaiz = new Nodo("html", "");
    arbol = new ArbolNArio(nodoRaiz);
  }

  public void generarArbol(String cadena) {
    this.cadena = cadena;
    generarArbol(cadena, nodoRaiz);
  }

  private void generarArbol(String cadena, Nodo nodo) {
    String res = "";
    for (int i = 0; i < cadena.length(); i++) {
      if (cadena.charAt(i) == '<') {
        for (int j = 0; j < cadena.length(); j++) {
          if (cadena.charAt(j) == '>') {
            String padre = cadena.substring(i + 1, j);
            this.cadena = cadena.substring(j + 1, cadena.length());
            String contenido = obtencionDeContenido(this.cadena);
            Nodo hijo = new Nodo(padre, contenido);
            nodo.agregarHijo(hijo);
            boolean termino = crearHijos(hijo);
            i = cadena.length();
            break;
          }
        }
      }
    }
  }

  private boolean crearHijos(Nodo nodoPadre1) {
    boolean termino = false;
    for (int i = 0; i < cadena.length(); i++) {
      if (cadena.charAt(i) == '<' && cadena.charAt(i + 1) == '/') {
        for (int j = i; j < cadena.length(); j++) {
          if (cadena.charAt(j) == '>') {
            try {
              String padreDe = cadena.substring(i + 2, j);
              if (padreDe.equals(nodoPadre1.getDato())) {
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

  public ArbolNArio getArbol(){
    return  arbol;
  }
}
