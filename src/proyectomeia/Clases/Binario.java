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
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class Binario {
    
    File archivoBinario;
    RandomAccessFile archivoMaster;
    NodoBinario nodoBinario;
    long tamanio = 0;
    ArrayList<NodoBinario> nodos = new ArrayList<>();
    
    public Binario(String RutaBinario){         
        archivoBinario = new File(RutaBinario);
    }
    
    public void Insertar(NodoBinario nuevoNodo) throws FileNotFoundException, IOException{
        archivoMaster = new RandomAccessFile(archivoBinario, "rw");  
        tamanio = archivoMaster.length();
        archivoMaster.seek(tamanio);
        if(tamanio == 0){
        nuevoNodo.setDerecho("-1");
        nuevoNodo.setIzquierdo("-1");
        archivoMaster.writeBytes(nuevoNodo.toString());
        archivoMaster.writeBytes(System.lineSeparator());
        archivoMaster.close();      
        }else{  
        nuevoNodo.setDerecho("-1");
        nuevoNodo.setIzquierdo("-1");
        archivoMaster.writeBytes(nuevoNodo.toString());
        archivoMaster.writeBytes(System.lineSeparator());
        nodoBinario = new NodoBinario("", "", "", "", "", "");
        nodoBinario = nuevoNodo;
        Reorganizar();
        nodos.clear();
        archivoMaster.close();        
        }
        
    }
    
    public void Eliminar(NodoBinario nodoEliminar){
        
    }
    
     /**
     *Modifica un dato del Indice
     * @param viejo
     * @param Nuevo
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void Modificar(String viejo, String Nuevo) throws FileNotFoundException, IOException{
        int posicion = PosicionRegistro(viejo);
        RandomAccessFile archivo = new RandomAccessFile(archivoBinario,"rw");
        for(int i = -1; i<posicion; i++){
            archivo.readLine();
        }
        long rewrite = archivo.getFilePointer();
        archivo.seek(rewrite);
        archivo.writeBytes(Nuevo);
        archivo.close();
        
    }
    
    private int PosicionRegistro(String registro){
        int posicion = -1;
        try {
           RandomAccessFile contador = new RandomAccessFile(archivoBinario,"r");
            String line;
            while((line = contador.readLine())!= null){
                if(line.equals(registro)){
                    contador.close();
                    return posicion;
                }
                posicion++;
            }
            contador.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ApiloFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ApiloFile.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return posicion;
    }
    
    public void Reorganizar() throws IOException{
        String linea;        
        archivoMaster.seek(0);
        while((linea = archivoMaster.readLine()) != null){
            NodoBinario nodo = new NodoBinario("", "", "", "", "", "");
            nodo.CreateFromString(linea);
            nodos.add(nodo);
            archivoMaster.seek(archivoMaster.getFilePointer());
        }
        NodoBinario raiz = new NodoBinario("", "", "", "", "", "");
        raiz = nodos.get(0);
        archivoMaster.seek(0);
        for (int i = 1; i < nodos.size(); i++) {
            NodoBinario hijo = nodos.get(i);
            //Inserciones en raiz
            if(raiz.getUsuarioEmisor().compareTo(hijo.getUsuarioEmisor()) > 1
                    || raiz.getUsuarioReceptor().compareTo(hijo.getUsuarioReceptor()) > 1
                    || raiz.getFechaTransaccion().compareTo(hijo.getFechaTransaccion()) > 1){//Raiz Derecho                
                if(raiz.getDerecho().contains("-1")){
                    NodoBinario viejo = new NodoBinario("", "", "", "", "", "");
                    viejo.CreateFromString(raiz.toString());
                    raiz.setDerecho(String.valueOf(PosicionRegistro(hijo.toString())+1));
                    Modificar(viejo.toString(), raiz.toString());
                }else {
                    //Nodo padre
                }
            }else if(raiz.getUsuarioEmisor().compareTo(hijo.getUsuarioEmisor()) < 1
                    || raiz.getUsuarioReceptor().compareTo(hijo.getUsuarioReceptor()) < 1
                    || raiz.getFechaTransaccion().compareTo(hijo.getFechaTransaccion()) < 1){//Raiz Izquierdo                
                if(raiz.getIzquierdo().contains("-1")){
                    NodoBinario viejo = new NodoBinario("", "", "", "", "", "");
                    viejo.CreateFromString(raiz.toString());
                    raiz.setIzquierdo(String.valueOf(PosicionRegistro(hijo.toString())+1));
                    Modificar(viejo.toString(), raiz.toString());
                }else{
                    //Nodo padre
                }
            }
        }
    }

    
    public void Busqueda(NodoBinario nodoBuscado){
        
    }
    
}


