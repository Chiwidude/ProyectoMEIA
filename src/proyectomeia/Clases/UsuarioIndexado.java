/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectomeia.Clases;

/**
 *
 * @author kevin
 */
public class UsuarioIndexado {
    public UsuarioIndexado(String Nlista,String Nusuario, String Uasociado,String Desc){
        NombreLista = Nlista;
        Usuario = Nusuario;
        UsuarioAsociado = Uasociado;
        Descripcion = Desc;
        Status = "1";
       
    }
    /**
     * @return the NombreLista
     */
    public String getNombreLista() {
        return NombreLista;
    }

    /**
     * @param NombreLista the NombreLista to set
     */
    public void setNombreLista(String NombreLista) {
        this.NombreLista = NombreLista;
    }

    /**
     * @return the Usuario
     */
    public String getUsuario() {
        return Usuario;
    }

    /**
     * @param Usuario the Usuario to set
     */
    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    /**
     * @return the UsuarioAsociado
     */
    public String getUsuarioAsociado() {
        return UsuarioAsociado;
    }

    /**
     * @param UsuarioAsociado the UsuarioAsociado to set
     */
    public void setUsuarioAsociado(String UsuarioAsociado) {
        this.UsuarioAsociado = UsuarioAsociado;
    }

    /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    /**
     * @return the Status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }
     protected String rightpad(String text, int length) {
    return String.format("%-" + length + "." + length + "s", text);
     }
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(rightpad(NombreLista,30));
        str.append("|");
        str.append(rightpad(Usuario,20));
        str.append("|");
        str.append(rightpad(UsuarioAsociado,20));
        str.append("|");
        str.append(rightpad(Descripcion,40));
        str.append("|");
        str.append(Status);
        return str.toString();
    }
    public void CreateFromString(String object){
        String[]  parts = object.split("\\|");
        NombreLista = parts[0];
        Usuario = parts[1];
        UsuarioAsociado = parts[2];
        Descripcion = parts[3];
        Status = parts[4];
        
    }
    
    private String NombreLista,Usuario,UsuarioAsociado,Descripcion,Status;    
}
