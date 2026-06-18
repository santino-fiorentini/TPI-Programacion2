package entities;

import java.time.LocalDateTime;

public class Producto extends Base {
    
    // Atributos
    private String nombre;
    private Double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private boolean disponible;
    
    // Categoria a la que pertenece el producto
    private Categoria categoria;
    
    // Constructor vacio
    public Producto() {
    }
    
    // Constructor completo
    public Producto(Long id,
                    boolean eliminado,
                    LocalDateTime createdAt,
                    String nombre,
                    Double precio,
                    String descripcion,
                    int stock,
                    String imagen,
                    boolean disponible,
                    Categoria categoria) {

        super(id, eliminado, createdAt);

        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.imagen = imagen;
        this.disponible = disponible;
        this.categoria = categoria;
    }
    
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {

        return String.format(
            """
            ----------------------
            ID: %d
            Nombre: %s
            Descripción: %s
            Precio: $%.2f
            Stock: %d
            Disponible: %s
            Categoría: %s
            """,
            getId(),
            nombre,
            descripcion,
            precio,
            stock,
            disponible ? "Sí" : "No",
            categoria != null
                    ? categoria.getNombre()
                    : "Sin categoría"
        );
    }
}
