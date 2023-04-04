package exception;

/**
 * Classe responsável pela criação da exceção, caso ocorra.
 *
 * @author Victor Garcia Gallinari
 * @since 23/03/2023
 */
public class SistemaException extends Exception {

    private static final long serialVersionUID = 1L;

    public SistemaException(String mensagem) {
        super(mensagem);
    }
}
