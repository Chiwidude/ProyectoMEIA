/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia.Clases;

import java.nio.file.Paths;

/**
 *
 * @author Pancho
 */
public class Singleton {
  public static final String pathArchivoApilo = Paths.get("C:/MEIA/bitacora_usuarios.txt").toString();
    public static final String pathArchivoUsuarios = Paths.get("C:/MEIA/usuarios.txt").toString();
public Usuario current;
public SequentialFile Usuarios;
private ApiloFile bitacora;

public Singleton(){
    current = new Usuario();
    bitacora = new ApiloFile(pathArchivoApilo,"Bitácora de Usuarios","Archivo de datos","",2,new String[]{"Usuario"},"Usuario|Nombre|Apellido|password|Rol"
            + "|Fecha_Nacimiento|Correo_alterno|Telefono|path_fotografia|Status");
      Usuarios = new SequentialFile(pathArchivoUsuarios,bitacora);
      if(!Usuarios.descriptorUsuario.exists()){
      Usuarios.CrearDescriptor("", 2);
      }
}


    
    
}
