package spider.navegador;

import spider.navegador.frontend.NavegadorWeb;

class Main {
  public static void main(String[] args) {
    Internet internet = new Internet();
    NavegadorWeb navegador = new NavegadorWeb(internet);
    navegador.run();
  }
}