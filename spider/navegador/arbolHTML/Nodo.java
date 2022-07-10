package spider.navegador.arbolHTML;

import java.util.ArrayList;
import java.util.List;

public class Nodo<T> {
  private T dato;
  private String contenido;
  private List<Nodo<T>> hijos;
  private Nodo<T> padre;

  public Nodo(T dato, String contenido) {
    this.dato = dato;
    this.hijos = new ArrayList<>();
    this.contenido = contenido;
  }

  public String getContenido() {
    return contenido;
  }

  public Nodo(Nodo<T> nodo) {
    this.dato = (T) nodo.getDato();
    hijos = new ArrayList<>();
  }

  public void agregarHijo(Nodo<T> hijo) {
    hijo.setPadre(this);
    hijos.add(hijo);
  }

  public T getDato() {
    return this.dato;
  }

  public void setPadre(Nodo<T> padre) {
    this.padre = padre;
  }

  public List<Nodo<T>> getHijos() {
    return this.hijos;
  }

  @Override
  public boolean equals(Object obj) {
    if (null == obj) return false;
    if (obj instanceof Nodo) {
      if (((Nodo<?>) obj).getDato().equals(this.dato)) return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return this.dato.toString();
  }
}