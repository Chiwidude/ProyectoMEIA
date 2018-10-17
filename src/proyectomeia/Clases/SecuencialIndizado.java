/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia.Clases;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Pancho
 */
public class SecuencialIndizado {

    protected RandomAccessFile Archivo;
    protected File descriptor;
    public File masterfile;
    private int registros;
    public int maxregistros;
    protected String[] llave;
    protected String[] atributos;
       /**
        * Constructor del archivo Apilo
        * @param Masterfile ruta del archivo
        * @param descripcion descripción de función de archivo
        * @param tipoFile tipo de archivo
        * @param Autor Autor del archivo
        * @param Maxregistros Máx registros para reorganizar 
        * @param llave parametro para hacer búsquedas en el archivo
        * @param Atributos Atributos que contendrá el archivo en formato "atributo|atributo|etc...."
        */
    public SecuencialIndizado(String Masterfile, String descripcion, String tipoFile, String Autor, int Maxregistros, String[] llave, String Atributos) {
        this.masterfile = new File(Masterfile);
        this.descriptor = new File(this.masterfile.getParent()+"/"+"desc_"+this.masterfile.getName().replaceAll(".txt", "")+".txt");
        this.llave = llave;
        atributos = Atributos.split("\\|");
        if(!descriptor.exists()){
        CrearDescriptor(descripcion,tipoFile,Autor,Maxregistros);
        }
        registros = 0;
        maxregistros = Maxregistros;
    }
    public void Insertar(String value) {
       

    }
    public int getNoRegistros(){
        
        return 0;
    }
    public List<String> Busqueda(String value){
        List<String> found = new ArrayList<>();        
        
        return found;
    }
    /**
     * Crea el archivo descriptor de este archivo
     * @param descripcion descripcion del archivo
     * @param tipoFile tipo de archivo
     * @param Autor Autor del archivo
     * @param Maxregistros máximo de registros para reorganizar en un archivo secuencial
     */
    private void CrearDescriptor(String descripcion, String tipoFile, String Autor, int Maxregistros){
    StringBuilder atributos = new StringBuilder();
    atributos.append("Archivo:"+ masterfile.getPath());
    atributos.append(System.lineSeparator());
    atributos.append("Descripción:"+ descripcion);
    atributos.append(System.lineSeparator());
    atributos.append("Tipo:" + tipoFile);
    atributos.append(System.lineSeparator());
    atributos.append("Organización:"+ "Apilo");
    atributos.append(System.lineSeparator());
    atributos.append("Autor:" + rightpad(Autor,20));
    atributos.append(System.lineSeparator());
    atributos.append("Creado:"+ new SimpleDateFormat("yyyyMMdd.HH:mm").format(Calendar.getInstance().getTime()));
    atributos.append(System.lineSeparator());
    atributos.append("Modificado:"+ new SimpleDateFormat("yyyyMMdd.HH:mm").format(Calendar.getInstance().getTime()));
    atributos.append(System.lineSeparator());
    atributos.append("Separador de Campos:" + "|");
    atributos.append(System.lineSeparator());
    atributos.append("Registros Activos:"+rightpad("0",6));
    atributos.append(System.lineSeparator());
    atributos.append("Registros Inactivos:"+rightpad("0",6));
    atributos.append(System.lineSeparator());
    atributos.append("Registros Máximos:" + rightpad(String.valueOf(Maxregistros),6));
        try {
            Archivo = new RandomAccessFile(descriptor,"rw");
            Archivo.writeBytes(atributos.toString());
            Archivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ApiloFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ApiloFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    /**
     * Actualiza un registro del archivo através de la eliminación lógica
     * del registro anterior
     * @param anterior registro a eliminar
     * @param nuevo registro que se inserta en reemplazo
     * @throws IOException 
     */
    public void UpdateRegister(String anterior, String nuevo) throws IOException{
      EliminacionLogica(anterior);
      Insertar(nuevo);
      UpdateDescriptor(String.valueOf(RegistrosActivos()),String.valueOf(RegistrosInactivos()),new SimpleDateFormat("yyyyMMdd.HH:mm").format(Calendar.getInstance().getTime()));
    }
    protected int PosicionRegistro(String registro){
        int posicion = -1;
        try {
            Archivo = new RandomAccessFile(masterfile,"r");

            String line;
            while((line = Archivo.readLine())!= null){
                if(line.equals(registro)){
                    return posicion;
                }
                posicion++;
            }
            Archivo.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ApiloFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ApiloFile.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return posicion;
    }
    protected void EliminacionLogica(String registro) throws FileNotFoundException, IOException{
        
    }
    protected int RegistrosActivos() throws FileNotFoundException, IOException{
     return 0;
    }
    protected int RegistrosInactivos() throws FileNotFoundException, IOException{
     
        return 0;
    }
    public void updateAutor(String autor) throws FileNotFoundException, IOException{
        
        
    }
     protected void UpdateDescriptor(String registrosA, String registrosI, String fechamod) throws FileNotFoundException, IOException{
     
      
    }
    public void Reorganizar() throws FileNotFoundException, IOException{  
     
      
        
    }
    public void empty() throws IOException{
        
    }
     protected String rightpad(String text, int length) {
    return String.format("%-" + length + "." + length + "s", text);
}
    public int ReturnMaxreg() throws FileNotFoundException, IOException{
        
          return 0;
    }
}
