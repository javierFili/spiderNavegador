package spider.servidor;

public class PedidoHTTP {
  private String metodo;  // en principio ahora solo GET
  private String uRL;     // nombre del spider.servidor y recurso

  public PedidoHTTP(String metodo, String uRL) {
    this.metodo = metodo;
    this.uRL = uRL;
  }

  public String getMetodo() {
    return metodo;
  }


  //posible error.
  public String getUrl() {
    return uRL;
  }
}