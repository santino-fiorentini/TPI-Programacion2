package exceptions;

public class MailDuplicadoException
        extends Exception {

    public MailDuplicadoException(
            String mensaje) {

        super(mensaje);
    }
}
