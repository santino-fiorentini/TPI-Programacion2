import services.CategoriaService;
import services.PedidoService;
import services.ProductoService;
import services.UsuarioService;
import ui.MenuPrincipal;

public class Main {

    public static void main(String[] args) {

        CategoriaService categoriaService =
                new CategoriaService();

        ProductoService productoService =
                new ProductoService();

        UsuarioService usuarioService =
                new UsuarioService();

        PedidoService pedidoService =
                new PedidoService();

        MenuPrincipal menuPrincipal =
                new MenuPrincipal(
                        categoriaService,
                        productoService,
                        usuarioService,
                        pedidoService
                );

        menuPrincipal.iniciar();
    }

}
