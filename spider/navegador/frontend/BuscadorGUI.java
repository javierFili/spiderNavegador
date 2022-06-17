package spider.navegador.frontend;

import spider.navegador.backend.BuscadorBackend;
import spider.servidor.Internet;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscadorGUI extends JFrame {
  private JTextArea documentoShow;
  private JTextField datosDeEntrada;
  private JButton botonDeBuscador;
  private JPanel panel1;
  private BuscadorBackend backend;
  private Internet internet;

  public BuscadorGUI(Internet inter) {
    super("Navegador Spider");
    this.internet = inter;
    backend = new BuscadorBackend(internet);
    setContentPane(panel1);
    botonDeBuscador.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String entrada = datosDeEntrada.getText();
        String res = backend.busqueda(entrada);
        documentoShow.setText(res);
      }
    });
  }

}
