package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Categoria extends Base {
    
    // Atributos
    private String nombre;
    private String descripcion;
    
    // Lista de productos
    private List<Producto> productos;
    
    // Constructor vacio
    public Categoria() {

        productos = new ArrayList<>();

    }
    
    // Constructor completo
    public Categoria(Long id,
                     boolean eliminado,
                     LocalDateTime createdAt,
                     String nombre,
                     String descripcion) {

        super(id, eliminado, createdAt);

        this.nombre = nombre;
        this.descripcion = descripcion;

        this.productos = new ArrayList<>();
    }
    
    // Agrega un producto a la categoria
    public void agregarProducto(Producto producto) {

        productos.add(producto);

    }
    
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {

        return String.format(
            """
            ----------------------
            ID: %d
            Nombre: %s
            Descripción: %s
            """,
            getId(),
            nombre,
            descripcion
        );
    }
}
