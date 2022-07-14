package spider.navegador.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import spider.navegador.Internet;
import spider.navegador.arbolHTML.EtiquetaHTML;
import spider.navegador.backend.CreadorArbol;

public class BuscadorGUI extends JFrame {
  private JPanel contenidoNavegador;
  private JTextField textoEntrada;
  private NavegadorWeb navegadorWeb;

  public BuscadorGUI( NavegadorWeb nav) {
    this.navegadorWeb = nav;
    initWindow();
  }

  private void crearComponentes() {
    contenidoNavegador = new JPanel();
    textoEntrada = new JTextField();
  }

  private void accionBotonBuscar() {
    remove(contenidoNavegador);
    contenidoNavegador = new JPanel();
    contenidoNavegador.setLayout(null);
    contenidoNavegador.setBackground(Color.white);
    contenidoNavegador.setBounds(0, 0, 1280, 2000);
    String pedido = textoEntrada.getText();
    String recursoHTML = navegadorWeb.ejecutarPedido(pedido);
    recursoHTML = recursoHTML.replaceAll("\r", "");
    desplegar(recursoHTML);
    add(contenidoNavegador);
    repaint();
  }

  private void redireccion(String command) {
    remove(contenidoNavegador);
    contenidoNavegador = new JPanel();
    contenidoNavegador.setLayout(null);
    contenidoNavegador.setBackground(Color.white);
    contenidoNavegador.setBounds(0, 0, 1280, 10000);
    String pedido = textoEntrada.getText();
    String servidor = pedido.split(";")[0];
    NavegadorWeb servicio = navegadorWeb;
    String recursoHTML = servicio.ejecutarPedido(servidor + ";" + command);
    desplegar(recursoHTML);
    add(contenidoNavegador);
    repaint();
  }

  private void desplegar(String documento) {
    CreadorArbol treeGenerator = new CreadorArbol();
    EtiquetaHTML parsear = treeGenerator.crearDOM(documento);
    JComponent root = parsear.graficar();
    ArrayList<JComponent> components = new ArrayList<>();
    if (!(root instanceof JPanel)) {
      root.setBounds(10, 10, 1000, 1000);
      contenidoNavegador.add(root);
    }
    if (root instanceof JPanel) {
      int x = 20;
      int y = -25;
      filtroDeComponentes(root, components, x, y);
    }
    for (JComponent c : components) {
      contenidoNavegador.add(c);
    }
  }

  private void filtroDeComponentes(JComponent root, ArrayList<JComponent> agregado, int x, int y) {
    if (root instanceof JLabel) {
      JLabel label = (JLabel) root;
      label.setBounds(x, y, 1080, 200);
      agregado.add(label);
    }
    if (root instanceof JButton) {
      JButton boton = (JButton) root;
      String nombre = boton.getText();
      String command = htmlCommandRedirect(nombre.trim());
      boton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          super.mouseClicked(e);
          redireccion(command);
        }
      });
      boton.setBounds(x, y + 90, 150, 20);
      agregado.add(boton);
    }
    if (root instanceof JPanel) {
      Component[] contenido = root.getComponents();
      for (Component c : contenido) {
        y += 25;
        filtroDeComponentes((JComponent) c, agregado, x, y);
      }
    }
  }

  private String htmlCommandRedirect(String nombre) {
    String toHTML = nombre.replaceAll(" ", "");
    toHTML += ".html";
    return toHTML.toLowerCase();
  }

  private void initWindow() {
    int width = 1080;
    int heigth = 620;
    setTitle("Navegador Spider");
    setPreferredSize(new Dimension(width, heigth));
    setBackground(Color.WHITE);
    setResizable(false);
    crearComponentes();
    initComponents();
    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  private void initComponents() {
    JPanel divBusqueda = new JPanel();
    JLabel indicacion = new JLabel();
    JButton botonBusqueda = new JButton();
    divBusqueda.setLayout(null);
    divBusqueda.setBounds(0, 0, 1280, 70);
    textoEntrada = new JTextField();
    divBusqueda.setBackground(new Color(101, 121, 67));
    indicacion.setFont(new Font("Bahnschrift", Font.BOLD, 18));
    indicacion.setText("Buscar:");
    textoEntrada.setToolTipText("");
    botonBusqueda.setBackground(new Color(73, 82, 55));
    botonBusqueda.setFont(new Font("Bahnschrift", Font.BOLD, 15));
    botonBusqueda.setText("Buscar");
    botonBusqueda.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        accionBotonBuscar();

      }
    });
    indicacion.setBounds(230, 20, 500, 25);
    textoEntrada.setBounds(385, 18, 500, 25);
    botonBusqueda.setBounds(890, 18, 100, 25);
    divBusqueda.add(indicacion);
    divBusqueda.add(textoEntrada);
    divBusqueda.add(botonBusqueda);
    contenidoNavegador = new JPanel();
    contenidoNavegador.setLayout(null);
    contenidoNavegador.setBackground(Color.white);
    contenidoNavegador.setBounds(0, 100, 1280, 620);
    add(divBusqueda);
    add(contenidoNavegador);
  }
}
