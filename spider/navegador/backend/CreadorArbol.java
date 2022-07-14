package spider.navegador.backend;

import spider.navegador.arbolHTML.*;

import java.awt.*;

public class CreadorArbol {
  private EtiquetaRama raiz;
  private String cadena;
  private CreadorHijo creadorHijos;

  public CreadorArbol() {
    this.raiz = new EtiquetaRama(EtiquetaEnum.HTML);
    creadorHijos = new CreadorHijo();
  }

  private void generarArbol(EtiquetaRama nodo) {
    for (int i = 0; i < this.cadena.length(); i++) {
      boolean crear = this.cadena.charAt(i) == '<';
      i = condicionDeCreacion(crear, i, nodo);
    }
  }

  private int condicionDeCreacion(boolean crear, int i, EtiquetaRama nodo) {
    if (crear) {
      i = detenerminarFinTag(nodo, i);
    }
    return i;
  }

  //** para elimiar esas anidaciones.
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

  //*
  private void eliminarTagSalida() {
    for (int i = 0; i < cadena.length(); i++) {
      boolean inicioDeCierre = cadena.charAt(i) == '<' && cadena.charAt(i + 1) == '/';
      i = condicionDeCierre(inicioDeCierre, i);
    }
  }

  private int condicionDeCierre(boolean condicion, int i) {
    if (condicion) {
      i = buscarCierre(i);
    }
    return i;
  }


  private int buscarCierre(int i) {
    for (int j = 0; j < cadena.length(); j++) {
      Point valores = condicionDeCierreCorte(j);
      i = valores.x;
      j = valores.y;
    }
    return i;
  }

  private Point condicionDeCierreCorte(int j) {
    Point p = new Point(0, j);
    if (cadena.charAt(j) == '>') {
      this.cadena = cadena.substring(j + 1, cadena.length());
      p.x = cadena.length();
      p.y = cadena.length();
    }
    return p;
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


  private boolean seleccionarHojaRama(EtiquetaRama nodo, String contenido, EtiquetaEnum convertir) {
    if (!contenido.equals("\n") && !contenido.isBlank()) {
      EtiquetaHoja hijoFinal = new EtiquetaHoja(convertir, contenido);
      nodo.insertarHijo(hijoFinal);
      eliminarTagSalida();
      return false;
    }
    inserccionHoja(nodo, convertir);
    return true;
  }

  private void inserccionHoja(EtiquetaRama nodo, EtiquetaEnum convertir) {
    EtiquetaRama hijo = new EtiquetaRama(convertir);
    nodo.insertarHijo(hijo);
    crearHijos(hijo, convertir);
  }


  private void crearHijos(EtiquetaRama nodoPadre1, EtiquetaEnum tipo) {
    for (int i = 0; i < cadena.length(); i++) {
      i = hijoPadre(nodoPadre1, tipo, i);
    }
  }

  private int hijoPadre(EtiquetaRama nodoPadre1, EtiquetaEnum tipo, int i) {
    String cadenaEspera = cadena;
    i = creaPadre(nodoPadre1, i);
    if (cadena.charAt(i) == '<' && cadena.charAt(i + 1) == '/') {
      cadena = cadenaEspera;
      i = creadorHijos.creaHijo(tipo, i, this.cadena);
      cadena = creadorHijos.actualizarCadena();
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