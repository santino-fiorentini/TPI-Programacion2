package services;

import entities.Categoria;
import exceptions.EntidadNoEncontradaException;

import java.util.ArrayList;
import java.util.List;

public class CategoriaService {

    // Colección de categorías
    private final List<Categoria> categorias;

    // Generador de IDs
    private Long idCounter;

    // Constructor
    public CategoriaService() {

        categorias = new ArrayList<>();
        idCounter = 1L;

    }

    // Crear categoría
    public Categoria crearCategoria(String nombre,
                                    String descripcion) {

        for (Categoria categoria : categorias) {

            if (!categoria.isEliminado()
                    && categoria.getNombre()
                    .equalsIgnoreCase(nombre)) {

                throw new IllegalArgumentException(
                        "Ya existe una categoría con ese nombre."
                );
            }
        }

        Categoria categoria = new Categoria();

        categoria.setId(idCounter++);
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);

        categorias.add(categoria);

        return categoria;
    }

    // Listar categorías activas
    public List<Categoria> listarCategorias() {

        List<Categoria> activas =
                new ArrayList<>();

        for (Categoria categoria : categorias) {

            if (!categoria.isEliminado()) {
                activas.add(categoria);
            }
        }

        return activas;
    }

    // Buscar por ID
    public Categoria buscarPorId(Long id)
            throws EntidadNoEncontradaException {

        for (Categoria categoria : categorias) {

            if (!categoria.isEliminado()
                    && categoria.getId().equals(id)) {

                return categoria;
            }
        }

        throw new EntidadNoEncontradaException(
                "Categoría no encontrada."
        );
    }

    // Editar categoría
    public void editarCategoria(Long id,
                                String nombre,
                                String descripcion)
            throws EntidadNoEncontradaException {

        Categoria categoria =
                buscarPorId(id);

        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
    }

    // Baja lógica
    public void eliminarCategoria(Long id)
            throws EntidadNoEncontradaException {

        Categoria categoria =
                buscarPorId(id);

        categoria.setEliminado(true);
    }

}
