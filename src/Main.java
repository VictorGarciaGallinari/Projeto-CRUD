import exception.SistemaException;
import service.Service;
import util.Strings;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe runner do serviço de cadastro.
 *
 * @author Victor Garcia Gallinari
 * @since 31/03/2023
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        Service service = new Service(scanner);
        boolean continua = true;

        do {
            try {
                System.out.println(Strings.MENU_PRINCIPAL);
                int opcaoMenu = scanner.nextInt();
                scanner.nextLine();
                switch (opcaoMenu) {
                    case 1:
                        service.criarCadastro();
                        break;
                    case 2:
                        service.listarCadastro();
                        break;
                    case 3:
                        service.buscarPorNome();
                        break;
                    case 4:
                        service.atualizarDados(service.receberID());
                        break;
                    case 0 :
                        System.out.println(Strings.ENCERRAMENTO);
                        continua = false;
                        break;
                    default:
                        System.out.println(Strings.OPCAO_INVALIDA);
                        break;

                }
            } catch (SistemaException exception) {
                System.out.println(exception.getMessage());
            } catch (InputMismatchException exception) {
                System.out.println(Strings.OPCAO_INVALIDA);
                scanner.next();
            } finally {
                Thread.sleep(1000);
            }

        } while (continua);
    }
}