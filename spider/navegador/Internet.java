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
            dnsParaAgregar = informacionSacada;
            dns.add(dnsParaAgregar);
          }
        }
        almacenamiento.close();
      } catch (Exception e) {
        System.out.println(e.toString());
      }
    }
  }

  public String resolverNombre(String nombre) throws ServerNameNotFound {
    String nombreServer = "";
    for (int i = 0; i < dns.size(); i++) {
      nombreServer = (String) dns.get(i);
      int pos = nombreServer.indexOf(';');
      String servi = nombreServer.substring(0, pos);
      if (servi.equals(nombre)) {
        int posDos = nombreServer.indexOf(';');
        String ip = nombreServer.substring(posDos + 1, nombreServer.length());
        return ip;
      }
    }
    throw new ServerNameNotFound();
  }
}
