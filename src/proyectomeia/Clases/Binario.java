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
    NodoBinario nodoIzquierdo,nodoDerecho,nodoRaiz;
    long tamanio = 0;
    int cantRegistros = 0;  
    NodoBinario padre = new NodoBinario("", "", "", "", "", "");   
    
    public Binario(String RutaBinario){         
        archivoBinario = new File(RutaBinario);
    }
    
    public void Insertar(NodoBinario nuevoNodo) throws FileNotFoundException, IOException{
        archivoMaster = new RandomAccessFile(archivoBinario, "rw");  
        tamanio = archivoMaster.length();
        archivoMaster.seek(tamanio);
        NodoBinario viejo = new NodoBinario("", "", "", "", "", "");
        //Nodo Raiz
        if(tamanio == 0){
        nuevoNodo.setDerecho("-1");
        nuevoNodo.setIzquierdo("-1");
        nodoRaiz = new NodoBinario("", "", "", "", "", "");
        nodoRaiz.CreateFromString(nuevoNodo.toString());
        archivoMaster.writeBytes(nodoRaiz.toString());
        archivoMaster.writeBytes(System.lineSeparator());
        archivoMaster.close(); 
        cantRegistros++;
            }else{  
            nuevoNodo.setDerecho("-1");
            nuevoNodo.setIzquierdo("-1");
            if((nodoRaiz.getUsuarioEmisor().compareTo(nuevoNodo.getUsuarioEmisor())>0)||
               (nodoRaiz.getFechaTransaccion().compareTo(nuevoNodo.getFechaTransaccion())>0)||
               (nodoRaiz.getUsuarioReceptor().compareTo(nuevoNodo.getUsuarioReceptor())>0)){            
                if(nodoRaiz.getDerecho().trim().contains("-1")){
                    cantRegistros++;
                    viejo.CreateFromString(nodoRaiz.toString());
                    nodoRaiz.setDerecho(String.valueOf(cantRegistros));
                    Modificar(viejo.toString(), nodoRaiz.toString());
                    archivoMaster.writeBytes(nuevoNodo.toString());
                    archivoMaster.writeBytes(System.lineSeparator());
                    archivoMaster.close();
                } else{
                    nodoDerecho = obtenerPadre(Integer.parseInt(nodoRaiz.getDerecho().trim()));                    
                    InsertarInterno(nuevoNodo,nodoDerecho);
                }
                    
            }else{            
                if((nodoRaiz.getUsuarioEmisor().compareTo(nuevoNodo.getUsuarioEmisor())<0)||
               (nodoRaiz.getFechaTransaccion().compareTo(nuevoNodo.getFechaTransaccion())<0)||
               (nodoRaiz.getUsuarioReceptor().compareTo(nuevoNodo.getUsuarioReceptor())<0)){  
                     if(nodoRaiz.getIzquierdo().trim().contains("-1")){
                        cantRegistros++;
                        viejo.CreateFromString(nodoRaiz.toString());
                        nodoRaiz.setIzquierdo(String.valueOf(cantRegistros));
                        Modificar(viejo.toString(), nodoRaiz.toString());
                        archivoMaster.writeBytes(nuevoNodo.toString());
                        archivoMaster.writeBytes(System.lineSeparator());
                        archivoMaster.close(); 
                      }else{                          
                        nodoIzquierdo = obtenerPadre(Integer.parseInt(nodoRaiz.getIzquierdo().trim()));
                        InsertarInterno(nuevoNodo,nodoIzquierdo);
                    }
                }
            }
        }
    }//FIN DEL METODO
    
    public void InsertarInterno(NodoBinario nuevoNodo,NodoBinario nodoRaiz) throws IOException{  
        NodoBinario viejo = new NodoBinario("", "", "", "", "", "");
        nuevoNodo.setDerecho("-1");
        nuevoNodo.setIzquierdo("-1");
            if((nodoRaiz.getUsuarioEmisor().compareTo(nuevoNodo.getUsuarioEmisor())>0)||
               (nodoRaiz.getFechaTransaccion().compareTo(nuevoNodo.getFechaTransaccion())>0)||
               (nodoRaiz.getUsuarioReceptor().compareTo(nuevoNodo.getUsuarioReceptor())>0)){            
                if(nodoRaiz.getDerecho().trim().contains("-1")){
                    cantRegistros++;
                    viejo.CreateFromString(nodoRaiz.toString());
                    nodoRaiz.setDerecho(String.valueOf(cantRegistros));
                    Modificar(viejo.toString(), nodoRaiz.toString());
                    archivoMaster.writeBytes(nuevoNodo.toString());
                    archivoMaster.writeBytes(System.lineSeparator());
                    archivoMaster.close();
                } else{
                    nodoDerecho = obtenerPadre(Integer.parseInt(nodoRaiz.getDerecho().trim()));                    
                    InsertarInterno(nuevoNodo,nodoDerecho);
                }
                    
            }else{   
                if((nodoRaiz.getUsuarioEmisor().compareTo(nuevoNodo.getUsuarioEmisor())<0)||
               (nodoRaiz.getFechaTransaccion().compareTo(nuevoNodo.getFechaTransaccion())<0)||
               (nodoRaiz.getUsuarioReceptor().compareTo(nuevoNodo.getUsuarioReceptor())<0)){  
                     if(nodoRaiz.getIzquierdo().trim().contains("-1")){
                        cantRegistros++;
                        viejo.CreateFromString(nodoRaiz.toString());
                        nodoRaiz.setIzquierdo(String.valueOf(cantRegistros));
                        Modificar(viejo.toString(), nodoRaiz.toString());
                        archivoMaster.writeBytes(nuevoNodo.toString());
                        archivoMaster.writeBytes(System.lineSeparator());
                        archivoMaster.close(); 
                      }else{                          
                        nodoIzquierdo = obtenerPadre(Integer.parseInt(nodoRaiz.getIzquierdo().trim()));
                        InsertarInterno(nuevoNodo,nodoIzquierdo);
                    }
                }
            }
    } //FIN DEL METODO 
          
    public void Eliminar(NodoBinario nodoEliminar){
        
    }//FIN DEL METODO
    
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
        
    }//FIN DEL METODO
    
    public NodoBinario obtenerPadre(int posicion) throws FileNotFoundException, IOException{
       RandomAccessFile archivo = new RandomAccessFile(archivoBinario,"rw");
        for(int i = 0; i<posicion; i++){
            padre.CreateFromString(archivo.readLine());
        }
        return padre;
    }//FIN DEL METODO
    
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
    }//FIN DEL METODO
    
    public void Reorganizar() throws IOException{
       
    }
    
    public void Busqueda(NodoBinario nodoBuscado){
        
    }
    
}


