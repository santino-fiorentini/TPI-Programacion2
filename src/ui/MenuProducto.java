package ui;

import entities.Categoria;
import entities.Producto;
import exceptions.EntidadNoEncontradaException;
import exceptions.PrecioInvalidoException;
import exceptions.StockInvalidoException;
import services.CategoriaService;
import services.ProductoService;

import java.util.List;
import java.util.Scanner;

public class MenuProducto {

    // Scanner
    private final Scanner scanner;

    // Services
    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    // Constructor
    public MenuProducto(Scanner scanner,
                        ProductoService productoService,
                        CategoriaService categoriaService) {

        this.scanner = scanner;
        this.productoService = productoService;
        this.categoriaService = categoriaService;

    }

    // Menú
    public void mostrar() {

        int opcion;

        do {

            System.out.println("\n===== PRODUCTOS =====");
            System.out.println("1. Crear producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Editar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    crearProducto();
                    break;

                case 2:
                    listarProductos();
                    break;

                case 3:
                    editarProducto();
                    break;

                case 4:
                    eliminarProducto();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    // Crear producto
    private void crearProducto() {

        try {

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Descripción: ");
            String descripcion = scanner.nextLine();

            System.out.print("Precio: ");
            Double precio = scanner.nextDouble();

            System.out.print("Stock: ");
            int stock = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Imagen: ");
            String imagen = scanner.nextLine();

            System.out.print("Disponible (true/false): ");
            boolean disponible = scanner.nextBoolean();
            scanner.nextLine();

            System.out.println("\nCategorías disponibles:");

            for (Categoria categoria :
                    categoriaService.listarCategorias()) {

                System.out.println(categoria);
            }

            System.out.print("ID Categoría: ");
            Long categoriaId = scanner.nextLong();
            scanner.nextLine();

            Categoria categoria =
                    categoriaService.buscarPorId(categoriaId);

            productoService.crearProducto(
                    nombre,
                    descripcion,
                    precio,
                    stock,
                    imagen,
                    disponible,
                    categoria
            );

            System.out.println("Producto creado correctamente.");

        } catch (EntidadNoEncontradaException |
                 PrecioInvalidoException |
                 StockInvalidoException e) {

            System.out.println(e.getMessage());

        }
    }

    // Listar productos
    private void listarProductos() {

        List<Producto> productos =
                productoService.listarProductos();

        if (productos.isEmpty()) {

            System.out.println("No hay productos cargados.");
            return;
        }

        for (Producto producto : productos) {

            System.out.println(producto);
        }
    }

    // Editar producto
    private void editarProducto() {

        try {

            System.out.print("ID del producto: ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            
            Producto producto =
                productoService.buscarPorId(id);

            System.out.println("Producto encontrado:");
            System.out.println(producto);

            System.out.print("Nuevo nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Nueva descripción: ");
            String descripcion = scanner.nextLine();

            System.out.print("Nuevo precio: ");
            Double precio = scanner.nextDouble();

            System.out.print("Nuevo stock: ");
            int stock = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nueva imagen: ");
            String imagen = scanner.nextLine();

            System.out.print("Disponible (true/false): ");
            boolean disponible = scanner.nextBoolean();
            scanner.nextLine();

            System.out.println("\nCategorías disponibles:");

            for (Categoria categoria :
                    categoriaService.listarCategorias()) {

                System.out.println(categoria);
            }

            System.out.print("ID Categoría: ");
            Long categoriaId = scanner.nextLong();
            scanner.nextLine();

            Categoria categoria =
                    categoriaService.buscarPorId(categoriaId);

            productoService.editarProducto(
                    id,
                    nombre,
                    descripcion,
                    precio,
                    stock,
                    imagen,
                    disponible,
                    categoria
            );

            System.out.println("Producto editado correctamente.");

        } catch (EntidadNoEncontradaException |
                 PrecioInvalidoException |
                 StockInvalidoException e) {

            System.out.println(e.getMessage());

        }
    }

    // Eliminar producto
    private void eliminarProducto() {

        try {

            System.out.print("ID del producto: ");
            Long id = scanner.nextLong();
            scanner.nextLine();

            productoService.eliminarProducto(id);

            System.out.println("Producto eliminado correctamente.");

        } catch (EntidadNoEncontradaException e) {

            System.out.println(e.getMessage());

        }
    }

}
