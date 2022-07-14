package spider.navegador.backend;

import spider.navegador.arbolHTML.*;

public class CreadorArbol {
  private EtiquetaRama raiz;
  private String cadena;
  private CreadorHijo creadorHijos;

  public CreadorArbol() {
    this.raiz = new EtiquetaRama(EtiquetaEnum.HTML);
    creadorHijos = new CreadorHijo();
  }

  public EtiquetaHTML crearDOM(String mensajeIn) {
    this.cadena = mensajeIn;
    generarArbol(cadena);
    return raiz;
  }

  private void generarArbol(String cad) {
    this.cadena = cad;
    generarArbol(raiz);
  }

  private void generarArbol(EtiquetaRama nodo) {
    for (int i = 0; i < this.cadena.length(); i++) {
      if (this.cadena.charAt(i) == '<') {
        i = detenerminarFinTag(nodo, i);
      }
    }
  }

  private int detenerminarFinTag(EtiquetaRama nodo, int i) {
    for (int j = i; j < this.cadena.length(); j++) {
      if (this.cadena.charAt(j) == '>' || this.cadena.charAt(j) == ' ') {
        String padre = this.cadena.substring(i + 1, j);
        if (this.cadena.charAt(j) == ' ') {
          j += 2;
        }
        this.cadena = this.cadena.substring(j + 1, this.cadena.length());
        String contenido = creadorHijos.obtencionDeContenido(this.cadena);
        this.cadena = creadorHijos.actualizarCadena();
        EtiquetaEnum convertir = EtiquetaEnum.valueOf(padre);
        seleccionarHojaRama(nodo, contenido, convertir);
        i = this.cadena.length();
        break;
      }
    }
    return i;
  }

  private void seleccionarHojaRama(EtiquetaRama nodo, String contenido, EtiquetaEnum convertir) {
    if (!contenido.equals("\n") && !contenido.isBlank()) {
      EtiquetaHoja hijoFinal = new EtiquetaHoja(convertir, contenido);
      nodo.insertarHijo(hijoFinal);
      eliminarTagSalida();
    } else {
      EtiquetaRama hijo = new EtiquetaRama(convertir);
      nodo.insertarHijo(hijo);
      crearHijos(hijo, convertir);
    }
  }

  private void eliminarTagSalida() {
    for (int i = 0; i < cadena.length(); i++) {
      if (cadena.charAt(i) == '<' && cadena.charAt(i + 1) == '/') {
        i = buscarCierre(i);
      }
    }
  }

  private int buscarCierre(int i) {
    for (int j = 0; j < cadena.length(); j++) {
      if (cadena.charAt(j) == '>') {
        this.cadena = cadena.substring(j + 1, cadena.length());
        i = cadena.length();
        break;
      }
    }
    return i;
  }

  private void crearHijos(EtiquetaRama nodoPadre1, EtiquetaEnum tipo) {
    for (int i = 0; i < cadena.length(); i++) {
      i = hijoPadre(nodoPadre1, tipo, i);
    }
  }

  private int hijoPadre(EtiquetaRama nodoPadre1, EtiquetaEnum tipo, int i) {
    String cadenaEspera = cadena;
    if (cadena.charAt(i) == '<' && cadena.charAt(i + 1) == '/') {
      i = creadorHijos.creaHijo(tipo, i, this.cadena);
      cadena = creadorHijos.actualizarCadena();
    } else {
      i = creaPadre(nodoPadre1, i);
    }
    return i;
  }

  private int creaPadre(EtiquetaRama nodoPadre1, int i) {
    if (cadena.charAt(i) == '<' && cadena.charAt(i + 1) != '/') {
      i = 0;
      generarArbol(nodoPadre1);
    }
    return i;
  }

}