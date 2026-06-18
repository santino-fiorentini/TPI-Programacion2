package ui;

import services.CategoriaService;
import services.PedidoService;
import services.ProductoService;
import services.UsuarioService;

import java.util.Scanner;

public class MenuPrincipal {

    // Scanner
    private final Scanner scanner;

    // Services
    private final CategoriaService categoriaService;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;

    // Menús
    private final MenuCategoria menuCategoria;
    private final MenuProducto menuProducto;
    private final MenuUsuario menuUsuario;
    private final MenuPedido menuPedido;

    // Constructor
    public MenuPrincipal(CategoriaService categoriaService,
                         ProductoService productoService,
                         UsuarioService usuarioService,
                         PedidoService pedidoService) {

        this.scanner = new Scanner(System.in);

        this.categoriaService = categoriaService;
        this.productoService = productoService;
        this.usuarioService = usuarioService;
        this.pedidoService = pedidoService;

        this.menuCategoria =
                new MenuCategoria(scanner, categoriaService);

        this.menuProducto =
                new MenuProducto(
                        scanner,
                        productoService,
                        categoriaService
                );

        this.menuUsuario =
                new MenuUsuario(
                        scanner,
                        usuarioService
                );

        this.menuPedido =
                new MenuPedido(
                        scanner,
                        pedidoService,
                        usuarioService,
                        productoService
                );
    }

    // Menú Principal
    public void iniciar() {

        int opcion;

        do {

            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Categorías");
            System.out.println("2. Productos");
            System.out.println("3. Usuarios");
            System.out.println("4. Pedidos");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    menuCategoria.mostrar();
                    break;

                case 2:
                    menuProducto.mostrar();
                    break;

                case 3:
                    menuUsuario.mostrar();
                    break;

                case 4:
                    menuPedido.mostrar();
                    break;

                case 0:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

    }

}
