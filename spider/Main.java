//Javier Filigrana Agreda
package spider;

import spider.navegador.NavegadorWeb;
import spider.servidor.Internet;
import spider.servidor.ServidorWeb;

class Main {
  public static void main(String[] args) {
    ServidorWeb google = new ServidorWeb("www.google.com");
    ServidorWeb fcyt = new ServidorWeb("www.fcyt.umss.edu.bo");
    ServidorWeb csumss = new ServidorWeb("www.cs.umss.edu.bo");
    Internet internet = new Internet();
    internet.registrar(google);
    internet.registrar(fcyt);
    internet.registrar(csumss);
    NavegadorWeb aracnido = new NavegadorWeb(internet);
    aracnido.run();
  }
}

