
package plyf1p;

/**
 *
 * @author DulceGracia
 */
public class Arista {
    private int peso;
    private Nodo nodoOrigen,nodoDestino;

    public Arista(Nodo nodoOrigen, Nodo nodoDestino, int peso) {
        this.peso = peso;
        this.nodoOrigen = nodoOrigen;
        this.nodoDestino = nodoDestino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Nodo getNodoOrigen() {
        return nodoOrigen;
    }

    public void setNodoOrigen(Nodo nodoOrigen) {
        this.nodoOrigen = nodoOrigen;
    }

    public Nodo getNodoDestino() {
        return nodoDestino;
    }

    public void setNodoDestino(Nodo nodoDestino) {
        this.nodoDestino = nodoDestino;
    }
    
    
}
