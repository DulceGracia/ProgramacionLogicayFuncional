
package plyf1p;

import java.awt.Color;
import java.util.LinkedList;

/**
 *
 * @author DulceGracia
 */
public class GrafoBeltran {
    private Nodo[] V;
    private Arista[] E;
    private int nV, nE;
    private boolean esDir;
    private int[][] matriz;
    private String[][] matrizDFS;
    
    /*Variables para el SCC*/
    private GrafoBeltran Transpuesta;
    private int Direccion;
    LinkedList<LinkedList<Nodo>> Bosque;
    
    public GrafoBeltran(int nodos, int aristas, boolean dir){
        V = new Nodo[nodos];
        E = new Arista[aristas];
        nV = 0;
        nE = 0;
        esDir = dir;
    }
    
    /*Metodo para crear aristas en el grafo*/
    public void addArista(String n1, String n2, String peso){
        Nodo nodo1 = isNodo(n1), nodo2 = isNodo(n2);
        if(nodo1 == null){
            nodo1 = new Nodo(n1);
            V[nV++] = nodo1;
        }
        if(nodo2 == null){
            nodo2 = new Nodo(n2);
            V[nV++] = nodo2;
        }
        E[nE++]= new Arista(nodo1,nodo2,Integer.valueOf(peso));
        nodo1.getAdj().add(nodo2);
        if(!esDir && !nodo1.getNombre().equals(nodo2.getNombre())){ // implementa el regreso... 
            nodo2.getAdj().add(nodo1);
        }
        
        
    }
    
    /*Metodo para saber si es nodo o la información de arriba*/
    public Nodo isNodo(String nodo){
        for (int i = 0; i < nV; i++) {
            Nodo aux = V[i];
            if(nodo.compareTo(aux.getNombre())==0){ //checa que el nodo que entra sea igual a alguna posicion en el 
                   return aux;                     //arreglo de nodos
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        String string = "";
        String tmp;
        for (int i = 0; i < nV; i++) { // puede ser ya sea el numero de nodos o el tamaño total del arreglo
           Nodo nodo = V[i];
            tmp = nodo.getNombre()+"\t"; //+ ":" + nodo.getD() + "/" + nodo.getF() + " ";
            for (int j = 0; j < nodo.getAdj().size(); j++) {
               nodo = V[i];
              // tmp = nodo.toString();
              tmp+="\n";
               string+=tmp;
            }
           }
        return string;
     }
    
    /*Algoritmo del BFS*/
    public void bfs(Nodo s){
        for (int i = 0; i < nV; i++) {
            Nodo nodo = V[i];
            nodo.setColor(Color.WHITE);
            nodo.setD(Integer.MAX_VALUE);
        }
        s.setD(0);
        s.setColor(Color.GRAY);
        LinkedList<Nodo> Q = new LinkedList<>();
        Q.add(s);
        Nodo u;
        while(!Q.isEmpty()){
            u = Q.removeFirst();
            for(Nodo v:u.getAdj()){
                if(v.getColor().equals(Color.WHITE)){
                    v.setColor(Color.GRAY);
                    v.setD(u.getD()+1);
                    Q.add(v);
                }
            }
            u.setColor(Color.BLACK);
        }
    }
    
    public Nodo[] getV() {
        return V;
    }

    public void setV(Nodo[] V) {
        this.V = V;
    }
    
    //Utilizando el mismo metodo de bfs
    public void llenarMatriz(){
        matriz = new int[nV][nV];
        for(int i=0; i<nV; i++){
            bfs(V[i]);
            for (int j = 0; j < nV; j++) {
                matriz[i][j] = V[j].getD();
            }
        }
    }
    
    public String imprimeMatriz(){
        String mat="\t";
        for (Nodo n:V) {
            mat+=n.getNombre()+"\t";
        }
        for (int i = 0; i < nV; i++) {
            mat+="\n"+V[i].getNombre()+"\t";
            for (int j = 0; j < nV; j++) {
                if(matriz[i][j]==Integer.MAX_VALUE){
                    mat+="∞\t";
                }else{
                    mat+=matriz[i][j]+"\t";
                }
            }
        }
        return mat;
    }
  /*Metodo dfs con un parametro de tipo arreglo  */
    public void dfs(Nodo nodos[]){
        int tiempo = 0;
        for (Nodo v:nodos) {
            v.setColor(Color.WHITE);
        }
        //Otorgarle el tiempo en que se descubrio
        for(Nodo u:nodos){
            if(u.getColor().equals(Color.WHITE)){
                tiempo=dfsVisit(u, tiempo);
            }
        }
    }
    /* Algoritmo dfs sin ningun parametro*/
    public void dfs(){
        int tiempo = 0;
         for (Nodo v:V) {
            v.setColor(Color.WHITE);
        }
        //Otorgarle el tiempo en que se descubrio
        for(Nodo u:V){
            if(u.getColor().equals(Color.WHITE)){
                tiempo=dfsVisit(u, tiempo);
            }
        }
    }
    /* Algoritmo del dfs Visit */
    public int dfsVisit(Nodo u, int tiempo){
        
        LinkedList<Nodo> adj = u.getAdj();
        
        u.setColor(Color.GRAY);
        u.setD(++tiempo);
        for (Nodo v:adj) {
            if(v.getColor().equals(Color.WHITE)){
                tiempo=dfsVisit(v,tiempo);
            }
        }
        u.setColor(Color.BLACK);
        u.setF(++tiempo);
        return tiempo;
    }
    
    /* Metodo de ordenamiento */
    public void sorted(){
        for(int i = 1 ; i < V.length ; i++){
           int j = i;
           Nodo toInsert  = V[i];
           while((j>0) && (V[j-1].getF() < toInsert.getF())){
               V[j] = V[j-1];
               j--;
           }
              V[j] = toInsert;
        }
        
        
    }
    
    /* Algoritmo fuertemente conectado */
   public void SCC(GrafoBeltran g){
       /*Primero se calcula el paso 1 que es calcular el dfs al grafo normal*/
        dfs();
        
       /*Luego se calcula la transpuesta que es el paso 2*/
        for(Nodo v: g.getV()){
            v.setColor(Color.WHITE);
            v.setD(0);
            v.setF(0);
        }
       
        /*Paso 3*/
       
       for(int i=0;i<V.length;i++){
        System.out.println("Arreglo V:"+ V[i].getF()+"  "+V[i].getNombre()+"\n");   
       }
       System.out.println("--------------------------------------------------------------");
   
       sorted();
       
       for(int i=0;i<V.length;i++){
        System.out.println("Arreglo V:"+ V[i].getF()+"  "+V[i].getNombre()+"\n");   
       }
        //
       int tiempo = 0;
       Bosque = new LinkedList<LinkedList<Nodo>>();//fuertemente mente conectado
       
                  
       for(Nodo v : g.getV()){
        LinkedList<Nodo> tmp  = new LinkedList<Nodo>(); //Arbol
        Bosque.add(tmp);
        if(v.getColor() == Color.WHITE){
            tiempo = dfsVisitS(v,tiempo,tmp);
        }else{
            Bosque.pollLast();
           
        }
      
       }
       String str="";
       int i=0;
       for (LinkedList<Nodo> a : Bosque){
           str+="Arbol"+(++i)+": ";
           for(Nodo z: a){
               str += z.getNombre();
           }
           str+="\n";
           
       }
            System.out.println("Llegue aqui :)");    
            System.out.println(str);
   } 
   
   public int dfsVisitS(Nodo u, int tiempo, LinkedList arbol){
        LinkedList<Nodo> adj = u.getAdj();
        arbol.add(u);
        u.setColor(Color.GRAY);
        u.setD(++tiempo);
        for (Nodo v:adj) {
            if(v.getColor().equals(Color.WHITE)){
                tiempo=dfsVisitS(v,tiempo,arbol);
            }
        }
        u.setColor(Color.BLACK);
        u.setF(++tiempo);
        return tiempo;
       
   }
 
    public GrafoBeltran getTranspuerta(){
       GrafoBeltran g = new GrafoBeltran(V.length, E.length,esDir);
       for (int i = 0; i < V.length; i++) {
         //  g.V[i] = new Nodo(V[i].getNombre());
           g.addNodo(new Nodo(V[i].getNombre()));
       }
       for(int i = 0 ; i < E.length; i++){
           g.addArista(E[i].getNodoDestino().getNombre(),
                       E[i].getNodoOrigen().getNombre(), String.valueOf(E[i].getPeso()));
       }
       return g;
   }
    
    
    public void llenarMatrizDFS(){
        matrizDFS = new String[nV][nV];
        Nodo auxV[] = V;
        for(int i=0; i<nV; i++){
            Nodo aux = auxV[0];
            for (int j = 0; j < nV-1; j++) {
                auxV[j] = auxV[j+1];
            }
            auxV[nV-1]=aux;
            dfs(auxV);
            for (int j = 0; j < nV; j++) {
                if(j+i<nV){
                    matrizDFS[i][j+i] = auxV[j].getD()+"/"+auxV[j].getF();
                }
                else{
                    matrizDFS[i][nV-j-1] = auxV[j].getD()+"/"+auxV[j].getF();
                }
            }
        }
    }
    
    public String imprimeMatrizDFS(){
        String mat="\t";
        for (Nodo n:V) {
            mat+=n.getNombre()+"\t";
        }
        for (int i = 0; i < nV; i++) {
            mat+="\n"+V[i].getNombre()+"\t";
            for (int j = 0; j < nV; j++) {
                mat+=matrizDFS[i][j]+"\t";
            }
        }
        return mat;
    }
    
    
    
    public void grafoInverso(){
         nE=0; //numero de aritstas. 
        for(Nodo n:V){
            n.getAdj().clear();
        }
        for(Arista a:E){
            addArista(a.getNodoDestino().getNombre(), a.getNodoOrigen().getNombre(), a.getPeso()+"");
        }
    }
   
   
   public void addNodo(Nodo nodo){
       V[nV++]=nodo;
   }
    
    public String imprimeAristas(){
        String ar="Aristas:\n";
        for(Arista a:E){
            ar+=a.getNodoOrigen().getNombre()+"-->"+a.getNodoDestino().getNombre()+"\n";
        }
        return ar;
    }
    
    //Asi lo hice yo
//    public void llenarMatriz(){
//        matriz = new int[nV][nV];
//        for(int i=0; i<nV; i++){
//            matriz[i] = comoLlegar(V[i]);
//        }
//    }
//    
//    public String imprimeMatriz(){
//        String mat="\t";
//        for (Nodo n:V) {
//            mat+=n.getNombre()+"\t";
//        }
//        for (int i = 0; i < nV; i++) {
//            mat+="\n"+V[i].getNombre()+"\t";
//            for (int j = 0; j < nV; j++) {
//                if(matriz[i][j]==Integer.MAX_VALUE){
//                    mat+="-\t";
//                }else{
//                    mat+=matriz[i][j]+"\t";
//                }
//            }
//        }
//        return mat;
//    }
//    
//    public int[] comoLlegar(Nodo nodo){
//        int tmp[] = new int[nV];
//        for (int i = 0; i < nV; i++) {
//            tmp[i] = Integer.MAX_VALUE;
//        }
//        tmp[indexV(nodo.getNombre())] = 0;
//        LinkedList<Nodo> Q = new LinkedList<>();
//        Q.add(nodo);
//        Nodo u;
//        while(!Q.isEmpty()){
//            u = Q.removeFirst();
//            for(Nodo v:u.getAdj()){
//                if(tmp[indexV(v.getNombre())]==Integer.MAX_VALUE){
//                    tmp[indexV(v.getNombre())] = tmp[indexV(u.getNombre())] + 1;
//                    Q.add(v);
//                }
//            }
//        }
//        return tmp;
//    }
//    
//    public int indexV(String n){
//        for (int i = 0; i < nV; i++) {
//            if(V[i].getNombre().equals(n)){
//                return i;
//            }
//        }
//        return -1;
//    }
    
}
