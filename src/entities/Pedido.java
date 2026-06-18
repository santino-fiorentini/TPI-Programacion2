package entities;

import enums.Estado;
import enums.FormaPago;
import interfaces.Calculable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
                                // Implementa interfaz calculable
public class Pedido extends Base implements Calculable {
    
    // Atributos
    private static Long detalleIdCounter = 1L;
    private LocalDate fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;
    
    // Detalles que componen el pedido
    private List<DetallePedido> detalles;
    
    // Usuario que hace el pedido
    private Usuario usuario;
    
    // Constructor vacio
    public Pedido() {
        
        super();
        detalles = new ArrayList<>();
        total = 0.0;

    }
    
    // Constructor completo
    public Pedido(Long id,
                  boolean eliminado,
                  LocalDateTime createdAt,
                  LocalDate fecha,
                  Estado estado,
                  Double total,
                  FormaPago formaPago,
                  Usuario usuario) {

        super(id, eliminado, createdAt);

        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
        this.formaPago = formaPago;
        this.usuario = usuario;

        this.detalles = new ArrayList<>();
    }

    @Override
    public void calcularTotal() {   // Calcula el total

        total = 0.0;

        for (DetallePedido detalle : detalles) {

            total += detalle.getSubtotal();

        }
    }
    
    // Agrega un detalle al pedido
    public void addDetallePedido(int cantidad,
                                Double precio,
                                Producto producto) {

        DetallePedido detalle = new DetallePedido();

        detalle.setId(detalleIdCounter++);
        detalle.setEliminado(false);
        detalle.setCreatedAt(LocalDateTime.now());

        detalle.setCantidad(cantidad);
        detalle.setProducto(producto);

        detalles.add(detalle);

        calcularTotal();
    }
    
    // Busca un detalle
    public DetallePedido findDetallePedidoByProducto(
            Producto producto) {

        for (DetallePedido detalle : detalles) {

            if (detalle.getProducto()
                    .getId()
                    .equals(producto.getId())) {

                return detalle;
            }
        }

        return null;
    }
    
    // Elimina un detalle
    public void deleteDetallePedidoByProducto(
            Producto producto) {

        DetallePedido detalle =
                findDetallePedidoByProducto(producto);

        if (detalle != null) {

            detalles.remove(detalle);

            calcularTotal();
        }
    }
    
    // Getters y Setters
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
        calcularTotal();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {

        return String.format(
            """
            ======================
            PEDIDO #%d
            ======================
            Fecha: %s
            Usuario: %s
            Estado: %s
            Forma de Pago: %s
            Total: $%.2f
            """,
            getId(),
            fecha,
            usuario != null
                    ? usuario.getNombre() + " " + usuario.getApellido()
                    : "Sin usuario",
            estado,
            formaPago,
            total
        );
    }
}
