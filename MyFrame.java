import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.ArrayList;

public class MyFrame extends JFrame
{
    private JLabel titulo = new JLabel("Dibuja aquí el grafo usando los botones.");

    private JButton botonVertice = new JButton("Vértice");
    private JButton botonArista = new JButton("Conectar arista");
    private JButton botonListo = new JButton("Clasificar");
    private JButton botonReiniciar = new JButton("Reiniciar");

    private Grafo grafo;

    private JTextField cajita1 = new JTextField();
    private JTextField cajita2 = new JTextField();

    private boolean dibAris = false, dibVerti = false;
    private int xAct = 0;
    private int yAct = 0;
    private int n = 0;
    private ArrayList<Integer> puntos = new ArrayList();
    private ArrayList<Integer> aristas = new ArrayList();

    JPanel zonaDeDibujo = new JPanel(){
            public void paint(Graphics g){
                super.paint(g);
                if(dibVerti){ 
                    //verifica si en esta repintada se debe pintar también un círculo
                    int n = 0;
                    for(int i = 0; i<puntos.size(); i = i+2){
                        //recorre la lista de puntos para pintar los vértices anteriores
                        g.setColor(Color.blue);
                        g.fillOval(puntos.get(i),puntos.get(i+1), 40, 40);

                        g.setColor(Color.green);
                        g.fillOval(puntos.get(i)+20, puntos.get(i+1)+20, 5, 5);

                    }
                }
                if(dibAris){
                    for(int i = 0; i<aristas.size();i=i+2){
                        g.setColor(Color.black);
                        try{
                            int x1 = puntos.get(aristas.get(i)*2)+20;
                            int y1 = puntos.get((aristas.get(i)*2)+1)+20;
                            int x2 = puntos.get(aristas.get(i+1)*2)+20;
                            int y2 = puntos.get((aristas.get(i+1)*2)+1)+20;
                            g.drawLine(x1,y1,x2,y2);
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(zonaDeDibujo,"Vértice no encontrado.");
                            aristas.remove(aristas.size()-1);
                            aristas.remove(aristas.size()-1);
                            zonaDeDibujo.repaint();
                        }
                    }
                }
            }
        };

    public MyFrame(){

        //seteado del frame

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1000,700);
        this.setVisible(true);
        this.setTitle("Clasificador de grafos simples especiales");
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(0,170,204));

        titulo.setVerticalAlignment(JLabel.TOP);
        titulo.setFont(new Font("Arial",Font.ROMAN_BASELINE,30));
        titulo.setBounds(10,10,700,250);

        //iniciación de los botones

        botonVertice.setBounds(800,100,100,40);

        botonArista.setBounds(790,210,130,40);

        botonListo.setBounds(800,300,100,40);

        botonReiniciar.setBounds(800,400,100,40);

        //agrupacion de botones

        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(botonArista);
        bgroup.add(botonVertice);
        bgroup.add(botonListo);
        bgroup.add(botonReiniciar);

        cajita1.setBounds(820,170,30,30);
        cajita2.setBounds(870,170,30,30);
        //iniciacion de la zona de dibujo

        zonaDeDibujo.setBounds(20,60,700,500);

        //Agregar componentes al frame
        this.add(titulo);
        this.add(zonaDeDibujo);
        this.add(botonVertice);
        this.add(botonArista);
        this.add(botonListo);
        this.add(botonReiniciar);
        this.add(cajita1);
        this.add(cajita2);

        zonaDeDibujo.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if(dibVerti){
                        //dibVerti indica si se presiono el boton vertice para comenzar a dibujarlos

                        //las coordenadas marcan el punto para dibujar el círculo para el vértice
                        //se añaden a un arrayList para poder dibujarlos cada que el panel se repinte
                        puntos.add(e.getX());
                        puntos.add(e.getY());

                        //cada vértice tendrá un label con su número
                        JLabel t = new JLabel("" + n);
                        t.setBounds(e.getX()-10, e.getY()-10, 100,10);
                        zonaDeDibujo.add(t);
                        n++;

                        //aquí se pintan los círculos
                        zonaDeDibujo.repaint();
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }
            });

        // Dibuja el círculo si se ha hecho click
        botonVertice.addActionListener(new ActionListener(){
                public void actionPerformed (ActionEvent e){
                    dibVerti = true;
                }
            });
        botonListo.addActionListener(new ActionListener(){
                public void actionPerformed (ActionEvent e){
                    dibVerti = false;
                    dibAris = false;
                    
                    //clasificacion del grafo

                    grafo = new Grafo(puntos.size()/2); //la cantidad de puntos siempre será par
                    //y la mitad será el número de vértices
                    for(int i = 0; i<aristas.size(); i = i+2){
                        grafo.agregarVecino(aristas.get(i),aristas.get(i+1));
                    }
                    ClasificadorDeGrafos g1 = new ClasificadorDeGrafos(grafo);
                    String clas = g1.clasificar();
                    JOptionPane.showMessageDialog(zonaDeDibujo, clas);
                    resetearValores();
                }
            });
        botonReiniciar.addActionListener(new ActionListener(){
                public void actionPerformed (ActionEvent e){
                    //cuando se presione listo, el panel de dibujo se reinicia
                    resetearValores(); 
                }
            });

        botonArista.addActionListener(new ActionListener(){
                public void actionPerformed (ActionEvent e){
                    //cuando se presione listo, el panel de dibujo se reinicia
                    aristas.add(Integer.parseInt(cajita1.getText()));
                    aristas.add(Integer.parseInt(cajita2.getText()));
                    dibAris = true;
                    zonaDeDibujo.repaint();
                    cajita1.setText("");
                    cajita2.setText("");
                }
            });

        //para las aristas
        cajita1.addKeyListener(new KeyListener(){
                public void keyTyped (KeyEvent e){
                    //verifica que no se metan letras

                    if(!Character.isDigit(e.getKeyChar()) ){
                        e.consume();
                    }
                }

                public void keyReleased(KeyEvent e){

                }

                public void keyPressed(KeyEvent e){

                }
            });
        cajita2.addKeyListener(new KeyListener(){
                public void keyTyped (KeyEvent e){
                    //verifica que no se metan letras
                    if(!Character.isDigit(e.getKeyChar()) ){
                        e.consume();
                    }
                }

                public void keyReleased(KeyEvent e){

                }

                public void keyPressed(KeyEvent e){

                }
            });

    }

    private void resetearValores(){
        puntos.clear();
        aristas.clear();
        grafo = new Grafo(1);
        zonaDeDibujo.repaint();
        zonaDeDibujo.removeAll();
        n=0;
        dibVerti = false;
        dibAris = false;
    }
}
