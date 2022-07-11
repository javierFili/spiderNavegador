package spider.navegador;

public class ServerNameNotFound extends Throwable {
  public ServerNameNotFound() {
    super("Error 500, server name not found");
  }

  public ServerNameNotFound(String men) {
    super(men);
  }
}
