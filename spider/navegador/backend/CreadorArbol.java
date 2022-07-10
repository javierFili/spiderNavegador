package spider.navegador.backend;

import spider.navegador.arbolHTML.*;

import java.util.ArrayList;

public class CreadorArbol {

  //ojo no debes importar el arbol ni el nodo, ni convertidor..
  //cuidado, solo se pueden importar y usar EtiquetaHTML, Rama,Hoja
  public EtiquetaHTML crearDOM(String mensajeIn) {
    Convertidor con = new Convertidor();
    EtiquetaRama eti = new EtiquetaRama(EtiquetaEnum.title, "");
    String contenido = "";
    con.generarArbol(mensajeIn);
    ArbolNArio a = con.getArbol();
    ArrayList<Nodo> list = a.getPreOrder();
    for (int i = 0; i < list.size(); i++) {
      String tipo = (String) list.get(i).getDato();
      contenido += list.get(i).getContenido();
      try {
        EtiquetaEnum tipoDeEti = EtiquetaEnum.valueOf(tipo);
        eti = new EtiquetaRama(tipoDeEti, contenido);
      } catch (Exception e) {
        System.out.println("existe una etiqueta no permitida");
      }
    }
    return eti;
  }
}
