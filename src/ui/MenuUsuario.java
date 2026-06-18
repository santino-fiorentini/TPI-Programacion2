package ui;

import entities.Usuario;
import enums.Rol;
import exceptions.EntidadNoEncontradaException;
import exceptions.MailDuplicadoException;
import services.UsuarioService;

import java.util.List;
import java.util.Scanner;

public class MenuUsuario {

    // Scanner
    private final Scanner scanner;

    // Service
    private final UsuarioService usuarioService;

    // Constructor
    public MenuUsuario(Scanner scanner,
                       UsuarioService usuarioService) {

        this.scanner = scanner;
        this.usuarioService = usuarioService;

    }

    // Menú
    public void mostrar() {

        int opcion;

        do {

            System.out.println("\n===== USUARIOS =====");
            System.out.println("1. Crear usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("3. Editar usuario");
            System.out.println("4. Eliminar usuario");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    crearUsuario();
                    break;

                case 2:
                    listarUsuarios();
                    break;

                case 3:
                    editarUsuario();
                    break;

                case 4:
                    eliminarUsuario();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    // Crear usuario
    private void crearUsuario() {

        try {

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Apellido: ");
            String apellido = scanner.nextLine();

            System.out.print("Mail: ");
            String mail = scanner.nextLine();

            System.out.print("Celular: ");
            String celular = scanner.nextLine();

            System.out.print("Contraseña: ");
            String contrasenia = scanner.nextLine();

            System.out.println("\nRoles disponibles:");

            for (Rol rol : Rol.values()) {
                System.out.println("- " + rol);
            }

            System.out.print("Rol: ");
            Rol rol = Rol.valueOf(
                    scanner.nextLine().toUpperCase()
            );

            usuarioService.crearUsuario(
                    nombre,
                    apellido,
                    mail,
                    celular,
                    contrasenia,
                    rol
            );

            System.out.println("Usuario creado correctamente.");

        } catch (MailDuplicadoException e) {

            System.out.println(e.getMessage());

        } catch (IllegalArgumentException e) {

            System.out.println("Rol inválido.");

        }
    }

    // Listar usuarios
    private void listarUsuarios() {

        List<Usuario> usuarios =
                usuarioService.listarUsuarios();

        if (usuarios.isEmpty()) {

            System.out.println("No hay usuarios cargados.");
            return;
        }

        for (Usuario usuario : usuarios) {

            System.out.println(usuario);
        }
    }

    // Editar usuario
    private void editarUsuario() {

        try {

            System.out.print("ID del usuario: ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            
            Usuario usuario =
                usuarioService.buscarPorId(id);

            System.out.println("Usuario encontrado:");
            System.out.println(usuario);

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Apellido: ");
            String apellido = scanner.nextLine();

            System.out.print("Mail: ");
            String mail = scanner.nextLine();

            System.out.print("Celular: ");
            String celular = scanner.nextLine();

            System.out.print("Contraseña: ");
            String contrasenia = scanner.nextLine();

            System.out.println("\nRoles disponibles:");

            for (Rol rol : Rol.values()) {
                System.out.println("- " + rol);
            }

            System.out.print("Rol: ");
            Rol rol = Rol.valueOf(
                    scanner.nextLine().toUpperCase()
            );

            usuarioService.editarUsuario(
                    id,
                    nombre,
                    apellido,
                    mail,
                    celular,
                    contrasenia,
                    rol
            );

            System.out.println("Usuario editado correctamente.");

        } catch (EntidadNoEncontradaException |
                 MailDuplicadoException e) {

            System.out.println(e.getMessage());

        } catch (IllegalArgumentException e) {

            System.out.println("Rol inválido.");

        }
    }

    // Eliminar usuario
    private void eliminarUsuario() {

        try {

            System.out.print("ID del usuario: ");
            Long id = scanner.nextLong();
            scanner.nextLine();

            usuarioService.eliminarUsuario(id);

            System.out.println("Usuario eliminado correctamente.");

        } catch (EntidadNoEncontradaException e) {

            System.out.println(e.getMessage());

        }
    }

}
