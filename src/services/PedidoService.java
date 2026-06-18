package services;

import entities.Pedido;
import entities.Producto;
import entities.Usuario;
import exceptions.EntidadNoEncontradaException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoService {

    // Colección de pedidos
    private final List<Pedido> pedidos;

    // Generador de IDs
    private Long idCounter;

    // Constructor
    public PedidoService() {

        pedidos = new ArrayList<>();
        idCounter = 1L;

    }

    // Crear pedido
    public Pedido crearPedido(Usuario usuario) {

        Pedido pedido = new Pedido();

        pedido.setId(idCounter++);
        pedido.setFecha(LocalDate.now());
        pedido.setUsuario(usuario);

        pedidos.add(pedido);

        return pedido;
    }

    // Agregar detalle
    public void agregarDetalle(Long pedidoId,
                               int cantidad,
                               Double precio,
                               Producto producto)
            throws EntidadNoEncontradaException {

        Pedido pedido =
                buscarPorId(pedidoId);

        pedido.addDetallePedido(
                cantidad,
                precio,
                producto
        );
    }

    // Listar pedidos activos
    public List<Pedido> listarPedidos() {

        List<Pedido> activos =
                new ArrayList<>();

        for (Pedido pedido : pedidos) {

            if (!pedido.isEliminado()) {
                activos.add(pedido);
            }
        }

        return activos;
    }

    // Buscar por ID
    public Pedido buscarPorId(Long id)
            throws EntidadNoEncontradaException {

        for (Pedido pedido : pedidos) {

            if (!pedido.isEliminado()
                    && pedido.getId().equals(id)) {

                return pedido;
            }
        }

        throw new EntidadNoEncontradaException(
                "Pedido no encontrado."
        );
    }

    // Actualizar estado
    public void actualizarEstado(Long id,
                                 enums.Estado estado)
            throws EntidadNoEncontradaException {

        Pedido pedido =
                buscarPorId(id);

        pedido.setEstado(estado);
    }

    // Actualizar forma de pago
    public void actualizarFormaPago(Long id,
                                    enums.FormaPago formaPago)
            throws EntidadNoEncontradaException {

        Pedido pedido =
                buscarPorId(id);

        pedido.setFormaPago(formaPago);
    }

    // Baja lógica
    public void eliminarPedido(Long id)
            throws EntidadNoEncontradaException {

        Pedido pedido =
                buscarPorId(id);

        pedido.setEliminado(true);
    }

}
