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
    NodoBinario anterior = new NodoBinario("", "", "", "", "", "");
    
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
        RandomAccessFile archivo = new RandomAccessFile(archivoBinario,"rw");
        archivo.seek(0);
        archivoMaster.seek(0);
        while((linea = archivoMaster.readLine()) != null){
            NodoBinario nodo = new NodoBinario("", "", "", "", "", "");
            nodo.CreateFromString(linea);
            nodos.add(nodo);
            archivoMaster.seek(archivoMaster.getFilePointer());
        }
        NodoBinario raiz = new NodoBinario("", "", "", "", "", "");
        NodoBinario padre = new NodoBinario("", "", "", "", "", "");
        
        raiz = nodos.remove(0);
        archivoMaster.seek(0);
        int size = nodos.size();
        for (int i = 0; i < size; i++) {
            if(!nodos.isEmpty()){
            NodoBinario hijo = new NodoBinario("", "", "", "", "", "");
            hijo = nodos.remove(0);
            //Inserciones en raiz
            if(raiz.getDerecho().contains("-1")){                
                    if(raiz.getUsuarioEmisor().compareTo(hijo.getUsuarioEmisor()) > 1
                    || raiz.getUsuarioReceptor().compareTo(hijo.getUsuarioReceptor()) > 1
                    || raiz.getFechaTransaccion().compareTo(hijo.getFechaTransaccion()) > 1){//Raiz Derecho    
                    NodoBinario viejo = new NodoBinario("", "", "", "", "", "");
                    anterior = hijo;
                    viejo.CreateFromString(raiz.toString());
                    raiz.setDerecho(String.valueOf(PosicionRegistro(hijo.toString())+1));
                    Modificar(viejo.toString(), raiz.toString());                
                    }                
            }else if(raiz.getIzquierdo().contains("-1")){                 
                    if(raiz.getUsuarioEmisor().compareTo(hijo.getUsuarioEmisor()) < 1
                    || raiz.getUsuarioReceptor().compareTo(hijo.getUsuarioReceptor()) < 1
                    || raiz.getFechaTransaccion().compareTo(hijo.getFechaTransaccion()) < 1){//Raiz Izquierdo  
                    NodoBinario viejo = new NodoBinario("", "", "", "", "", "");
                    viejo.CreateFromString(raiz.toString());
                    raiz.setIzquierdo(String.valueOf(PosicionRegistro(hijo.toString())+2));
                    Modificar(viejo.toString(), raiz.toString());                
                    }
            }else{//Nodo hijos      
                if(!nodos.isEmpty()){
                    int hijoIzquierdo = Integer.valueOf(raiz.getIzquierdo().trim());
                    int hijoDerecho = Integer.valueOf(raiz.getDerecho().trim());
                //busca los hijos de la raiz y compara
                if(hijoIzquierdo != -1){
                    String hijoIz = null;
                    for(int j = -1; j<hijoIzquierdo; j++){
                            hijoIz = archivo.readLine();
                        }  
                    padre.CreateFromString(hijoIz); 
                    raiz = padre;
                    hijo = nodos.remove(nodos.size()-1);                    
                    if(raiz.getDerecho().contains("-1")){                
                        if(raiz.getUsuarioEmisor().compareTo(hijo.getUsuarioEmisor()) > 1
                        || raiz.getUsuarioReceptor().compareTo(hijo.getUsuarioReceptor()) > 1
                        || raiz.getFechaTransaccion().compareTo(hijo.getFechaTransaccion()) > 1){//Raiz Derecha    
                        NodoBinario viejo = new NodoBinario("", "", "", "", "", "");
                        viejo.CreateFromString(raiz.toString());
                        raiz.setDerecho(String.valueOf(PosicionRegistro(hijo.toString())+1));
                        Modificar(viejo.toString(), raiz.toString());                
                        }else if(raiz.getIzquierdo().contains("-1")){                 
                            if(raiz.getUsuarioEmisor().compareTo(hijo.getUsuarioEmisor()) < 1
                            || raiz.getUsuarioReceptor().compareTo(hijo.getUsuarioReceptor()) < 1
                            || raiz.getFechaTransaccion().compareTo(hijo.getFechaTransaccion()) < 1){//Raiz Izquierdo  
                            NodoBinario viejo = new NodoBinario("", "", "", "", "", "");
                            viejo.CreateFromString(raiz.toString());
                            raiz.setIzquierdo(String.valueOf(PosicionRegistro(hijo.toString())+2));
                            Modificar(viejo.toString(), raiz.toString());                
                            }
                        }
                    }               
               }else if(hijoDerecho != -1){ 
                    String hijoDer = null;
                    for(int j = -1; j<hijoDerecho; j++){
                            hijoDer = archivo.readLine();
                        } 
                    raiz = padre;
                    hijo = nodos.remove(nodos.size()-1);                    
                    if(raiz.getDerecho().contains("-1")){                
                        if(raiz.getUsuarioEmisor().compareTo(hijo.getUsuarioEmisor()) > 1
                        || raiz.getUsuarioReceptor().compareTo(hijo.getUsuarioReceptor()) > 1
                        || raiz.getFechaTransaccion().compareTo(hijo.getFechaTransaccion()) > 1){//Raiz Derecho    
                        NodoBinario viejo = new NodoBinario("", "", "", "", "", "");
                        viejo.CreateFromString(raiz.toString());
                        raiz.setDerecho(String.valueOf(PosicionRegistro(hijo.toString())+1));
                        Modificar(viejo.toString(), raiz.toString());                
                    }else if(raiz.getIzquierdo().contains("-1")){                 
                            if(raiz.getUsuarioEmisor().compareTo(hijo.getUsuarioEmisor()) < 1
                            || raiz.getUsuarioReceptor().compareTo(hijo.getUsuarioReceptor()) < 1
                            || raiz.getFechaTransaccion().compareTo(hijo.getFechaTransaccion()) < 1){//Raiz Izquierdo  
                            NodoBinario viejo = new NodoBinario("", "", "", "", "", "");
                            viejo.CreateFromString(raiz.toString());
                            raiz.setIzquierdo(String.valueOf(PosicionRegistro(hijo.toString())+2));
                            Modificar(viejo.toString(), raiz.toString());                
                            }
                        }
                    }                                  
                   }
                }
            }            
         }
      }
    }
    
    public void Busqueda(NodoBinario nodoBuscado){
        
    }
    
}


