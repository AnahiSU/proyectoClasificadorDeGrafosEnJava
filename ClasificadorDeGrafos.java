import java.util.ArrayList;
/**
 * Write a description of class grafos here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ClasificadorDeGrafos
{
    private Grafo grafo;
    private ArrayList<Integer>[] arr; 
    private int n;

    public ClasificadorDeGrafos(Grafo grafo){
        this.grafo = grafo;
        this.arr = grafo.getVerticesYVec();
        n = grafo.getVertices();
    }

    private boolean clasificarCiclo(){
        boolean flag = true;
        //clasificación para un ciclo
        for(int i = 0; i<n;i++){
            if(arr[i].size()!=2){
                flag = false;
            }
        }
        if(grafo.contarAristas() == n){
            flag = true;
        }else{
            flag = false;
        }

        return flag;
    }

    private boolean clasificarCompleto(){
        boolean flag = true;
        //clasificación de un grafo completo
        if(grafo.contarAristas() == ((n*n)-n)/2){
            for(int i = 0; i<n; i++){/*grado de cada vértice*/
                if(arr[i].size() != n-1){
                    flag = false;
                    i = arr.length;
                }
            }
        }else{
            flag = false;
        }
        return flag;
    }

    private boolean clasificarRueda(){
        int w = -1;
        boolean flag = false;
        //localizar vertice w
        for(int i = 0; i<n; i++){
            if(arr[i].size()+1==n){
                flag = true;
                w = i;
            }
        }
        if(flag){
            if(grafo.contarAristas() == 2*(n-1)){
                for(int i = 0; i<n; i++){
                    if(i!=w && arr[i].size()==3){
                        flag = true; 
                    }else if(i!=w){
                        flag = false;
                    }
                }
            }else{
                flag = false;
            }
        }
        return flag;
    }
    
    private boolean clasificarCubo(){
        boolean flag = false;
        if(esPotenciaDeDos(grafo.getVertices())){
            int ord = (int)(Math.log(n)/Math.log(2)); 
            if(grafo.contarAristas() == Math.pow(2,ord-1)*ord){
                flag = true;
            }
        }
        return flag;
    }

    private boolean esPotenciaDeDos(int num){
        // Comprobar que el número sea diferente de 0
        if (num == 0) {
            return false;
        }

        // Convertir el número a representación binaria
        String binario = Integer.toBinaryString(n);

        // Restar 1 al número original
        int resta = num - 1;

        // Verificar si el resultado de la resta es una potencia de 2
        return (resta & num) == 0;
    }
    
    public String clasificar(){
        String res = "";
        if(arr.length > 0){
            if(clasificarCiclo()){
                if(res.isEmpty()){
                    res += "Es ciclo";    
                }else{
                    res+=", es ciclo";
                }
            }
            if(clasificarRueda()){
                if(res.isEmpty()){
                    res += "Es rueda";    
                }else{
                    res+=", es rueda";
                }
            }
            if(clasificarCompleto()){
                if(res.isEmpty()){
                    res += "Es completo";    
                }else{
                    res+=", es completo";
                }
            }
            if(clasificarCubo()){
                if(res.isEmpty()){
                    res+= "Es cubo";
                }else{
                    res+=", es cubo";
                }
            }
            if((!clasificarCompleto() && !clasificarRueda() && !clasificarCiclo()&& !clasificarCubo() )|| grafo.isEmpty()){
                res = "No es completo, rueda, ciclo o cubo.";         
            }
        }else{
            res = "Es vacio";
        }

        
        return res;
    }
}
