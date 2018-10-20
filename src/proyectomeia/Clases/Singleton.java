/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia.Clases;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import static proyectomeia.Inicio.pathArchivoApilo;
import static proyectomeia.Inicio.pathArchivoUsuarios;

/**
 *
 * @author Pancho
 */
public class Singleton {
    public static final String pathArchivoApilo = Paths.get("C:/MEIA/bitacora_usuarios.txt").toString();
    public static final String pathArchivoUsuarios = Paths.get("C:/MEIA/usuarios.txt").toString();
    public static final String pathArchivoListas = Paths.get("C:/MEIA/lista.txt").toString();
    public static final String pathBlistas = Paths.get("C:/MEIA/bitacora_listas.txt").toString();
    public static final String pathListasUsuario = Paths.get("C:/MEIA/Lista_usuario.txt").toString();

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    public Usuario current;
    public SequentialFile Usuarios;
    private ApiloFile bitacora;
    private Bitacora_lista Blista;
    public lista Listas;
    public SecuencialIndexado ListaUsuarios;

public Singleton(){
    current = new Usuario();
    bitacora = new ApiloFile(pathArchivoApilo,"Bitácora de Usuarios","Archivo de datos","",5,new String[]{"Usuario"},"Usuario|Nombre|Apellido|password|Rol"
            + "|Fecha_Nacimiento|Correo_alterno|Telefono|path_fotografia|Status");
      Usuarios = new SequentialFile(pathArchivoUsuarios,bitacora);
      if(!Usuarios.descriptorUsuario.exists()){
      Usuarios.CrearDescriptor("", 5);
      }
      Blista = new Bitacora_lista(pathBlistas,"Bitácora Listas","Archivo de datos","",5,new String[]{"Nombre_lista","Usuario"},"Nombre_lista|Usuario|Descripción|Número_Usuarios|"
              + "Fecha_creacion|Estatus");
      Listas = new lista(pathArchivoListas,Blista);
}
public boolean ExistsUser(String object){
     File temp1 = new File(pathArchivoApilo);
       File temp2 = new File(pathArchivoUsuarios);
    boolean exists = false;
    if(!temp1.exists() && !temp2.exists()){
        exists = false;
    } else if(temp1.exists()){
        List<String> exist = Usuarios.bitacora.Busqueda(object);
        if(!exist.isEmpty()){
            String line = "";
               for(int i = 0; i<exist.size();i++){
                   line = exist.get(i);
                   if(!line.contains("/0")&&!line.split("\\|")[line.split("\\|").length-1].contains("0")){
                       line = line;
                   }else{
                       line = "";
                   }      
               }
               if(line.equals("")){
                   exists = false;
               }else {
                   exists = true;
               }
        } else if(!temp2.exists()){
            exists = false;
        } else {
            String busqueda = Usuarios.Buscar(object);
            if(!busqueda.isEmpty() || !busqueda.equals("")){
                exists = true;
            } else{
                exists = false;
            }
        }
    }
    return exists;
}       
}
