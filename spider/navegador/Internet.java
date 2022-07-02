package spider.navegador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Internet {
  private List dns;
  private File recursosDNS;

  public Internet() {
    dns = new ArrayList<String>();
    leerArchivoDns("dns.txt");
  }

  private void leerArchivoDns(String s) {
    FileReader leer;
    BufferedReader almacenamiento;
    String dnsParaAgregar = "", informacionSacada = "";
    String pathName = s;
    recursosDNS = new File(pathName);
    long hayContenido = recursosDNS.length();
    if (hayContenido != 0) {
      try {
        leer = new FileReader(recursosDNS);
        almacenamiento = new BufferedReader(leer);
        while (informacionSacada != null) {
          informacionSacada = almacenamiento.readLine();
          if (informacionSacada != null) {
            dns.add(dnsParaAgregar);
          }
        }
        almacenamiento.close();
      } catch (Exception e) {
        System.out.println(e.toString());
      }
    }
  }


  public void registrar(String nombre, String ip) {
    // debe verificar que el nombre no esté registrado
  }

  public String resolverNombre(String nombre) {
    // busca en el registro de nombres y devuelve la ip correspondiente
    return "";
  }

/*
  Archivo dns.txt tiene el siguiente formato
      nombre;ip
  www.google.com;25.80.61.85
  www.fcyt.umss.edu.bo;25.80.137.142
  www.cs.umss.edu.bo;25.82.184.206
*/


}
