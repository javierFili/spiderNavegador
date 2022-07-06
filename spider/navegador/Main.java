package spider.navegador;

class Main {
  public static void main(String[] args) {
    Internet internet = new Internet();
    NavegadorWeb aracnido = new NavegadorWeb(internet);
    aracnido.run();
  }
}