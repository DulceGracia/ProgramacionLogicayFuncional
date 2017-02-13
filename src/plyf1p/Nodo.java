
package plyf1p;

import java.awt.Color;
import java.util.LinkedList;

/**
 *
 * @author DulceGracia
 */
public class Nodo {
    private String nombre;
    private Color color;
    private int d; //Distancia
    private int f; //Tiempo
    private LinkedList<Nodo> adj; //Nodos vecinos

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.d = Integer.MAX_VALUE;
        this.color = Color.WHITE;
        adj = new LinkedList<>();
    }

    public String toString(){
        String string = "";
        string+=nombre;
        string += ":";
        string +=String.valueOf(d);
        string +="/";
        string += String.valueOf(f);
        string += " ";
        for (Nodo nodo : adj){
            string+= nodo.getNombre();
            string += " ";
        }
       return string; 
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public LinkedList<Nodo> getAdj() {
        return adj;
    }

    public void setAdj(LinkedList<Nodo> adj) {
        this.adj = adj;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }
    
    
}
