package service;

import cadastros.Cadastros;
import exception.SistemaException;
import model.Aluno;
import model.Pessoa;
import repository.Repository;
import util.Formatadores;
import util.Strings;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Classe responsável por definir todos os serviços da aplicação, como diferenciar Pessoa de Aluno,
 * criar cadastros de ambos, realizar a listagem de todos os cadastrados, atualizar os dados de ambos,
 * deletar uma pessoa ou aluno cadastrados e encerrar o programa (CRUD).
 *
 * @author Victor Garcia Gallinari
 * @since 28/03/2023
 */
public class Service {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Repository<Pessoa> repository = new Repository<>();
    Cadastros cadastros = new Cadastros(repository);
    Scanner scanner;

    public Service(Scanner scanner){
        this.scanner = scanner;
    }

    /**
     * Método responsável pela criação de cadastros, Pessoa ou Aluno.
     */
    public void criarCadastro() {
        System.out.println(Strings.CADASTRAMENTO);
        String nome = receberNome();
        String telefone = receberTelefone();
        Date dataNascimento = receberDataNascimento();
        Pessoa pessoa = null;
        String temNota = "";
        System.out.println(Strings.TEM_NOTA);
        while (!temNota.equals("S")) {
            temNota = scanner.nextLine().toUpperCase();
            if (temNota.equals("N")) {
                pessoa = new Pessoa(nome, telefone, dataNascimento);
                break;
            } else if (temNota.equals("S")) {
                double notaFinal = receberNotaFinal();
                pessoa = new Aluno(nome, telefone, dataNascimento, notaFinal);
            }
        }
        repository.salvar(pessoa);
        System.out.println(Strings.CADASTRADO.toString() + pessoa);
    }

    /**
     * Método responsável por receber o nome digitado pelo usuário.
     *
     * @return {@code String}
     *      - Retorna o nome digitado.
     */
    private String receberNome() {
        System.out.println(Strings.NOME);
        String nomeDigitado = scanner.nextLine();
        return nomeDigitado;
    }

    /**
     * Método responsável por receber o telefone digitado pelo usuário em um formato padrão.
     *
     * @return {@code String}
     *      - Retorna o telefone inserido.
     */
    private String receberTelefone() {
        String telefone = "";
        while (!telefone.matches("[0-9]+")|| telefone.length() > 11 || telefone.length() < 11) {
            System.out.println(Strings.TELEFONE);
            telefone = scanner.nextLine().replaceAll("^0", "");
        }
        return telefone;
    }

    /**
     * Método responsável por receber a data de nascimento digitada pelo usuário em um formato padrão.
     *
     * @return {@code Date}
     *      - Retorna data de nascimento.
     */
    private Date receberDataNascimento() {
        String data = "";
        Date dataNascimento = null;
        boolean continua = true;
        while (continua == true) {
            try {
                dataNascimento = simpleDateFormat.parse(data);
                continua = false;
            } catch (Exception exception) {
                System.out.println(Strings.DATA_NASCIMENTO);
                data = scanner.next();
            }
        }
        return dataNascimento;
    }

    /**
     * Método responsável por receber a nota digitada referente aos Alunos.
     *
     * @return {@code Double}
     *      - Retorna a nota digitada caso o cadastro seja de Aluno.
     */
    private Double receberNotaFinal() {
        String nota = "";
        double notaDouble = -1;
        while (notaDouble == -1) {
            if (Formatadores.isNumeric(nota) && (Double.parseDouble(nota)) >= 0 && (Double.parseDouble(nota)) <= 10) {
                notaDouble = Double.parseDouble(nota);
            } else {
                System.out.println(Strings.NOTA);
                nota = scanner.nextLine().replace(",", ".");
            }
        }
        return notaDouble;
    }

    /**
     * Método responsável pela listagem dos cadastros realizados,
     * separando por listar todos, listar somente pessoas e listar somente alunos,
     * também realizando buscas por ID ou nome.
     *
     * @throws SistemaException
     *      - Exceção criada para os tratamentos, caso ocorram.
     */
    public void listarCadastro() throws SistemaException {

        List<Pessoa> pessoas = ordenarStream(repository.buscarTodos())
                .collect(Collectors.toList());

        System.out.println(Strings.MENU_CADASTROS);
        int opcao = scanner.nextInt();

        if (opcao < 0 || opcao > 3) {
            throw new SistemaException(Strings.OPCAO_INVALIDA.toString());
        } else if (opcao == 0) {
            throw new SistemaException(Strings.RETORNANDO_MENU.toString());
        }
        if (opcao != 0) {
            System.out.println(Strings.LISTAGEM);
        }
        if (opcao == 1) {
            pessoas.stream().forEach(pessoa -> System.out.println(pessoa));
        } else if (opcao == 2) {
            pessoas.stream().filter(pessoa -> pessoa instanceof Aluno)
                    .forEach(aluno -> System.out.println(aluno));
        } else if (opcao == 3) {
            pessoas.stream().filter(pessoa -> !(pessoa instanceof Aluno))
                    .forEach(pessoa -> System.out.println(pessoa));
        }
        atualizarDados(receberID());
    }

    /**
     *  Método responsável pela ordenação da lista de cadastros, por nome em ordem alfabética.
     *
     * @param listaPessoa {@code List<Pessoa>}
     *      - Lista a ser ordenada como parâmetro.
     * @return{@code Stream}
     *      - Retorna nossa ordem alfabética já implantada.
     */
    private Stream<Pessoa> ordenarStream(List<Pessoa> listaPessoa) {

        Stream<Pessoa> pessoasOrdenadas = repository.buscarTodos()
                    .stream().sorted(Comparator.comparing(Pessoa::getNome,
                    Comparator.comparing((String s)-> !s.equals("None"))
                    .thenComparing(Comparator.naturalOrder())));

        return pessoasOrdenadas;
    }

    /**
     *
     * Método responsável por receber o ID para busca de cadastro.
     *
     * @return{@code Integer}
     *      - Retorna o ID solicitado com o cadastro completo detalhado.
     * @throws SistemaException
     *      - Exceção criada para os tratamentos, caso ocorram.
     */
    public Integer receberID() throws SistemaException {

        System.out.println(Strings.INFORME_ID);

        int informarID = scanner.nextInt();

        if (informarID == 0) {
            throw new SistemaException(Strings.RETORNANDO_MENU.toString());
        }
        return informarID;
    }

    /**
     *  Método responsável por buscar dentro da memória o nome solicitado pelo usuário.
     *
     * @throws SistemaException
     *          - Exceção criada para os tratamentos, caso ocorram.
     */
    public void buscarPorNome () throws SistemaException {
        System.out.println(Strings.INFORME_NOME);
        String fragmentoNome = scanner.next().toLowerCase();

        List<Pessoa> pessoas = ordenarStream(repository.buscarTodos())
                .filter(pessoa -> pessoa.getNome().toLowerCase().contains(fragmentoNome))
                .collect(Collectors.toList());
        if (pessoas.size() > 1) {
            pessoas.forEach(pessoa -> System.out.println(pessoa));
            atualizarDados(receberID());
        } else if (pessoas.size() == 1) {
            atualizarDados(pessoas.get(0).getId());
        } else if (pessoas.isEmpty()) {
            throw new SistemaException(Strings.SEM_OCORRENCIA + fragmentoNome + Strings.SEM_OCORRENCIA_2);
        }
    }

    /**
     * Método responsável por realizar alterações nos dados dos cadastrados, como nome, telefone, etc.
     * Gerando assim uma nova data de alteração no cadastro.
     *
     * @param id {@code Integer}
     *      - ID a ser buscado.
     * @throws SistemaException
     *      - Exceção criada para os tratamentos, caso ocorram.
     */
    public void atualizarDados(Integer id) throws SistemaException {

        Pessoa pessoa = verificarIdRepository(id);

        int opcaoMenu;
        boolean continua = true;
        boolean virouAluno = false;

        while (continua) {
            System.out.println(Strings.CABECALHO_ATUALIZAR.toString() + pessoa + Strings.OPCOES_ATUALIZAR);
            opcaoMenu = scanner.nextInt();
            scanner.nextLine();

            if (opcaoMenu == 1 ) {
                pessoa.setNome(receberNome());
            } else if (opcaoMenu == 2) {
                pessoa.setTelefone(receberTelefone());
            } else if (opcaoMenu == 3) {
                pessoa.setDataNascimento(receberDataNascimento());
            } else if (opcaoMenu == 4) {
                if (pessoa instanceof Aluno) {
                    Aluno aluno = (Aluno) pessoa;
                    aluno.setNotaFinal(receberNotaFinal());
                } else {
                    Aluno aluno = pessoaParaAluno(pessoa, receberNotaFinal());
                    virouAluno = true;
                    pessoa = aluno;
                }
            } else if(opcaoMenu == 5) {
                deletarPessoa(pessoa.getId());
            } else if (opcaoMenu == 0) {
                throw new SistemaException(Strings.RETORNANDO_MENU.toString());
            } else {
                System.out.println(Strings.OPCAO_DESEJADA);
            }

            if (virouAluno == true) {
                System.out.println(pessoa.getNome() + Strings.VIROU_ALUNO);
                virouAluno = false;
            } else {
                System.out.println(Strings.ATUALIZADO);
            }

            pessoa.setDataAlteracao(new Date());
        }
    }

    /**
     * Método responsável pela verificação e consulta de ID no repository.
     *
     * @param id {@code Integer}
     *      - Número ID para consulta
     * @return{@code Pessoa}
     *      - Retorna o cadastro pessoa com o ID solicitado.
     * @throws SistemaException
     *      - Exceção criada para os tratamentos, caso ocorram.
     */
    private Pessoa verificarIdRepository(Integer id) throws SistemaException {

        Pessoa pessoa = repository.buscarPorId(id);

        if(pessoa == null) {
            throw new SistemaException(Strings.NAO_ENCONTRADO.toString());
        }
        return pessoa;
    }

    /**
     * Método responsável converter um cadastro de pessoa para aluno,
     * salvando o novo aluno no repository e deletando a pessoa da base.
     *
     * @param pessoa {@code Pessoa}
     *      - Pessoa a ser alterada.
     * @param notaFinal {@code Double}
     *      - Nova nota a ser inserida para o novo aluno criado.
     * @return {@code Aluno}
     *      - Retorna um novo Aluno cadastrado.
     */
    private Aluno pessoaParaAluno(Pessoa pessoa, Double notaFinal) {
        Pessoa aluno = new Aluno(pessoa.getNome(), pessoa.getTelefone(), pessoa.getDataNascimento(), notaFinal);
        repository.salvar(aluno);
        aluno.setDataCadastro(pessoa.getDataCadastro());
        repository.removerId(pessoa.getId());
        System.out.println(Strings.NOVO_ALUNO);

        return (Aluno) aluno;
    }

    /**
     * Método responsável por deletar um cadastro do repository.
     *
     * @param id {@code Integer}
     *      - ID informado para fazer a deleção.
     * @throws SistemaException
     *      - Exceção criada para os tratamentos, caso ocorram.
     */
    private void deletarPessoa(Integer id) throws SistemaException {
        Pessoa pessoa = this.verificarIdRepository(id);
        System.out.println(Strings.REMOVER_CADASTRO + pessoa.getNome() + Strings.REMOVER_CADASTRO_2);
        String remover = scanner.next().toUpperCase();

        if (remover.equals("N")) {
            System.out.println(Strings.RETORNANDO_MENU);
        } else if (remover.equals("S")) {
            repository.removerId(id);
            System.out.println(Strings.REMOVIDO);
        } else {
            throw new SistemaException(Strings.OPCAO_INVALIDA_RETORNANDO.toString());
        }
    }
}