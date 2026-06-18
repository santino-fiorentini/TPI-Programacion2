package ui;

import entities.Categoria;
import exceptions.EntidadNoEncontradaException;
import services.CategoriaService;

import java.util.List;
import java.util.Scanner;

public class MenuCategoria {

    // Scanner
    private final Scanner scanner;

    // Service
    private final CategoriaService categoriaService;

    // Constructor
    public MenuCategoria(Scanner scanner,
                         CategoriaService categoriaService) {

        this.scanner = scanner;
        this.categoriaService = categoriaService;
    }

    // Menú categorías
    public void mostrar() {

        int opcion;

        do {

            System.out.println("\n===== CATEGORÍAS =====");
            System.out.println("1. Crear categoría");
            System.out.println("2. Listar categorías");
            System.out.println("3. Editar categoría");
            System.out.println("4. Eliminar categoría");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    crearCategoria();
                    break;

                case 2:
                    listarCategorias();
                    break;

                case 3:
                    editarCategoria();
                    break;

                case 4:
                    eliminarCategoria();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    // Crear categoría
    private void crearCategoria() {

        try {

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Descripción: ");
            String descripcion = scanner.nextLine();

            categoriaService.crearCategoria(
                    nombre,
                    descripcion
            );

            System.out.println("Categoría creada correctamente.");

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());

        }
    }

    // Listar categorías
    private void listarCategorias() {

        List<Categoria> categorias =
                categoriaService.listarCategorias();

        if (categorias.isEmpty()) {

            System.out.println("No hay categorías cargadas.");
            return;
        }

        for (Categoria categoria : categorias) {

            System.out.println(categoria);
        }
    }

    // Editar categoría
    private void editarCategoria() {

        try {

            System.out.print("ID de la categoría: ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            
            Categoria categoria =
                categoriaService.buscarPorId(id);
            
            System.out.println("Categoría encontrada:");
            System.out.println(categoria);

            System.out.print("Nuevo nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Nueva descripción: ");
            String descripcion = scanner.nextLine();

            categoriaService.editarCategoria(
                    id,
                    nombre,
                    descripcion
            );

            System.out.println("Categoría editada correctamente.");

        } catch (EntidadNoEncontradaException e) {

            System.out.println(e.getMessage());

        }
    }

    // Eliminar categoría
    private void eliminarCategoria() {

        try {

            System.out.print("ID de la categoría: ");
            Long id = scanner.nextLong();
            scanner.nextLine();

            categoriaService.eliminarCategoria(id);

            System.out.println("Categoría eliminada correctamente.");

        } catch (EntidadNoEncontradaException e) {

            System.out.println(e.getMessage());

        }
    }

}
