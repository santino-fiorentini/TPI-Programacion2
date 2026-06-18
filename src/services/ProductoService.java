package services;

import entities.Categoria;
import entities.Producto;
import exceptions.EntidadNoEncontradaException;
import exceptions.PrecioInvalidoException;
import exceptions.StockInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class ProductoService {

    // Colección de productos
    private final List<Producto> productos;

    // Generador de IDs
    private Long idCounter;

    // Constructor
    public ProductoService() {

        productos = new ArrayList<>();
        idCounter = 1L;

    }

    // Crear producto
    public Producto crearProducto(String nombre,
                                  String descripcion,
                                  Double precio,
                                  Integer stock,
                                  String imagen,
                                  boolean disponible,
                                  Categoria categoria)
            throws PrecioInvalidoException,
                   StockInvalidoException {

        if (precio < 0) {
            throw new PrecioInvalidoException(
                    "El precio no puede ser negativo."
            );
        }

        if (stock < 0) {
            throw new StockInvalidoException(
                    "El stock no puede ser negativo."
            );
        }

        Producto producto = new Producto();

        producto.setId(idCounter++);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setImagen(imagen);
        producto.setDisponible(disponible);
        producto.setCategoria(categoria);

        productos.add(producto);

        return producto;
    }

    // Listar productos activos
    public List<Producto> listarProductos() {

        List<Producto> activos =
                new ArrayList<>();

        for (Producto producto : productos) {

            if (!producto.isEliminado()) {
                activos.add(producto);
            }
        }

        return activos;
    }

    // Buscar por ID
    public Producto buscarPorId(Long id)
            throws EntidadNoEncontradaException {

        for (Producto producto : productos) {

            if (!producto.isEliminado()
                    && producto.getId().equals(id)) {

                return producto;
            }
        }

        throw new EntidadNoEncontradaException(
                "Producto no encontrado."
        );
    }

    // Editar producto
    public void editarProducto(Long id,
                               String nombre,
                               String descripcion,
                               Double precio,
                               Integer stock,
                               String imagen,
                               boolean disponible,
                               Categoria categoria)
            throws EntidadNoEncontradaException,
                   PrecioInvalidoException,
                   StockInvalidoException {

        if (precio < 0) {
            throw new PrecioInvalidoException(
                    "El precio no puede ser negativo."
            );
        }

        if (stock < 0) {
            throw new StockInvalidoException(
                    "El stock no puede ser negativo."
            );
        }

        Producto producto =
                buscarPorId(id);

        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setImagen(imagen);
        producto.setDisponible(disponible);
        producto.setCategoria(categoria);
    }

    // Baja lógica
    public void eliminarProducto(Long id)
            throws EntidadNoEncontradaException {

        Producto producto =
                buscarPorId(id);

        producto.setEliminado(true);
    }

}
