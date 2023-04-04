package repository;

import model.Banco;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


/**
 * Classe responsável por criar nosso repositório, banco de dados em memória, que define os métodos de
 * salvar, buscar e remover por um ID.
 *
 * @author Victor Garcia Gallinari
 * @since 23/03/2023
 */
public class Repository <T extends Banco>{

    Map<Integer, T> bancoDeDados;

    /**
     * Construtor de repositório
     */
    public Repository() {
        this.bancoDeDados = new TreeMap<>();
    }

    /**
     * Método responsável por salvar no banco de dados.
     *
     * @param t {@code T}
     */
    public void salvar (T t) {
        this.bancoDeDados.put(t.getId(), t);
    }

    /**
     * Método responsável por listar todos os usuários.
     *
     * @return {@code List<T>}
     *      - Retorna uma lista de usuários
     */
    public List<T> buscarTodos() {
        return this.bancoDeDados.values().stream().collect(Collectors.toList());
    }

    /**
     * Método responsável por fazer uma consulta de Pessoa ou Aluno pelo ID.
     *
     * @param id {@code int}
     *      - ID para consulta.
     * @return {@code T}
     *      - Retorna Pessoa ou Aluno.
     */
    public T buscarPorId(int id) {
        return this.bancoDeDados.get(id);

    }

    /**
     * Método responsável pela remoção de uma Pessoa ou Aluno pelo ID.
     *
     * @param id {@code int}
     *      - ID para remoção.
     */
    public void removerId(int id) {

        this.bancoDeDados.remove(id);
    }

}
