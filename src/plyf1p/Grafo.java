
package plyf1p;

import java.util.LinkedList;

/**
 *
 * @author DulceGracia
 */
public class Grafo {
    private LinkedList<Nodo> V;
    private LinkedList<Arista> E;
    private int nV,nE;
    private boolean esDir;
    
    public Grafo(int nodos, int aristas, boolean dir){
        nV=nodos;
        nE=aristas;
        V = new LinkedList<>();
        E = new LinkedList<>();
        esDir = dir;
    }

    public int getnV() {
        return nV;
    }

    public int getnE() {
        return nE;
    }
    
    public void addArista(String n1, String n2, String peso){
        Nodo nodo1 = new Nodo(n1), nodo2 = new Nodo(n2);
        if(!V.contains(nodo1)){
            V.add(nodo1);
        }
        if(!V.contains(nodo2)){
            V.add(nodo2);
        }
        E.add(new Arista(nodo1,nodo2,Integer.parseInt(peso)));
        nodo1.getAdj().add(nodo2);
        if(!esDir){
            nodo2.getAdj().add(nodo1);
        }
    }
    
    @Override
    public String toString() {
        String string = "";
        String tmp;
        for (int i = 0; i < nV; i++) {
            Nodo nodo = V.get(i);
            tmp = nodo.getNombre() + " ";
            for (int j = 0; j < nodo.getAdj().size(); j++) {
                tmp += nodo.getAdj().get(j).getNombre() + " ";
            }
            tmp += "\n";
            string += tmp;
        }
        return string;
    }
}
