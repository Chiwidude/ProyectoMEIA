/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia.Clases;

import java.io.File;
import java.io.RandomAccessFile;

/**
 *
 * @author kevin
 */
public class Binario {
    
    File archivoBinario;
    RandomAccessFile archivoMaster;
    NodoBinario nodoBinario;
    
    public Binario(String RutaBinario, NodoBinario nodoBinario){         
        archivoBinario = new File(RutaBinario);
        this.nodoBinario = nodoBinario;
    }
    
    public void Insertar(NodoBinario nuevoNodo){
        
    }
    
    public void Eliminar(NodoBinario nodoEliminar){
        
    }
    
    public void Reorganizar(){
        
    }
    
    public void Busqueda(NodoBinario nodoBuscado){
        
    }
}
