package spider.servidor;

class Main {
  public static void main(String[] args) {
    //ServidorWeb google = new ServidorWeb("www.google.com");
    ServidorWeb fcyt = new ServidorWeb("www.fcyt.umss.edu.bo");
    /* ServidorWeb csumss = new ServidorWeb("www.cs.umss.edu.bo");*/
    //google.iniciar();
    fcyt.iniciar();
  }
}

