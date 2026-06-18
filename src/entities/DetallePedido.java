package entities;

import java.time.LocalDateTime;

public class DetallePedido extends Base {
    
    // Atributos
    private int cantidad;
    private Double subtotal;
    
    // Producto asociado al detalle
    private Producto producto;
    
    // Constructor vacio
    public DetallePedido() {
    }
    
    // Constructor completo
    public DetallePedido(Long id,
                         boolean eliminado,
                         LocalDateTime createdAt,
                         int cantidad,
                         Producto producto) {

        super(id, eliminado, createdAt);

        this.cantidad = cantidad;
        this.producto = producto;

        this.subtotal = calcularSubtotal();
    }
    
    // Calcula el subtotal
    private double calcularSubtotal() {

        if(producto != null) {
            this.subtotal = cantidad * producto.getPrecio();
        } else {
            subtotal = 0.0;
        }
        return subtotal;

    }
    
    // Getters y Setters
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {

        this.cantidad = cantidad;

        if (producto != null) {
            subtotal = calcularSubtotal();
        }
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {

        this.producto = producto;

        if (producto != null) {
            subtotal = calcularSubtotal();
        }
    }

    @Override
    public String toString() {

        return String.format(
            """
            ----------------------
            ID Detalle: %d
            Producto: %s
            Cantidad: %d
            Subtotal: $%.2f
            """,
            getId(),
            producto != null
                    ? producto.getNombre()
                    : "Sin producto",
            cantidad,
            subtotal
        );
    }
}
