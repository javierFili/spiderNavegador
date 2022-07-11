package spider.navegador.arbolHTML;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EtiquetaRama implements EtiquetaHTML {
  private EtiquetaEnum tipo;
  private List<EtiquetaHTML> hijos;
  private List<EtiquetaHoja> hojas;


  public EtiquetaRama(EtiquetaEnum etiqueta) {
    super();
    this.tipo = etiqueta;
    hijos = new ArrayList<>();
    hojas = new ArrayList<>();
  }

  @Override
  public JComponent graficar() {
    JLabel label = new JLabel();
    label.setText("");
    return label;
  }

  @Override
  public String desplegar() {
    return obtenetTextos();
  }

  public void insertarHijo(EtiquetaHTML hijo) {
    hijos.add(hijo);
  }

  private String obtenetTextos() {
    ArrayList<EtiquetaHoja> hojas = soloHojas((ArrayList<EtiquetaHTML>) this.hijos);
    String cadena = "";
    for (int i = 0; i < hojas.size(); i++) {
      cadena += hojas.get(i).toString();
    }
    return cadena;
  }

  private ArrayList<EtiquetaHoja> soloHojas(ArrayList<EtiquetaHTML> hijos) {
    for (int i = 0; i < hijos.size(); i++) {
      if (hijos.get(i) instanceof EtiquetaHoja) {
        obtenerCadena(hijos.get(i).toString());
      } else {

      }
    }
    return null;
  }

  private ArrayList<EtiquetaHTML> darHijos() {
    return (ArrayList<EtiquetaHTML>) hijos;
  }

  public String obtenerCadena(String cadena) {
    String res = "";
    for (int i = 0; i < cadena.length(); i++) {
      if (cadena.charAt(i) == '<') {
        res += cadena.substring(0, i);
        for (int j = i; j < cadena.length(); j++) {
          if (cadena.charAt(j) == '>') {
            cadena = cadena.substring(j + 1, cadena.length());
            i = 0;
            j = cadena.length() + 1;
          }
        }
      }
    }
    System.out.println(res);
    return res;
  }

}
