package spider.navegador;
import spider.navegador.frontend.BuscadorGUI;
import javax.swing.*;

public class NavegadorWeb {

  public NavegadorWeb() {}
  public NavegadorWeb(Internet internet){

  }
  public void run() {
    SwingUtilities.invokeLater(new Runnable() {
                                 @Override
                                 public void run() {
                                   JFrame frame = new BuscadorGUI();
                                   frame.setSize(500, 550);
                                   frame.setVisible(true);
                                   frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                                 }
                               }
    );
  }
  public String ejecutarPedido(String url) {
    // Parsea la url y traduce el nombre a una dirección ip para conectarse por medio de un socket al puerto por defecto 8080
    // Envia el pedido
    return  "";
  }

}