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
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class SecuencialIndexado {
    
    //VARIBALES GLOBALES
    private RandomAccessFile archivo;
    private File DescriptorIndice;
    private File Indice;
    private File DescriptorMasterFile;
    private File masterFile;
    private int nPosicion = 1;
   
    /**
     * Metodo constructor
     * @param nuevoUsuarioLista usuario
     * @param desc_indice_ruta RUTA DESCRIPTOR INDICE
     * @param indice_ruta RUTA INDICE
     * @param desc_lista_ruta RUTA DESCRIPTOR LISTA
     * @param lista_ruta RUTA LISTA
     */
    public SecuencialIndexado(String desc_indice_ruta,String indice_ruta,String desc_lista_ruta,String lista_ruta ){
       Indice = new File(indice_ruta);
       masterFile = new File(lista_ruta);
       DescriptorIndice = new File(desc_indice_ruta);
       DescriptorMasterFile = new File(desc_lista_ruta);
    }   
    
    /**
     * Metodo que permite insertar en el indice y rerganizar las entradas
     * @param nombreLista nombre de la lista
     * @param Usuario nombre de usuario
     * @param UsuarioAsociado nombre del usuario asociado
     * @param Siguiente registro siguiente
     * @param Estatus estatus
     * @throws IOException
     * @throws FileNotFoundException 
     */
    public void InsertarIndice(String Object) throws IOException,FileNotFoundException{
            String Posicion = "1.";
            archivo = new RandomAccessFile(Indice,"rw");
            long tamanio = archivo.length();
            //Primera Posicion
            if(tamanio == 0){                        
            archivo.seek(tamanio);
           ObjectIndice newv = new ObjectIndice();
            newv.CreateFromString(Object);
            newv.setNregistro(String.valueOf(nPosicion));
            newv.setPosicion(Posicion+nPosicion);
             newv.setSiguiente(String.valueOf(-1));
            archivo.writeBytes(newv.toString()); 
            archivo.writeBytes(System.lineSeparator());
            nPosicion++;
            CrearDescriptorIndice();
            archivo.close();
            }else{
                //Dos posiciones o mas
                if(CantidadRegistrosIndice() >= 1){
                    archivo.seek(tamanio);
                    nPosicion = CantidadRegistrosIndice();
                    nPosicion++;
                    archivo.seek(tamanio);
                    ObjectIndice newv = new ObjectIndice();
                    newv.CreateFromString(Object);
                    newv.setNregistro(String.valueOf(nPosicion));
                    newv.setPosicion(Posicion+nPosicion);
                    newv.setSiguiente(ObtenerDSiguiente(newv.toString()));
                    String nuevo = newv.toString();
                    archivo.writeBytes(nuevo); 
                    archivo.writeBytes(System.lineSeparator());
                    archivo.close(); 
                }
            }
    }
    
    /**
     * Metodo que permite insertar en una lista
     * @param nombreLista nombre de la lista
     * @param usuario nombre del usuario creador
     * @param usuarioAsociado nombre del usuario asociado
     * @param descriptor nombre de la descripcion
     * @param Status estatus
     * @throws IOException
     * @throws FileNotFoundException 
     */
    public void InsertarLista (String nombreLista,String usuario,String usuarioAsociado,String descriptor,String Status) throws IOException,FileNotFoundException{
            StringBuilder contenidoLista = new StringBuilder();
            archivo = new RandomAccessFile(masterFile,"rw"); 
            long tamanio = archivo.length(); 
            archivo.seek(tamanio);
            archivo.writeBytes(contenidoLista.toString()); 
            archivo.writeBytes(System.lineSeparator());
            archivo.close();
    }
    private int Next(String find){
        String[] positions = find.split("\\|");
        int next = Integer.parseInt(positions[5].trim());
        return next;
    }
    private String siguiente(int position) throws IOException{
        String line = "";
        InputStream f = new FileInputStream(Indice);
        BufferedReader br = new BufferedReader(new InputStreamReader(f));
                String inputLine;
                for(int i=0; i<position; i++){
                    inputLine = br.readLine();
                    if(i == position-1){
                        line = inputLine;
                    }
                }
                
                br.close(); 
                return line;
    }
    
    
    public void Eliminar(String NombreLista){
        //sin implementar
    }
    
    public void Modificar(String viejo, String Nuevo) throws FileNotFoundException, IOException{
        int posicion = PosicionRegistro(viejo);
        RandomAccessFile archivo = new RandomAccessFile(Indice,"rw");
        for(int i = -1; i<posicion; i++){
            archivo.readLine();
        }
        long rewrite = archivo.getFilePointer();
        archivo.seek(rewrite);
        archivo.writeBytes(Nuevo);
        archivo.close();
        
    }
    
    public void Busqueda(String NombreLista,String usuario,String usuarioAsociado){
        //sin implementar
    }     
    
    /**
     * Formatea el string para insertarlo en en Archivo INDICE
     */
    
    /**
     * Formatea el string para insertarlo en en Archivo LISTA
     */      
       
    /**
     * Sobreescrbe el descriptor LISTA, lo actualiza, sin problemas
     * @param usuario coloca el utltimo usuario
     * @throws IOException 
     */
    public void CrearDescriptorLista(String usuario) throws IOException{     
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
     * Metodo que crea el descriptor del indice, lo va actualizando
     * @throws IOException 
     */
    public void CrearDescriptorIndice() throws IOException{      
        RandomAccessFile archivo = new RandomAccessFile(DescriptorIndice, "rw");
        StringBuilder descriptorLista = new StringBuilder();
        descriptorLista.append("Numero de Registros:"+rightpad(String.valueOf(CantidadRegistrosIndice()),4)); 
        descriptorLista.append(System.lineSeparator());
        descriptorLista.append("Registro Inicio:"+rightpad("1",4)); 
        descriptorLista.append(System.lineSeparator());        
        descriptorLista.append("Registros Activos:"+rightpad(String.valueOf(CantidadRegistrosActivosIndice()),4)); 
        descriptorLista.append(System.lineSeparator());
        descriptorLista.append("Registros Inactivos:"+rightpad(String.valueOf(CantidadRegistrosInactivosIndice()),4));
        archivo.writeBytes(descriptorLista.toString());
        
    }
    public int ObtenerInicio() throws FileNotFoundException, IOException{
        int inicio = 0;
        String line;
        InputStream f = new FileInputStream(DescriptorIndice);
        BufferedReader br = new BufferedReader(new InputStreamReader(f));
        while((line = br.readLine())!= null){
            if(line.contains("Registro Inicio")){
                String temp = "Registro Inicio:";
                String ini = line.substring(temp.length(), line.length());
                inicio = Integer.parseInt(ini.trim());
            }
            
        }
        return inicio;
    }
    public void NuevoInicio(String nInicio) throws FileNotFoundException, IOException{
        String line;
        RandomAccessFile r = new RandomAccessFile(DescriptorIndice,"rw");
        while((line = r.readLine())!= null){
            if(line.contains("Numero de Registros")){
                long mod = r.getFilePointer();
                r.seek(mod);
                String newval = "Registro Inicio:" + nInicio;
                r.writeBytes(newval);
                
            }
        }
        r.close();
        
    }
    private boolean esInicio(int t){
        boolean is = false;
        try {
            int inicio = ObtenerInicio();
            if(t == inicio){
                is = true;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(SecuencialIndexado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return is;
    }
    
     protected String rightpad(String text, int length) {
    return String.format("%-" + length + "." + length + "s", text);
}
    private String ObtenerDSiguiente(String object) throws IOException{
        int inicio = ObtenerInicio();
        String siguiente = "";
        String comparate = siguiente(inicio);
        boolean found = false;
        while(found == false){
            int compare = compare(object,comparate);
            if(compare < 0){
               int nxt = Integer.parseInt(comparate.split("\\|")[0].trim());
               boolean is = esInicio(nxt);
               if(is){
                   siguiente = comparate.split("\\|")[0];
                   found = true;
                   //falta cambiar el inicio
                   String nin = object.split("\\|")[0];
                   NuevoInicio(nin);
               }else {
                   int next = Integer.parseInt(comparate.split("\\|")[0].trim());
                   siguiente = comparate.split("\\|")[0];
                   found = true;
                   RefactornI(next,object);
                   
               }
            }  else {
                int mfound = Next(comparate);
                if(mfound==-1){
                    siguiente = String.valueOf(mfound);
                    found = true;
                    String nnext = object.split("\\|")[0];
                    RefactorF(mfound, nnext);
                } else{
                    comparate= siguiente(mfound);
                }
                
                
            }
        }
        
        return siguiente;
    }
    private void RefactornI(int i, String n){
        try {
            int inicio = ObtenerInicio();
            String comparacion = siguiente(inicio);
            boolean found = false;
            while(!found){
                found = csig(String.valueOf(i),comparacion);
                if(found){
                    String news = n.split("\\|")[0];
                    ObjectIndice change = new ObjectIndice();
                    change.CreateFromString(comparacion);
                    change.setSiguiente(news);
                    Modificar(comparacion,change.toString());
                }else {
                    int mfound = Next(comparacion);
                    comparacion = siguiente(mfound);
                }
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(SecuencialIndexado.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    private void RefactorF(int vfin, String nfin) throws IOException{
        int inicio = ObtenerInicio();
        String comparacion = siguiente(inicio);
        boolean found = false;
        while(!found){
            found = foundfin(vfin,comparacion);
            if(found){
                ObjectIndice change = new ObjectIndice();
                change.CreateFromString(comparacion);
                change.setSiguiente(nfin);
                Modificar(comparacion,change.toString());
            }else {
                int mfound = Next(comparacion);
                comparacion = siguiente(mfound);
            }
        }
    }
    private boolean foundfin(int i,String comp){
          String l1 = String.valueOf(i);
        String l2 = comp.split("\\|")[5].trim();
        boolean found = false;
        int result = l1.compareTo(l2);
        if(result == 0){
            found = true;
        }
        return found;
        
    }
    private int PosicionRegistro(String registro){
        int posicion = -1;
        try {
           RandomAccessFile contador = new RandomAccessFile(Indice,"r");
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
    
    public int compare(String o1, String o2) {
        String l1 = o1.split("\\|")[2];
        String l2 = o2.split("\\|")[2];
        int result = l1.compareTo(l2);
        if(result == 0){
            l1 = o1.split("\\|")[3];
            l2 = o2.split("\\|")[3];
            result = l1.compareTo(l2);
          if(result ==0){
             l1 = o1.split("\\|")[4];
             l2 = o2.split("\\|")[4];
             result = l1.compareTo(l2);
          }
        }
        return result;
        
    }
    public boolean csig(String o1, String o2){
        String l1 = o1;
        String l2 = o2.split("\\|")[5].trim();
        boolean found = false;
        int result = l1.compareTo(l2);
        if(result == 0){
            found = true;
        }
        return found;
    }
   
    /**
     * Metodo que retorna la cantidad de Registros en la lista
     * @return cantidadRegistrosLista
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private int CantidadRegistrosLista() throws FileNotFoundException, IOException{        
        int cantidadRegistrosIndice = 0;
        InputStream f = new FileInputStream(masterFile);
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
        InputStream f = new FileInputStream(Indice);
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
        InputStream f = new FileInputStream(Indice);
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
     * Metodo que retorna la cantidad de Registros inactivos en el indice
     * @return cantidadRegistrosInactivosLista
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private int CantidadRegistrosInactivosIndice() throws IOException{
        int cantidadRegistrosInactivosIndice = 0;
       String [] atributos = null;
        InputStream f = new FileInputStream(Indice);
        BufferedReader br = new BufferedReader(new InputStreamReader(f));
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    atributos = inputLine.split("\\|");
                    if(atributos[6].contains("0")){
                        cantidadRegistrosInactivosIndice++;
                    }
                }
                br.close(); 
                return cantidadRegistrosInactivosIndice;
    }
    
    /**
     * Metodo que retorna la cantidad de Registros activos en el indice
     * @return cantidadRegistrosActivosLista
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private int CantidadRegistrosActivosIndice() throws FileNotFoundException, IOException{
       int cantidadRegistrosActivosIndice = 0;
       String [] atributos = null;
        InputStream f = new FileInputStream(Indice);
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
     * Metodo que retorna la cantidad de Registros en el indice
     * @return cantidadRegistrosIndice
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private int CantidadRegistrosIndice() throws FileNotFoundException, IOException{        
        int cantidadRegistrosIndice = 0;
        InputStream f = new FileInputStream(Indice);
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
