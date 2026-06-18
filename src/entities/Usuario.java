package entities;

import enums.Rol;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Usuario extends Base {
    
    // Atributos
    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contrasenia;
    private Rol rol;
    
    // Pedidos realizados por el usuario
    private List<Pedido> pedidos;
    
    // Constructor vacio
    public Usuario() {

        pedidos = new ArrayList<>();

    }
    
    // Constructor completo
    public Usuario(Long id,
                   boolean eliminado,
                   LocalDateTime createdAt,
                   String nombre,
                   String apellido,
                   String mail,
                   String celular,
                   String contrasenia,
                   Rol rol) {

        super(id, eliminado, createdAt);

        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.contrasenia = contrasenia;
        this.rol = rol;

        this.pedidos = new ArrayList<>();
    }
    
    // Agrega un pedido al usuario
    public void agregarPedido(Pedido pedido) {

        pedidos.add(pedido);

    }
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {

        return String.format(
            """
            ----------------------
            ID: %d
            Nombre: %s %s
            Mail: %s
            Celular: %s
            Rol: %s
            """,
            getId(),
            nombre,
            apellido,
            mail,
            celular,
            rol
        );
    }
}
