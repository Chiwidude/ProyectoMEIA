/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia.Clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author kevin
 */
public class Binario {
    
    File archivoBinario;
    RandomAccessFile archivoMaster;
    NodoBinario nodoBinario;
    long tamanio = 0;
    
    public Binario(String RutaBinario){         
        archivoBinario = new File(RutaBinario);
    }
    
    public void Insertar(NodoBinario nuevoNodo) throws FileNotFoundException, IOException{
        archivoMaster = new RandomAccessFile(archivoBinario, "rw");  
        tamanio = archivoMaster.length();
        if(tamanio == 0){//Nodo Raiz  
        archivoMaster.seek(tamanio);
        nuevoNodo.setDerecho("0");
        nuevoNodo.setIzquierdo("0");
        archivoMaster.writeBytes(nuevoNodo.toString());
        archivoMaster.writeBytes(System.lineSeparator());
        archivoMaster.close();        
        }else{//Nodo que no son Raiz
            archivoMaster.seek(tamanio);
            archivoMaster.writeBytes(nuevoNodo.toString());
            archivoMaster.writeBytes(System.lineSeparator());
            archivoMaster.close();            
        }
    }
    
    public void Eliminar(NodoBinario nodoEliminar){
        
    }
    
    public void Reorganizar(){
        
    }
    
    public void Busqueda(NodoBinario nodoBuscado){
        
    }
}
