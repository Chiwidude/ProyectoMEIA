/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia.Clases;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author kevin
 */
public class SecuencialIndexado {
    
    private UsuarioIndexado nuevoUsuarioLista;
    private String desc_indice_ruta,indice_ruta,desc_lista,lista_ruta;
    private RandomAccessFile archivo;
    private File DescriptorIndice;
    private File Indice;
    private File DescriptorMasterFile;
    private File masterFile;
    private int nRegistros;
    private int nRegistrosActivos;
    private int nRegistrosInactivos;
    private int RegistroInicio;
    public SecuencialIndexado(UsuarioIndexado nuevoUsuarioLista,String desc_indice_ruta,String indice_ruta,String desc_lista,String lista_ruta ){
        this.nuevoUsuarioLista = nuevoUsuarioLista;   
        this.desc_indice_ruta = desc_indice_ruta;
        this.desc_lista = lista_ruta;
        this.indice_ruta = indice_ruta;
        this.lista_ruta = lista_ruta;
    }
    
    
    public void Insertar(Usuario nuevoUsuario){
       //sin implementar 
    }
    
    public void Eliminar(String nombre){
        //sin implementar
    }
    
    public void Modificar(String nombre){
        //sin implementar
    }
    
    public void Busqueda(String Nombre){
        //sin implementar
    }
    
    //sin comprobar
    public String FormatearString(){
        StringBuilder cadenaFormateada = new StringBuilder();
        nuevoUsuarioLista.NombreLista = String.format("%-30s", nuevoUsuarioLista.NombreLista);
        nuevoUsuarioLista.Usuario = String.format("%-20s", nuevoUsuarioLista.Usuario);
        nuevoUsuarioLista.UsuarioAsociado = String.format("%-20s", nuevoUsuarioLista.UsuarioAsociado);
        nuevoUsuarioLista.Descripcion = String.format("%-40s",nuevoUsuarioLista.Descripcion);
        nuevoUsuarioLista.fechaCreacion = String.format("%-20s",nuevoUsuarioLista.fechaCreacion);
        nuevoUsuarioLista.Status = String.format("%-1s", nuevoUsuarioLista.Status);
        cadenaFormateada.append(nuevoUsuarioLista.NombreLista+"|"+
                nuevoUsuarioLista.Usuario+"|"+
                nuevoUsuarioLista.UsuarioAsociado+"|"+
                nuevoUsuarioLista.Descripcion+"|"+
                nuevoUsuarioLista.fechaCreacion+"|"+
                nuevoUsuarioLista.Status);
        return cadenaFormateada.toString();
    }
    
    //sin comprobar
    public void CrearLista() throws IOException{
        masterFile = new File(lista_ruta);
        StringBuilder descriptor = new StringBuilder();
        if(!masterFile.exists()){
            masterFile.createNewFile();
        }else{
            archivo = new RandomAccessFile(masterFile,"w");              
            descriptor.append("Nombre Lista:"+ nuevoUsuarioLista.NombreLista);            
            descriptor.append(System.lineSeparator());
            descriptor.append("Usuario:"+ nuevoUsuarioLista.Usuario);
            descriptor.append(System.lineSeparator());
            descriptor.append("Usuario Asociado:" + nuevoUsuarioLista.UsuarioAsociado);
            descriptor.append(System.lineSeparator());
            descriptor.append("Descripcion:"+nuevoUsuarioLista.Descripcion);
            descriptor.append(System.lineSeparator());
            descriptor.append("Fecha Creacion:" + nuevoUsuarioLista.fechaCreacion);
            descriptor.append(System.lineSeparator()); 
            descriptor.append("Estatus:" + nuevoUsuarioLista.Status);
            descriptor.append(System.lineSeparator());
            archivo.writeBytes(desc_indice_ruta.toString()); 
            archivo.close();
        }
    }
//     public void CrearLista(){
//        Descriptor = new File(desc_indice_ruta);
//        StringBuilder descriptor = new StringBuilder();
//        descriptor.append("Nombre Lista:"+ nuevoUsuarioLista.NombreLista);
//        descriptor.append(System.lineSeparator());
//        descriptor.append("Usuario:"+ nuevoUsuarioLista.Usuario);
//        descriptor.append(System.lineSeparator());
//        descriptor.append("Usuario Asociado:" + nuevoUsuarioLista.UsuarioAsociado);
//        descriptor.append(System.lineSeparator());
//        descriptor.append("Descripcion:"+nuevoUsuarioLista.Descripcion);
//        descriptor.append(System.lineSeparator());
//        descriptor.append("Fecha Creacion:" + nuevoUsuarioLista.fechaCreacion);
//        descriptor.append(System.lineSeparator()); 
//        descriptor.append("Estatus:" + nuevoUsuarioLista.Status);
//        descriptor.append(System.lineSeparator());        
//    }
}
