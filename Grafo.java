import java.util.ArrayList;
/**
 * Write a description of class Grafo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Grafo  
{
    private ArrayList<Integer>[] vertices;
    private int nVertices;
    private int aristas;
    
    public Grafo(int nVertices){
        vertices = new ArrayList[nVertices];
        for(int i = 0;i<nVertices;i++){
            vertices[i] = new ArrayList();
        }
        this.nVertices = nVertices;
        this.aristas = contarAristas();
    }
    
    public boolean isEmpty(){
        boolean res = true;
        for(int i = 0;i<vertices.length;i++){
            if(vertices[i].size()> 0){
                res = false;
            }
        }
        return res;
    }
    
    public void agregarVecino(int pos, int dato){
        //agrega para ambos ya que son grafos bidireccionales
        vertices[pos].add(dato);
        vertices[dato].add(pos);
    }
    public int contarVecinos(int pos){
        int res = 0;
        for(int i = 0; i<vertices[pos].size(); i++){
            res++;
        }
        return res;
    }
    public int contarAristas(){
        int res = 0;
        for(int i = 0; i<nVertices;i++){
            
            res += vertices[i].size(); 
        }
        return res/2;
    }
    public int getVertices(){
        return nVertices;
    }
    public ArrayList<Integer>[] getVerticesYVec(){
        return vertices;
    }
}
