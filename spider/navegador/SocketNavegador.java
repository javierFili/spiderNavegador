package spider.navegador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketNavegador {
  static final String HOST = "localhost";
  static final int PUERTO = 8081;

  public SocketNavegador() {
    try {
      Socket skCliente = new Socket(HOST, PUERTO);

      DataInputStream in = new DataInputStream(skCliente.getInputStream());
      DataOutputStream out = new DataOutputStream(skCliente.getOutputStream());

      Scanner param = new Scanner(System.in);

      String mensajeOut = "";

      while (!mensajeOut.equals("chau")) {
        mensajeOut = param.next();
        out.writeUTF(mensajeOut);
        System.out.println(in.readUTF());
      }
      skCliente.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static void main(String[] arg) {
    new SocketNavegador();
  }
}
