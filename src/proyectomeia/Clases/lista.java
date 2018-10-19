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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pancho
 */
public class lista{
private  RandomAccessFile ArchivoLista;
public  File descriptorLista;
public  File masterfileLista;
public Bitacora_lista bitacora;
public boolean flag;
public lista(String masterfile, Bitacora_lista bitacora){
    this.masterfileLista = new File(masterfile);
    this.bitacora = bitacora;
    this.descriptorLista = new File(this.masterfileLista.getParent()+"/"+"desc_"+this.masterfileLista.getName().replaceAll(".txt", "")+".txt");
}

public void Insertar(String valor) throws IOException{
          if(bitacora.masterfile.exists()){
        List<String>results = bitacora.Busqueda(valor);
        if(results.isEmpty()&& this.masterfileLista.exists()){
            String result = Buscar(valor);
            if(result == null || result.isEmpty()){
                if(bitacora.getNoRegistros() == bitacora.maxregistros){
                    refactorBitacora();
                    flag = true;
                }
                bitacora.Insertar(valor);
            }
        }else if(results.isEmpty()){
            if(bitacora.getNoRegistros() == bitacora.maxregistros){
                    refactorBitacora();
                    flag = true;
                }
                bitacora.Insertar(valor);
        }
       }else {
            bitacora.Insertar(valor);
        }
}
/**
 * Método para realizar busquedas al insertar
 * @param value valor string a buscar
 * @return retorna el valor en caso encontrarlo
 * @throws FileNotFoundException
 * @throws IOException 
 */
public String Buscar(String value) throws FileNotFoundException, IOException{
    String resultado = "";
    int[] posbusqueda = new int[bitacora.llave.length];
    for(int i = 0; i<posbusqueda.length; i++){
        for(int j = 0; j<bitacora.atributos.length;j++){
            if(bitacora.llave[i].equals(bitacora.atributos[j])){
                posbusqueda[i] = j;
            }
        }
    }
    RandomAccessFile filer = new RandomAccessFile(masterfileLista,"r");
    String line;
    int counter = 0;
    
    while((line = filer.readLine()) != null){
        for(int k = 0; k <posbusqueda.length; k++){
                    String compare = line.split("\\|")[posbusqueda[k]];
                    String tocompare = value.split("\\|")[posbusqueda[k]];
                    if(compare.trim().equals(tocompare.trim())){
                        counter++;
                    }
                }
        if(counter == bitacora.llave.length){
            resultado = line;
        }
        
    }
    return resultado;
}
public List<String> BuscarP(String value, String parameter) throws FileNotFoundException, IOException{
    List<String> resultados = new ArrayList<>();
    int posaBuscar = 0;
    for(int i = 0; i< bitacora.atributos.length; i++){
        if(bitacora.atributos[i].equals(parameter)){
            posaBuscar = i;
        }
    }
    RandomAccessFile lector = new RandomAccessFile(masterfileLista, "r");
    String line;
    while((line = lector.readLine())!= null){
        String compare = line.split("\\|")[posaBuscar];
        if(compare.trim().equals(value)){
            resultados.add(line);
        }
    } 
    
    return resultados;
}

protected void refactorBitacora(){
    
}
private void UpdateDescriptor(String registrosA, String registrosI, String fechamod) throws FileNotFoundException, IOException{
      ArchivoLista = new RandomAccessFile(descriptorLista,"rw");
      String linea;
       while((linea = ArchivoLista.readLine())!= null){
            if(linea.contains("usuario_creacion:")){
                long mod = ArchivoLista.getFilePointer();
                ArchivoLista.seek(mod);
                String newsvalue = "fecha_modificacion:" + fechamod;
                ArchivoLista.writeBytes(newsvalue);
            } 
       }
        linea = null;
        ArchivoLista.close();
        ArchivoLista = null;
        ArchivoLista = new RandomAccessFile(descriptorLista,"rw");
        while((linea = ArchivoLista.readLine())!= null){
           if(linea.contains("usuario_modificacion")){
               long aregis = ArchivoLista.getFilePointer();
               ArchivoLista.seek(aregis);
                String replace = "#_registros:" + rightpad(String.valueOf(ObtenerCantidadListas()),6);
                ArchivoLista.writeBytes(replace);
            } 
        }
        linea = null;
        ArchivoLista.close();
        ArchivoLista = null;
        ArchivoLista = new RandomAccessFile(descriptorLista,"rw");
        while((linea = ArchivoLista.readLine())!= null){
             if(linea.contains("#_registros")){
                 long iregs = ArchivoLista.getFilePointer();
                 ArchivoLista.seek(iregs);
                String newvalue = "Registros Activos:" + rightpad(registrosA,6);
                ArchivoLista.writeBytes(newvalue);
            }
        }
        linea = null;
        ArchivoLista.close();
        ArchivoLista = null;
        ArchivoLista = new RandomAccessFile(descriptorLista,"rw");
        while((linea = ArchivoLista.readLine())!= null){
             if(linea.contains("Registros Activos")){
                 long iregs = ArchivoLista.getFilePointer();
                 ArchivoLista.seek(iregs);
                String newvalue = "Registros Inactivos:" + rightpad(registrosI,6);
                ArchivoLista.writeBytes(newvalue);
            }
        }
        ArchivoLista.close();
        
      
    }
 protected String rightpad(String text, int length) {
    return String.format("%-" + length + "." + length + "s", text);
}
 public int ObtenerCantidadListas(){
        int registros = 0;
        try {
            RandomAccessFile contador = new RandomAccessFile(masterfileLista,"r");
            String linea;
            while((linea = contador.readLine())!= null){
                if(!linea.contains("/0"))
                        registros++;
            }
            contador.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ApiloFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ApiloFile.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return registros;
        
    }
 private int RegistrosActivos() throws FileNotFoundException, IOException{
     ArchivoLista = new RandomAccessFile(masterfileLista,"r");
     String linea;
     int records = 0;
     while((linea = ArchivoLista.readLine())!=null){
         String[]check = linea.split("\\|");
     
         if(check[check.length-1].contains("1")&&!check[check.length-1].contains("/0")){
             records++;
         }
       }
     ArchivoLista.close();
    
        return records;
    }
     private int RegistrosInactivos() throws FileNotFoundException, IOException{
     ArchivoLista = new RandomAccessFile(masterfileLista,"r");
     int registers = 0;
     String linea;
     while((linea = ArchivoLista.readLine())!= null){
         String[] check = linea.split("\\|");
         if(check[check.length-1].contains("0")&&!check[check.length-1].contains("/0")){
             registers++;
         }
     }
        ArchivoLista.close();
        return registers;
    }
     private int PosicionRegistro(String registro){
        int posicion = -1;
        try {
           RandomAccessFile contador = new RandomAccessFile(masterfileLista,"r");

            String line;
            while((line = contador.readLine())!= null){
                if(line.equals(registro)){
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
     
       public void Modificar(String nuevo, String Antiguo) throws FileNotFoundException, IOException{
        List<String> busqueda = bitacora.Busqueda(Antiguo);
        if(busqueda.isEmpty()){
            int position = PosicionRegistro(Antiguo);
                ArchivoLista = new RandomAccessFile(masterfileLista,"rw");
                for(int i = -1; i<position; i++){
                    ArchivoLista.readLine();
                }
                long rewrite = ArchivoLista.getFilePointer();
                ArchivoLista.seek(rewrite);
                ArchivoLista.writeBytes(nuevo);
                ArchivoLista.close();
                flag = true;
                UpdateDescriptor(String.valueOf(RegistrosActivos()),String.valueOf(RegistrosInactivos()),new SimpleDateFormat("yyyyMMdd.HH:mm").format(Calendar.getInstance().getTime()));
        }else {
            bitacora.UpdateRegister(Antiguo, nuevo);
            flag = false;
        }
     
    }
       public void CrearDescriptor(String Autor, int Maxregistros){
    StringBuilder atributos = new StringBuilder();
    atributos.append("Nombre_simbolico:"+ masterfileLista.getPath());
    atributos.append(System.lineSeparator());
    atributos.append("fecha_creacion:"+ new SimpleDateFormat("yyyyMMdd.HH:mm").format(Calendar.getInstance().getTime()));
    atributos.append(System.lineSeparator());
    atributos.append("usuario_creacion:" + rightpad(Autor,20));
    atributos.append(System.lineSeparator());
    atributos.append("fecha_modificacion:"+ new SimpleDateFormat("yyyyMMdd.HH:mm").format(Calendar.getInstance().getTime()));
    atributos.append(System.lineSeparator());
    atributos.append("usuario_modificacion:" + rightpad(Autor,20));
    atributos.append(System.lineSeparator());
    atributos.append("#_registros:" + rightpad("0",6));
    atributos.append(System.lineSeparator());
    atributos.append("Registros Activos:"+rightpad("0",6));
    atributos.append(System.lineSeparator());
    atributos.append("Registros Inactivos:"+rightpad("0",6));
    atributos.append(System.lineSeparator());
    atributos.append("Registros Máximos:" + rightpad(String.valueOf(Maxregistros),6));
        try {
          RandomAccessFile  ArchivoUsuario = new RandomAccessFile(descriptorLista,"rw");
            ArchivoUsuario.writeBytes(atributos.toString());
            ArchivoUsuario.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ApiloFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ApiloFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }

    
    
}

