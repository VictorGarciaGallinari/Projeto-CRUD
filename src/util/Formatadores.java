package util;

import javax.swing.text.MaskFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Classe responsável por definir a formatação de todas as datas, nota e números de telefones.
 *
 * @author Victor Garcia Gallinari
 * @since 23/03/2023
 */
public class Formatadores {

    /**
     * Método responsável por converter a data de uma String para um Date.
     *
     * @param data {@code String}
     *      - Data informada.
     * @return {@code Date}
     *      - Data convertida para Date.
     */
    public static Date stringParaData(String data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date paraData = null;

        try {
            paraData = simpleDateFormat.parse(data);
        } catch (ParseException exception) {
            System.out.println("Formato de data incompatível");
        }
        return paraData;
    }

    /**
     * Método responsável por formatar a data em data e hora.
     *
     * @param dataParam {@code Date}
     *      - Data para ser formatada.
     * @return {@code String}
     *      - Retorna Data formatada.
     */
    public static String formataData(Date dataParam) {
        String data = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(dataParam);
        return data;
    }

    /**
     *  Método responsável por formatar a data de nascimento.
     *
     * @param dataParam {@code Date}
     *      - Data para ser formatada.
     * @return {@code String}
     *      - Retorna Data de nascimento formatada.
     */
    public static String formataNascimento(Date dataParam) {
        String data = DateFormat.getDateInstance(DateFormat.SHORT).format(dataParam);
        return data;
    }

    /**
     * Método responsável por verificar se a nota final é numérica.
     *
     * @param nota {@code String}
     *      - Nota final informada.
     * @return {@code boolean}
     *      - Retorna true caso a nota seja numérica ou false caso seja null.
     */
    public static boolean isNumeric(String nota) {
        Pattern padrao = Pattern.compile("-?\\d+(\\.\\d+)?");

        if (nota == null) {
            return false;
        }
        return padrao.matcher(nota).matches();
    }


    /**
     * Método responsável pela formatação do modelo de telefone.
     *
     * @param telefone {@code String}
     *      - Telefone para ser formatado.
     * @return {@code String}
     *      - Telefone formatado.
     */
    public static String formataTelefone(String telefone) {
        MaskFormatter maskFormatter;

        try {
            maskFormatter = new MaskFormatter("(##) #####-####");
            maskFormatter.setValueContainsLiteralCharacters(false);
            return maskFormatter.valueToString(telefone);
        } catch (ParseException exception) {
            return telefone;
        }
    }
}