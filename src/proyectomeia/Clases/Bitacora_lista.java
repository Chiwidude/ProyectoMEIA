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
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pancho
 */
public class Bitacora_lista extends ApiloFile {
    /**
     * Constructor heredado (obsoleto)
     * @param Masterfile
     * @param descripcion
     * @param tipoFile
     * @param Autor
     * @param Maxregistros
     * @param llave
     * @param Atributos 
     */
    public Bitacora_lista(String Masterfile, String descripcion, String tipoFile, String Autor, int Maxregistros, String[] llave, String Atributos) {
        super(Masterfile, descripcion, tipoFile, Autor, Maxregistros, llave, Atributos);
        descriptor.delete();
        descriptor = new File(masterfile.getParent()+"/"+"desc_"+masterfile.getName().replaceAll(".txt", "")+".txt");
    }
    @Override
  public List<String>Busqueda(String line ){
      List<String>instances = new ArrayList<String>();
         int[] busquedas = new int[llave.length];
                for(int i = 0; i<busquedas.length; i++){
                    for(int j = 0; j<atributos.length; j++){
                        if(llave[i].equals(atributos[j])){
                            busquedas[i] = j;
                        }
                    }
                    
                }
      return instances;
  }
    @Override
  public void Insertar(String value){
        try {
            Archivo = new RandomAccessFile(super.masterfile, "rw");
            long size = Archivo.length();
            Archivo.seek(size);
            Archivo.writeBytes(value);
            Archivo.writeBytes(System.lineSeparator());
            Archivo.close();
        } catch (IOException ex) {
            Logger.getLogger(Bitacora_lista.class.getName()).log(Level.SEVERE, null, ex);
        }
      
  }
    @Override
  public void UpdateRegister(String nuevo, String viejo) throws IOException{
      EliminacionLogica(viejo);
      Insertar(nuevo);
  }
    @Override
  protected void EliminacionLogica(String viejo) throws FileNotFoundException, IOException{
        int position = PosicionRegistro(viejo);
        Archivo = new RandomAccessFile(masterfile,"rw");
        for(int i = -1; i<position; i++){
            Archivo.readLine();
        }
        long rewriting = Archivo.getFilePointer();
        Archivo.seek(rewriting);
        String registroEliminado = viejo.substring(0,viejo.length()-2)+"/0";
        Archivo.writeBytes(registroEliminado);
        Archivo.close();
  }
    @Override
  public void empty() throws IOException{
      FileChannel.open(Paths.get(masterfile.getPath()), StandardOpenOption.WRITE).truncate(0).close();
  }
  
  private void CrearDescriptor(){
  }
  public void UpdateDescriptor(){
      
  }
  
    @Override
    /**
     * Se vuelve despreciable, se realizadesde
     * UpdateDescriptor
     */
  public void updateAutor(String autor){
      //nothing
  }
  
  
  
}
