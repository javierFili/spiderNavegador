package spider.navegador;

import spider.navegador.backend.BuscadorBackend;
import spider.navegador.frontend.BuscadorGUI;
import spider.servidor.Internet;

import javax.swing.*;

public class NavegadorWeb {
  private Internet aracnido;

  private BuscadorBackend buscaBackend;

  public NavegadorWeb(Internet internet) {
    this.aracnido = internet;
    buscaBackend = new BuscadorBackend(aracnido);

  }

  public void run() {

    SwingUtilities.invokeLater(new Runnable() {
                                 @Override
                                 public void run() {
                                   JFrame frame = new BuscadorGUI(aracnido);
                                   frame.setSize(500, 550);
                                   frame.setVisible(true);
                                   frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                                 }
                               }
    );
  }

}
