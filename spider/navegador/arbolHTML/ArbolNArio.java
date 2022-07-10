package spider.navegador.arbolHTML;

import java.util.ArrayList;

public class ArbolNArio<T> {
  private Nodo<T> raiz;

  public ArbolNArio(Nodo<T> raiz) {
    this.raiz = raiz;
  }
  public ArrayList<Nodo<T>> getPreOrder() {
    ArrayList<Nodo<T>> preOrder = new ArrayList<Nodo<T>>();
    construirPreOrder(raiz, preOrder);
    return preOrder;
  }

  public ArrayList<Nodo<T>> getPostOrder() {
    ArrayList<Nodo<T>> postOrder = new ArrayList<Nodo<T>>();
    construirPostOrder(raiz, postOrder);
    return postOrder;
  }

  private void construirPreOrder(Nodo<T> nodo, ArrayList<Nodo<T>> preOrder) {
    preOrder.add(nodo);
    for (Nodo<T> hijo : nodo.getHijos()) {
      construirPreOrder(hijo, preOrder);
    }
  }

  private void construirPostOrder(Nodo<T> nodo, ArrayList<Nodo<T>> postOrder) {
    for (Nodo<T> hijo : nodo.getHijos()) {
      construirPostOrder(hijo, postOrder);
    }
    postOrder.add(nodo);
  }
  private ArrayList<Nodo<T>> clone(ArrayList<Nodo<T>> list) {
    ArrayList<Nodo<T>> lista = new ArrayList<Nodo<T>>();
    for (Nodo<T> nodo : list) lista.add(new Nodo<T>(nodo));
    return lista;
  }
}