package spider.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketInternet {
  static final int PUERTO = 8081;
  public SocketInternet() {
    try {
      Scanner resp = new Scanner(System.in);
      ServerSocket skServidor = new ServerSocket(PUERTO);
      System.out.println("Escucho el puerto " + PUERTO);

      for (int numCli = 0; numCli < 3; numCli++) {
        Socket skCliente = skServidor.accept();
        System.out.println("Sirvo   al cliente " + numCli);

        DataInputStream in = new DataInputStream(skCliente.getInputStream());
        DataOutputStream out = new DataOutputStream(skCliente.getOutputStream());
        String mesajeIn = "";
        String mesajeOut = "";
        while (!mesajeIn.equals("chau")) {
          mesajeIn = in.readUTF();
          System.out.println(mesajeIn);
          mesajeOut = resp.next();
          out.writeUTF(mesajeOut);
        }
        skCliente.close();
      }

      System.out.println("Demasiados clientes por hoy");

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static void main(String[] arg) {
    new SocketInternet();
  }
}
