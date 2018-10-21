/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia.Clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author kevin
 */
public class SecuencialIndexado {
    
    private final UsuarioIndexado nuevoUsuarioLista;
    private final String desc_indice_ruta,indice_ruta,desc_lista_ruta,lista_ruta;
    private RandomAccessFile archivo;
    private File DescriptorIndice;
    private File Indice;
    private File DescriptorMasterFile;
    private File masterFile;
    private int nPosicion = 1;
   
    public SecuencialIndexado(UsuarioIndexado nuevoUsuarioLista,String desc_indice_ruta,String indice_ruta,String desc_lista_ruta,String lista_ruta ){
        this.nuevoUsuarioLista = nuevoUsuarioLista;   
        this.desc_indice_ruta = desc_indice_ruta;
        this.desc_lista_ruta = desc_lista_ruta;
        this.indice_ruta = indice_ruta;
        this.lista_ruta = lista_ruta;
    }   
    
    public void InsertarIndice(String nombreLista,String Usuario,String UsuarioAsociado,String Siguiente,String Estatus) throws IOException,FileNotFoundException{
            StringBuilder contenidoIndice = new StringBuilder();
            String Posicion = "1.",inputLine="";
            archivo = new RandomAccessFile(Indice,"rw");
            long tamanio = archivo.length();
            FormatearStringIndice();
            //Primera Posicion
            if(tamanio == 0){                        
            archivo.seek(tamanio);
            contenidoIndice.append(nPosicion);
            contenidoIndice.append("|"+Posicion.format("%-10s", Posicion+nPosicion));
            contenidoIndice.append("|"+ nuevoUsuarioLista.NombreLista);
            contenidoIndice.append("|"+ nuevoUsuarioLista.Usuario);
            contenidoIndice.append("|" + nuevoUsuarioLista.UsuarioAsociado);
            contenidoIndice.append("|"+nuevoUsuarioLista.Siguiente);
            contenidoIndice.append("|" + nuevoUsuarioLista.Status);
            archivo.writeBytes(contenidoIndice.toString()); 
            archivo.writeBytes(System.lineSeparator());
            nPosicion++;
            archivo.close();
            }else{
                ArrayList<UsuarioIndexado> indice = new ArrayList<>();
                String[] atributos;
                //Dos posiciones o mas
                if(CantidadRegistrosIndice() >= 1){
                    nPosicion = CantidadRegistrosIndice();
                    nPosicion++;
                    archivo.seek(tamanio);
                    contenidoIndice.append(nPosicion);
                    contenidoIndice.append("|"+Posicion.format("%-10s", Posicion+nPosicion));
                    contenidoIndice.append("|"+ nuevoUsuarioLista.NombreLista);
                    contenidoIndice.append("|"+ nuevoUsuarioLista.Usuario);
                    contenidoIndice.append("|" + nuevoUsuarioLista.UsuarioAsociado);
                    contenidoIndice.append("|"+(nPosicion-1));
                    contenidoIndice.append("|" + nuevoUsuarioLista.Status);
                    archivo.writeBytes(contenidoIndice.toString()); 
                    archivo.writeBytes(System.lineSeparator());
                    archivo.seek(0);
                    while((inputLine = archivo.readLine()) != null){
                        atributos = inputLine.split("\\|");
                        UsuarioIndexado encontrado = new UsuarioIndexado();
                        encontrado.Usuario = atributos[3];
                        encontrado.UsuarioAsociado = atributos[4];
                        encontrado.Siguiente = atributos[5];
                        indice.add(encontrado);
                        }                    
                    //Se ordena
                    Collections.sort(indice,(o1,o2)-> o1.Usuario.compareToIgnoreCase(o2.Usuario)); 
                    int contador = 0;
                    int recorrido = 0;
                    StringBuilder nuevaReorganizacion = new StringBuilder();
                    atributos = null;
                    archivo.seek(0);
                    while((inputLine = archivo.readLine()) != null){
                        try{
                        atributos = inputLine.split("\\|");
                        UsuarioIndexado modificarLinea = indice.get(contador);
                        recorrido +=inputLine.length();
                        //Posicion Uno
                        if(atributos[3].contains(modificarLinea.Usuario) && atributos[4].contains(modificarLinea.UsuarioAsociado)){                            
                            archivo.seek(recorrido-3);   
                            archivo.writeBytes(String.valueOf(contador));
                            contador++;
                            archivo.seek(recorrido+2);
                        }else{
                            //Cualquier Posicion
                            contador++; 
                            if(atributos[5].contains("0")){
                            contador = 1;
                            archivo.seek(recorrido-3); 
                            archivo.writeBytes(String.valueOf(contador));                              
                            archivo.seek(recorrido+2);
                            }else{
                            contador = 0;
                            archivo.seek(recorrido-1); 
                            archivo.writeBytes(String.valueOf(contador));                              
                            archivo.seek(recorrido);
                            }
                        }
                        }catch(Exception e){
                            //fin archivo 
                            return;
                        }
                        }
                    archivo.close(); 
                }
            }
    }
    
    public void InsertarLista (String nombreLista,String usuario,String usuarioAsociado,String descriptor,String Status) throws IOException,FileNotFoundException{
            StringBuilder contenidoLista = new StringBuilder();
            archivo = new RandomAccessFile(masterFile,"rw"); 
            long tamanio = archivo.length(); 
            FormatearStringLista();
            archivo.seek(tamanio);
            contenidoLista.append(nuevoUsuarioLista.NombreLista);
            contenidoLista.append("|"+ nuevoUsuarioLista.Usuario);
            contenidoLista.append("|" + nuevoUsuarioLista.UsuarioAsociado);
            contenidoLista.append("|" + nuevoUsuarioLista.Descripcion);
            contenidoLista.append("|" + nuevoUsuarioLista.Status);
            archivo.writeBytes(contenidoLista.toString()); 
            archivo.writeBytes(System.lineSeparator());
            archivo.close();
    }  
    
    
    public void Eliminar(String NombreLista){
        //sin implementar
    }
    
    public void Modificar(String NombreLista){
        //sin implementar
    }
    
    public void Busqueda(String NombreLista,String usuario,String usuarioAsociado){
        //sin implementar
    }
    
    //sin comprobar   
     
    /**
     * Crear e archivo indice e inserta, en el mismo si el usuario no esta ingresado
     */
    public void CrearIndice(UsuarioIndexado usuario,String nRegistro,String Posicion) throws IOException{
        Indice = new File(indice_ruta);        
        if(!Indice.exists()){
            Indice.createNewFile();
            InsertarIndice(usuario.NombreLista, usuario.Usuario, usuario.UsuarioAsociado, usuario.Siguiente, usuario.Status);
            }else{
            InsertarIndice(usuario.NombreLista, usuario.Usuario, usuario.UsuarioAsociado, usuario.Siguiente, usuario.Status);
        }
    }       
    
    /**
     * Crear e archivo lista e inserta, en el mismo si el usuario no esta ingresado
     */
    public void CrearLista(UsuarioIndexado usuario) throws IOException{
        masterFile = new File(lista_ruta);      
        
        if(!masterFile.exists()){
            masterFile.createNewFile();
            InsertarLista(usuario.NombreLista, usuario.Usuario, usuario.UsuarioAsociado, usuario.Descripcion, usuario.Status);
            }else{
            InsertarLista(usuario.NombreLista, usuario.Usuario, usuario.UsuarioAsociado, usuario.Descripcion, usuario.Status);
        }
    } 
    
    /**
     * Formatea el string para insertarlo en en Archivo INDICE
     */
    private void FormatearStringIndice(){
        
        nuevoUsuarioLista.NombreLista = String.format("%-30s", nuevoUsuarioLista.NombreLista);
        nuevoUsuarioLista.Usuario = String.format("%-20s", nuevoUsuarioLista.Usuario);
        nuevoUsuarioLista.UsuarioAsociado = String.format("%-20s", nuevoUsuarioLista.UsuarioAsociado);
        nuevoUsuarioLista.Siguiente = String.format("%-1s",nuevoUsuarioLista.Siguiente);
        nuevoUsuarioLista.Status = String.format("%-1s", nuevoUsuarioLista.Status);
    }
    
    /**
     * Formatea el string para insertarlo en en Archivo LISTA
     */
    private void FormatearStringLista(){
        nuevoUsuarioLista.NombreLista = String.format("%-30s", nuevoUsuarioLista.NombreLista);
        nuevoUsuarioLista.Usuario = String.format("%-20s", nuevoUsuarioLista.Usuario);
        nuevoUsuarioLista.UsuarioAsociado = String.format("%-20s", nuevoUsuarioLista.UsuarioAsociado);
        nuevoUsuarioLista.Descripcion = String.format("%-40s",nuevoUsuarioLista.Descripcion);
        nuevoUsuarioLista.Status = String.format("%-1s", nuevoUsuarioLista.Status);
    }  
    
    
   
    /**
     * Sobreescrbe el descriptor LISTA, lo actualiza, sin problemas
     * @param usuario coloca el utltimo usuario
     * @throws IOException 
     */
    public void CrearDescriptorLista(String usuario) throws IOException{
        DescriptorMasterFile = new File(desc_lista_ruta);        
        DescriptorMasterFile.createNewFile();
        archivo = new RandomAccessFile(DescriptorMasterFile, "rw");
        StringBuilder descriptorLista = new StringBuilder();
        descriptorLista.append("Usuario Creado:"+usuario);
        descriptorLista.append(System.lineSeparator());
        descriptorLista.append("Fecha Creacion:"+new SimpleDateFormat("yyyyMMdd.HH:mm").format(Calendar.getInstance().getTime()));
        descriptorLista.append(System.lineSeparator());
        descriptorLista.append("Numero de Registros:"+CantidadRegistrosLista()); 
        descriptorLista.append(System.lineSeparator());
        descriptorLista.append("Registros Activos:"+CantidadRegistrosActivosLista()); 
        descriptorLista.append(System.lineSeparator());
        descriptorLista.append("Registros Inactivos:"+CantidadRegistrosInactivosLista());
        archivo.writeBytes(descriptorLista.toString());
        
    }   
   
    /**
     * Metodo que retorna la cantidad de Registros en la lista
     * @return cantidadRegistrosLista
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private int CantidadRegistrosLista() throws FileNotFoundException, IOException{        
        int cantidadRegistrosIndice = 0;
        InputStream f = new FileInputStream(lista_ruta);
        BufferedReader br = new BufferedReader(new InputStreamReader(f));
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    if(inputLine!=null){
                        cantidadRegistrosIndice++;
                    }
                }
                br.close(); 
                return cantidadRegistrosIndice;
    }
    
    /**
     * Metodo que retorna la cantidad de Registros activos en la lista
     * @return cantidadRegistrosActivosLista
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private int CantidadRegistrosActivosLista() throws FileNotFoundException, IOException{
       int cantidadRegistrosActivosIndice = 0;
       String [] atributos = null;
        InputStream f = new FileInputStream(indice_ruta);
        BufferedReader br = new BufferedReader(new InputStreamReader(f));
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    atributos = inputLine.split("\\|");
                    if(atributos[6].contains("1")){
                        cantidadRegistrosActivosIndice++;
                    }
                }
                br.close(); 
                return cantidadRegistrosActivosIndice;
    }
    
     /**
     * Metodo que retorna la cantidad de Registros inactivos en la lista
     * @return cantidadRegistrosInactivosLista
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private int CantidadRegistrosInactivosLista() throws IOException{
        int cantidadRegistrosActivosIndice = 0;
       String [] atributos = null;
        InputStream f = new FileInputStream(indice_ruta);
        BufferedReader br = new BufferedReader(new InputStreamReader(f));
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    atributos = inputLine.split("\\|");
                    if(atributos[6].contains("0")){
                        cantidadRegistrosActivosIndice++;
                    }
                }
                br.close(); 
                return cantidadRegistrosActivosIndice;
    }
    
     /**
     * Metodo que retorna la cantidad de Registros en el indice
     * @return cantidadRegistrosIndice
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private int CantidadRegistrosIndice() throws FileNotFoundException, IOException{        
        int cantidadRegistrosIndice = 0;
        InputStream f = new FileInputStream(indice_ruta);
        BufferedReader br = new BufferedReader(new InputStreamReader(f));
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    if(inputLine!=null){
                        cantidadRegistrosIndice++;
                    }
                }
                br.close(); 
                return cantidadRegistrosIndice;
    }
      
}
