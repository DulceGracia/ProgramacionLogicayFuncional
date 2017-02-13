/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plyf1p;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author DulceGracia
 */
public class PLyF1P {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LinkedList<String> lista = new LinkedList<>();
        try{
            JFileChooser archivo = new JFileChooser();
            
            FileInputStream fstream = new FileInputStream("xd.txt");
            DataInputStream entrada = new DataInputStream(fstream);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String strLinea;
            while((strLinea = buffer.readLine()) != null){
                lista.add(strLinea);
            }
            entrada.close();
        } catch (Exception ex) {
            System.out.println("Ocurrio un error: "+ex.getMessage());
        }
        
        int nVertices = Integer.valueOf(lista.getFirst());
        int nArcos = Integer.valueOf(lista.get(1));
        boolean esDirigido = Boolean.FALSE;
        int tmp = Integer.valueOf(lista.get(2));
        if(tmp == 1){
            esDirigido = Boolean.TRUE;
        }
        
        GrafoBeltran grafo = new GrafoBeltran(nVertices,nArcos, esDirigido);
      //  int i;
       
        for (int i = 3; i < 3+nArcos; i++) {
            String items[] = lista.get(i).split(" ");
            if(items.length != 3){
                System.out.println("Archivo con formato no valido");
                return;
            }else{
                grafo.addArista(items[0], items[1], items[2]);
            }
//            for(;i<lista.size();i++){
//                Nodo nodo = new Nodo(lista.get(i));
//                grafo.addNodo(nodo);
//            }
        }
        /*
        grafo.bfs(grafo.getV()[0]);
        System.out.println("\nGrafo BFS: \n"+grafo);
        
        grafo.llenarMatriz();
        System.out.println("Matriz: \n"+grafo.imprimeMatriz());
        
        grafo.dfs(grafo.getV());
        System.out.println("\nGrafo DFS: \n"+grafo);
        
        grafo.llenarMatrizDFS();
        System.out.println("Matriz: \n"+grafo.imprimeMatrizDFS());
        
        System.out.println(grafo.imprimeAristas());
        grafo.grafoInverso();
        System.out.println(grafo.imprimeAristas());
        */
        System.out.println("Grafo:\n"+grafo);
        //grafo.grafoInverso();
     
        System.out.println("Grafo transpuesto:\n"+grafo);
        
        grafo.SCC(grafo.getTranspuerta());
        
        
        
    }
    
}
