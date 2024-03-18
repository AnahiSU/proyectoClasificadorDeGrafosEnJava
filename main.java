import java.util.ArrayList;
/**
 * Write a description of class main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class main
{
    public static void main(String args[]){

        Grafo grafo = new Grafo(3);
        //ingresar las aristas solo una vez por par
        grafo.agregarVecino(0,1);
        grafo.agregarVecino(0,2);
        grafo.agregarVecino(1,2);
        
        ClasificadorDeGrafos g = new ClasificadorDeGrafos(grafo);
        System.out.println(g.clasificar());
    }

}
