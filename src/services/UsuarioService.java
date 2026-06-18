package services;

import entities.Usuario;
import enums.Rol;
import exceptions.EntidadNoEncontradaException;
import exceptions.MailDuplicadoException;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    // Colección de usuarios
    private final List<Usuario> usuarios;

    // Generador de IDs
    private Long idCounter;

    // Constructor
    public UsuarioService() {

        usuarios = new ArrayList<>();
        idCounter = 1L;

    }

    // Crear usuario
    public Usuario crearUsuario(String nombre,
                                String apellido,
                                String mail,
                                String celular,
                                String contrasenia,
                                Rol rol)
            throws MailDuplicadoException {

        if (existeMail(mail)) {

            throw new MailDuplicadoException(
                    "El mail ya está registrado."
            );
        }

        Usuario usuario = new Usuario();

        usuario.setId(idCounter++);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setMail(mail);
        usuario.setCelular(celular);
        usuario.setContrasenia(contrasenia);
        usuario.setRol(rol);

        usuarios.add(usuario);

        return usuario;
    }

    // Listar usuarios activos
    public List<Usuario> listarUsuarios() {

        List<Usuario> activos =
                new ArrayList<>();

        for (Usuario usuario : usuarios) {

            if (!usuario.isEliminado()) {
                activos.add(usuario);
            }
        }

        return activos;
    }

    // Buscar por ID
    public Usuario buscarPorId(Long id)
            throws EntidadNoEncontradaException {

        for (Usuario usuario : usuarios) {

            if (!usuario.isEliminado()
                    && usuario.getId().equals(id)) {

                return usuario;
            }
        }

        throw new EntidadNoEncontradaException(
                "Usuario no encontrado."
        );
    }

    // Editar usuario
    public void editarUsuario(Long id,
                              String nombre,
                              String apellido,
                              String mail,
                              String celular,
                              String contrasenia,
                              Rol rol)
            throws EntidadNoEncontradaException,
                   MailDuplicadoException {

        Usuario usuario =
                buscarPorId(id);

        if (!usuario.getMail()
                .equalsIgnoreCase(mail)
                && existeMail(mail)) {

            throw new MailDuplicadoException(
                    "El mail ya está registrado."
            );
        }

        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setMail(mail);
        usuario.setCelular(celular);
        usuario.setContrasenia(contrasenia);
        usuario.setRol(rol);
    }

    // Baja lógica
    public void eliminarUsuario(Long id)
            throws EntidadNoEncontradaException {

        Usuario usuario =
                buscarPorId(id);

        usuario.setEliminado(true);
    }

    // Verifica mail repetido
    public boolean existeMail(String mail) {

        for (Usuario usuario : usuarios) {

            if (!usuario.isEliminado()
                    && usuario.getMail()
                    .equalsIgnoreCase(mail)) {

                return true;
            }
        }

        return false;
    }

}
