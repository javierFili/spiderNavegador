package spider.navegador.frontend;

import spider.navegador.backend.BuscadorBackend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class BuscadorGUI extends JFrame {
  private JTextArea documentoShow;
  private JTextField datosDeEntrada;
  private JButton botonDeBuscador;
  private JPanel panel1;

  private BuscadorBackend backend;

  public BuscadorGUI() {
    super("Navegador Spider");
    setContentPane(panel1);
    arrancarSocket();
    backend = new BuscadorBackend();
  }

  private String mensajeOut = "";
  private DataInputStream in;
  private DataOutputStream out;

  public void arrancarSocket() {
    String HOST = "localhost";
    int PUERTO = 8080;
    try {
      Socket skCliente = new Socket(HOST, PUERTO);
      in = new DataInputStream(skCliente.getInputStream());
      out = new DataOutputStream(skCliente.getOutputStream());
      botonDeBuscador.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          String entrada = "GET;";
          entrada += datosDeEntrada.getText();
          try {
            mensajeOut = entrada;
            out.writeUTF(mensajeOut);
            String mensajeIn = in.readUTF();
            mensajeIn = backend.obtenerCadena(mensajeIn);
            documentoShow.setText(mensajeIn);
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        }
      });

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
