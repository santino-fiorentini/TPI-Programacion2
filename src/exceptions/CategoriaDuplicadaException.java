package exceptions;

public class CategoriaDuplicadaException
        extends Exception {

    public CategoriaDuplicadaException(
            String mensaje) {

        super(mensaje);
    }
}
