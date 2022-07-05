package spider.navegador.backend;

import spider.navegador.arbolHTML.*;

import java.util.ArrayList;

public class CreadorComposite {
  public EtiquetaHTML crearDOM(String mensajeIn) {
    Convertidor con = new Convertidor();
    EtiquetaComposite eti = new EtiquetaComposite(EtiquetaEnum.title, "");
    String contenido = "";
    con.generarArbol(mensajeIn);
    ArbolNArio a = con.getArbol();
    ArrayList<Nodo> list = a.getPreOrder();
    for (int i = 0; i < list.size(); i++) {
      String tipo = (String) list.get(i).getDato();
      contenido += list.get(i).getContenido();
      try {
        EtiquetaEnum tipoDeEti = EtiquetaEnum.valueOf(tipo);
        eti = new EtiquetaComposite(tipoDeEti, contenido);
      } catch (Exception e) {

      }
    }
    return eti;
  }
}
