package ui;

import entities.Pedido;
import entities.Producto;
import entities.Usuario;
import enums.Estado;
import enums.FormaPago;
import exceptions.EntidadNoEncontradaException;
import services.PedidoService;
import services.ProductoService;
import services.UsuarioService;

import java.util.List;
import java.util.Scanner;

public class MenuPedido {

    // Scanner
    private final Scanner scanner;

    // Services
    private final PedidoService pedidoService;
    private final UsuarioService usuarioService;
    private final ProductoService productoService;

    // Constructor
    public MenuPedido(Scanner scanner,
                      PedidoService pedidoService,
                      UsuarioService usuarioService,
                      ProductoService productoService) {

        this.scanner = scanner;
        this.pedidoService = pedidoService;
        this.usuarioService = usuarioService;
        this.productoService = productoService;

    }

    // Menú
    public void mostrar() {

        int opcion;

        do {

            System.out.println("\n===== PEDIDOS =====");
            System.out.println("1. Crear pedido");
            System.out.println("2. Agregar detalle");
            System.out.println("3. Listar pedidos");
            System.out.println("4. Cambiar estado");
            System.out.println("5. Cambiar forma de pago");
            System.out.println("6. Eliminar pedido");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    crearPedido();
                    break;

                case 2:
                    agregarDetalle();
                    break;

                case 3:
                    listarPedidos();
                    break;

                case 4:
                    cambiarEstado();
                    break;

                case 5:
                    cambiarFormaPago();
                    break;

                case 6:
                    eliminarPedido();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    // Crear pedido
    private void crearPedido() {

        try {

            System.out.println("\nUsuarios disponibles:");

            for (Usuario usuario :
                    usuarioService.listarUsuarios()) {

                System.out.println(usuario);
            }

            System.out.print("ID Usuario: ");
            Long usuarioId = scanner.nextLong();
            scanner.nextLine();

            Usuario usuario =
                    usuarioService.buscarPorId(usuarioId);

            pedidoService.crearPedido(usuario);

            System.out.println("Pedido creado correctamente.");

        } catch (EntidadNoEncontradaException e) {

            System.out.println(e.getMessage());

        }
    }

    // Agregar detalle
    private void agregarDetalle() {

        try {

            System.out.print("ID Pedido: ");
            Long pedidoId = scanner.nextLong();

            System.out.print("Cantidad: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine();

            System.out.println("\nProductos disponibles:");

            for (Producto producto :
                    productoService.listarProductos()) {

                System.out.println(producto);
            }

            System.out.print("ID Producto: ");
            Long productoId = scanner.nextLong();
            scanner.nextLine();

            Producto producto =
                    productoService.buscarPorId(productoId);

            pedidoService.agregarDetalle(
                    pedidoId,
                    cantidad,
                    producto.getPrecio(),
                    producto
            );

            System.out.println("Detalle agregado.");

        } catch (EntidadNoEncontradaException e) {

            System.out.println(e.getMessage());

        }
    }

    // Listar pedidos
    private void listarPedidos() {

        List<Pedido> pedidos =
                pedidoService.listarPedidos();

        if (pedidos.isEmpty()) {

            System.out.println("No hay pedidos cargados.");
            return;
        }

        for (Pedido pedido : pedidos) {

            System.out.println(pedido);
        }
    }

    // Cambiar estado
    private void cambiarEstado() {

        try {

            System.out.print("ID Pedido: ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            
            Pedido pedido =
                pedidoService.buscarPorId(id);

            System.out.println("Pedido encontrado:");
            System.out.println(pedido);

            System.out.println("\nEstados disponibles:");

            for (Estado estado : Estado.values()) {

                System.out.println("- " + estado);
            }

            System.out.print("Estado: ");

            Estado estado =
                    Estado.valueOf(
                            scanner.nextLine().toUpperCase()
                    );

            pedidoService.actualizarEstado(
                    id,
                    estado
            );

            System.out.println("Estado actualizado.");

        } catch (EntidadNoEncontradaException e) {

            System.out.println(e.getMessage());

        } catch (IllegalArgumentException e) {

            System.out.println("Estado inválido.");

        }
    }

    // Cambiar forma de pago
    private void cambiarFormaPago() {

        try {

            System.out.print("ID Pedido: ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            
            Pedido pedido =
                pedidoService.buscarPorId(id);

            System.out.println("Pedido encontrado:");
            System.out.println(pedido);

            System.out.println("\nFormas de pago:");

            for (FormaPago formaPago :
                    FormaPago.values()) {

                System.out.println("- " + formaPago);
            }

            System.out.print("Forma de pago: ");

            FormaPago formaPago =
                    FormaPago.valueOf(
                            scanner.nextLine().toUpperCase()
                    );

            pedidoService.actualizarFormaPago(
                    id,
                    formaPago
            );

            System.out.println("Forma de pago actualizada.");

        } catch (EntidadNoEncontradaException e) {

            System.out.println(e.getMessage());

        } catch (IllegalArgumentException e) {

            System.out.println("Forma de pago inválida.");

        }
    }

    // Eliminar pedido
    private void eliminarPedido() {

        try {

            System.out.print("ID Pedido: ");
            Long id = scanner.nextLong();
            scanner.nextLine();

            pedidoService.eliminarPedido(id);

            System.out.println("Pedido eliminado.");

        } catch (EntidadNoEncontradaException e) {

            System.out.println(e.getMessage());

        }
    }

}
