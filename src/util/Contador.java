package util;

/**
 * Classe responsável por definir o Contador de ID's.
 *
 * @author Victor Garcia Gallinari
 * @since 23/03/2023
 */
public class Contador {

    private static Integer contador = 0;

    /**
     * Método responsável por avançar os ID's.
     * @return {@code Integer}
     */
    public static Integer proximoId(){
        contador++;
        return contador;
    }
}
