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
    

    
    
}
